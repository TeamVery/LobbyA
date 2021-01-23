package com.teamvery.manage;

import com.teamvery.configframework.cfg;
import com.teamvery.manage.cmd.settings;
import com.teamvery.manage.event.joinquitevent;
import com.teamvery.manage.event.playerevents;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {

    public static String p = "Manage"; // plugin name
    public static String c = "config.yml";
    public static String m = "messages.yml";

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("manage")).setExecutor(new settings());
        getServer().getPluginManager().registerEvents(new joinquitevent(), this);
        getServer().getPluginManager().registerEvents(new playerevents(), this);

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        cfg.makeData(p, c);
        cfg.save(p, c);

        cfg.makeData(p, m);

        if (cfg.get(p, c).getBoolean("시간 고정.활성화")) {
            for (World worlds : Bukkit.getWorlds()) {
                worlds.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            }
        } else {
            for (World worlds : Bukkit.getWorlds()) {
                worlds.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            }
        }
    }
}
