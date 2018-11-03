package me.ajan12.PlaytimesX;

/*
    /rankup to give/remove a rank
    TODO: Testing
 */
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(args.length < 2 || args.length > 3)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("playtimesx.command.rankup")) {
                    if (args.length == 3) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add " + args[1]);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove " + args[2]);
                        p.sendMessage(ChatColor.GREEN + "Successfully added the rank " + args[1] + " to player " + args[0] + " and removed the rank " + args[2] + ".");
                        PlaytimesX.logger.info("Successfully added the rank " + args[1] + " to player " + args[0] + " and removed the rank " + args[2] + ".");
                        return true;
                    } else {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add " + args[1]);
                        p.sendMessage(ChatColor.GREEN + "Successfully added the rank " + args[1] + " to player " + args[0] + ".");
                        PlaytimesX.logger.info("Successfully added the rank " + args[1] + " to player " + args[0] + ".");
                        return true;
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to do that!");
                    return true;
                }
            } else {
                if (args.length == 3) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add " + args[1]);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove " + args[2]);
                    PlaytimesX.logger.info("Successfully added the rank " + args[1] + " to player " + args[0] + " and removed the rank " + args[2] + ".");
                    return true;
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add " + args[1]);
                    PlaytimesX.logger.info("Successfully added the rank " + args[1] + " to player " + args[0] + ".");
                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
