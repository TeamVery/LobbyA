package com.teamvery.manage;

import com.teamvery.manage.cmd.settings;
import com.teamvery.manage.event.joinquitevent;
import com.teamvery.manage.event.playerevents;
import com.teamvery.verylib.license;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("manage")).setExecutor(new settings());
        getServer().getPluginManager().registerEvents(new joinquitevent(), this);
        getServer().getPluginManager().registerEvents(new playerevents(), this);
        license.load(getConfig().getInt("인증코드"));

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        config.setup();
        config.get().options().copyDefaults(true);
        config.save();

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        if (config.config.getBoolean("시간 고정")) {
            String cmd = "gamerule doDaylightCycle false";
            Bukkit.dispatchCommand(console, cmd);
        } else {
            String cmd = "gamerule doDaylightCycle true";
            Bukkit.dispatchCommand(console, cmd);
        }
    }
}
