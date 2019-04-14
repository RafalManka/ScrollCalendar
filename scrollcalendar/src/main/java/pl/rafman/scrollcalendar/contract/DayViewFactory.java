package pl.rafman.scrollcalendar.contract;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.rafman.scrollcalendar.R;

public abstract class DayViewFactory {

    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
    }

    public void setupView(View view, int year, int month, int day) {

    }
}
