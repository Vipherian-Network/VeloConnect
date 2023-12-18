package dev.vipherian.http;

import com.sun.net.httpserver.HttpServer;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.vipherian.VeloConnect;
import dev.vipherian.http.handlers.Handler;
import dev.vipherian.http.handlers.impl.OnlinePlayersHandler;
import dev.vipherian.http.handlers.impl.TeleportHandler;
import dev.vipherian.http.handlers.impl.TestHandler;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public final class WebServer {
    private static void registries(){
        handlers.add(new TestHandler());
        handlers.add(new TeleportHandler(serverProxy));
        handlers.add(new OnlinePlayersHandler(serverProxy));
    }

    private static final List<Handler> handlers = new ArrayList<>();
    private static ProxyServer serverProxy;
    public static void start(ProxyServer proxy)throws Exception{
        serverProxy = proxy;
        registries();
        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);
        for (Handler handler : handlers){
            server.createContext(handler.getPath(),handler);
        }
        server.setExecutor(null);
        server.start();
    }
}
