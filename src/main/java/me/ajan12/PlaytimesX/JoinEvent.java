package me.ajan12.PlaytimesX;

/*
    EventListener for PlayerJoinEvent
    updates "playtimesx.plus"
 */

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new RankUpdate().updateLoopStart(p);
        if (!p.hasPermission("playtimesx.plus")) {
            if (Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Plus").getScore(p.getName()).getScore() == 1) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add playtimesx.plus");
                PlaytimesX.logger.info("Player, " + p.getName() + " had a plus rank. Updating their plus permission.");
            }
        }
    }
}
