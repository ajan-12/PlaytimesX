package me.ajan12.PlaytimesX.Utils;

import me.ajan12.PlaytimesX.Objects.PlayerProfile;
import me.ajan12.PlaytimesX.Objects.Rank;
import me.ajan12.PlaytimesX.PlaytimesX;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void calculateRankUp() {
        if (DataStorage.instance.getRankUp() != -1) Bukkit.getScheduler().cancelTask(DataStorage.instance.getRankUp());
        final int[] interval = {Integer.MAX_VALUE};

        Bukkit.getOnlinePlayers().forEach(player -> {
            final int ticks = VersionSupport.getPlaytime(player);

            for (final Rank rank : DataStorage.instance.getRanks()) {
                final int rankTicks = (rank.getHours() * 72000);
                if (rankTicks > ticks) {
                    final int temp = rankTicks - ticks;
                    if (temp < interval[0]) interval[0] = temp;
                    break;
                }
            }
        });

        if (interval[0] == Integer.MAX_VALUE) return;
        DataStorage.instance.setRankUp(
                Bukkit.getScheduler().runTaskLater(
                        PlaytimesX.getInstance(),
                        new RankUp(),
                        interval[0]
                ).getTaskId()
        );
        DataStorage.instance.setRankUpInterval(interval[0]);
    }

    public static int[] convert(final int ticks) {
        int[] arr = new int[4];
        int seconds = ticks / 20;
        arr[3] = (int) TimeUnit.SECONDS.toDays(seconds);
        arr[2] = (int) TimeUnit.SECONDS.toHours(seconds) - (arr[3] * 24);
        arr[1] = (int) (TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60));
        arr[0] = (int) (TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60));
        return arr;
    }

    public static HashMap<UUID, PlayerProfile> readPlayers() {
        final File dataFile = new File(PlaytimesX.getInstance().getDataFolder().getAbsolutePath() + File.separator + "player-info.yml");
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {

                final FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
                if (!data.contains("profiles")) return new HashMap<>();
                final Set<String> profileKeys = data.getConfigurationSection("profiles").getKeys(false);
                final HashMap<UUID, PlayerProfile> profiles = new HashMap<>();

                profileKeys.forEach(key -> {
                    final ConfigurationSection profileCFG = data.getConfigurationSection("profiles." + key);
                    final UUID uuid = UUID.fromString(key);

                    final String name = profileCFG.getString("name");
                    final int ticks = profileCFG.getInt("ticks");
                    final long firstPlayed = profileCFG.getLong("first-played");
                    final long lastPlayed = profileCFG.getLong("last-played");
                    final boolean plus = profileCFG.getBoolean("plus");

                    profiles.put(uuid, new PlayerProfile(name, ticks, firstPlayed, lastPlayed, plus));
                });
                return profiles;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void writePlayers(HashMap<UUID, PlayerProfile> profiles) {
        final File dataFile = new File(PlaytimesX.getInstance().getDataFolder().getAbsolutePath() + File.separator + "player-info.yml");
        try {
            if (dataFile.exists()) {
                dataFile.delete();
            }
            dataFile.createNewFile();

            final FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
            final ConfigurationSection profilesCFG = data.createSection("profiles");
            profiles.forEach(((uuid, profile) -> {
                final ConfigurationSection profileCFG = profilesCFG.createSection(uuid.toString());
                profileCFG.set("name", profile.getName());
                profileCFG.set("ticks", profile.getTicks());
                profileCFG.set("first-played", profile.getFirstPlayed());
                profileCFG.set("last-played", profile.getLastPlayed());
                profileCFG.set("plus", profile.isPlus());
            }));

            data.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
