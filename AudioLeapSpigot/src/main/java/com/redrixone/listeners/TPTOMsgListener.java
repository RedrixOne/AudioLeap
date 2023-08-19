package com.redrixone.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static org.bukkit.Bukkit.getPlayer;

public class TPTOMsgListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase("tpto:main")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subchannel = in.readUTF();
        if (subchannel.equalsIgnoreCase("AudioLeap")) {
            String playerName = in.readUTF();
            int x = in.readInt();
            int y = in.readInt();
            int z = in.readInt();
            Player target = getPlayer(playerName);

            if (target != null) {
                target.teleport(target.getWorld().getBlockAt(x, y, z).getLocation());
            }
        }
    }
}
