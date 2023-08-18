package com.redrixone.commands;

import com.redrixone.AudioLeap;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class Playsound implements SimpleCommand {


    private final ProxyServer server;
    public AudioLeap audioLeap;
    Component parsed;

    public Playsound(AudioLeap audioLeap, ProxyServer server) {
        this.server = server;
        this.audioLeap = audioLeap;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();

        Player player = (Player) source;

        if (!player.hasPermission("audioleap.playsound")) {
            parsed = Component.text("No permission");
            source.sendMessage(parsed);
            return;
        }

        String[] args = invocation.arguments();

        if (args.length < 2 || args.length > 4) {
            parsed = Component.text("Usage: /playsound <player> <sound> [volume] [pitch]");
            source.sendMessage(parsed);
            return;
        }

        String target = args[0];
        UUID targetUUID = server.getPlayer(target).get().getUniqueId();
        String sound = args[1];
        int volume = Integer.parseInt(args[2]);
        int pitch = Integer.parseInt(args[3]);

        try {
            //DataManager.setPlaysoundRequest(targetUUID, true);
            //DataManager.setSound(targetUUID, sound, pitch, volume);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
