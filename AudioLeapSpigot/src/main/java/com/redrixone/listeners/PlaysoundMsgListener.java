package com.redrixone.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.redrixone.AudioLeapSpigot;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.bukkit.Bukkit.getPlayer;

public class PlaysoundMsgListener implements PluginMessageListener {

    AudioLeapSpigot plugin;
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase("playsound:main")) {
            return;
        }

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subchannel = in.readUTF();

        if (subchannel.equalsIgnoreCase("Playsound")) {
            String playerName = in.readUTF();
            String soundIn = in.readUTF().toUpperCase();
            int pitch = in.readInt();
            int volume = in.readInt();
            String cmdSender = in.readUTF();
            Player sender = getPlayer(cmdSender);
            Player target = getPlayer(playerName);

            Sound sound = Sound.valueOf(soundIn);

            if (target != null) {
                try {
                    target.playSound(target.getLocation(), sound, volume, pitch);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("Invalid sound");
                }
            }
        }

    }
}
