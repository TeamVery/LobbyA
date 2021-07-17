package com.teamverymc.manage.cmd;

import com.teamverymc.configframework.cfg;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.teamverymc.manage.main.*;

public class settings implements CommandExecutor, TabExecutor {

    private void timelock(Player player) {
        if (cfg.get(p, c).getBoolean("시간 고정.활성화")) {
            for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                if (Bukkit.getWorld(worlds) != null) {
                    Objects.requireNonNull(Bukkit.getWorld(worlds)).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                } else {
                    System.out.println(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                    player.sendMessage(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                }
            }
        } else {
            for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                if (Bukkit.getWorld(worlds) != null) {
                    Objects.requireNonNull(Bukkit.getWorld(worlds)).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                } else {
                    System.out.println(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                    player.sendMessage(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                }
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player player)) {
            System.out.println("Comming Soon");

            return false;
        }

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("RELOAD CONFIRM")));

                if (cfg.get(p, c).getBoolean("시간 고정.활성화")) {
                    for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                        if (Bukkit.getWorld(worlds) != null) {
                            Objects.requireNonNull(Bukkit.getWorld(worlds)).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                        } else {
                            System.out.println(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                            player.sendMessage(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                        }
                    }
                } else {
                    for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                        if (Bukkit.getWorld(worlds) != null) {
                            Objects.requireNonNull(Bukkit.getWorld(worlds)).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                        } else {
                            System.out.println(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                            player.sendMessage(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                        }
                    }
                }
            }
            if (args[0].equalsIgnoreCase("debug")) {

                player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DEBUG")));
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("IGNORE_ENTITY_SPAWN")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("엔티티 소환 차단")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ENTITY_SPAWN Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ENTITY_SPAWN True")));
                            cfg.get(p, c).set("엔티티 소환 차단", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("엔티티 소환 차단")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ENTITY_SPAWN Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ENTITY_SPAWN False")));
                            cfg.get(p, c).set("엔티티 소환 차단", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_WEATHER")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("날씨 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_WEATHER Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_WEATHER True")));
                            cfg.get(p, c).set("날씨 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("날씨 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_WEATHER Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_WEATHER False")));
                            cfg.get(p, c).set("날씨 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("FREEZE_TIME")) {
                    if (args[2].equalsIgnoreCase("Enable")) {
                        if (args[3].equalsIgnoreCase("true")) {
                            if (cfg.get(p, c).getBoolean("시간 고정.활성화")) {
                                player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("FREEZE_TIME Already True")));
                            } else {
                                player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("FREEZE_TIME True")));
                                cfg.get(p, c).set("시간 고정.활성화", true);
                                cfg.save(p, c, true);

                                timelock(player);
                            }
                        } else if (args[3].equalsIgnoreCase("false")) {
                            if (!cfg.get(p, c).getBoolean("시간 고정.활성화")) {
                                player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("FREEZE_TIME Already False")));
                            } else {
                                player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("FREEZE_TIME False")));
                                cfg.get(p, c).set("시간 고정.활성화", false);
                                cfg.save(p, c, true);

                                timelock(player);
                            }
                        }
                    } else if (args[2].equalsIgnoreCase("Worlds")) {
                        if (args[3].equalsIgnoreCase("add")) {
//                            for (Object worlds : Bukkit.getWorlds().toArray()) {
//                                if (worlds.toString().matches(args[4])) {
                            List<String> l = cfg.get(p, c).getStringList("시간 고정.월드");
                            l.add(args[4]);
                            cfg.get(p, c).set("시간 고정.월드", l);
                            player.sendMessage(args[4] + "가 성공적으로 추가되었습니다.");
                            player.sendMessage(ChatColor.RED + "변경 사항을 저장하려면 /manage reload 를 입력해주세요.");
                            cfg.save(p, c, true);
//                                }
//                            }
                        } else if (args[3].equalsIgnoreCase("remove")) {
                            for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                                if (worlds.matches(args[4])) {
                                    List<String> l = cfg.get(p, c).getStringList("시간 고정.월드");
                                    l.remove(args[4]);
                                    cfg.get(p, c).set("시간 고정.월드", l);
                                    player.sendMessage(args[4] + "가 성공적으로 제거되었습니다.");
                                    player.sendMessage(ChatColor.RED + "변경 사항을 저장하려면 /manage reload 를 입력해주세요.");
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("list")) {
                            player.sendMessage("현재 시간고정에 영향을 받는 월드는 다음과 같습니다");
                            for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                                if (Bukkit.getWorld(worlds) == null) {
                                    player.sendMessage(ChatColor.RED + "§m" + worlds);
                                } else {
                                    player.sendMessage(ChatColor.GREEN + worlds);
                                }
                            }
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_ITEM_DROP")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("아이템 드랍 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_DROP Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_DROP True")));
                            cfg.get(p, c).set("아이템 드랍 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("아이템 드랍 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_DROP Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_DROP False")));
                            cfg.get(p, c).set("아이템 드랍 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_ITEM_PICKUP")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("아이템 획득 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_PICKUP Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_PICKUP True")));
                            cfg.get(p, c).set("아이템 획득 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("아이템 획득 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_PICKUP Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_ITEM_PICKUP False")));
                            cfg.get(p, c).set("아이템 획득 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_BLOCK_BREAK")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("블럭 파괴금지")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_BREAK Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_BREAK True")));
                            cfg.get(p, c).set("블럭 파괴금지", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("블럭 파괴금지")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_BREAK Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_BREAK False")));
                            cfg.get(p, c).set("블럭 파괴금지", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("IGNORE_BLOCK_PLACE")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("블럭 설치금지")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_PLACE Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_PLACE True")));
                            cfg.get(p, c).set("블럭 설치금지", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("블럭 설치금지")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_PLACE Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("IGNORE_BLOCK_PLACE False")));
                            cfg.get(p, c).set("블럭 설치금지", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_HUNGER")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("플레이어 허기 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_HUNGER Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_HUNGER True")));
                            cfg.get(p, c).set("플레이어 허기 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("플레이어 허기 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_HUNGER Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_HUNGER False")));
                            cfg.get(p, c).set("플레이어 허기 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PVP")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("플레이어 PVP 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PVP Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PVP True")));
                            cfg.get(p, c).set("플레이어 PVP 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("플레이어 PVP 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PVP Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PVP False")));
                            cfg.get(p, c).set("플레이어 PVP 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_CHAT")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("플레이어 채팅 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_CHAT Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_CHAT True")));
                            cfg.get(p, c).set("플레이어 채팅 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("플레이어 채팅 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_CHAT Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_CHAT False")));
                            cfg.get(p, c).set("플레이어 채팅 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_INTERACT")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("플레이어 상호작용 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_INTERACT Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_INTERACT True")));
                            cfg.get(p, c).set("플레이어 상호작용 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("플레이어 상호작용 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_INTERACT Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_INTERACT False")));
                            cfg.get(p, c).set("플레이어 상호작용 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_DAMAGE")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("플레이어 무적")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_DAMAGE Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_DAMAGE True")));
                            cfg.get(p, c).set("플레이어 무적", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("플레이어 무적")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_DAMAGE Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_DAMAGE False")));
                            cfg.get(p, c).set("플레이어 무적", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_PLAYER_MOVEMENT")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("플레이어 움직임 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_MOVEMENT Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_MOVEMENT True")));
                            cfg.get(p, c).set("플레이어 움직임 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("플레이어 움직임 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_MOVEMENT Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_PLAYER_MOVEMENT False")));
                            cfg.get(p, c).set("플레이어 움직임 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("DISABLE_INVENTORY_INTERACT")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        if (cfg.get(p, c).getBoolean("인벤토리 상호작용 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_INVENTORY_INTERACT Already True")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_INVENTORY_INTERACT True")));
                            cfg.get(p, c).set("인벤토리 상호작용 비활성화", true);
                            cfg.save(p, c, true);
                        }
                    } else if (args[2].equalsIgnoreCase("false")) {
                        if (!cfg.get(p, c).getBoolean("인벤토리 상호작용 비활성화")) {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_INVENTORY_INTERACT Already False")));
                        } else {
                            player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("DISABLE_INVENTORY_INTERACT False")));
                            cfg.get(p, c).set("인벤토리 상호작용 비활성화", false);
                            cfg.save(p, c, true);
                        }
                    }
                } else {
                    player.sendMessage(Objects.requireNonNull(cfg.get(p, m).getString("Info.MANAGE_SET")));
                }
            }

            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[1].equalsIgnoreCase("QUIT")) {
                    if (args[2].equalsIgnoreCase("MESSAGE")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("재접속.퇴장 메시지.활성화")) {
                                    player.sendMessage("§cQUIT_MESSAGE is already true");
                                } else {
                                    player.sendMessage("§aQUIT_MESSAGE == TRUE");
                                    cfg.get(p, c).set("재접속.퇴장 메시지.활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("재접속.퇴장 메시지.활성화")) {
                                    player.sendMessage("§cQUIT_MESSAGE is already false");
                                } else {
                                    player.sendMessage("§aQUIT_MESSAGE == FALSE");
                                    cfg.get(p, c).set("재접속.퇴장 메시지.활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("CONTENTS")) {
                            StringBuilder M = null;
                            for (int i = 4; i <= args.length - 1; i++) {
                                if (M == null) {
                                    M = new StringBuilder(args[i]);
                                } else {
                                    M.append(" ").append(args[i]);
                                }
                            }
                            player.sendMessage("§a성공적으로 '로그아웃'하는 유저에 대한 퇴장 문장이 변경되었습니다.");
                            player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("재접속.퇴장 메시지.내용"));
                            cfg.get(p, c).set("재접속.퇴장 메시지.내용", Objects.requireNonNull(M).toString());
                            cfg.save(p, c, true);
                            player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("재접속.퇴장 메시지.내용"));
                        }
                    }
                }

                if (args[1].equalsIgnoreCase("FIRST_JOIN")) {
                    if (args[2].equalsIgnoreCase("MESSAGE")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("첫접속.입장 메시지.활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_MESSAGE is already true");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_MESSAGE == TRUE");
                                    cfg.get(p, c).set("첫접속.입장 메시지.활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("첫접속.입장 메시지.활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_MESSAGE is already false");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_MESSAGE == FALSE");
                                    cfg.get(p, c).set("첫접속.입장 메시지.활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("CONTENTS")) {
                            StringBuilder M = null;
                            for (int i = 4; i <= args.length - 1; i++) {
                                if (M == null) {
                                    M = new StringBuilder(args[i]);
                                } else {
                                    M.append(" ").append(args[i]);
                                }
                            }
                            player.sendMessage("§a성공적으로 '첫 접속'유저에 대한 입장 문장이 변경되었습니다.");
                            player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("첫접속.입장 메시지.내용"));
                            cfg.get(p, c).set("첫접속.입장 메시지.내용", Objects.requireNonNull(M).toString());
                            cfg.save(p, c, true);
                            player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("첫접속.입장 메시지.내용"));
                        }
                    } else if (args[2].equalsIgnoreCase("FIREWORK")) {
                        if (args[3].equalsIgnoreCase("true")) {
                            if (cfg.get(p, c).getBoolean("첫접속.폭죽")) {
                                player.sendMessage("§cFIRST_JOIN_FIREWORK is already true");
                            } else {
                                player.sendMessage("§aFIRST_JOIN_FIREWORK == TRUE");
                                cfg.get(p, c).set("첫접속.폭죽", true);
                                cfg.save(p, c, true);
                            }
                        } else if (args[3].equalsIgnoreCase("false")) {
                            if (!cfg.get(p, c).getBoolean("첫접속.폭죽")) {
                                player.sendMessage("§cFIRST_JOIN_FIREWORK is already false");
                            } else {
                                player.sendMessage("§aFIRST_JOIN_FIREWORK == FALSE");
                                cfg.get(p, c).set("첫접속.폭죽", false);
                                cfg.save(p, c, true);
                            }
                        }
                    } else if (args[2].equalsIgnoreCase("SOUND")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("첫접속.소리.활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_SOUND_ENABLE is already true");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_SOUND_ENABLE == TRUE");
                                    cfg.get(p, c).set("첫접속.소리.활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("첫접속.소리.활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_SOUND_ENABLE is already false");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_SOUND_ENABLE == FALSE");
                                    cfg.get(p, c).set("첫접속.소리.활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("SET_SOUND")) {
                            for (Sound a : Sound.values()) {
                                if (a.toString().matches(args[4])) {
                                    player.sendMessage("§a성공적으로 '첫 접속'유저에 대한 노래가 변경되었습니다.");
                                    player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("첫접속.소리.사운드"));
                                    cfg.get(p, c).set("첫접속.소리.사운드", args[4]);
                                    cfg.save(p, c, true);
                                    player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("첫접속.소리.사운드"));
                                }
                            }
                        }
                    } else if (args[2].equalsIgnoreCase("COMMAND")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("첫접속.명령어(콘솔).활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_CONSOLE_COMMAND_ENABLE is already true");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_CONSOLE_COMMAND_ENABLE == TRUE");
                                    cfg.get(p, c).set("첫접속.명령어(콘솔).활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("첫접속.명령어(콘솔).활성화")) {
                                    player.sendMessage("§cFIRST_JOIN_CONSOLE_COMMAND_ENABLE is already false");
                                } else {
                                    player.sendMessage("§aFIRST_JOIN_CONSOLE_COMMAND_ENABLE == FALSE");
                                    cfg.get(p, c).set("첫접속.명령어(콘솔).활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("SET_COMMAND")) {
                            StringBuilder M = null;
                            for (int i = 4; i <= args.length - 1; i++) {
                                if (M == null) {
                                    M = new StringBuilder(args[i]);
                                } else {
                                    M.append(" ").append(args[i]);
                                }
                            }
                            player.sendMessage("§a성공적으로 '첫 접속'유저에 대한 명령어가 변경되었습니다.");
                            player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("첫접속.명령어(콘솔).명령어"));
                            cfg.get(p, c).set("첫접속.명령어(콘솔).명령어", Objects.requireNonNull(M).toString());
                            cfg.save(p, c, true);
                            player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("첫접속.명령어(콘솔).명령어"));
                        }
                    }
                }

                if (args[1].equalsIgnoreCase("JOIN")) {
                    if (args[2].equalsIgnoreCase("MESSAGE")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("재접속.입장 메시지.활성화")) {
                                    player.sendMessage("§cJOIN_MESSAGE is already true");
                                } else {
                                    player.sendMessage("§aJOIN_MESSAGE == TRUE");
                                    cfg.get(p, c).set("재접속.입장 메시지.활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("재접속.입장 메시지.활성화")) {
                                    player.sendMessage("§cJOIN_MESSAGE is already false");
                                } else {
                                    player.sendMessage("§aJOIN_MESSAGE == FALSE");
                                    cfg.get(p, c).set("재접속.입장 메시지.활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("CONTENTS")) {
                            StringBuilder M = null;
                            for (int i = 4; i <= args.length - 1; i++) {
                                if (M == null) {
                                    M = new StringBuilder(args[i]);
                                } else {
                                    M.append(" ").append(args[i]);
                                }
                            }
                            player.sendMessage("§a성공적으로 '재접속'유저에 대한 입장 문장이 변경되었습니다.");
                            player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("재접속.입장 메시지.내용"));
                            cfg.get(p, c).set("재접속.입장 메시지.내용", Objects.requireNonNull(M).toString());
                            cfg.save(p, c, true);
                            player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("재접속.입장 메시지.내용"));
                        }
                    } else if (args[2].equalsIgnoreCase("FIREWORK")) {
                        if (args[3].equalsIgnoreCase("true")) {
                            if (cfg.get(p, c).getBoolean("재접속.폭죽")) {
                                player.sendMessage("§cJOIN_FIREWORK is already true");
                            } else {
                                player.sendMessage("§aJOIN_FIREWORK == TRUE");
                                cfg.get(p, c).set("재접속.폭죽", true);
                                cfg.save(p, c, true);
                            }
                        } else if (args[3].equalsIgnoreCase("false")) {
                            if (!cfg.get(p, c).getBoolean("재접속.폭죽")) {
                                player.sendMessage("§cJOIN_FIREWORK is already false");
                            } else {
                                player.sendMessage("§aJOIN_FIREWORK == FALSE");
                                cfg.get(p, c).set("재접속.폭죽", false);
                                cfg.save(p, c, true);
                            }
                        }
                    } else if (args[2].equalsIgnoreCase("SOUND")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("재접속.소리.활성화")) {
                                    player.sendMessage("§cJOIN_SOUND_ENABLE is already true");
                                } else {
                                    player.sendMessage("§aJOIN_SOUND_ENABLE == TRUE");
                                    cfg.get(p, c).set("재접속.소리.활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("재접속.소리.활성화")) {
                                    player.sendMessage("§cJOIN_SOUND_ENABLE is already false");
                                } else {
                                    player.sendMessage("§aJOIN_SOUND_ENABLE == FALSE");
                                    cfg.get(p, c).set("재접속.소리.활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("SET_SOUND")) {
                            for (Sound a : Sound.values()) {
                                if (a.toString().matches(args[4])) {
                                    player.sendMessage("§a성공적으로 '재접속'유저에 대한 노래가 변경되었습니다.");
                                    player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("재접속.소리.사운드"));
                                    cfg.get(p, c).set("재접속.소리.사운드", args[4]);
                                    cfg.save(p, c, true);
                                    player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("재접속.소리.사운드"));
                                }
                            }
                        }
                    } else if (args[2].equalsIgnoreCase("COMMAND")) {
                        if (args[3].equalsIgnoreCase("ENABLE")) {
                            if (args[4].equalsIgnoreCase("true")) {
                                if (cfg.get(p, c).getBoolean("재접속.명령어(콘솔).활성화")) {
                                    player.sendMessage("§cJOIN_CONSOLE_COMMAND_ENABLE is already true");
                                } else {
                                    player.sendMessage("§aJOIN_CONSOLE_COMMAND_ENABLE == TRUE");
                                    cfg.get(p, c).set("재접속.명령어(콘솔).활성화", true);
                                    cfg.save(p, c, true);
                                }
                            } else if (args[4].equalsIgnoreCase("false")) {
                                if (!cfg.get(p, c).getBoolean("재접속.명령어(콘솔).활성화")) {
                                    player.sendMessage("§cJOIN_CONSOLE_COMMAND_ENABLE is already false");
                                } else {
                                    player.sendMessage("§aJOIN_CONSOLE_COMMAND_ENABLE == FALSE");
                                    cfg.get(p, c).set("재접속.명령어(콘솔).활성화", false);
                                    cfg.save(p, c, true);
                                }
                            }
                        } else if (args[3].equalsIgnoreCase("SET_COMMAND")) {
                            StringBuilder M = null;
                            for (int i = 4; i <= args.length - 1; i++) {
                                if (M == null) {
                                    M = new StringBuilder(args[i]);
                                } else {
                                    M.append(" ").append(args[i]);
                                }
                            }
                            player.sendMessage("§a성공적으로 '재접속'유저에 대한 명령어가 변경되었습니다.");
                            player.sendMessage("기존 문장 : " + cfg.get(p, c).getString("재접속.명령어(콘솔).명령어"));
                            cfg.get(p, c).set("재접속.명령어(콘솔).명령어", Objects.requireNonNull(M).toString());
                            cfg.save(p, c, true);
                            player.sendMessage("변경된 문장 : " + cfg.get(p, c).getString("재접속.명령어(콘솔).명령어"));
                        }
                    }
                }
            }
        } else {
            for (String sendList : cfg.get(p, m).getStringList("Help")) {
                player.sendMessage(sendList);
            }
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
            if (sender instanceof Player) {
                arguments.add("gui");
            }
            matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());

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
                arguments.add("DISABLE_PLAYER_MOVEMENT");
                arguments.add("DISABLE_INVENTORY_INTERACT");
                matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[1].toLowerCase())).collect(Collectors.toList());

                return matches;
            }
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                arguments.add("FIRST_JOIN");
                arguments.add("JOIN");
                arguments.add("QUIT");
                matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[1].toLowerCase())).collect(Collectors.toList());

                return matches;
            }
        }

        if (args.length == 3) {
            List<String> arguments = new ArrayList<>();
            List<String> matches;
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("FREEZE_TIME")) {
                    arguments.add("ENABLE");
                    arguments.add("WORLDS");
                } else {
                    arguments.add("true");
                    arguments.add("false");
                }
                matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[2].toLowerCase())).collect(Collectors.toList());
                return matches;
            }
                if (args[0].equalsIgnoreCase("setJoinQuit")) {
                    if (args[1].equalsIgnoreCase("QUIT")) {
                        arguments.add("MESSAGE");
                        matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[2].toLowerCase())).collect(Collectors.toList());

                        return matches;
                    }
                    if (args[1].equalsIgnoreCase("FIRST_JOIN")) {
                        arguments.add("SOUND");
                        arguments.add("FIREWORK");
                        arguments.add("MESSAGE");
                        arguments.add("COMMAND");
                        matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[2].toLowerCase())).collect(Collectors.toList());

                        return matches;
                    }
                    if (args[1].equalsIgnoreCase("JOIN")) {
                        arguments.add("SOUND");
                        arguments.add("FIREWORK");
                        arguments.add("MESSAGE");
                        arguments.add("COMMAND");
                        matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[2].toLowerCase())).collect(Collectors.toList());

                        return matches;
                    }
                }
            }

        if (args.length == 4) {
            List<String> arguments = new ArrayList<>();
            List<String> matches;
            if (args[1].equalsIgnoreCase("FREEZE_TIME")) {
                if (args[2].equalsIgnoreCase("ENABLE")) {
                    arguments.add("true");
                    arguments.add("false");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[3].toLowerCase())).collect(Collectors.toList());

                    return matches;
                } else if (args[2].equalsIgnoreCase("WORLDS")) {
                    arguments.add("add");
                    arguments.add("remove");
                    arguments.add("list");

                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[3].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
            }
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[2].equalsIgnoreCase("MESSAGE")) {
                    arguments.add("ENABLE");
                    arguments.add("CONTENTS");

                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[3].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
                if (args[2].equalsIgnoreCase("FIREWORK")) {
                    arguments.add("true");
                    arguments.add("false");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[3].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
                if (args[2].equalsIgnoreCase("SOUND")) {
                    arguments.add("ENABLE");
                    arguments.add("SET_SOUND");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[3].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
                if (args[2].equalsIgnoreCase("COMMAND")) {
                    arguments.add("ENABLE");
                    arguments.add("SET_COMMAND");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[3].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
            }
        }

        if (args.length == 5) {
            List<String> arguments = new ArrayList<>();
            List<String> matches = new ArrayList<>();
            if (args[2].equalsIgnoreCase("WORLDS")) {
                if (args[3].equalsIgnoreCase("add")) {
                    for (World worlds : Bukkit.getWorlds()) {
                        arguments.add(worlds.getName());

                        matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[4].toLowerCase())).collect(Collectors.toList());

                    }
                    return matches;
                } else if (args[3].equalsIgnoreCase("remove")) {
                    for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                        arguments.add(worlds);

                        matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[4].toLowerCase())).collect(Collectors.toList());

                    }
                    return matches;
                }
            }
            if (args[0].equalsIgnoreCase("setJoinQuit")) {
                if (args[3].equalsIgnoreCase("ENABLE")) {
                    arguments.add("true");
                    arguments.add("false");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[4].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
                if (args[3].equalsIgnoreCase("SET_SOUND")) {
                    for (Sound a : Sound.values()) {
                        arguments.add(a.name());
                        matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[4].toLowerCase())).collect(Collectors.toList());
                    }
                    return matches;
                }
                if (args[3].equalsIgnoreCase("SET_COMMAND")) {
                    arguments.add("[<string>]");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[4].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
                if (args[3].equalsIgnoreCase("CONTENTS")) {
                    arguments.add("[<string>]");
                    matches = arguments.stream().filter(val -> val.toLowerCase().startsWith(args[4].toLowerCase())).collect(Collectors.toList());

                    return matches;
                }
            }
        }
        return null;
    }
}
