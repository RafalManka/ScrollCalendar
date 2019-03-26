package pl.rafman.scrollcalendar.data;

import android.support.annotation.NonNull;

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
    public static final int FIRST_SELECTED = 5;
    public static final int LAST_SELECTED = 6;
    public static final int ONLY_SELECTED = 7;
    public static final int[] SELECTED_STATES = {SELECTED, FIRST_SELECTED, LAST_SELECTED};
    private final int day;
    @State
    private int state = DEFAULT;
    private String subTitle;

    CalendarDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    @State
    public int getState() {
        return state;
    }

    public void setState(@State int state) {
        this.state = state;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? "" : subTitle;
    }

    @Override
    public String toString() {
        return "CalendarDay{" +
                "state=" + state +
                ", scrollcalendar_day=" + day +
                ", scrollcalendar_subtitle=" + subTitle +
                '}';
    }
}
