package api.endpoints;

public class Routes {


    public static String baseUri="https://swapi.dev/api/";
//    User
    public static String userPostUrl=baseUri +"/user";
    public static String peopleGetUrl= baseUri+"/people/{people}";
    public static String filmsGetUrl= baseUri+"/films/{films}";
    public static String starshipsGetUrl= baseUri+"/starships/{starships}";
    public static String speciesGetUrl= baseUri+"/species/{species}";
    public static String planetsGetUrl= baseUri+"/planets/{planets}";
    public static String homeworldGetUrl=baseUri+"/homeworld/{homeworld}";
    public static String postPlanetUrl=baseUri+"/planet/";




}
