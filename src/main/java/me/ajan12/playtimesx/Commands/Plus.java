package me.ajan12.PlaytimesX.Commands;

import me.ajan12.PlaytimesX.Objects.PlayerProfile;
import me.ajan12.PlaytimesX.PlaytimesX;
import me.ajan12.PlaytimesX.Utils.ConfigManager;

import me.ajan12.PlaytimesX.Utils.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Plus implements CommandExecutor {

    private static PluginCommand cmd;

    public Plus(final PlaytimesX main) {
        cmd = main.getCommand("plus");
        if (cmd == null) return;
        cmd.setExecutor(this);
        cmd.setDescription(ConfigManager.Messages.COMMANDS__PLUS__DESCRIPTION.getMessage());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("playtimesx.command.plus")) {
                sender.sendMessage(ConfigManager.Messages.COMMANDS__PLUS__PERMISSION_ERROR.getMessage());
                return true;
            }
        }

        PlayerProfile profile = null;
        if (args.length == 1) {
            if (args[0].matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) {
                profile = DataStorage.instance.getProfiles().get(UUID.fromString(args[0]));
            } else {
                final Player target = Bukkit.getPlayer(args[0]);
                if (target != null) profile = DataStorage.instance.getProfiles().get(target.getUniqueId());
            }
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                profile = DataStorage.instance.getProfiles().get(((Player) sender).getUniqueId());
            }
        } else {
            return false;
        }

        if (profile == null) {
            sender.sendMessage(ConfigManager.Messages.COMMANDS__PLUS__PLAYER_NOT_FOUND.getMessage());
            return true;
        }

        profile.setPlus(!profile.isPlus());
        sender.sendMessage(ConfigManager.Messages.COMMANDS__PLUS__SUCCESS.getMessage()
                .replaceAll("%PLAYER-NAME%", profile.getName()));
        return true;
    }

    public static PluginCommand getCmd()                  { return cmd;         }
    public static void          setCmd(PluginCommand cmd) { Plus.cmd = cmd;     }
}
