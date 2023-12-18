package dev.vipherian.http.handlers.impl;

import com.sun.net.httpserver.HttpExchange;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.vipherian.http.handlers.Handler;

import java.io.OutputStream;

public class OnlinePlayersHandler extends Handler {
    private final ProxyServer proxy;
    public OnlinePlayersHandler(ProxyServer server){
        this.proxy = server;
    }



    @Override
    public String getPath() {
        return "/online/";
    }
    @Override
    protected void onHandle(HttpExchange t) {
        try {
            String response = String.valueOf(proxy.getPlayerCount());
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }catch (Exception ignore){}
    }
}
