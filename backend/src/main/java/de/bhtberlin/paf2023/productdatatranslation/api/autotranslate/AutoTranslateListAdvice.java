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
import java.util.List;

/**
 * An AutoTranslateAdvice translating lists of {@link Translatable Translatables}.
 */
@ControllerAdvice
public class AutoTranslateListAdvice extends AutoTranslateAdvice<List<Translatable>> {

    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getGenericParameterType() instanceof ParameterizedType parameterizedType) {
            try {
                Type[] args = parameterizedType.getActualTypeArguments();
                if (args[0] instanceof ParameterizedType parameterizedArg) {
                    Class<?> cls = (Class<?>) parameterizedArg.getRawType();
                    Type[] types = parameterizedArg.getActualTypeArguments();
                    if (cls != null && List.class.isAssignableFrom(cls) && types.length > 0 && Translatable.class.isAssignableFrom((Class<?>) types[0])) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Translatable> beforeBodyWrite(List<Translatable> body,
                                              @NotNull MethodParameter returnType,
                                              @NotNull MediaType selectedContentType,
                                              @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                              @NotNull ServerHttpRequest request,
                                              @NotNull ServerHttpResponse response) {
        if (body != null) {
            body.forEach(translatable ->
                    translatable = this.translateTranslatable(translatable, AppConfig.DEFAULT_LANGUAGE, LocaleContextHolder.getLocale().toLanguageTag()));
        }
        return body;
    }
}
