package me.ajan12.PlaytimesX;

import me.ajan12.PlaytimesX.Commands.Playtime;
import me.ajan12.PlaytimesX.Commands.PlaytimesXCommand;
import me.ajan12.PlaytimesX.Commands.Plus;
import me.ajan12.PlaytimesX.Commands.Ranks;
import me.ajan12.PlaytimesX.Utils.ConfigManager;
import me.ajan12.PlaytimesX.Utils.DataStorage;
import me.ajan12.PlaytimesX.Utils.Utils;
import me.ajan12.PlaytimesX.Utils.VaultHook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PlaytimesX extends JavaPlugin {

    private static PlaytimesX instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        ConfigManager.config = getConfig();
        ConfigManager.localize();
        new DataStorage();

        if (!VaultHook.init(this)) return;

        new Playtime(this);
        new PlaytimesXCommand(this);
        new Ranks(this);
        new Plus(this);
        new Listeners(this);

        if (ConfigManager.getBoolean("settings.auto-save.enabled")) {
            int interval = ConfigManager.getInteger("settings.auto-save.interval-ticks");
            if (interval == 0) interval = 100;

            DataStorage.instance.setAutoSave(Bukkit.getScheduler().runTaskTimer(this,
                    () -> Utils.writePlayers(DataStorage.instance.getProfiles()), interval, interval
            ).getTaskId());
        }
    }

    @Override
    public void onDisable() {
        ConfigManager.config = null;
        DataStorage.instance = null;

        Playtime.setCmd(null);
        PlaytimesXCommand.setCmd(null);
        Ranks.setCmd(null);
        Plus.setCmd(null);
    }

    public void reload() {
        instance = this;

        saveDefaultConfig();
        reloadConfig();
        ConfigManager.config = getConfig();
        ConfigManager.localize();
        new DataStorage();

        Playtime.getCmd().setDescription(
                ConfigManager.Messages.COMMANDS__PLAYTIME__DESCRIPTION.getMessage());
        PlaytimesXCommand.getCmd().setDescription(
                ConfigManager.Messages.COMMANDS__PLAYTIMESX__DESCRIPTION.getMessage());
        Ranks.getCmd().setDescription(
                ConfigManager.Messages.COMMANDS__RANKS__DESCRIPTION.getMessage());
        Ranks.setContent(ConfigManager.getStringList("localization.commands.ranks.content"));
        Plus.getCmd().setDescription(
                ConfigManager.Messages.COMMANDS__PLUS__DESCRIPTION.getMessage());

        if (DataStorage.instance.getAutoSave() != -1) {
            Bukkit.getScheduler().cancelTask(DataStorage.instance.getAutoSave());
        }

        if (ConfigManager.getBoolean("settings.auto-save.enabled")) {
            int interval = ConfigManager.getInteger("settings.auto-save.interval-ticks");
            if (interval == 0) interval = 100;

            DataStorage.instance.setAutoSave(Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                    () -> Utils.writePlayers(DataStorage.instance.getProfiles()), interval, interval
            ));
        }
    }

    public static PlaytimesX getInstance() { return instance; }
}