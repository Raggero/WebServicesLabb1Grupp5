package com.webservices.plugin;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webservices.annotations.Route;
import com.webservices.constants.NameConstants;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Route("/number")
public class NumberHttpHandler implements HttpHandler {

        int num = 0;

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            num++;
            OutputStream outputStream = exchange.getResponseBody();

            String page2 = """
                <html>
                <head>
                    <title>Easteregg</title>
                </head>
                <body>
                <h1>You've been here %s times </h1> 
                </body>
                </html>""".formatted(num);

            exchange.getResponseHeaders().set(NameConstants.CONTENTTYPE, "text/html");

            exchange.getResponseHeaders().set(NameConstants.CONTENTLENGTH, String.valueOf(page2.length()));
            exchange.sendResponseHeaders(200, page2.length());

            outputStream.write(page2.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }
}
