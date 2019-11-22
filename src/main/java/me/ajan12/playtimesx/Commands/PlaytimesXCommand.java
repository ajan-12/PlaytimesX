package me.ajan12.PlaytimesX.Commands;

import me.ajan12.PlaytimesX.Objects.PlayerProfile;
import me.ajan12.PlaytimesX.PlaytimesX;
import me.ajan12.PlaytimesX.Utils.ConfigManager;
import me.ajan12.PlaytimesX.Utils.DataStorage;
import me.ajan12.PlaytimesX.Utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlaytimesXCommand implements CommandExecutor {

    private static PluginCommand cmd;

    public PlaytimesXCommand(final PlaytimesX main) {
        cmd = main.getCommand("playtimesx");
        if (cmd == null) return;
        cmd.setExecutor(this);
        cmd.setDescription(ConfigManager.Messages.COMMANDS__PLAYTIMESX__DESCRIPTION.getMessage());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("playtimesx.command.main")) {
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PERMISSION_ERROR.getMessage());
                return true;
            }
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__RELOAD_START.getMessage());
                final long beginningReload = System.currentTimeMillis();
                PlaytimesX.getInstance().reload();
                final long endingReload = System.currentTimeMillis();
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__RELOAD_END.getMessage()
                        .replaceAll("%TIME%", String.valueOf(endingReload - beginningReload)));
                return true;
            } else if (args[0].equalsIgnoreCase("display-profiles")) {
                if (PlayerProfile.getProfiles().isEmpty()) {
                    sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_NOT_FOUND.getMessage());
                    return true;
                }

                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_HEADER.getMessage()
                        .replaceAll("%AMOUNT%", String.valueOf(DataStorage.instance.getProfiles().size())));

                final PlayerProfile[] page = PlayerProfile.getProfiles().get(0);
                for (final PlayerProfile playerProfile : page) {
                    if (playerProfile == null) break;
                    sender.sendMessage(playerProfile.toString());
                }

                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_FOOTER.getMessage()
                        .replaceAll("%CURRENT-PAGE%", "1")
                        .replaceAll("%TOTAL-PAGES%", String.valueOf(PlayerProfile.getProfiles().size())));
                return true;
            } else if (args[0].equalsIgnoreCase("display-profile")) {
                if (sender instanceof Player) {
                    final PlayerProfile profile = DataStorage.instance.getProfiles().get(((Player) sender).getUniqueId());
                    if (profile == null) {
                        sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PLAYER_NOT_FOUND.getMessage());
                        return true;
                    }

                    sender.sendMessage(profile.toString());
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("recalculate-rankup")) {
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__RECALCULATE_RANKUP_START.getMessage());
                final long beginningRecalculate = System.currentTimeMillis();
                final long endingRecalculate = System.currentTimeMillis();
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__RECALCULATE_RANKUP_END.getMessage()
                        .replaceAll("%TIME%", String.valueOf(endingRecalculate - beginningRecalculate))
                        .replaceAll("%NEXT-RANKUP%", String.valueOf(DataStorage.instance.getRankUpInterval())));
                return true;
            } else if (args[0].equalsIgnoreCase("next-rankup")) {
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__NEXT_RANKUP.getMessage()
                        .replaceAll("%RANKUP%", String.valueOf(DataStorage.instance.getRankUpInterval())));
                return true;
            }

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("display-profiles")) {
                if (args[1].matches("^[0-9]*$")) {
                    if (PlayerProfile.getProfiles().isEmpty()) {
                        sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_NOT_FOUND.getMessage());
                        return true;
                    }

                    sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_HEADER.getMessage()
                            .replaceAll("%AMOUNT%", String.valueOf(DataStorage.instance.getProfiles().size())));

                    final int pageNumber = Integer.parseInt(args[1]);
                    final PlayerProfile[] page = PlayerProfile.getProfiles().get(pageNumber);
                    for (final PlayerProfile playerProfile : page) {
                        sender.sendMessage(playerProfile.toString());
                    }

                    sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_FOOTER.getMessage()
                            .replaceAll("%CURRENT-PAGE%", args[1])
                            .replaceAll("%TOTAL-PAGES%", String.valueOf(PlayerProfile.getProfiles().size())));
                    return true;
                }


                PlayerProfile profile = null;
                if (args[0].matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) {
                    profile = DataStorage.instance.getProfiles().get(UUID.fromString(args[0]));
                } else {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) profile = DataStorage.instance.getProfiles().get(target.getUniqueId());
                }

                if (profile == null) {
                    sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PLAYER_NOT_FOUND.getMessage());
                    return true;
                }

                sender.sendMessage(profile.toString());
                return true;
            } else if (args[0].equalsIgnoreCase("display-profile")) {
                PlayerProfile profile = null;
                if (args[0].matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) {
                    profile = DataStorage.instance.getProfiles().get(UUID.fromString(args[0]));
                } else {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) profile = DataStorage.instance.getProfiles().get(target.getUniqueId());
                }

                if (profile == null) {
                    sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIMESX__PLAYER_NOT_FOUND.getMessage());
                    return true;
                }

                sender.sendMessage(profile.toString());
                return true;
            }
        }
        return false;
    }

    public static PluginCommand getCmd()                  { return cmd;           }
    public static void          setCmd(PluginCommand cmd) { PlaytimesXCommand.cmd = cmd; }
}
