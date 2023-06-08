package com.mgon.customresponse.global.response.handler;

import com.mgon.customresponse.global.response.CustomResponse;
import lombok.Getter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Getter
@RestControllerAdvice
public class RestResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        String classPath = returnType.getContainingClass().toString();  // 현재 실행된 controller의 경로를 불러온다.
        return classPath.startsWith("class com.mgon.customresponse.api");  // controller의 경로가 api 패키지 내부라면 true를 반환한다. 외부의 경우 false를 반환하는데 여기에는 swagger, exceptionHandler, util... 등이 해당되야한다.
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return CustomResponse.of(body);
    }
}
