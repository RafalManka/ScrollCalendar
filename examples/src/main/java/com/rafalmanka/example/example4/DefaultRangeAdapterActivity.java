package com.rafalmanka.example.example4;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rafalmanka.example.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.rafman.scrollcalendar.ScrollCalendar;
import pl.rafman.scrollcalendar.adapter.ScrollCalendarAdapter;
import pl.rafman.scrollcalendar.adapter.example.DefaultRangeScrollCalendarAdapter;
import pl.rafman.scrollcalendar.contract.OnDateClickListener;


public class DefaultRangeAdapterActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollCalendar scrollCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_adapter_range);
        TextView title = findViewById(R.id.title);
        title.setText(R.string.range_activity_title);

        scrollCalendar = findViewById(R.id.scrollCalendar);
        findViewById(R.id.toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ScrollCalendarAdapter adapter = scrollCalendar.getAdapter();
        if (adapter instanceof DefaultRangeScrollCalendarAdapter) {
            DefaultRangeScrollCalendarAdapter defaultAdapter = (DefaultRangeScrollCalendarAdapter) adapter;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date from = defaultAdapter.getStartDate();
            String text = "";
            if (from == null) {
                text += "?";
            } else {
                text += format.format(from);
            }
            text += " - ";
            Date until = defaultAdapter.getEndDate();
            if (until == null) {
                text += "?";
            } else {
                text += format.format(until);
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
