package com.redrixone.commands;

import com.redrixone.AudioLeap;
import com.redrixone.storage.DataManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

public class TPTo implements SimpleCommand {


    private final ProxyServer server;
    public AudioLeap audioLeap;
    Component parsed;

    public TPTo(AudioLeap audioLeap, ProxyServer server) {
        this.server = server;
        this.audioLeap = audioLeap;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();

        if (!(source instanceof Player)) {
            parsed = Component.text("You cannot execute that command as a console!");
            source.sendMessage(parsed);
            return;
        }


        Player player = (Player) source;

        if (!player.hasPermission("audioleap.tpto")) {
            parsed = Component.text("No permission");
            source.sendMessage(parsed);
            return;
        }

        String[] args = invocation.arguments();

        if (args.length < 4 || args.length > 4) {
            parsed = Component.text("Usage: /tpto <server> <location>");
            source.sendMessage(parsed);
            return;
        }

        String serverName = args[0];
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int z = Integer.parseInt(args[3]);
        try {
            player.createConnectionRequest(server.getServer(serverName).get())
                    .fireAndForget();
            DataManager.saveCoordinates(player.getUniqueId(), x, y, z, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
