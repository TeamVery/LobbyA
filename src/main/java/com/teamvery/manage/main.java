package com.teamvery.manage;

import com.teamvery.manage.cmd.settings;
import com.teamvery.manage.event.joinquitevent;
import com.teamvery.manage.event.playerevents;
import com.teamvery.verylib.license;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
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

        if (config.config.getBoolean("시간 고정.활성화")) {
            Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(config.get().getString("시간 고정.월드"))))
                    .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        } else {
            Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(config.get().getString("시간 고정.월드"))))
                    .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        }
    }
}
