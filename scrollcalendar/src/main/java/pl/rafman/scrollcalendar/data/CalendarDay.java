package pl.rafman.scrollcalendar.data;

import java.io.Serializable;

import pl.rafman.scrollcalendar.contract.State;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class CalendarDay implements Serializable {

    public static final int DEFAULT = 0;
    public static final int DISABLED = 1;
    public static final int TODAY = 2;
    public static final int UNAVAILABLE = 3;
    public static final int SELECTED = 4;

    @State
    private int state = DEFAULT;
    private final int day;

    CalendarDay(int day) {
        this.day = day;
    }

    @State
    public int getState() {
        return state;
    }

    public void setState(@State int state) {
        this.state = state;
    }

    public int getDay() {
        return day;
    }


    @Override
    public String toString() {
        return "CalendarDay{" +
                "state=" + state +
                ", scrollcalendar_day=" + day +
                '}';
    }


}
