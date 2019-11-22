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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Playtime implements CommandExecutor {

    private static PluginCommand cmd;

    public Playtime(final PlaytimesX main) {
        cmd = main.getCommand("playtime");
        if (cmd == null) return;
        cmd.setExecutor(this);
        cmd.setDescription(ConfigManager.Messages.COMMANDS__PLAYTIME__DESCRIPTION.getMessage());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        PlayerProfile profile = null;

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (args.length == 0) {
                profile = DataStorage.instance.getProfiles().get(player.getUniqueId());

            } else if (args.length == 1) {
                if (args[0].matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) {
                    profile = DataStorage.instance.getProfiles().get(UUID.fromString(args[0]));
                } else {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        profile = DataStorage.instance.getProfiles().get(target.getUniqueId());
                    } else {
                        for (final PlayerProfile playerProfile : DataStorage.instance.getProfiles().values()) {
                            if (playerProfile.getName().equalsIgnoreCase(args[0])) profile = playerProfile;
                        }
                    }
                }
            } else {
                return false;
            }

        } else {
            if (args.length == 1) {
                if (args[0].matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) {
                    profile = DataStorage.instance.getProfiles().get(UUID.fromString(args[0]));
                } else {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        profile = DataStorage.instance.getProfiles().get(target.getUniqueId());
                    } else {
                        for (final PlayerProfile playerProfile : DataStorage.instance.getProfiles().values()) {
                            if (playerProfile.getName().equalsIgnoreCase(args[0])) profile = playerProfile;
                        }
                    }
                }
            } else {
                return false;
            }
        }

        if (profile == null) {
            sender.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIME__PLAYER_NOT_FOUND.getMessage());
            return true;
        }

        return process(
                sender,
                profile.getName(),
                profile.getTicks(),
                profile.getFirstPlayed(),
                profile.getLastPlayed(),
                profile.isPlus()
        );
    }

    private boolean process(final CommandSender receiver, final String name, final int ticks, final long first, final long last, final boolean plus) {
        receiver.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIME__HEADER.getMessage().replaceAll("%PLAYER-NAME%", name));

        final int[] playtime = Utils.convert(ticks);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DataStorage.instance.getDateSyntax());
        final String firstPlayed = dateFormat.format(new Date(first));
        final String lastPlayed = dateFormat.format(new Date(last));

        receiver.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIME__DATES.getMessage()
                .replaceAll("%FIRST-PLAYED%", firstPlayed)
                .replaceAll("%LAST-PLAYED%", lastPlayed));

        receiver.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIME__PLAYTIME.getMessage()
                .replaceAll("%DAYS%", String.valueOf(playtime[3]))
                .replaceAll("%HOURS%", String.valueOf(playtime[2]))
                .replaceAll("%MINUTES%", String.valueOf(playtime[1]))
                .replaceAll("%SECONDS%", String.valueOf(playtime[0])));

        DataStorage.instance.getRanks().forEach(rank -> {
            if (playtime[2] >= rank.getHours()) {
                receiver.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIME__RANK_UNLOCKED.getMessage()
                        .replaceAll("%RANK-DISPLAY-NAME%", rank.getDisplayName())
                        .replaceAll("%PLUS%", plus ? "+" : ""));

            } else {
                if (!rank.isHidden()) {
                    int requiredHours = rank.getHours() - playtime[2];
                    receiver.sendMessage(ConfigManager.Messages.COMMANDS__PLAYTIME__RANK_LOCKED.getMessage()
                            .replaceAll("%RANK-DISPLAY-NAME%", rank.getDisplayName())
                            .replaceAll("%HOURS-LEFT%", String.valueOf(requiredHours))
                            .replaceAll("%PLUS%", plus ? "+" : ""));
                }
            }
        });
        return true;
    }

    public static PluginCommand getCmd()                  { return cmd;         }
    public static void          setCmd(PluginCommand cmd) { Playtime.cmd = cmd; }
}
