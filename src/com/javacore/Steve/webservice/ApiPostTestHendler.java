package com.javacore.Steve.webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class ApiPostTestHendler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //recieved from client-browser
        InputStream is = httpExchange.getRequestBody();
        byte[] requestBytes = new byte[is.available()];
        is.read(requestBytes);



        //sending result
        httpExchange.getRequestHeaders().put("Content-Type", Arrays.asList(new String[]{"text/plain"}));
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(requestBytes);
        os.close();
    }
}
