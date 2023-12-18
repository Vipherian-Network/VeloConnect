package dev.vipherian.http.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.vipherian.VeloConnect;

import java.io.IOException;

public abstract class Handler implements HttpHandler {
    public final Gson gson = new Gson();

    public abstract String getPath();

    protected abstract void onHandle(HttpExchange t);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String key = exchange.getRequestURI().getPath().replace(getPath(),"");
        if(key == null || !key.equals(VeloConnect.settings.getKey())){
            return;
        }
        this.onHandle(exchange);
    }
}
