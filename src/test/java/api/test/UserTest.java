package api.test;

import api.endpoints.UserEndPoints;
import api.payloads.*;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;


public class UserTest {
    People peopleTestData;
    Starships starshipsTestData;
    Species speciesTestData;

    @BeforeClass
    public void createPeopleTestData() {
        peopleTestData = new People("R2-D2", "96", "", "", "white, blue", "", "", "", "",
                Arrays.asList("A New Hope", "The Empire Strikes Back", "Return of the Jedi", "The Phantom Menace", "Attack of the Clones", "Revenge of the Sith")
                , Arrays.asList("", "", "", ""), Arrays.asList("", "", "", ""), Arrays.asList("", "", "", ""), "", "", "");

        starshipsTestData = new Starships("Death Star", "", "", "", "", "", "342,953", "", "", ""
                , "", "", "", Arrays.asList(), Arrays.asList(), "", "", "");

        speciesTestData = new Species("Wookie", "mammal", "", "", "", "", "", "", "Kashyyyk", ""
                , Arrays.asList(), Arrays.asList(), "", "", "");

    }

    @Test(priority = 1)
    public void testPeopleDetails() {
        Response res = UserEndPoints.getPeopleDetails("3");
        res.then().log().all();
        res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("people-schema.json"));
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.contentType(), "application/json");
        Assert.assertEquals(res.path("name"), peopleTestData.getName());
        Assert.assertEquals(res.path("skin_color"), peopleTestData.getSkin_color());
        List<String> filmUrlList = res.jsonPath().getList("films");
        if (filmUrlList.size() == peopleTestData.getFilms().size()) {
            for (String str : filmUrlList) {
                String filmNumber = str.substring(str.length() - 2, str.length() - 1);
                Response response = UserEndPoints.getFilmsDetails(filmNumber);
                String title = response.jsonPath().get("title");
                Assert.assertTrue(peopleTestData.getFilms().contains(title));
                System.out.println(title);
            }
        }
    }

    @Test(priority = 2)
    public void testFilmsDetails() {
        Response res = UserEndPoints.getFilmsDetails("3");
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.contentType(), "application/json");
    }

    @Test(priority = 2)
    public void testStarshipsDetails() {
        Response res = UserEndPoints.getStartshipsDetails("9");
        res.then().log().all();
        res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("starships-schema.json"));
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.contentType(), "application/json");
        Assert.assertEquals(res.path("name"), starshipsTestData.getName());
        Assert.assertEquals(res.path("crew"), starshipsTestData.getCrew());
    }

    @Test(priority = 3)
    public void testSpeciesDetails() {
        Response res = UserEndPoints.getSpeciesDetails("3");
        res.then().log().all();
        res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("species-schema.json"));
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.contentType(), "application/json");
        Assert.assertEquals(res.path("name"), speciesTestData.getName());
        Assert.assertEquals(res.path("classification"), speciesTestData.getClassification());
        String homeworld = res.path("homeworld");
        String[] arr=homeworld.split("/");
        String homeworldNumber = arr[arr.length-1];
        System.out.println(homeworldNumber);
        Response response = UserEndPoints.getPlatedDetails(homeworldNumber);
        response.then().log().all();
        Assert.assertEquals(response.path("name"), speciesTestData.getHomeworld());
    }

    @Test(priority = 4)
    public void testPlanetPost() {
        Response res = UserEndPoints.postPlanetsDetails("{\n" +
                "    \"name\": \"Test Person\",\n" +
                "    \"height\": \"180\"\n" +
                "}");
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 404);
    }

    @Test(priority = 5)
    public void testPlanetDetailsInWookiee() {
        Response res = UserEndPoints.getPlatedDetailsInWookiee("14");
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.contentType(), "application/json; charset=utf-8");
        Assert.assertEquals(res.path("whrascwo"), "Kracacrororoor");
        res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("planets-schema.json"));
    }
}
