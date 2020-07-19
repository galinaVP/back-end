package com.brainacad;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RequestSpec {

    public static final RequestSpecification REQUEST_SPECIFICATION =
            (RequestSpecification) new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in")
                    .setContentType(ContentType.JSON)
                    .setBasePath("/api/{parameter}/{id}")
                    .build();
}

