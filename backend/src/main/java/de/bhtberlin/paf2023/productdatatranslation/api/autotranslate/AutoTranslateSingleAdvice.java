package de.bhtberlin.paf2023.productdatatranslation.api.autotranslate;

import de.bhtberlin.paf2023.productdatatranslation.config.AppConfig;
import de.bhtberlin.paf2023.productdatatranslation.translation.Translatable;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * An AutoTranslateAdvice translating single {@link Translatable Translatables}.
 */
@ControllerAdvice
public class AutoTranslateSingleAdvice extends AutoTranslateAdvice<Translatable> {

    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getGenericParameterType() instanceof ParameterizedType parameterizedType) {
            try {
                Type[] args = parameterizedType.getActualTypeArguments();
                return Translatable.class.isAssignableFrom((Class<?>) args[0]);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Translatable beforeBodyWrite(Translatable body,
                                        @NotNull MethodParameter returnType,
                                        @NotNull MediaType selectedContentType,
                                        @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                        @NotNull ServerHttpRequest request,
                                        @NotNull ServerHttpResponse response) {
        if (body != null) {
            body = this.translateTranslatable(body, AppConfig.DEFAULT_LANGUAGE, LocaleContextHolder.getLocale().toLanguageTag());
        }
        return body;
    }
}
