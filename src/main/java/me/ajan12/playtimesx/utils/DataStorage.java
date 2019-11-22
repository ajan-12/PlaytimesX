package me.ajan12.PlaytimesX.Utils;

import me.ajan12.PlaytimesX.Objects.PlayerProfile;
import me.ajan12.PlaytimesX.Objects.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DataStorage {

    public static DataStorage instance = null;

    public DataStorage() {
        DataStorage.instance = this;
        ranks = ConfigManager.getRanks();
        profiles = Utils.readPlayers();
        dateSyntax = ConfigManager.getString("settings.date-syntax");
        autoSave = -1;
        rankUp = -1;
        rankUpInterval = 0;
    }

    private ArrayList<Rank>              ranks;
    private HashMap<UUID, PlayerProfile> profiles;
    private String                       dateSyntax;
    private int                          autoSave;
    private int                          rankUp;
    private int                          rankUpInterval;

    public ArrayList<Rank>              getRanks()          { return ranks;          }
    public HashMap<UUID, PlayerProfile> getProfiles()       { return profiles;       }
    public String                       getDateSyntax()     { return dateSyntax;     }
    public int                          getAutoSave()       { return autoSave;       }
    public int                          getRankUp()         { return rankUp;         }
    public int                          getRankUpInterval() { return rankUpInterval; }

    public void setAutoSave(final int autoSave)             { this.autoSave = autoSave;             }
    public void setRankUp(final int rankUp)                 { this.rankUp = rankUp;                 }
    public void setRankUpInterval(final int rankUpInterval) { this.rankUpInterval = rankUpInterval; }
}
