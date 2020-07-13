package logToAllure;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class bodyToAllureTest {
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/{parameter}/{id}")
            .build();

    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void getListUsersTest() {
        ValidatableResponse resp =
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", "")
                .param("page", 2)
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .body("per_page", Matchers.equalTo(6));
    }

    @Test
    public void getSingleUserTest() {
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", "2")
                .log().body()

                .when()
                .get()

                .then()
                .log().body()
                .statusCode(200)
                .body("", hasKey("data"))
                .body("", hasKey("ad"))
                .body("data.id", Matchers.equalTo(2))
                .body("data.email", Matchers.containsString("janet.weaver"))
                .body("data.first_name", Matchers.equalTo("Janet"))
                .body("data.last_name", Matchers.equalTo("Weaver"));
    }

    @Test
    public void getSingleUserNoFoundTest() {
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", "23")
                .log().body()

                .when()
                .get()

                .then()
                .log().body()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void getResourceListTest() {
        ValidatableResponse resp =
                given()
                        .spec(requestSpec)
                        .pathParam("parameter", "unknown")
                        .pathParam("id", "")
                        .log().body()

                        .when()
                        .get()

                        .then()
                        .log().body()
                        .statusCode(200)
                        .body("", hasKey("page"))
                        .body("", hasKey("per_page"))
                        .body("", hasKey("data"))
                        .body("", hasKey("total"))
                        .body("", hasKey("total_pages"));
        resp.body("data.size()", Matchers.equalTo(
                resp
                        .extract()
                        .body()
                        .path("per_page")));
    }

    @Test
    public void getSingleResourceTest() {
        given()
                .spec(requestSpec)
                .pathParam("parameter", "unknown")
                .pathParam("id", "2")
                .log().body()

                .when()
                .get()

                .then()
                .log().body()
                .statusCode(200)
                .body("", hasKey("data"))
                .body("", hasKey("ad"))
                .body("data.id", Matchers.equalTo(2))
                .body("data.name", Matchers.equalTo("fuchsia rose"))
                .body("data.year", Matchers.equalTo(2001))
                .body("data.color", Matchers.equalTo("#C74375"))
                .body("data.pantone_value", Matchers.equalTo("17-2031"));
    }

    @Test
    public void getNotFoundResourceTest() {
        given()
                .spec(requestSpec)
                .pathParam("parameter", "unknown")
                .pathParam("id", "23")
                .log().body()

                .when()
                .get()

                .then()
                .log().body()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void postCreateUserTest() {
        User userCreate = User.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", "")
                .body(userCreate)

                .when()
                .post()

                .then()
                .log().body()
                .statusCode(201)
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("leader"))
                .body("id", Matchers.notNullValue())
                .body("createdAt", Matchers.notNullValue());
    }

    @Test
    public void putUpdateUserTest() {
        User userUpdate = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", 2)
                .body(userUpdate)

                .when()
                .put()

                .then()
                .log().body()
                .statusCode(200)
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("zion resident"))
                .body("updatedAt", Matchers.notNullValue());
    }

    @Test
    public void patchUpdateUserTest() {
        User userUpdate = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", 2)
                .body(userUpdate)

                .when()
                .patch()

                .then()
                .log().body()
                .statusCode(200)
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("zion resident"))
                .body("updatedAt", Matchers.notNullValue());
    }

    @Test
    public void deleteUserTest() {
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", "2")
                .log().body()

                .when()
                .delete()

                .then()
                .log().body()
                .statusCode(204);
    }

    @Test
    public void postRegisterUserSuccessfulTest() {
        User userRegister = User.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "register")
                .pathParam("id", "")
                .body(userRegister)

                .when()
                .post()

                .then()
                .log().body()
                .statusCode(200)
                .body("", hasKey("id"))
                .and()
                .body("", hasKey("token"));
    }

    @Test
    public void postRegisterUserUnsuccessfulTest() {
        User userRegister = User.builder()
                .email("sydney@fife")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "register")
                .pathParam("id", "")
                .body(userRegister)

                .when()
                .post()

                .then()
                .log().body()
                .statusCode(400)
                .body("", hasKey("error"))
                .body("error", equalToIgnoringCase
                        ("Missing password"));
    }

    @Test
    public void postLoginUserSuccessfulTest() {
        User userLogin = User.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "login")
                .pathParam("id", "")
                .body(userLogin)

                .when()
                .post()

                .then()
                .log().body()
                .statusCode(200)
                .body("", hasKey("token"))
                .and()
                .body("token", Matchers.notNullValue());
    }

    @Test
    public void postLoginUserUnsuccessfulTest() {
        User userLogin = User.builder()
                .email("peter@klaven")
                .build();
        given()
                .spec(requestSpec)
                .pathParam("parameter", "login")
                .pathParam("id", "")
                .body(userLogin)

                .when()
                .post()

                .then()
                .log().body()
                .statusCode(400)
                .body("error", equalToIgnoringCase
                        ("Missing password"));
    }

    @Test
    public void getListUsersWithDelayedTest() {
        given()
                .spec(requestSpec)
                .pathParam("parameter", "users")
                .pathParam("id", "")
                .param("delay", 3)
                .log().body()

                .when()
                .get()

                .then()
                .log().body()
                .statusCode(200)
                .time(lessThan(6000L));
    }
}
