package me.ajan12.PlaytimesX;

/*
    Detects and updates regular ranks in every 5 minutes!
    TODO: Testing
 */

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RankUpdate {

    public RankUpdate() {}

    public void updateLoopStart(Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(PlaytimesX.main, new BukkitRunnable() {

            @Override
            public void run() {
                int ticks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
                Integer[] list = TickConvertion.conversion(ticks);
                if (p.hasPermission("playtimesx.plus")) {
                    if (!p.hasPermission("playtimesx.legend") && list[3] > 2520000) {
                        // Rank add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add legend+");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove veteran+");
                        // Rank perm node add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.legend");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " remove playtimesx.veteran");
                    } else if (!p.hasPermission("playtimesx.veteran") && list[3] > 1620000) {
                        // Rank add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add veteran+");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove senior+");
                        // Rank perm node add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.veteran");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " remove playtimesx.senior");
                    } else if (!p.hasPermission("playtimesx.senior") && list[3] > 720000) {
                        // Rank add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add senior+");
                        // Rank perm node add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.senior");
                    }
                } else {
                    if (!p.hasPermission("playtimesx.legend") && list[3] > 2520000) {
                        // Rank add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add legend");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove veteran");
                        // Rank perm node add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.legend");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " remove playtimesx.veteran");
                    } else if (!p.hasPermission("playtimesx.veteran") && list[3] > 1620000) {
                        // Rank add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add veteran");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove senior");
                        // Rank perm node add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.veteran");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " remove playtimesx.senior");
                    } else if (!p.hasPermission("playtimesx.senior") && list[3] > 720000) {
                        // Rank add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add senior");
                        // Rank perm node add-removal
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.senior");
                    }
                }
            }

        }, 6000, 6000);
    }
}
