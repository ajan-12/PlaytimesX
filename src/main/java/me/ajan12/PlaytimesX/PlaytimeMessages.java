package me.ajan12.PlaytimesX;

/*
    Done the playtime message creation!
    + is defined by a permission called "playtimesx.plus"
    WORKS!
 */

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlaytimeMessages implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(ChatColor.GREEN + "Fetching player information... please wait.");
            int ticks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
            Integer[] list = TickConvertion.conversion(ticks);
            p.sendMessage(ChatColor.GREEN + "You have played for " + ChatColor.GOLD + list[0] + ChatColor.GREEN + " days, " + ChatColor.GOLD + list[1] + ChatColor.GREEN + " hours, " + ChatColor.GOLD + list[2] + ChatColor.GREEN + " minutes and " + ChatColor.GOLD + list[3] + ChatColor.GREEN + " seconds!");
            if (p.hasPermission("playtimesx.plus")) {
                //YES +
                p.sendMessage(ChatColor.GREEN + "You have reached the rank [Member" + ChatColor.GOLD + "+" + ChatColor.GREEN + "]");
                if (list[3] > 720000) {
                    p.sendMessage(ChatColor.GREEN + "You have reached the rank " + ChatColor.BLUE + "[Senior" + ChatColor.GOLD + "+" + ChatColor.DARK_AQUA + "]");
                } else if (list[3] < 1620000) {
                    p.sendMessage(ChatColor.DARK_RED + "You need to play " + ChatColor.GOLD + (150 - list[1]) + ChatColor.DARK_RED + " more hours to reach the rank " + ChatColor.BLUE + "[Senior" + ChatColor.GOLD + "+" + ChatColor.BLUE + "]");
                }
                if (list[3] > 1620000) {
                    p.sendMessage(ChatColor.GREEN + "You have reached the rank " + ChatColor.AQUA + "[Veteran" + ChatColor.GOLD + "+" + ChatColor.AQUA + "]");
                } else if (list[3] < 2520000) {
                    p.sendMessage(ChatColor.DARK_RED + "You need to play " + ChatColor.GOLD + (450 - list[1]) + ChatColor.DARK_RED + " more hours to reach the rank " + ChatColor.AQUA + "[Veteran" + ChatColor.GOLD + "+" + ChatColor.AQUA + "]");
                }
                if (list[3] > 2520000) {
                    p.sendMessage(ChatColor.GREEN + "You have reached the rank " + ChatColor.GRAY + "[Legend" + ChatColor.GOLD + "+" + ChatColor.GRAY + "]");
                } else {
                    p.sendMessage(ChatColor.DARK_RED + "You need to play " + ChatColor.GOLD + (700 - list[1]) + ChatColor.DARK_RED + " more hours to reach the rank " + ChatColor.GRAY + "[Legend" + ChatColor.GOLD + "+" + ChatColor.GRAY + "]");
                }
                //END
                p.sendMessage(ChatColor.GREEN + "Type /ranks for more information on ranks.");
                return true;
            } else {
                //NO +
                p.sendMessage(ChatColor.GREEN + "You have reached the rank [Member]");
                if (list[3] > 720000) {
                    p.sendMessage(ChatColor.GREEN + "You have reached the rank " + ChatColor.BLUE + "[Senior]");
                } else if (list[3] < 1620000) {
                    p.sendMessage(ChatColor.DARK_RED + "You need to play " + ChatColor.GOLD + (150 - list[1]) + ChatColor.DARK_RED + " more hours to reach the rank " + ChatColor.BLUE + "[Senior]");
                }
                if (list[3] > 1620000) {
                    p.sendMessage(ChatColor.GREEN + "You have reached the rank " + ChatColor.AQUA + "[Veteran]");
                } else if (list[3] < 2520000) {
                    p.sendMessage(ChatColor.DARK_RED + "You need to play " + ChatColor.GOLD + (450 - list[1]) + ChatColor.DARK_RED + " more hours to reach the rank " + ChatColor.AQUA + "[Veteran]");
                }
                if (list[3] > 2520000) {
                    p.sendMessage(ChatColor.GREEN + "You have reached the rank " + ChatColor.GRAY + "[Legend]");
                } else {
                    p.sendMessage(ChatColor.DARK_RED + "You need to play " + ChatColor.GOLD + (700 - list[1]) + ChatColor.DARK_RED + " more hours to reach the rank " + ChatColor.GRAY + "[Legend]");
                }
                //END
                p.sendMessage(ChatColor.GREEN + "Type /ranks for more information on ranks.");
                return true;
            }
        } else {
            PlaytimesX.logger.warning("This command can only be applied in-game!");
            return true;
        }
    }
}
