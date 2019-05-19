package com.javacore.Steve.appserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class CrimeFamilyApiHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String dbResult = requestDB();
            httpExchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"text/plain"}));
            httpExchange.sendResponseHeaders(200, 0);
            OutputStream os = httpExchange.getResponseBody();
            os.write(dbResult.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpExchange.close();
        }
    }

    private String requestDB() throws Exception {
        URL url = new URL("http://localhost:6701/api/query");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        String query =
                "SELECT " +
                        "id," +
                        "name,";
        os.write(query.getBytes());
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String dbResult = "";
        String line;
        while ((line = br.readLine()) != null) {
            dbResult += line;
        }
        br.close();
        return dbResult;
    }
}
