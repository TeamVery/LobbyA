package com.teamvery.lobbya;

import com.teamvery.lobbya.cmd.settings;
import com.teamvery.verylib.license;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("lobbya")).setExecutor(new settings());
        license.load(475842821);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        config.setup();
        config.get().options().copyDefaults(true);
        config.save();

    }
}
