package me.ajan12.PlaytimesX;

/*
    Converts ticks to days, hours, minutes and seconds combined!
    WORKS!
 */

import java.util.concurrent.TimeUnit;

public class TickConvertion {

    public static Integer[] conversion(int ticks) {
        int seconds = ticks / 20;
        int days = (int) TimeUnit.SECONDS.toDays(seconds);
        int hours = (int) TimeUnit.SECONDS.toHours(seconds) - (days * 24);
        int minute = (int) (TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60));
        int second = (int) (TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60));
        Integer[] list = new Integer[4];
        list[0] = days;
        list[1] = hours;
        list[2] = minute;
        list[3] = second;
        return list;
    }
}
