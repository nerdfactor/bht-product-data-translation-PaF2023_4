package de.bhtberlin.paf2023.productdatatranslation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LanguageDto {

    private int id;

    private String name;

    private String isoCode;

    private CurrencyDto currency;

    private MeasurementDto measurement;

    public LanguageDto(int id) {
        this.id = id;
    }
}
