package me.ajan12.PlaytimesX.Utils;

import me.ajan12.PlaytimesX.Objects.Rank;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ConfigManager {

    public static FileConfiguration config = null;

    public static boolean getBoolean(final String path) {
        return config.getBoolean(path);
    }
    public static int getInteger(final String path) {
        return config.getInt(path);
    }
    public static String getString(final String path) {
        return config.getString(path);
    }
    public static ArrayList<String> getStringList(final String path) {
        return new ArrayList<>(config.getStringList(path));
    }

    static ArrayList<Rank> getRanks() {
        final ArrayList<String> list = getStringList("settings.ranks");
        final ArrayList<String> hiddenRanks = getStringList("settings.hidden-ranks");
        final ArrayList<Rank> ranks = new ArrayList<>();

        list.forEach(entry -> {
            final String[] values = entry.split(" ");
            if (values.length == 3) {
                final Rank rank = new Rank(
                        values[0],
                        ChatColor.translateAlternateColorCodes('&', values[2]),
                        Integer.parseInt(values[1]),
                        hiddenRanks.contains(values[0])
                );
                ranks.add(rank);
            }
        });

        return ranks;
    }

    public static void localize() {
        for (final Messages entry : Messages.values()) {
            if (entry.name().startsWith("$")) continue;
            final String message = getString("localization." + entry.getPath());
            if (message != null) entry.setMessage(ChatColor.translateAlternateColorCodes('&', message));
            else entry.setMessage(ChatColor.translateAlternateColorCodes('&', entry.getMessage()));
        }
    }

    public enum Messages {

        ENABLE__VAULT_NOT_FOUND("The plugin \"Vault\" is needed for this plugin to work!"),
        ENABLE__PERM_PLUGIN_NOT_FOUND("A permissions plugin is needed for this plugin to work!"),


        $COMMANDS__PLAYTIMESX__HELP_HEADER("&6.oOo._________.o{ &bPlaytimesX v%VERSION% &6}o._________.oOo."),
        COMMANDS__PLAYTIMESX__DESCRIPTION("The main admin command for plugin settings."),
        COMMANDS__PLAYTIMESX__PERMISSION_ERROR("&4You do not have permission to use this command."),
        COMMANDS__PLAYTIMESX__PLAYER_NOT_FOUND("&4Could not find the specified player!"),

        COMMANDS__PLAYTIMESX__RELOAD_START("&aStarting to reload the plugin."),
        COMMANDS__PLAYTIMESX__RELOAD_END("&aReloading the plugin finished in &6%TIME%ms&a."),

        COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_NOT_FOUND("&4Could not find any player profiles."),
        COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_HEADER("&6.oOo._________.o{ &b%AMOUNT% Player Profiles &6}o._________.oOo."),
        COMMANDS__PLAYTIMESX__PROFILE_DISPLAY_FOOTER("&6.oOo._________.o{ &bPage: %CURRENT-PAGE%&2/&b%TOTAL-PAGES% &6}o._________.oOo."),

        COMMANDS__PLAYTIMESX__RECALCULATE_RANKUP_START("&aStarting to recalculating the next rankup time."),
        COMMANDS__PLAYTIMESX__RECALCULATE_RANKUP_END("&aRecalculated the next rankup to &6%NEXT-RANKUP% &ain &6%TIME%ms&a."),

        COMMANDS__PLAYTIMESX__NEXT_RANKUP("&aThe next rankup is scheduled to &6%RANKUP% &aticks later."),


        COMMANDS__PLAYTIME__DESCRIPTION("Shows a player's total online-time, last login and first join dates and rank status."),
        COMMANDS__PLAYTIME__USER_ERROR("This command can only be applied ingame!"),
        COMMANDS__PLAYTIME__PLAYER_NOT_FOUND("&4Could not find the specified player!"),

        COMMANDS__PLAYTIME__HEADER("&6.oOo._________.o{ &b%PLAYER-NAME% &6}o._________.oOo."),
        COMMANDS__PLAYTIME__DATES("&2Registered: &a%FIRST-PLAYED% &7| &2Last Login: &a%LAST-PLAYED%"),
        COMMANDS__PLAYTIME__PLAYTIME("&2Playtime: &6%DAYS% days&a, &6%HOURS% hours&a, &6%MINUTES% minutes &aand &6%SECONDS% seconds"),
        COMMANDS__PLAYTIME__RANK_UNLOCKED("&aYou have unlocked &r%RANK-DISPLAY-NAME%&a."),
        COMMANDS__PLAYTIME__RANK_LOCKED("&cYou have &6%HOURS-LEFT% &cmore hours left to unlock &r%RANK-DISPLAY-NAME%&c."),


        COMMANDS__RANKS__DESCRIPTION("Shows information about both staff and regular ranks."),


        COMMANDS__PLUS__DESCRIPTION("Grants a player with a plus."),
        COMMANDS__PLUS__PERMISSION_ERROR("&4You do not have permission to use this command."),
        COMMANDS__PLUS__PLAYER_NOT_FOUND("&4Could not find the specified player!"),
        COMMANDS__PLUS__SUCCESS("&aSuccessfully changed the plus state of %PLAYER-NAME%.");

        private String message;

        Messages(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public String getPath() {
            return name()
                .replaceAll("__", ".")
                .replaceAll("_", "-")
                .replaceAll("I", "i")
                .toLowerCase();
        }
        @Override
        public String toString() { return message; }
    }
}
