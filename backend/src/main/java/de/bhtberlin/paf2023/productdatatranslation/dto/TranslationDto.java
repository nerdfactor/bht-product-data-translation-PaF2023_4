package de.bhtberlin.paf2023.productdatatranslation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TranslationDto {

    private int id;

    private String shortDescription;

    private String longDescription;

    @JsonIgnoreProperties({"translations"})
    private ProductDto product;

    @JsonIgnoreProperties({"translations"})
    private LanguageDto language;
}
