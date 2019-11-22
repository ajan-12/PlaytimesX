package me.ajan12.PlaytimesX.Objects;

import me.ajan12.PlaytimesX.Utils.ConfigManager;

import java.util.ArrayList;

public class PlayerProfile {

    private static ArrayList<PlayerProfile[]> profiles = null;

    private String name;
    private int  ticks;
    private long firstPlayed;
    private long lastPlayed;
    private boolean plus;

    public PlayerProfile(final String name, final int ticks, final long firstPlayed, final long lastPlayed, final boolean plus) {
        this.name = name;
        this.ticks = ticks;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.plus = plus;

        if (profiles == null) profiles = new ArrayList<>();
        final int maxPageSize = ConfigManager.getInteger("settings.profile-display.entry-per-page");
        if (profiles.isEmpty()) {
            final PlayerProfile[] newLastPage = new PlayerProfile[maxPageSize];
            newLastPage[0] = this;
            profiles.add(newLastPage);
            return;
        }

        final PlayerProfile[] lastPage = profiles.get(profiles.size() - 1);
        for (int i = 0; i < maxPageSize; i++) {
            if (lastPage[i] == null) {
                lastPage[i] = this;
                profiles.set(profiles.size() - 1, lastPage);
                return;
            }
        }

        final PlayerProfile[] newLastPage = new PlayerProfile[maxPageSize];
        newLastPage[0] = this;
        profiles.add(newLastPage);
    }

    public String  getName()        { return name;        }
    public int     getTicks()       { return ticks;       }
    public long    getFirstPlayed() { return firstPlayed; }
    public long    getLastPlayed()  { return lastPlayed;  }
    public boolean isPlus()         { return plus;        }

    public void setName(String name)              { this.name = name;               }
    public void setTicks(int ticks)               { this.ticks = ticks;             }
    public void setFirstPlayed(long firstPlayed)  { this.firstPlayed = firstPlayed; }
    public void setLastPlayed(long lastPlayed)    { this.lastPlayed = lastPlayed;   }
    public void setPlus(boolean plus)             { this.plus = plus;               }

    @Override
    public String toString() {
        return ConfigManager.getString("settings.profile-display.display-syntax")
                .replaceAll("%PLAYER-NAME%", name)
                .replaceAll("%TICKS%", String.valueOf(ticks))
                .replaceAll("%FIRST-PLAYED%", String.valueOf(firstPlayed))
                .replaceAll("%LAST-PLAYED%", String.valueOf(lastPlayed))
                .replaceAll("%PLUS%", String.valueOf(plus));
    }

    public static ArrayList<PlayerProfile[]> getProfiles() { return profiles; }
}
