package de.bhtberlin.paf2023.productdatatranslation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.bhtberlin.paf2023.productdatatranslation.entity.Language;
import de.bhtberlin.paf2023.productdatatranslation.translation.CompositeTranslatable;
import de.bhtberlin.paf2023.productdatatranslation.translation.Translatable;
import de.bhtberlin.paf2023.productdatatranslation.translation.TranslationVisitor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDto implements CompositeTranslatable {

    private int id;

    private String serial;

    private String name;

    private double height;

    private double width;

    private double depth;

    private double weight;

    private double price;

    @JsonIgnoreProperties(value = {"products"}, allowSetters = true)
    private Set<CategoryDto> categories;

    @JsonIgnoreProperties(value = {"products"}, allowSetters = true)
    private Set<ColorDto> colors;

    @JsonIgnoreProperties(value = {"product"}, allowSetters = true)
    private Set<PictureDto> pictures;

    @JsonIgnoreProperties(value = {"product", "revisions"}, allowSetters = true)
    private Set<TranslationDto> translations;

    public ProductDto(int id) {
        this.id = id;
    }

    @Override
    public void addTranslatable(@NotNull Translatable translatable) {
        if (translatable instanceof CategoryDto categoryDto && this.categories != null) {
            this.categories.add(categoryDto);
        } else if (translatable instanceof ColorDto colorDto && this.colors != null) {
            this.colors.add(colorDto);
        }
    }

    @Override
    public void removeTranslatable(@NotNull Translatable translatable) {
        if (translatable instanceof CategoryDto categoryDto && this.categories != null) {
            this.categories.remove(categoryDto);
        } else if (translatable instanceof ColorDto colorDto && this.colors != null) {
            this.colors.remove(colorDto);
        }
    }

    @JsonIgnore
    @Override
    public List<Translatable> getTranslatables() {
        return Stream.concat(
                        Optional.ofNullable(this.categories).orElse(new HashSet<>()).stream(),
                        Optional.ofNullable(this.colors).orElse(new HashSet<>()).stream()).
                toList();
    }

    /**
     * Translate the {@link ProductDto} and all containing {@link Translatable Translatables}.
     * <br>
     * The ProductDto implements {@link Translatable#translate(TranslationVisitor, Language, Language)}
     * and doesn't use the default implementation in order to take care of its
     * composite nature and the containing {@link Translatable Translatables}.
     *
     * @param visitor The visiting {@link TranslationVisitor}.
     * @param from    The {@link Language} of the current locale.
     * @param to      The {@link Language} of the target locale.
     * @return The translated {@link ProductDto}.
     */
    @Override
    public @NotNull Translatable translate(@NotNull TranslationVisitor visitor, @NotNull Language from, @NotNull Language to) {
        visitor.visit(this, from, to);
        // could not use getTranslatables() and instead loop over getCategories()
        // and getColors() separately but getTranslatables() already takes care
        // of empty sets.
        this.getTranslatables().forEach(translatable -> {
            if (translatable instanceof CategoryDto categoryDto) {
                visitor.visit(categoryDto, from, to);
            } else if (translatable instanceof ColorDto colorDto) {
                visitor.visit(colorDto, from, to);
            }
        });
        return this;
    }
}
