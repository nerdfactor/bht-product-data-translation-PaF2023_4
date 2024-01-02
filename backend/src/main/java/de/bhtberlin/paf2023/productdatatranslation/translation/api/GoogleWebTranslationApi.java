package de.bhtberlin.paf2023.productdatatranslation.translation.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.bhtberlin.paf2023.productdatatranslation.exception.ExternalTranslationApiException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Set;

/**
 * External translation Api using the unofficial Google Web Api.
 */
@Component
@RequiredArgsConstructor
public class GoogleWebTranslationApi implements ExternalTranslationApi {

    private static final String apiUrl = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s";

    private final ObjectMapper mapper;

    /**
     * Supported locales.
     */
    private static final Set<String> supportedLocales = Set.of("af", "sq", "am", "ar", "hy", "as", "ay", "az", "bm", "eu", "be", "bn", "bs", "bg", "ca", "ceb", "zh-CN", "zh-TW", "co", "hr", "cs", "da", "dv", "nl", "en", "eo", "et", "ee", "fi", "fr", "fy", "gl", "ka", "de", "el", "gn", "gu", "ht", "ha", "he", "hi", "hu", "is", "ig", "id", "ga", "it", "ja", "jv", "kn", "kk", "km", "rw", "ko", "ku", "ky", "lo", "la", "lv", "ln", "lt", "lg", "lb", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mn", "my", "ne", "no", "ny", "or", "om", "ps", "fa", "pl", "pt", "pa", "qu", "ro", "ru", "sm", "sa", "gd", "sr", "st", "sn", "sd", "si", "sk", "sl", "so", "es", "su", "sw", "sv", "tl", "tg", "ta", "tt", "te", "th", "ti", "ts", "tr", "tk", "ak", "uk", "ur", "ug", "uz", "vi", "cy", "xh", "yi", "yo", "zu");

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Set<String> getSupportedLocales() {
        return supportedLocales;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull String translate(@Nullable String text, @NotNull String from, @NotNull String to) throws ExternalTranslationApiException {
        if (text == null || text.isEmpty()) {
            return "";
        }
        RestTemplate restTemplate = new RestTemplate();

        String uri = apiUrl.formatted(from, to, URLEncoder.encode(text, StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/121.0");

        String json = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
        try {
            JsonNode root = this.mapper.readTree(json);
            return root.path(0).path(0).path(0).asText(text);
        } catch (JsonProcessingException e) {
            throw new ExternalTranslationApiException("Exception during Google Web Api translation", e);
        }
    }
}
