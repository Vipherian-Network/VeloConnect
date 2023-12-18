package dev.vipherian;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.vipherian.http.WebServer;
import dev.vipherian.settings.VeloConnectObject;
import lombok.Getter;
import net.http.aeon.Aeon;
import org.slf4j.Logger;

import java.io.File;

@Plugin(
        id = "veloconnect",
        name = "VeloConnect",
        version = "1.0",
        description = "(c)2023 Vipherian",
        url = "https://youtube.com/@aredblock",
        authors = {"aredblock(Vipherian)"}
)
public class VeloConnect {
    @Inject
    private Logger logger;
    @Getter
    private final ProxyServer server;
    public static final VeloConnectObject settings = Aeon.insert(new VeloConnectObject(),new File("VeloConnect-token").toPath());

    @Inject
    public VeloConnect(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;

        try {
            WebServer.start(server);
            logger.info("Booted REST on http://localhost:9090");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Hello there! VeloConnect loaded!");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        logger.info("Shutdown http://localhost:9090");
    }
}
