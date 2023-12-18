package dev.vipherian.http.handlers.impl;

import com.sun.net.httpserver.HttpExchange;
import dev.vipherian.http.handlers.Handler;

import java.io.OutputStream;

public class TestHandler extends Handler {

    @Override
    public String getPath() {
        return "/test/";
    }

    @Override
    protected void onHandle(HttpExchange t) {
        System.out.println("Test request received!");
        try {
            String response = "true";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }catch (Exception ignore){}
    }
}
