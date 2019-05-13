package org.labs.instacart.client;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class MyHttpClient {

    private static Gson gson;

    public void get(String uri) throws Exception {
        String fileName = "/Users/adityasakhuja/Code/tutorials/instacart-streaming/src/main/java/org/labs/instacart/" +
                          "client/out.json";
        String jsonBody = "";

        HttpRequest.BodyPublisher bp = HttpRequest.BodyPublishers.ofString(jsonBody);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                    //.POST(bp)
                                    .uri(URI.create(uri))
                                    .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(response.body());
        writer.close();

        // --------------------------------------------------------------------------------

        InputStream in = new FileInputStream(fileName);
        List<Message> messages = readJsonStream(in, jsonBody);

        for (Message message : messages) {
            System.out.println(message.away_team.country);
            System.out.println(message);
        }
        System.out.println("total : " + messages.size());
    }

    public List<Message> readJsonStream(InputStream in, String str) throws IOException {
        gson = new Gson();
        List<Message> messages = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        // JsonReader jsonReader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        Message message;

        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            message = gson.fromJson(jsonReader, Message.class);
            messages.add(message);
        }

        jsonReader.endArray();
        jsonReader.close();

        return messages;
    }

    public static void main(String[] args) throws Exception {
        MyHttpClient myHttpClient = new MyHttpClient();
        myHttpClient.get("https://worldcup.sfg.io/matches");
    }

    private class Message {
        private String venue;
        private String location;
        private String status;
        private String time;
        private String fifa_id;
        private String attendance;
        private String[] officials;
        private String stage_name;
        private String home_team_country;
        private String away_team_country;
        private String datetime;
        private String winner;
        private String winner_code;
        private HomeTeam home_team;
        private AwayTeam away_team;
        private HomeTeamEvents[] home_team_events;
        private AwayTeamEvents[] away_team_events;
        private HomeTeamStatistics home_team_statistics;
        private AwayTeamStatistics away_team_statistics;
        private String last_event_update_at;
        private String last_score_update_at;

        private class HomeTeam {
            String country;
            String code;
            String goal;
            Integer penalties;
        }

        private class AwayTeam {
            String country;
            String code;
            String goal;
            String penalties;
        }

        private class HomeTeamStatistics {
            String country;
        }

        private class AwayTeamStatistics {
            String country;
        }

        private class HomeTeamEvents {
            String id;
        }

        private class AwayTeamEvents {
            String id;
        }
    }
}
