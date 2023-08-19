package com.redrixone.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static org.bukkit.Bukkit.getPlayer;

public class PlaysoundMsgListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase("playsound:main")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subchannel = in.readUTF();

        if (subchannel.equalsIgnoreCase("Playsound")) {
            String playerName = in.readUTF();
            String soundIn = in.readUTF();
            int pitch = in.readInt();
            int volume = in.readInt();
            Player target = getPlayer(playerName);

            if (target != null) {
                target.playSound();
            }
        }

    }
}
