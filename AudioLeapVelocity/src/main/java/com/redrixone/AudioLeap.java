package com.redrixone;


import com.google.inject.Inject;
import com.redrixone.commands.TPTo;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;

@Plugin(id = "audioleap",
        name = "AudioLeap",
        version = "1.0",
        description = "Simple TPTO & PlaySound velocity plugin.",
        authors = {"RedrixOne"}
)

public class AudioLeap {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public AudioLeap(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        logger.info("[AudioLeap] Plguin enabled.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(MinecraftChannelIdentifier.from("tpto:main"));
        CommandManager commandManager = server.getCommandManager();

        CommandMeta tpto = commandManager.metaBuilder("tpto").build();
        commandManager.register(tpto, new TPTo(this, server));
    }
}
