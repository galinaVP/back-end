package contract;

import io.restassured.http.ContentType;
import org.junit.Test;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class contractTest {
    @Test
    public void restGetUsers() {
        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .basePath("/api/users")
                .param("page", 2)
                .when()
                .get()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("usersJsonSchema.json"));
    }
    @Test
    public void restGetResources() {
        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .basePath("/api/unknown")
                .when()
                .get()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("resourcesJsonSchema.json"));
    }
    @Test
    public void restPostCreateUsers() {
        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .basePath("/api/users")
                .when()
                .body("{\"name\":\"morpheus\", \"job\":\"leader\"}")
                .post()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("newUserJsonSchema.json"));
    }
    @Test
    public void restPostRegisterUsers() {
        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .basePath("/api/register")
                .when()
                .body("{\"email\":\"eve.holt@reqres.in\", \"password\":\"pistol\"}")
                .post()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("registerUserJsonSchema.json"));
    }
    @Test
    public void restPostLoginUsers() {
        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .basePath("/api/login")
                .when()
                .body("{\"email\":\"eve.holt@reqres.in\", \"password\":\"cityslicka\"}")
                .post()
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("loginUserJsonSchema.json"));
    }
}