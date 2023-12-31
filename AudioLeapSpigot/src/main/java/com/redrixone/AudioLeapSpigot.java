package com.redrixone;

import com.redrixone.listeners.PlaysoundMsgListener;
import com.redrixone.listeners.TPTOMsgListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AudioLeapSpigot extends JavaPlugin {

    public void onEnable() {
        checkIfBungee();
        getServer().getMessenger().registerIncomingPluginChannel(this, "tpto:main", new TPTOMsgListener());
        getServer().getMessenger().registerIncomingPluginChannel(this, "playsound:main", new PlaysoundMsgListener());

        getLogger().info("AudioLeapSpigot has been enabled!");
    }

    public void onDisable() {
        getLogger().info("AudioLeapSpigot has been disabled!");
    }

    private void checkIfBungee() {
        if (!getServer().spigot().getConfig().getConfigurationSection("settings").getBoolean("bungeecord")) {
            getLogger().severe("This server isn't BungeeCord.");
            getLogger().severe("Please enable it into your spigot.yml.");
            getLogger().severe("Plugin disabled!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
