package org.example.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {
    @GetMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("hellobasic");
        return "dfdf";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
    // public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")  // 파라미터 조건 설정
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-param", headers = "mode=debug")  // 헤더 조건 설정
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // Content-type을 맵핑할때
    // headers 대신 consume을 사용해야함
    // @PostMapping(value = "/mapping-cosume", consumes = "application/json")
    @PostMapping(value = "/mapping-cosume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    // response 받을 수 있는 데이터 타입을 제한할 때 (client의 accept */*)
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
