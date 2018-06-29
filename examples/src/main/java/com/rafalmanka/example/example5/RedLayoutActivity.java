package com.rafalmanka.example.example5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rafalmanka.example.DateLabel;
import com.rafalmanka.example.R;

import java.util.Date;

import pl.rafman.scrollcalendar.ScrollCalendar;
import pl.rafman.scrollcalendar.adapter.ScrollCalendarAdapter;
import pl.rafman.scrollcalendar.adapter.example.DefaultRangeScrollCalendarAdapter;
import pl.rafman.scrollcalendar.contract.OnDateClickListener;

public class RedLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red);
        setupToolbar();

        final ScrollCalendar scrollCalendar = findViewById(R.id.scrollCalendar);
        final DateLabel from = findViewById(R.id.from);
        final DateLabel to = findViewById(R.id.to);
        scrollCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onCalendarDayClicked(int year, int month, int day) {
                ScrollCalendarAdapter adapter = scrollCalendar.getAdapter();
                if (adapter instanceof DefaultRangeScrollCalendarAdapter) {
                    DefaultRangeScrollCalendarAdapter rangeAdapter = (DefaultRangeScrollCalendarAdapter) adapter;
                    Date start = rangeAdapter.getStartDate();
                    from.setDate(start);
                    to.setCta(start == null ? "" : getString(R.string.select_ending_date));
                    to.setDate(rangeAdapter.getEndDate());
                }
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowHomeEnabled(true);
        }
    }
}
