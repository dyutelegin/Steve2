package com.javacore.Steve.db.server;

import com.javacore.Steve.db.DBApplication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public enum  DBServer {
    INSTANCE;
    public static final int PORT = 6701;

    public static final String BAD_XML =
 /*           "<HTML>" +
                    "<nead></head>" +
                    "<body><div style=\"background-color:#AAAA00; width: 100%; height: 100%; border: 1px solid black; color:white;\">{{state}}</body>" +
            "</HTML>"; */
             "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                     "<table name=\"CrimeFamily\">" +
                     "<columns>" +
                     "<column systemName=\"id\" displayName=\"ID\"/>" +
                     "<column systemName=\"name\" displayName=\"Name\"/>" +
                     "</columns>" +
                     "</table>";

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 10 );
        server.createContext("/db/state", new StateHandler(){
                    @Override
                    public void handle(HttpExchange httpExchange) throws IOException {
                        String state = BAD_XML.replace("{{state}}", DBApplication.INSTANCE.getStateName()) ;
                        // state = String.format(BAD_HTML, state);
                        httpExchange.sendResponseHeaders(200,state.length());
                        httpExchange.getRequestHeaders().put("Content-Type", Arrays.asList(new String[]{"text/html"}));
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(state.getBytes());
                        os.close();
                    }
        });

      // Анонимный класс Hendler
  /*          @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String state = BAD_HTML.replace("{{state}}", DBApplication.INSTANCE.getStateName()) ;
               // state = String.format(BAD_HTML, state);
                httpExchange.sendResponseHeaders(200,state.length());
                httpExchange.getRequestHeaders().put("Content-Type", Arrays.asList(new String[]{"text/html"}));
                OutputStream os = httpExchange.getResponseBody();
                os.write(state.getBytes());
                os.close();
            }
        }); */
        server.createContext("/db/structure", new StateHandler());
        server.start();
        String message = String.format("Server is running on port: %d", server.getAddress().getPort());
        System.out.println(message);
    }
// Прочитать xml файл


}
