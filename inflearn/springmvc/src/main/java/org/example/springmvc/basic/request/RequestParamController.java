package org.example.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.basic.HelloData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok i'm writer");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "okok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(
        @RequestParam String username,
        @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "okok";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(String username, int age) {  // 단순 Type은 애노테이션을 생략가능
        log.info("username={}, age={}", username, age);
        return "okok";
    }

    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "okok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") String username,  // 값을 안넣어도 BadRequest 안뜸, 빈문자 "" 넣어도 디폴트값 동작함
        @RequestParam(required = false, defaultValue = "20") Integer age) {
        log.info("username={}, age={}", username, age);
        return "okok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamRequired(@RequestParam Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            log.info("{}: {}", entry.getKey(), entry.getValue());
        }
        return "okok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "good!";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "good! v2";
    }
}
