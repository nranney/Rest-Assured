package AccuweatherTests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setUp(ITestContext context){
        String API_KEY = context.getCurrentXmlTest().getParameter("accuweather.apikey");
        System.setProperty("API_KEY",API_KEY);
        RestAssured.baseURI = "http://dataservice.accuweather.com";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apikey",System.getProperty("API_KEY")).build();

        RestAssured.requestSpecification = requestSpecification;

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.responseSpecification = responseSpecification;

    }


}
