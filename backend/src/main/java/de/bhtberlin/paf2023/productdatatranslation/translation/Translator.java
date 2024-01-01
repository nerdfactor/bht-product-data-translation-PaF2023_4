package de.bhtberlin.paf2023.productdatatranslation.translation;

/**
 * Translates texts, currencies and measurements.
 */
public interface Translator {

    /**
     * Translate a text.
     *
     * @param text The text to translate.
     * @param from The tag of the current locale.
     * @param to   The tag of the target locale.
     * @return The translated text.
     */
    String translateText(String text, String from, String to);

    /**
     * Convert a currency.
     *
     * @param currency The value to convert.
     * @param from     The tag of the current locale.
     * @param to       The tag of the target locale.
     * @return The converted value.
     */
    double convertCurrency(double currency, String from, String to);

    /**
     * Convert a measurement.
     *
     * @param measurement The value to convert.
     * @param from        The tag of the current locale.
     * @param to          The tag of the target locale.
     * @return The converted value.
     */
    double convertMeasurement(double measurement, String from, String to);
}