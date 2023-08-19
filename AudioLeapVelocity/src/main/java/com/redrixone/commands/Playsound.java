package com.redrixone.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.redrixone.AudioLeap;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import net.kyori.adventure.text.Component;

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
            parsed = Component.text("Usage: /playsoundv <player> <sound> [volume] [pitch]");
            source.sendMessage(parsed);
            return;
        }

        String sender = player.getUsername();
        String target = args[0];
        String sound = args[1];
        int volume = Integer.parseInt(args[2]);
        int pitch = Integer.parseInt(args[3]);

        Player playerTarget = server.getPlayer(target).get();

        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Playsound");
        out.writeUTF(target);
        out.writeUTF(sound);
        out.writeInt(volume);
        out.writeInt(pitch);
        out.writeUTF(sender);
        ServerConnection serverConnection = playerTarget.getCurrentServer().get();

        serverConnection.sendPluginMessage(MinecraftChannelIdentifier.from("playsound:main"), out.toByteArray());
    }
}
