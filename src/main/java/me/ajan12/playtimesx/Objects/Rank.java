package me.ajan12.PlaytimesX.Objects;

public class Rank {

    private final String name;
    private final String displayName;
    private final int    hours;
    private boolean      hidden;

    public Rank(final String name, final String displayName, final int hours, final boolean hidden) {
        this.name = name;
        this.displayName = displayName;
        this.hours = hours;
        this.hidden = hidden;
    }

    public String  getName()        { return name;          }
    public String  getDisplayName() { return displayName;   }
    public int     getHours()       { return hours;         }
    public boolean isHidden()       { return hidden;        }
}
