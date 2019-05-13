package org.labs.instacart.client;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MyJsonHandler {
    private static Gson gson;

    public static void main(String[] args) throws IOException {
        gson = new Gson();
         InputStream in = new FileInputStream("/Users/adityasakhuja/Code/softwaredesign/src/main/java/org/labs/messages.json");
         MyJsonHandler myJsonHandler = new MyJsonHandler();
         List<Message> messages = myJsonHandler.readJsonStream(in);
         myJsonHandler.writeJsonStream(System.out, messages);
        //        prettyprint(new JsonReader(new FileReader("/Users/adityasakhuja/Code/softwaredesign/src/main/java/org/labs/messages.json")),
        //                new JsonWriter(new FileWriter("/Users/adityasakhuja/Code/softwaredesign/src/main/java/org/labs/out.json")));
    }

    public List<Message> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Message> messages = new ArrayList<>();
        reader.beginArray();

        while (reader.hasNext()) {
            Message message = gson.fromJson(reader, Message.class);
            messages.add(message);
        }
        reader.endArray();
        reader.close();

        return messages;
    }

    public void writeJsonStream(OutputStream out, List<Message> messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writer.beginArray();
        for (Message message : messages) {
            gson.toJson(message, Message.class, writer);
        }
        writer.endArray();
        writer.close();
    }

    static void prettyprint(JsonReader reader, JsonWriter writer) throws IOException {
        while (true) {
            JsonToken token = reader.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    reader.beginArray();
                    writer.beginArray();
                    break;
                case END_ARRAY:
                    reader.endArray();
                    writer.endArray();
                    break;
                case BEGIN_OBJECT:
                    reader.beginObject();
                    writer.beginObject();
                    break;
                case END_OBJECT:
                    reader.endObject();
                    writer.endObject();
                    break;
                case NAME:
                    String name = reader.nextName();
                    writer.name(name);
                    break;
                case STRING:
                    String s = reader.nextString();
                    writer.value(s);
                    break;
                case NUMBER:
                    String n = reader.nextString();
                    writer.value(new BigDecimal(n));
                    break;
                case BOOLEAN:
                    boolean b = reader.nextBoolean();
                    writer.value(b);
                    break;
                case NULL:
                    reader.nextNull();
                    writer.nullValue();
                    break;
                case END_DOCUMENT:
                    return;
            }
        }
    }

    private class Message {
        String firstName;
        String lastName;
        Boolean isAlive;
        Integer age;
        Float heightCm;
        Address address;

        private class Address {
            String streetAddress;
            String city;
            String state;
            String postalCode;
            String phone;
        }

    }
}
