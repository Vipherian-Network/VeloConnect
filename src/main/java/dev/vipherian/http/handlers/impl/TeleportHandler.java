package dev.vipherian.http.handlers.impl;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.vipherian.http.handlers.Handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TeleportHandler extends Handler {
    private final ProxyServer proxy;
    public TeleportHandler(ProxyServer server){
        this.proxy = server;
    }

    private boolean teleport(String input){
        if(input == null) return false;
        try{
            JsonObject object = gson.fromJson(input, JsonObject.class);

            var player = proxy.getPlayer(object.get("player").getAsString()).get();
            var targetServer = proxy.getServer(object.get("server").getAsString()).get();

            player.createConnectionRequest(targetServer);
        }catch (Exception ignore){}
        return true;
    }

    @Override
    public String getPath() {
        return "/teleport/";
    }

    @Override
    protected void onHandle(HttpExchange t) {
        boolean operation = true;
        /*try(var input = t.getRequestBody()) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            try(var bb = new ByteArrayOutputStream()) {
                while ((bytesRead = input.read(buffer)) != -1) {
                    bb.write(bytesRead);
                }
                var s = new String(bb.toByteArray());
                operation = teleport(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        try {
            String response = "soon... (check for newer versions!)";//String.valueOf(operation)
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }catch (Exception ignore){}
    }
}
