package org.example.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message body: " + messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    // input stream, writer도 지원함
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws Exception {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message body: " + messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    // HttpEntity 지원. Body를 직접 조회함, 응답도 직접 만들 수 있음
    // ㄴ RequestEntity: Method, URL 정보 추가
    // ㄴ ResponseEntity: HTTP 상태 코드 설정 가능
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws Exception {
        String body = httpEntity.getBody();

        log.info("message body: " + body);
        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v4")
    @ResponseBody
    public String requestBodyStringV3(@RequestBody String messageBody) {
        log.info("message body: " + messageBody);
        return "ooook";
    }
}
