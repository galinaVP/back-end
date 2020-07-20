package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

import static com.Context.getContext;
import static com.brainacad.RequestSpec.REQUEST_SPECIFICATION;
import static io.restassured.RestAssured.given;

public class PutSteps {
    @When("I send PUT request on endpoint {string} with id {int}")
    public void i_send_put_request_on_endpoint(String endpoint, int id) {
        User user = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        getContext().setResponse(given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("parameter", endpoint)
                .pathParam("id", id)
                .when()
                .body(user)
                .put()
                .then());
    }
    @And("I check if name in body is {string} and job in body is {string}")
    public void i_check_body_name_and_job_are(String name, String job){
        getContext().getResponse().body("name", Matchers.equalTo(name));
        getContext().getResponse().body("job", Matchers.equalTo(job));
    }
}
