package api.endpoints;

import api.payloads.Species;
import api.payloads.User;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UserEndPoints {

//    Concept of Serialization
//    public static Response createuser(User userPayLoad){
//        Response response =
//                given()
//                        .contentType(ContentType.JSON)
//                        .accept(ContentType.JSON)
//                        .body(userPayLoad)
//               .when()
//                        .post(Routes.userPostUrl);
//        return response;
//    }
    public static Response getPeopleDetails(String people){
        Response response =
                given()
                        .pathParam("people",people)
                .when()
                        .get(Routes.peopleGetUrl);

        return response;
    }
    public static Response getFilmsDetails(String films){
        Response response =
                given()
                        .pathParam("films",films)
                        .when()
                        .get(Routes.filmsGetUrl);

        return response;
    }

    public static Response getStartshipsDetails(String starships){
        Response response =
                given()
                        .pathParam("starships",starships)
                        .when()
                        .get(Routes.starshipsGetUrl);

        return response;
    }

    public static Response getSpeciesDetails(String species){
        Response response =
                given()
                        .pathParam("species",species)
                        .when()
                        .get(Routes.speciesGetUrl);

        return response;
    }

    public static Response getHomeworldDetails(String homeworld){
        Response response =
                given()
                        .pathParam("homeworld",homeworld)
                        .when()
                        .get(Routes.homeworldGetUrl);

        return response;
    }

    public static Response postPlanetsDetails(String planetBody){
        Response response = given()
                .header("Content-Type", "application/json")
                .body(planetBody)
                .when()
                .post(Routes.postPlanetUrl);
        return response;
    }

    public static Response getPlatedDetails(String planet){
        Response response =
                given()
                        .pathParam("planets",planet)
                        .when()
                        .get(Routes.planetsGetUrl);

        return response;
    }

    public static Response getPlatedDetailsInWookiee(String planet){
        Response response =
                given()
                        .pathParam("planets",planet)
                        .queryParam("format","wookiee")
                        .when()
                        .get(Routes.planetsGetUrl);

        return response;
    }

}
