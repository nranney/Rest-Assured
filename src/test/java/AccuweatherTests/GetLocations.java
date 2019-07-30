package AccuweatherTests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetLocations extends BaseTest{

    @DataProvider(name = "Continents")
    public Object[][] getContinents(){
        return new Object[][]
                {
                {"ASI"},
                {"EUR"}
        };
    }

    @DataProvider(name = "Cities")
    public Object[][] getCities(){
        return new Object[][]
                {
                        {"204842", "Mumbai"}
                };
    }

    @Test (dataProvider = "Continents")
    public void getCountries(String continentCode){
           Response response= given()
                   .pathParam("continentCode", continentCode)
                   .when()
                   .get("/locations/v1/countries/{continentCode}");

           System.out.println("Response result for Continent Code " + continentCode + ": " + response.getBody().asString());
    }

    @Test (dataProvider = "Cities")
    public void getCityLocationDetails(String cityCode, String cityName){
         Response response = given().
                              when().
                                        get("/locations/v1/" + cityCode).
                               then().
                                       contentType(ContentType.JSON).
                                        body("LocalizedName",equalTo(cityName)).
                                        extract().
                                        response();
        String jsonResponseString = response.asString();
        System.out.println("Response result for City Code " + cityCode + ": " + jsonResponseString);
    }
}
