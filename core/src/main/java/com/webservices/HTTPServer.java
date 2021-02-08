package com.webservices;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/index.html", new  MyHttpHandler());
        server.createContext("/cat.png", new  MyHttpHandler());
        server.createContext("/sheet.pdf", new MyHttpHandler());
        server.createContext("/names", new NamesHttpHandler());
//        server.createContext("/index.html", new  MyHttpHandler());
        ExecutorService executorService = Executors.newCachedThreadPool();
        server.setExecutor(executorService);
        server.start();
    }

}