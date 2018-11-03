package me.ajan12.PlaytimesX;

/*
    /plus to give/remove plus
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("playtimesx.command.plus")) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        Player p1 = Bukkit.getPlayer(args[0]);
                        if (p1.hasPermission("playtimesx.plus")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " remove playtimesx.plus");
                            PlaytimesX.logger.info("Removed the plus of player " + args[0] + " by the command of " + p.getName() + ".");
                            p.sendMessage(ChatColor.GREEN + "Successfully removed the plus of player, " + args[0] + ".");
                            // RANK STUFF
                            if (p1.hasPermission("playtimesx.oldfag")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add oldfag");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove oldfag+");
                            } else if (p1.hasPermission("playtimesx.legend")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add legend");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove legend+");
                            } else if (p1.hasPermission("playtimesx.veteran")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add veteran");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove veteran+");
                            } else if (p1.hasPermission("playtimesx.senior")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add senior");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove senior+");
                            } else {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add member");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove member+");
                            }
                            return true;
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " add playtimesx.plus");
                            PlaytimesX.logger.info("Added plus to player " + args[0] + " by the command of " + p.getName() + ".");
                            p.sendMessage(ChatColor.GREEN + "Successfully added a plus to player, " + args[0] + ".");
                            // RANK STUFF
                            if (p1.hasPermission("playtimesx.oldfag")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add oldfag+");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove oldfag");
                            } else if (p1.hasPermission("playtimesx.legend")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add legend+");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove legend");
                            } else if (p1.hasPermission("playtimesx.veteran")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add veteran+");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove veteran");
                            } else if (p1.hasPermission("playtimesx.senior")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add senior+");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove senior");
                            } else {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add member+");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove member");
                            }
                            return true;
                        }
                    } else {
                        p.sendMessage(ChatColor.DARK_RED + "The player " + args[0] + " is not online!");
                        PlaytimesX.logger.warning("The player " + p.getName() + " has tried to use /plus command on player " + args[0] + "!");
                        return true;
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to do that!");
                    PlaytimesX.logger.warning("The player " + p.getName() + " has tried to use /plus command on player " + args[0] + "!");
                    return true;
                }
            } else {
                Player p1 = Bukkit.getPlayer(args[0]);
                if (Bukkit.getOnlinePlayers().contains(p1)) {
                    if (p1.hasPermission("playtimesx.plus")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " remove playtimesx.plus");
                        PlaytimesX.logger.info("Removed the plus of player " + args[0] + " by the command of console.");
                        // RANK STUFF
                        if (p1.hasPermission("playtimesx.oldfag")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add oldfag");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove oldfag+");
                        } else if (p1.hasPermission("playtimesx.legend")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add legend");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove legend+");
                        } else if (p1.hasPermission("playtimesx.veteran")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add veteran");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove veteran+");
                        } else if (p1.hasPermission("playtimesx.senior")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add senior");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove senior+");
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add member");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove member+");
                        }
                        return true;
                    } else {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " add playtimesx.plus");
                        PlaytimesX.logger.info("Added plus to player " + args[0] + " by the command of console.");
                        // RANK STUFF
                        if (p1.hasPermission("playtimesx.oldfag")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add oldfag+");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove oldfag");
                        } else if (p1.hasPermission("playtimesx.legend")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add legend+");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove legend");
                        } else if (p1.hasPermission("playtimesx.veteran")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add veteran+");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove veteran");
                        } else if (p1.hasPermission("playtimesx.senior")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add senior+");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove senior");
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add member+");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group remove member");
                        }
                        return true;
                    }
                } else {
                    PlaytimesX.logger.warning("The player " + args[0] + " is not online!");
                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
