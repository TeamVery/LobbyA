package com.teamvery.lobbya.cmd;

import com.teamvery.lobbya.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
            }
        } else {
            player.sendMessage("§7---------- §3LobbyA 플러그인 명령어 §7----------");
            player.sendMessage("§6/lobbya 리로드 §7- LobbyA플러그인의 Config를 리로드합니다.");
        }
        return false;
    }
}
