package me.ajan12.PlaytimesX.Utils;

import me.ajan12.PlaytimesX.Objects.PlayerProfile;
import me.ajan12.PlaytimesX.Objects.Rank;

import org.bukkit.Bukkit;

public class RankUp implements Runnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (DataStorage.instance.getProfiles().containsKey(player.getUniqueId())) {
                final PlayerProfile profile = DataStorage.instance.getProfiles().get(player.getUniqueId());
                for (final Rank rank : DataStorage.instance.getRanks()) {
                    if ((rank.getHours() * 72000) <= profile.getTicks()) {
                        if (!VaultHook.getPerms().playerInGroup(player, rank.getName() + (profile.isPlus() ? "+" : ""))) {
                            VaultHook.getPerms().playerAddGroup(player, rank.getName() + (profile.isPlus() ? "+" : ""));
                        }
                    }
                }
            }
        });

        Utils.calculateRankUp();
    }
}
