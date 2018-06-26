package pl.rafman.scrollcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import pl.rafman.scrollcalendar.adapter.LegendItem;
import pl.rafman.scrollcalendar.adapter.ResProvider;
import pl.rafman.scrollcalendar.adapter.ScrollCalendarAdapter;
import pl.rafman.scrollcalendar.adapter.example.DefaultDateScrollCalendarAdapter;
import pl.rafman.scrollcalendar.adapter.example.DefaultRangeScrollCalendarAdapter;
import pl.rafman.scrollcalendar.contract.DateWatcher;
import pl.rafman.scrollcalendar.contract.MonthScrollListener;
import pl.rafman.scrollcalendar.contract.OnDateClickListener;
import pl.rafman.scrollcalendar.values.Defaults;
import pl.rafman.scrollcalendar.values.Keys;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class ScrollCalendar extends LinearLayoutCompat implements ResProvider {

    @ColorInt
    private int fontColor;
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int disabledTextColor;
    @ColorInt
    private int unavailableTextColor;
    @ColorInt
    private int selectedTextColor;
    @ColorInt
    private int todayTextColor;

    @DrawableRes
    private int unavailableBackground;
    @DrawableRes
    private int selectedBackground;
    @DrawableRes
    private int selectedBackgroundBeginning;
    @DrawableRes
    private int selectedBackgroundEnd;
    @DrawableRes
    private int selectedBackgroundMiddle;

    @DrawableRes
    private int currentDayBackground;
    @ColorInt
    private int disabledBackgroundColor;
    @Dimension
    private float fontSize;

    @Nullable
    private String customFont;

    private final LegendItem[] legend = new LegendItem[7];

    @Nullable
    private ScrollCalendarAdapter adapter;

    private int defaultAdapter;


    public ScrollCalendar(Context context) {
        super(context);
        init(context);
    }

    public ScrollCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle(context, attrs);
        init(context);
    }

    public ScrollCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs);
        init(context);
    }

    private void init(@NonNull Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.scrollcalendar_calendar, this);
        for (int i = 0; i < legend.length; i++) {
            legend[i] = new LegendItem(i + 1);
        }
    }

    private void initStyle(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.ScrollCalendar, R.attr.scrollCalendarStyleAttr, R.style.ScrollCalendarStyle);
        currentDayBackground = typedArray.getResourceId(Keys.CURRENT_DAY_BG, Defaults.CURRENT_DAY_BG);
        fontColor = typedArray.getColor(Keys.FONT_COLOR, ContextCompat.getColor(context, Defaults.FONT_COLOR));
        backgroundColor = typedArray.getColor(Keys.BACKGROUND_COLOR, ContextCompat.getColor(context, Defaults.BACKGROUND_COLOR));
        disabledTextColor = typedArray.getColor(Keys.DISABLED_TEXT_COLOR, ContextCompat.getColor(context, Defaults.DISABLED_TEXT_COLOR));
        disabledBackgroundColor = typedArray.getColor(Keys.DISABLED_BACKGROUND_COLOR, ContextCompat.getColor(context, Defaults.DISABLED_BACKGROUND_COLOR));
        unavailableTextColor = typedArray.getColor(Keys.UNAVAILABLE_TEXT_COLOR, ContextCompat.getColor(context, Defaults.UNAVAILABLE_TEXT_COLOR));
        selectedTextColor = typedArray.getColor(Keys.SELECTED_TEXT_COLOR, ContextCompat.getColor(context, Defaults.SELECTED_TEXT_COLOR));
        todayTextColor = typedArray.getColor(Keys.TODAY_TEXT_COLOR, ContextCompat.getColor(context, Defaults.TODAY_TEXT_COLOR));
        unavailableBackground = typedArray.getResourceId(Keys.UNAVAILABLE_BACKGROUND, Defaults.UNAVAILABLE_BACKGROUND);
        selectedBackground = typedArray.getResourceId(Keys.SELECTED_BACKGROUND, Defaults.SELECTED_BACKGROUND);
        selectedBackgroundBeginning = typedArray.getResourceId(Keys.SELECTED_BACKGROUND_BEGINNING, Defaults.SELECTED_BACKGROUND_BEGINNING);
        selectedBackgroundMiddle = typedArray.getResourceId(Keys.SELECTED_BACKGROUND_MIDDLE, Defaults.SELECTED_BACKGROUND_MIDDLE);
        selectedBackgroundEnd = typedArray.getResourceId(Keys.SELECTED_BACKGROUND_END, Defaults.SELECTED_BACKGROUND_END);
        defaultAdapter = typedArray.getInt(Keys.ADAPTER, Defaults.ADAPTER);
        fontSize = typedArray.getDimension(Keys.FONT_SIZE, getResources().getDimensionPixelSize(Defaults.FONT_SIZE));
        customFont = typedArray.getString(Keys.CUSTOM_FONT);
        typedArray.recycle();
    }

    public void setOnDateClickListener(@Nullable final OnDateClickListener calendarCallback) {
        getAdapter().setOnDateClickListener(calendarCallback);
    }

    @SuppressWarnings("unused")
    public void refresh() {
        refreshLegend();
        getAdapter().notifyDataSetChanged();
    }

    public void setDateWatcher(@Nullable final DateWatcher dateWatcher) {
        getAdapter().setDateWatcher(dateWatcher);
    }

    public void setMonthScrollListener(@Nullable final MonthScrollListener monthScrollListener) {
        getAdapter().setMonthScrollListener(monthScrollListener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // Legend
        LinearLayout legendHolder = findViewById(R.id.legend);
        for (LegendItem legendItem : legend) {
            legendHolder.addView(legendItem.layout(legendHolder, this));
        }
        refreshLegend();
        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.setAdapter(getAdapter());
    }

    private void refreshLegend() {
        for (LegendItem legendItem : legend) {
            legendItem.display();
        }
    }

    @NonNull
    public ScrollCalendarAdapter getAdapter() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        return adapter;
    }

    private ScrollCalendarAdapter createAdapter() {
        switch (defaultAdapter) {
            case 1:
                return new DefaultDateScrollCalendarAdapter(this);
            case 2:
                return new DefaultRangeScrollCalendarAdapter(this);
            case 0:
            default:
                return new ScrollCalendarAdapter(this);
        }
    }

    // Colors
    @ColorInt
    @Override
    public int defaultFontColor() {
        return fontColor;
    }

    @ColorInt
    @Override
    public int defaultBackgroundColor() {
        return backgroundColor;
    }

    @ColorInt
    @Override
    public int disabledTextColor() {
        return disabledTextColor;
    }

    @ColorInt
    @Override
    public int disabledBackgroundColor() {
        return disabledBackgroundColor;
    }

    @ColorInt
    @Override
    public int todayTextColor() {
        return todayTextColor;
    }

    @ColorInt
    @Override
    public int unavailableTextColor() {
        return unavailableTextColor;
    }

    @ColorInt
    @Override
    public int selectedTextColor() {
        return selectedTextColor;
    }

    // Drawables

    @Override
    @DrawableRes
    public int todayBackground() {
        return currentDayBackground;
    }

    @Override
    @DrawableRes
    public int unavailableBackgroundColor() {
        return unavailableBackground;
    }

    @Override
    public int selectedBackgroundColor() {
        return selectedBackground;
    }

    @Override
    public int selectedBeginningBackgroundColor() {
        return selectedBackgroundBeginning;
    }

    @Override
    public int selectedEndBackgroundColor() {
        return selectedBackgroundEnd;
    }

    @Override
    public int selectedMiddleBackgroundColor() {
        return selectedBackgroundMiddle;
    }

    @Dimension
    @Override
    public float fontSize() {
        return fontSize;
    }

    // Other

    @Override
    @Nullable
    public Typeface getCustomFont() {
        if (customFont == null) {
            return null;
        }
        try {
            return Typeface.createFromAsset(getContext().getAssets(), customFont);
        } catch (Exception e) {
            return null;
        }
    }


}
