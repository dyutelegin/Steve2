package com.javacore.Steve.webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class ApiCrimeFamilyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URL url = new URL("http://localhost:6702/api/crimefamily");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream is = connection.getInputStream();

        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();

        File file = new File("webclient/snippets/crimefamilytablerow.html");
        byte[] fileBytes = null;
        if (file.exists()) {
            fileBytes = Utils.readBytes("webclient/snippets/crimefamilytablerow.html");
        }

        String result = "";
        String raw = new String(bytes);
        String template = new String(fileBytes);

        String[] records = raw.split(";");
        for(String rec: records){
            System.out.println(rec);
            String[] values = rec.split(",");
            String html = new String(template);
            for (int i = 0, len = values.length; i<len; i++) {
                html = html.replace("{{" + i + "}}", values[i]);
            }
            result += html;
        }

        httpExchange.getRequestHeaders().put("Content-Type", Arrays.asList(new String[]{"text/plain"}));
        httpExchange.sendResponseHeaders(200,0);

        OutputStream os = httpExchange.getResponseBody();
        os.write(result.getBytes());
        os.close();

    }
}
