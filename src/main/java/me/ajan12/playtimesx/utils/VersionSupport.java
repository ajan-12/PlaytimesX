package me.ajan12.PlaytimesX.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class VersionSupport {

    public static int getPlaytime(final Player player) {
        final String path = Bukkit.getServer().getClass().getPackage().getName();
        final String version = path.substring(path.lastIndexOf('.') + 1);

        if (version.startsWith("v1_14") || version.startsWith("v1_13")) {
            return player.getStatistic(Statistic.valueOf("PLAY_ONE_MINUTE"));
        } else {
            return player.getStatistic(Statistic.valueOf("PLAY_ONE_TICK"));
        }
    }
}
