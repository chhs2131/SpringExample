package org.example.tddexample.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ProductSteps {
    public static ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/products")
            .then()
            .log().all().extract();
        return response;
    }

    public static AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy policy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, policy);
        return request;
    }

    public static ExtractableResponse<Response> 상품조회요청(final Long prodouctId) {
        return RestAssured.given().log().all()
            .when()
            .get("/products/{productId}", prodouctId)
            .then().log().all()
            .extract();
    }

    public static UpdateProductRequest 상품수정요청() {
        return new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
    }
}