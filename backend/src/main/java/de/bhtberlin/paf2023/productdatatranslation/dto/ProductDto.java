package de.bhtberlin.paf2023.productdatatranslation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDto {

    private int id;

    private String serial;

    private String name;

    private double height;

    private double width;

    private double depth;

    private double weight;

    private double price;

    @JsonIgnoreProperties({"products"})
    private Set<CategoryDto> categories;

    @JsonIgnoreProperties({"products"})
    private Set<ColorDto> colors;

    @JsonIgnoreProperties({"product"})
    private Set<PictureDto> pictures;

    @JsonIgnoreProperties({"product", "revisions"})
    private Set<TranslationDto> translations;
}