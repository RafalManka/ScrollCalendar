package com.rafalmanka.example.example3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rafalmanka.example.R;
import com.rafalmanka.example.example4.DefaultRangeAdapterActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.rafman.scrollcalendar.ScrollCalendar;
import pl.rafman.scrollcalendar.adapter.ScrollCalendarAdapter;
import pl.rafman.scrollcalendar.adapter.example.DefaultDateScrollCalendarAdapter;
import pl.rafman.scrollcalendar.contract.OnDateClickListener;


public class DefaultAdapterActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollCalendar scrollCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_adapter);
        TextView title = findViewById(R.id.title);
        title.setText(R.string.date_activity_title);

        scrollCalendar = findViewById(R.id.scrollCalendar);
        findViewById(R.id.toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ScrollCalendarAdapter adapter = scrollCalendar.getAdapter();
        if (adapter instanceof DefaultDateScrollCalendarAdapter) {
            DefaultDateScrollCalendarAdapter defaultAdapter = (DefaultDateScrollCalendarAdapter) adapter;
            Date date = defaultAdapter.getSelectedDate();
            String text;
            if (date == null) {
                text = getString(R.string.no_date_selected);
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                text = format.format(date);
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
