package me.ajan12.PlaytimesX.Commands;

import me.ajan12.PlaytimesX.PlaytimesX;
import me.ajan12.PlaytimesX.Utils.ConfigManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;

public class Ranks implements CommandExecutor {

    private static PluginCommand cmd;
    private static ArrayList<String> content;

    public Ranks(final PlaytimesX main) {
        cmd = main.getCommand("ranks");
        if (cmd == null) return;
        cmd.setExecutor(this);
        cmd.setDescription(ConfigManager.Messages.COMMANDS__RANKS__DESCRIPTION.getMessage());

        content = ConfigManager.getStringList("localization.commands.ranks.content");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        content.forEach(line -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line)));
        return true;
    }

    public static PluginCommand getCmd()                                    { return cmd;              }
    public static void          setCmd(final PluginCommand cmd)             { Ranks.cmd = cmd;         }
    public static void          setContent(final ArrayList<String> content) { Ranks.content = content; }
}
