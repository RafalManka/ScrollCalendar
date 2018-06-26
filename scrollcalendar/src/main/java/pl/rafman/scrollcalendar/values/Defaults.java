package pl.rafman.scrollcalendar.values;

import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;

import pl.rafman.scrollcalendar.R;

/**
 * Created by rafal.manka on 15/09/2017
 */
public interface Defaults {
    // Colors
    @ColorRes
    int FONT_COLOR = android.R.color.black;
    @ColorRes
    int BACKGROUND_COLOR = android.R.color.transparent;
    @ColorRes
    int DISABLED_TEXT_COLOR = android.R.color.darker_gray;
    @ColorRes
    int DISABLED_BACKGROUND_COLOR = android.R.color.transparent;
    @ColorRes
    int UNAVAILABLE_TEXT_COLOR = android.R.color.darker_gray;
    @ColorRes
    int SELECTED_TEXT_COLOR = android.R.color.darker_gray;
    @ColorRes
    int TODAY_TEXT_COLOR = android.R.color.black;
    // Drawables
    @DrawableRes
    int CURRENT_DAY_BG = R.drawable.scrollcalendar_circle_outline;
    @DrawableRes
    int UNAVAILABLE_BACKGROUND = R.drawable.scrollcalendar_strikethrough;
    @DrawableRes
    int SELECTED_BACKGROUND = R.drawable.scrollcalendar_strikethrough;
    @DrawableRes
    int SELECTED_BACKGROUND_BEGINNING = R.drawable.scrollcalendar_range_start;
    @DrawableRes
    int SELECTED_BACKGROUND_MIDDLE = R.drawable.scrollcalendar_range_middle;
    @DrawableRes
    int SELECTED_BACKGROUND_END = R.drawable.scrollcalendar_range_end;
    // Dimens
    @DimenRes
    int FONT_SIZE = R.dimen.scrollcalendar_sp12;
    // enums
    int ADAPTER = 0;
}
