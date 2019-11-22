package me.ajan12.PlaytimesX;

import me.ajan12.PlaytimesX.Objects.PlayerProfile;
import me.ajan12.PlaytimesX.Utils.DataStorage;
import me.ajan12.PlaytimesX.Utils.Utils;
import me.ajan12.PlaytimesX.Utils.VersionSupport;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    Listeners(final PlaytimesX main) {
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        process(event.getPlayer());
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        process(event.getPlayer());
    }

    private void process(final Player player) {
        if (DataStorage.instance.getProfiles().containsKey(player.getUniqueId())) {
            final PlayerProfile profile = DataStorage.instance.getProfiles().get(player.getUniqueId());
            profile.setLastPlayed(player.getLastPlayed());
            profile.setTicks(VersionSupport.getPlaytime(player));
            profile.setName(player.getName());

            DataStorage.instance.getProfiles().replace(player.getUniqueId(), profile);
        } else {
            DataStorage.instance.getProfiles().put(player.getUniqueId(), new PlayerProfile(
                    player.getName(),
                    VersionSupport.getPlaytime(player),
                    player.getFirstPlayed(),
                    player.getLastPlayed(),
                    false
            ));
        }

        Utils.calculateRankUp();
    }
}
