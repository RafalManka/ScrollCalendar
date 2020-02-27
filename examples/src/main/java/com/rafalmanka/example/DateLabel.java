package com.rafalmanka.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateLabel extends FrameLayout {

    private TextView day;
    private TextView month;
    private TextView weekday;
    private TextView ctaTextView;
    private TextView hintLabel;
    private TextView ctaLabel;
    private View ctaLayout;
    private View dateLayout;

    private String cta;
    private String hint;

    private Date date;

    public DateLabel(Context context) {
        super(context);
        init(context, null);
    }

    public DateLabel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DateLabel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.layout_date_label, this);
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.DateLabel, 0, 0);
        cta = typedArray.getString(R.styleable.DateLabel_cta);
        hint = typedArray.getString(R.styleable.DateLabel_hint);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        weekday = findViewById(R.id.weekday);
        dateLayout = findViewById(R.id.dateLayout);
        ctaLayout = findViewById(R.id.ctaLayout);

        hintLabel = findViewById(R.id.hint);
        ctaLabel = findViewById(R.id.cta);
        hintLabel.setText(hint);
        ctaLabel.setText(cta);
        refresh();
    }

    public void setDate(Date date) {
        this.date = date;
        refresh();
    }

    @SuppressLint("SimpleDateFormat")
    private void refresh() {
        ctaLayout.setVisibility(date == null ? View.VISIBLE : View.GONE);
        dateLayout.setVisibility(date != null ? View.VISIBLE : View.GONE);
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd");
            day.setText(format.format(date));
            format = new SimpleDateFormat("MMM");
            month.setText(format.format(date));
            format = new SimpleDateFormat("EEEE");
            weekday.setText(format.format(date));
        }
    }

    public void setCta(String cta) {
        this.ctaLabel.setText(cta);
    }
}
