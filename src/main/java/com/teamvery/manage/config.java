package com.teamvery.manage;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class config {

    private static File file;
    public static FileConfiguration config;

    public static void setup() {
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("manage")).getDataFolder(), "config.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println("알수 없는 오류로 콘피그 파일을 생성할수 없습니다.");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save(){
        try{
            config.save(file);
        }catch (IOException e){
            System.out.println("알수 없는 오류로 콘피그 파일을 저장할수 없습니다");
        }
    }

    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}
