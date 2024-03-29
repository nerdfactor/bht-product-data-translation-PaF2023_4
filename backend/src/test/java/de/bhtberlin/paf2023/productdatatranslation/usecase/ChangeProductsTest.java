package de.bhtberlin.paf2023.productdatatranslation.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bhtberlin.paf2023.productdatatranslation.dto.ColorDto;
import de.bhtberlin.paf2023.productdatatranslation.dto.PictureDto;
import de.bhtberlin.paf2023.productdatatranslation.dto.ProductDto;
import de.bhtberlin.paf2023.productdatatranslation.dto.TranslationDto;
import de.bhtberlin.paf2023.productdatatranslation.entity.Product;
import de.bhtberlin.paf2023.productdatatranslation.entity.Translation;
import de.bhtberlin.paf2023.productdatatranslation.service.CategoryService;
import de.bhtberlin.paf2023.productdatatranslation.service.ColorService;
import de.bhtberlin.paf2023.productdatatranslation.service.ProductService;
import de.bhtberlin.paf2023.productdatatranslation.service.TranslationService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class ChangeProductsTest {

    private static final String API_PATH_PRODUCTS = "/api/products";

    private static final String API_PATH_TRANSLATIONS = "/api/translations";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductService productService;

    @Autowired
    TranslationService translationService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;

    @Autowired
    EntityManager entityManager;

    /**
     * Should change an existing product.
     */
    @Test
    void shouldChangeProductInSystem() throws Exception {
        // load a product
        Product product = this.productService.readProduct(2).orElseThrow();
        String originalName = product.getName();
        double originalPrice = product.getPrice();
        int originalAmountOfCategories = product.getCategories().size();
        int originalAmountOfColors = product.getColors().size();
        int originalAmountOfTranslations = product.getTranslations().size();

        // map it to a dto and change some values.
        ProductDto dto = this.modelMapper.map(product, ProductDto.class);
        dto.setName(UUID.randomUUID().toString());

        // just change the base values, so throw away all the relationships
        dto.setTranslations(null);
        dto.setColors(null);
        dto.setCategories(null);

        // update the product.
        String response = mockMvc.perform(patch(API_PATH_PRODUCTS + "/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.jsonMapper.writeValueAsString(dto))
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ProductDto responseDto = this.jsonMapper.readValue(response, ProductDto.class);
        Assertions.assertNotEquals(originalName, responseDto.getName());
        Assertions.assertEquals(dto.getName(), responseDto.getName());

        // reload data from database and make sure, nothing additional was changed
        product = this.productService.readProduct(2).orElseThrow();
        Assertions.assertEquals(originalPrice, product.getPrice());
        Assertions.assertEquals(originalAmountOfCategories, product.getCategories().size());
        Assertions.assertEquals(originalAmountOfColors, product.getColors().size());
        Assertions.assertEquals(originalAmountOfTranslations, product.getTranslations().size());
    }

    /**
     * Should change an existing product and its colors.
     */
    @Test
    void shouldChangeProductAndColorsInSystem() throws Exception {
        // load a product
        Product product = this.productService.readProduct(2).orElseThrow();
        String originalName = product.getName();
        int originalAmountOfColors = product.getColors().size();

        // map it to a dto and change some values.
        ProductDto dto = this.modelMapper.map(product, ProductDto.class);
        dto.setName(UUID.randomUUID().toString());
        dto.setColors(Set.of(new ColorDto(1)));

        // throw away all other the relationships
        dto.setTranslations(null);
        dto.setCategories(null);

        // update the product.
        String response = mockMvc.perform(patch(API_PATH_PRODUCTS + "/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.jsonMapper.writeValueAsString(dto))
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ProductDto responseDto = this.jsonMapper.readValue(response, ProductDto.class);

        Assertions.assertNotEquals(originalName, responseDto.getName());
        Assertions.assertEquals(dto.getName(), responseDto.getName());
        Assertions.assertEquals(1, responseDto.getColors().size());
        Assertions.assertEquals(1, responseDto.getColors().stream().findFirst().orElseThrow().getId());

        // reload data from database and make sure, changes where actually made.
        product = this.productService.readProduct(2).orElseThrow();
        Assertions.assertNotEquals(originalAmountOfColors, product.getColors().size());
        Assertions.assertEquals(1, product.getColors().stream().findFirst().orElseThrow().getId());
    }

    /**
     * Should change the translation of an existing product.
     */
    @Test
    void shouldChangeProductsTranslationInSystem() throws Exception {
        // load a product and its translation.
        Product product = this.productService.readProduct(3).orElseThrow();
        Translation translation = product.getTranslations().stream().findFirst().orElseThrow();
        String originalShortDescription = translation.getShortDescription();
        String originalLongDescription = translation.getLongDescription();

        // map it to a dto and change the description.
        TranslationDto dto = this.modelMapper.map(translation, TranslationDto.class);
        dto.setShortDescription(UUID.randomUUID().toString());
        dto.setLongDescription(UUID.randomUUID().toString() + UUID.randomUUID() + UUID.randomUUID());


        // update the translation.
        mockMvc.perform(patch(API_PATH_TRANSLATIONS + "/" + dto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.jsonMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());

        // reload data from database and make sure, changes where actually made.
        product = this.productService.readProduct(3).orElseThrow();
        translation = product.getTranslations().stream().findFirst().orElseThrow();
        Assertions.assertEquals(dto.getShortDescription(), translation.getShortDescription());
        Assertions.assertNotEquals(originalShortDescription, translation.getShortDescription());
        Assertions.assertNotEquals(originalLongDescription, translation.getLongDescription());
        Assertions.assertEquals(1, product.getTranslations().size());
    }

    /**
     * Should change one of multiple translations of an existing product.
     */
    @Test
    void shouldChangeSecondaryTranslationOfProductInSystem() throws Exception {
        // load a product and its translation.
        Product product = this.productService.readProduct(4).orElseThrow();
        Translation translation = product.getTranslations().stream().filter(t -> t.getLanguage().getIsoCode().equalsIgnoreCase("en")).toList().get(0);
        int originalAmountOfTranslations = product.getTranslations().size();
        String originalShortDescription = translation.getShortDescription();
        String originalLongDescription = translation.getLongDescription();

        // map it to a dto and change the description.
        TranslationDto dto = this.modelMapper.map(translation, TranslationDto.class);
        dto.setShortDescription(UUID.randomUUID().toString());
        dto.setLongDescription(UUID.randomUUID().toString() + UUID.randomUUID() + UUID.randomUUID());


        // update the translation.
        mockMvc.perform(patch(API_PATH_TRANSLATIONS + "/" + dto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.jsonMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());

        // reload data from database and make sure, changes where actually made.
        product = this.productService.readProduct(4).orElseThrow();
        translation = product.getTranslations().stream().filter(t -> t.getLanguage().getIsoCode().equalsIgnoreCase("en")).toList().get(0);
        Assertions.assertEquals(dto.getShortDescription(), translation.getShortDescription());
        Assertions.assertEquals(dto.getLongDescription(), translation.getLongDescription());
        Assertions.assertNotEquals(originalShortDescription, translation.getShortDescription());
        Assertions.assertNotEquals(originalLongDescription, translation.getLongDescription());
        Assertions.assertEquals(originalAmountOfTranslations, product.getTranslations().size());
    }

    /**
     * Should change one of multiple translations of an existing product.
     */
    @Test
    void shouldChangeDefaultTranslationOfProductInSystem() throws Exception {
        // load a product and its translation.
        Product product = this.productService.readProduct(4).orElseThrow();
        Translation translation = product.getTranslations().stream().filter(t -> t.getLanguage().getIsoCode().equalsIgnoreCase("de")).toList().get(0);
        Translation secondaryTranslation = product.getTranslations().stream().filter(t -> t.getLanguage().getIsoCode().equalsIgnoreCase("en")).toList().get(0);
        int originalAmountOfTranslations = product.getTranslations().size();
        String originalShortDescription = translation.getShortDescription();
        String originalLongDescription = translation.getLongDescription();
        String originalSecondaryShortDescription = secondaryTranslation.getShortDescription();
        String originalSecondaryLongDescription = secondaryTranslation.getLongDescription();

        // map it to a dto and change the description.
        TranslationDto dto = this.modelMapper.map(translation, TranslationDto.class);
        dto.setShortDescription("Eine neue Kurzbeschreibung.");
        dto.setLongDescription("Eine neue lange Beschreibung die anders sein sollte als die kurze Beschreibung aber inhatlich ähnlich ist.");


        // update the translation.
        String response = mockMvc.perform(patch(API_PATH_TRANSLATIONS + "/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.jsonMapper.writeValueAsString(dto))
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // reload data from database and make sure, changes where actually made.
        product = this.productService.readProduct(4).orElseThrow();
        translation = product.getTranslations().stream().filter(t -> t.getLanguage().getIsoCode().equalsIgnoreCase("de")).toList().get(0);
        Assertions.assertEquals(dto.getShortDescription(), translation.getShortDescription());
        Assertions.assertEquals(dto.getLongDescription(), translation.getLongDescription());
        Assertions.assertNotEquals(originalShortDescription, translation.getShortDescription());
        Assertions.assertNotEquals(originalLongDescription, translation.getLongDescription());
        Assertions.assertEquals(originalAmountOfTranslations, product.getTranslations().size());
        secondaryTranslation = product.getTranslations().stream().filter(t -> t.getLanguage().getIsoCode().equalsIgnoreCase("en")).toList().get(0);
        Assertions.assertNotEquals(originalSecondaryShortDescription, secondaryTranslation.getShortDescription());
        Assertions.assertNotEquals(originalSecondaryLongDescription, secondaryTranslation.getLongDescription());
        Assertions.assertEquals(originalAmountOfTranslations, product.getTranslations().size());
    }

    /**
     * Should change the product with all the transferred relationships.
     */
    @Test
    void shouldChangeProductWithAllRelations() throws Exception {
        Product product = this.productService.readProduct(6).orElseThrow();
        ProductDto dto = this.modelMapper.map(product, ProductDto.class);
        // change the name of a category to make sure, it is changed.
        String originalCategoryName = dto.getCategories().stream().findFirst().orElseThrow().getName();
        dto.getCategories().stream().findFirst().orElseThrow().setName(UUID.randomUUID().toString());
        // change the relations to colors to make sure, they are updated.
        dto.setColors(Set.of(new ColorDto(1), new ColorDto(2)));
        // change the amount of translations to make sure, the old ones are removed.
        dto.setTranslations(Set.of(dto.getTranslations().stream().findFirst().orElseThrow()));
        // add non-existing picture to make sure, it is added.
        PictureDto pictureDto = new PictureDto();
        pictureDto.setId(1);
        dto.setPictures(Set.of(pictureDto));
        mockMvc.perform(put(API_PATH_PRODUCTS + "/" + dto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.jsonMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());
        product = this.productService.readProduct(6).orElseThrow();
        Assertions.assertNotEquals(originalCategoryName, product.getCategories().stream().findFirst().orElseThrow().getName());
        Assertions.assertEquals(dto.getTranslations().size(), product.getTranslations().size());
        Assertions.assertEquals(1, product.getPictures().size());
    }
}
