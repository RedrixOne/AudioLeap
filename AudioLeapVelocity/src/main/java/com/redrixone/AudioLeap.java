package com.redrixone;


import com.google.inject.Inject;
import com.redrixone.commands.TPTo;
import com.redrixone.storage.DataManager;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.sql.DriverManager;
import java.sql.*;

@Plugin(id = "audioleap",
        name = "AudioLeap",
        version = "1.0",
        description = "Simple TPTO & PlaySound velocity plugin.",
        authors = {"RedrixOne"}
)

public class AudioLeap {

    private DataManager dataManager;
    private Connection connection;
    private PreparedStatement statement;

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public AudioLeap(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        databaseConnection();
        createTable();

        logger.info("[AudioLeap] Plguin enabled.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = server.getCommandManager();

        CommandMeta tpto = commandManager.metaBuilder("tpto").build();
        commandManager.register(tpto, new TPTo(this, server));
    }

    public void databaseConnection() {
        String username = "yourdb";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/audioleap";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement("SELECT * FROM audioleap WHERE uuid = ?");
            dataManager = new DataManager(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS audioleap (" +
                "uuid VARCHAR(36), " +
                "x INT, " +
                "y INT, " +
                "z INT, " +
                "request BOOLEAN, " +
                "playreq BOOLEAN, " +
                "sound STRING, " +
                "volume INT " +
                "pitch INT";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
