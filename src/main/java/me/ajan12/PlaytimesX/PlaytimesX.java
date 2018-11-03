package me.ajan12.PlaytimesX;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PlaytimesX extends JavaPlugin {

    public PlaytimesX() {}

    static Logger logger = Bukkit.getLogger();
    static PlaytimesX main;

    @Override
    public void onEnable() {
        //Initialization *-*-*-*-*-*-*-*-*-*-*-*
        this.getCommand("playtime").setExecutor(new PlaytimeMessages());
        this.getCommand("ranks").setExecutor(new RanksMessages());
        this.getCommand("plus").setExecutor(new PlusCommand());
        this.getCommand("rankup").setExecutor(new RankupCommand());
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        main = this;
    }

    public static Plugin getInstance() {
        return main;
    }
}