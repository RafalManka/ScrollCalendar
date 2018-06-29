package pl.rafman.scrollcalendar.values;

import android.support.annotation.StyleableRes;

import pl.rafman.scrollcalendar.R;

/**
 * Created by rafal.manka on 15/09/2017
 */
public interface Keys {
    @StyleableRes
    int FONT_COLOR = R.styleable.ScrollCalendar_fontColor;
    @StyleableRes
    int BACKGROUND_COLOR = R.styleable.ScrollCalendar_backgroundColor;
    @StyleableRes
    int DISABLED_TEXT_COLOR = R.styleable.ScrollCalendar_disabledTextColor;
    @StyleableRes
    int DISABLED_BACKGROUND_COLOR = R.styleable.ScrollCalendar_disabledBackgroundColor;
    @StyleableRes
    int UNAVAILABLE_TEXT_COLOR = R.styleable.ScrollCalendar_unavailableTextColor;
    @StyleableRes
    int UNAVAILABLE_BACKGROUND = R.styleable.ScrollCalendar_unavailableBackground;
    @StyleableRes
    int CUSTOM_FONT = R.styleable.ScrollCalendar_customFont;
    @StyleableRes
    int ADAPTER = R.styleable.ScrollCalendar_adapter;
}
