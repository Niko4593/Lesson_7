package Lesson_7;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunApplication {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather() throws IOException, InterruptedException {
        System.out.println("Write city for English: ");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/forecast?q="+ city +"&units=metric"))
                .header("X-RapidAPI-Key", "1cb3e039ddmsh99151aab8b58dfcp1b8b11jsn9d4ea3a85ad2")
                .header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode City = objectMapper.readTree(response.body()).at("/city/name");
        System.out.println("In city " + City);

        String fullText = objectMapper.writeValueAsString(response.body());
        System.out.println("JsonFile -> " + fullText);


// Я не смог разобраться как выводить отдельные данные на экран(погода, температура, дта).



        System.out.println("########################################");
        System.out.println("If you need exit from this program write 'EXIT' ");

        if(scanner.nextLine().equals("exit")){
            System.out.println("Good bye ;) ");
            System.exit(0);
        }
    }
}