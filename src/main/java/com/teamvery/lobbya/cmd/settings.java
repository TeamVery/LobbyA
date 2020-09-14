package com.teamvery.lobbya.cmd;

import com.teamvery.lobbya.config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class settings implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                config.reload();
                player.sendMessage("§a리로드가 완료되었습니다!");

                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                if (config.config.getBoolean("시간 고정")) {
                    String cmd = "gamerule doDaylightCycle false";
                    Bukkit.dispatchCommand(console, cmd);
                } else {
                    String cmd = "gamerule doDaylightCycle true";
                    Bukkit.dispatchCommand(console, cmd);
                }
            }
        } else {
            player.sendMessage("§7---------- §3LobbyA 플러그인 명령어 §7----------");
            player.sendMessage("§6/lobbya reload §7- LobbyA플러그인의 Config를 리로드합니다.");
        }
        return false;
    }
}
