package com.teamvery.manage.cmd;

import com.teamvery.manage.config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class settings implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                config.reload();
                player.sendMessage("§a리로드가 완료되었습니다!");

                if (config.config.getBoolean("시간 고정.활성화")) {
                    Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(config.get().getString("시간 고정.월드"))))
                            .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                } else {
                    Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(config.get().getString("시간 고정.월드"))))
                            .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                }
            }
            if (args[0].equalsIgnoreCase("debug")) {

                player.sendMessage("디버그 실행됨.");
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("IGNORE_ENTITY_SPAWN")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("엔티티 소환 차단")) {
                            player.sendMessage("§cENTITY_SPAWN_IGNORE is already true");
                        } else {
                            player.sendMessage("§aENTITY_SPAWN_IGNORE == TRUE");
                            config.config.set("엔티티 소환 차단", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("엔티티 소환 차단")) {
                            player.sendMessage("§cENTITY_SPAWN_IGNORE is already false");
                        } else {
                            player.sendMessage("§aENTITY_SPAWN_IGNORE == FALSE");
                            config.config.set("엔티티 소환 차단", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_WEATHER")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("날씨 차단")) {
                            player.sendMessage("§cWEATHER_IGNORE is already true");
                        } else {
                            player.sendMessage("§aWEATHER_IGNORE == TRUE");
                            config.config.set("날씨 차단", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("날씨 차단")) {
                            player.sendMessage("§cWEATHER_IGNORE is already false");
                        } else {
                            player.sendMessage("§aWEATHER_IGNORE == FALSE");
                            config.config.set("날씨 차단", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_ITEM_DROP")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("아이템 드랍 비활성화")) {
                            player.sendMessage("§cIGNORE_ITEM_DROP is already true");
                        } else {
                            player.sendMessage("§aIGNORE_ITEM_DROP == TRUE");
                            config.config.set("아이템 드랍 비활성화", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("아이템 드랍 비활성화")) {
                            player.sendMessage("§cIGNORE_ITEM_DROP is already false");
                        } else {
                            player.sendMessage("§aIGNORE_ITEM_DROP == FALSE");
                            config.config.set("아이템 드랍 비활성화", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_ITEM_PICKUP")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("아이템 획득 비활성화")) {
                            player.sendMessage("§cIGNORE_ITEM_PICKUP is already true");
                        } else {
                            player.sendMessage("§aIGNORE_ITEM_PICKUP == TRUE");
                            config.config.set("아이템 획득 비활성화", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("아이템 획득 비활성화")) {
                            player.sendMessage("§cIGNORE_ITEM_PICKUP is already false");
                        } else {
                            player.sendMessage("§aIGNORE_ITEM_PICKUP == FALSE");
                            config.config.set("아이템 획득 비활성화", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_BLOCK_BREAK")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("블럭 파괴금지")) {
                            player.sendMessage("§cIGNORE_BLOCK_BREAK is already true");
                        } else {
                            player.sendMessage("§aIGNORE_BLOCK_BREAK == TRUE");
                            config.config.set("블럭 파괴금지", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("블럭 파괴금지")) {
                            player.sendMessage("§cIGNORE_BLOCK_BREAK is already false");
                        } else {
                            player.sendMessage("§aIGNORE_BLOCK_BREAK == FALSE");
                            config.config.set("블럭 파괴금지", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_BLOCK_PLACE")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("블럭 설치금지")) {
                            player.sendMessage("§cIGNORE_BLOCK_PLACE is already true");
                        } else {
                            player.sendMessage("§aIGNORE_BLOCK_PLACE == TRUE");
                            config.config.set("블럭 설치금지", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("블럭 설치금지")) {
                            player.sendMessage("§cIGNORE_BLOCK_PLACE is already false");
                        } else {
                            player.sendMessage("§aIGNORE_BLOCK_PLACE == FALSE");
                            config.config.set("블럭 설치금지", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_HUNGER")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("플레이어 허기 비활성화")) {
                            player.sendMessage("§cDISABLE_PLAYER_HUNGER is already true");
                        } else {
                            player.sendMessage("§aDISABLE_PLAYER_HUNGER == TRUE");
                            config.config.set("플레이어 허기 비활성화", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("플레이어 허기 비활성화")) {
                            player.sendMessage("§cDISABLE_PLAYER_HUNGER is already false");
                        } else {
                            player.sendMessage("§aDISABLE_PLAYER_HUNGER == FALSE");
                            config.config.set("플레이어 허기 비활성화", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PVP")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("플레이어 PVP 비활성화")) {
                            player.sendMessage("§cDISABLE_PVP is already true");
                        } else {
                            player.sendMessage("§aDISABLE_PVP == TRUE");
                            config.config.set("플레이어 PVP 비활성화", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("플레이어 PVP 비활성화")) {
                            player.sendMessage("§cDISABLE_PVP is already false");
                        } else {
                            player.sendMessage("§aDISABLE_PVP == FALSE");
                            config.config.set("플레이어 PVP 비활성화", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_CHAT")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("플레이어 채팅 비활성화")) {
                            player.sendMessage("§cDISABLE_CHAT is already true");
                        } else {
                            player.sendMessage("§aDISABLE_CHAT == TRUE");
                            config.config.set("플레이어 채팅 비활성화", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("플레이어 채팅 비활성화")) {
                            player.sendMessage("§cDISABLE_CHAT is already false");
                        } else {
                            player.sendMessage("§aDISABLE_CHAT == FALSE");
                            config.config.set("플레이어 채팅 비활성화", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_INTERACT")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("플레이어 상호작용 비활성화")) {
                            player.sendMessage("§cDISABLE_PLAYER_INTERACT is already true");
                        } else {
                            player.sendMessage("§aDISABLE_PLAYER_INTERACT == TRUE");
                            config.config.set("플레이어 상호작용 비활성화", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("플레이어 상호작용 비활성화")) {
                            player.sendMessage("§cDISABLE_PLAYER_INTERACT is already false");
                        } else {
                            player.sendMessage("§aDISABLE_PLAYER_INTERACT == FALSE");
                            config.config.set("플레이어 상호작용 비활성화", false);
                            config.save();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_DAMAGE")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (config.config.getBoolean("플레이어 무적")) {
                            player.sendMessage("§cDISABLE_PLAYER_DAMAGE is already true");
                        } else {
                            player.sendMessage("§aDISABLE_PLAYER_DAMAGE == TRUE");
                            config.config.set("플레이어 무적", true);
                            config.save();
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!config.config.getBoolean("플레이어 무적")) {
                            player.sendMessage("§cDISABLE_PLAYER_DAMAGE is already false");
                        } else {
                            player.sendMessage("§aDISABLE_PLAYER_DAMAGE == FALSE");
                            config.config.set("플레이어 무적", false);
                            config.save();
                        }
                    }
                } else {
                    player.sendMessage("§6/manage set <string> <boolean> §7- Manage플러그인의 Config를 명령어로 수정합니다.");
                }
            }
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[1].equalsIgnoreCase("FIRST_JOIN")) {
                    if (args[2].equalsIgnoreCase("MESSAGE")) {
                        player.sendMessage("§a성공적으로 '첫 접속'유저에 대한 입장 문장이 변경되었습니다. §c(띄어쓰기를 하려면 %_%를 입력하세요!)");
                        player.sendMessage("기존 문장 : " + config.config.getString("첫접속.입장"));
                        config.config.set("첫접속.입장", args[3].replace("%_%", " "));
                        config.save();
                        player.sendMessage("변경된 문장 : " + config.config.getString("첫접속.입장"));
                    } else if (args[2].equalsIgnoreCase("FIREWORK")) {
                        if (args[3].equalsIgnoreCase("true")) {
                            if (config.config.getBoolean("첫접속.폭죽")) {
                                player.sendMessage("§cFIRST_JOIN_FIREWORK is already true");
                            } else {
                                player.sendMessage("§aFIRST_JOIN_FIREWORK == TRUE");
                                config.config.set("첫접속.폭죽", true);
                                config.save();
                            }
                        } else if (args[3].equalsIgnoreCase("false")) {
                            if (!config.config.getBoolean("첫접속.폭죽")) {
                                player.sendMessage("§cFIRST_JOIN_FIREWORK is already false");
                            } else {
                                player.sendMessage("§aFIRST_JOIN_FIREWORK == FALSE");
                                config.config.set("첫접속.폭죽", false);
                                config.save();
                            }
                        }
                    } else if (args[2].equalsIgnoreCase("SOUND")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (config.config.getBoolean("첫접속.소리.활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_SOUND_ENABLE is already true");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_SOUND_ENABLE == TRUE");
                                    config.config.set("첫접속.소리.활성화", true);
                                    config.save();
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!config.config.getBoolean("첫접속.소리.활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_SOUND_ENABLE is already false");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_SOUND_ENABLE == FALSE");
                                    config.config.set("첫접속.소리.활성화", false);
                                    config.save();
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("SET_SOUND")) {
                            player.sendMessage("§a성공적으로 '첫 접속'유저에 대한 노래가 변경되었습니다.");
                            player.sendMessage("기존 문장 : " + config.config.getString("첫접속.소리.사운드"));
                            config.config.set("첫접속.소리.사운드", args[4]);
                            config.save();
                            player.sendMessage("변경된 문장 : " + config.config.getString("첫접속.소리.사운드"));
                        }
                    }
                }
            }
        } else {
            player.sendMessage("§7---------- §3Manage 플러그인 명령어 §7----------");
            player.sendMessage("§6/manage reload §7- Manage플러그인의 Config를 리로드합니다.");
            player.sendMessage("§6/manage set <string> <boolean> §7- Manage플러그인의 Config를 명령어로 수정합니다.");
            player.sendMessage("§6/manage setJoinQuit <string> §7- Manage플러그인의 Join/Quit을 명령어로 수정합니다. §c(띄어쓰기를 하려면 %_%를 입력하세요!)");
            player.sendMessage("§6/manage debug §7- Manage플러그인의 디버그를 실행합니다.");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            List<String> matches;
            arguments.add("reload");
            arguments.add("debug");
            arguments.add("set");
            arguments.add("setJoinQuit");
            matches = arguments.stream().filter(val -> val.startsWith(args[0])).collect(Collectors.toList());

            return matches;
        }

        if (args.length == 2) {
            List<String> arguments = new ArrayList<>();
            List<String> matches;
            if (args[0].equalsIgnoreCase("set")) {
                arguments.add("IGNORE_ENTITY_SPAWN");
                arguments.add("DISABLE_WEATHER");
                arguments.add("FREEZE_TIME");
                arguments.add("IGNORE_ITEM_DROP");
                arguments.add("IGNORE_ITEM_PICKUP");
                arguments.add("IGNORE_BLOCK_BREAK");
                arguments.add("IGNORE_BLOCK_PLACE");
                arguments.add("DISABLE_PLAYER_HUNGER");
                arguments.add("DISABLE_PVP");
                arguments.add("DISABLE_CHAT");
                arguments.add("DISABLE_PLAYER_INTERACT");
                arguments.add("DISABLE_PLAYER_DAMAGE");
                matches = arguments.stream().filter(val -> val.startsWith(args[1])).collect(Collectors.toList());

                return matches;
            }
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                arguments.add("FIRST_JOIN");
                arguments.add("JOIN");
                arguments.add("QUIT");
                matches = arguments.stream().filter(val -> val.startsWith(args[1])).collect(Collectors.toList());

                return matches;
            }
        }

        if (args.length == 3) {
            List<String> arguments = new ArrayList<>();
            List<String> matches;
            if (args[0].equalsIgnoreCase("set")) {
                arguments.add("true");
                arguments.add("false");
                matches = arguments.stream().filter(val -> val.startsWith(args[2])).collect(Collectors.toList());

                return matches;
            }
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[1].equalsIgnoreCase("QUIT")) {
                    arguments.add("MESSAGE");
                    matches = arguments.stream().filter(val -> val.startsWith(args[2])).collect(Collectors.toList());

                    return matches;
                }
                if (args[1].equalsIgnoreCase("FIRST_JOIN")) {
                    arguments.add("SOUND");
                    arguments.add("FIREWORK");
                    arguments.add("MESSAGE");
                    arguments.add("COMMAND");
                    matches = arguments.stream().filter(val -> val.startsWith(args[2])).collect(Collectors.toList());

                    return matches;
                }
                if (args[1].equalsIgnoreCase("JOIN")) {
                    arguments.add("SOUND");
                    arguments.add("FIREWORK");
                    arguments.add("MESSAGE");
                    arguments.add("COMMAND");
                    matches = arguments.stream().filter(val -> val.startsWith(args[2])).collect(Collectors.toList());

                    return matches;
                }
            }
        }

        if (args.length == 4) {
            List<String> arguments = new ArrayList<>();
            List<String> matches;
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[2].equalsIgnoreCase("MESSAGE")) {
                    arguments.add("[<string>]");
                    matches = arguments.stream().filter(val -> val.startsWith(args[3])).collect(Collectors.toList());

                    return matches;
                }
                if (args[2].equalsIgnoreCase("FIREWORK")) {
                    arguments.add("true");
                    arguments.add("false");
                    matches = arguments.stream().filter(val -> val.startsWith(args[3])).collect(Collectors.toList());

                    return matches;
                }
                if (args[2].equalsIgnoreCase("SOUND")) {
                    arguments.add("ENABLE");
                    arguments.add("SET_SOUND");
                    matches = arguments.stream().filter(val -> val.startsWith(args[3])).collect(Collectors.toList());

                    return matches;
                }
                if (args[2].equalsIgnoreCase("COMMAND")) {
                    arguments.add("ENABLE");
                    arguments.add("SET_COMMAND");
                    matches = arguments.stream().filter(val -> val.startsWith(args[3])).collect(Collectors.toList());

                    return matches;
                }
            }
        }

        if (args.length == 5) {
            List<String> arguments = new ArrayList<>();
            List<String> matches = new ArrayList<>();
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[3].equalsIgnoreCase("ENABLE")) {
                    arguments.add("true");
                    arguments.add("false");
                    matches = arguments.stream().filter(val -> val.startsWith(args[4])).collect(Collectors.toList());

                    return matches;
                }
                if (args[3].equalsIgnoreCase("SET_SOUND")) {
                    for (Sound a : Sound.values()) {
                        arguments.add(a.name());
                        matches = arguments.stream().filter(val -> val.startsWith(args[4])).collect(Collectors.toList());
                    }
                    return matches;
                }
                if (args[3].equalsIgnoreCase("SET_COMMAND")) {
                    arguments.add("[<string>]");
                    matches = arguments.stream().filter(val -> val.startsWith(args[4])).collect(Collectors.toList());

                    return matches;
                }
            }
        }
        return null;
    }
}
