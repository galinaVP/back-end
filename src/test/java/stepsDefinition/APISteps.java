package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

import static com.Context.getContext;
import static com.brainacad.RequestSpec.REQUEST_SPECIFICATION;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class APISteps {

    private String url;
    public ValidatableResponse response;

    @Given("I have server by url {string}")
    public void i_have_server_by_url(String url) {
        this.url = url;
    }

    @When("I send GET request on endpoint {string} with page 2")
    public void i_send_GET_request_on_endpoint(String endpoint) {
        getContext().setResponse(given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("parameter", endpoint)
                .pathParam("id", "")
                .param("page", 2)
                .when()
                .get()
                .then());
    }
    @And("I check body contains 6 users under page")
    public void i_check_if_per_page_users_equel() {
        getContext().getResponse().body("per_page", Matchers.equalTo(6));
    }

    @When("I send GET request on endpoint {string} with id {string}")
    public void i_send_GET_request_on_endpoint(String endpoint, String id) {
        getContext().setResponse(given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("parameter", endpoint)
                .pathParam("id", id)
                .when()
                .get()
                .then());
    }

    @Then("I check if status code is {int}")
    public void i_check_if_status_code_equel(int code) {
        getContext().getResponse().statusCode(code);
    }

    @And("I check if body shows correct ID {int}")
    public void i_check_if_body_with_correct_ID(int id) {
        getContext().getResponse().body("data.id", Matchers.equalTo(id));
    }

    @And ("I check if email in body contains {string}")
    public void i_check_email_contains(String email_part){
        getContext().getResponse().body("data.email", Matchers.containsString("janet.weaver"));
    }

    @And ("I check if first name in body is {string}")
    public void i_check_first_name_equal(String first_name){
        getContext().getResponse().body("data.first_name", Matchers.equalTo("Janet"));
    }

    @And ("I check if last name in body is {string}")
    public void i_check_last_name_equal(String last_name){
        getContext().getResponse().body("data.last_name", Matchers.equalTo("Weaver"));
    }
    @And ("I check body has key {string}")
    public void i_check_body_has_key(String key){
        getContext().getResponse().body("", hasKey(key));
    }
    @And ("I check size according to per_page")
    public void i_check_real_size_according_to_per_page(){
        ValidatableResponse resp =
                given()
                        .spec(REQUEST_SPECIFICATION)
                        .pathParam("parameter", "unknown")
                        .pathParam("id", "")
                        .log().body()

                        .when()
                        .get()

                        .then();
        resp.body("data.size()", Matchers.equalTo(
                resp
                        .extract()
                        .body()
                        .path("per_page")));
    }
    @And ("I check if name in body is {string}")
    public void i_check_body_name_is(String name){
        getContext().getResponse().body("data.name", Matchers.equalTo(name));
    }
    @And ("I check if year in body is {int}")
    public void i_check_body_year_is(int year){
        getContext().getResponse().body("data.year", Matchers.equalTo(year));
    }
    @And ("I check if body is empty")
    public void i_check_body_is_empty(){
        getContext().getResponse().body("isEmpty()", Matchers.is(true));;
    }
    @When("I send GET request on endpoint {string} with delay {int}")
    public void i_send_GET_request_with_delay(String endpoint, int delay) {
        getContext().setResponse(given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("parameter", endpoint)
                .pathParam("id", "")
                .param("delay", delay)
                .when()
                .get()
                .then());
    }
    @And ("I check time lessThan 6000 ms")
    public void i_check_time_less(){
        getContext().getResponse().time(lessThan(6000L));
    }
    @When("I send DELETE request on endpoint {string} with id {int}")
    public void i_send_delete_request_on_endpoint(String endpoint, int id) {
        getContext().setResponse(given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("parameter", endpoint)
                .pathParam("id", id)
                .when()
                .delete()
                .then());
    }
}