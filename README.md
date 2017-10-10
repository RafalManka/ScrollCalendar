# Scroll Calendar

[![Download](https://api.bintray.com/packages/rafalmanka/maven/scroll-calendar/images/download.svg?version=1.5.0) ](https://bintray.com/rafalmanka/maven/scroll-calendar/1.5.0/link)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ScrollCalendar-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6228)

Android widget to present calendar in a recycler view. The idea was to
replicate calendar the way calendar is presented in the amazing
Airbnb app.

![Example App](gif.gif)

## Installing

Improt the library into gradle

```
compile 'pl.rafman.widgets:scroll-calendar:1.5.0', {
    exclude group: 'com.android.support'
}
```

### Getting Started

Define layout in your xml file

```xml
<pl.rafman.scrollcalendar.ScrollCalendar
        android:id="@+id/scrollCalendar"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        scrollcalendar:backgroundColor="@android:color/transparent"
        scrollcalendar:currentDayBackground="@drawable/scrollcalendar_circle_outline"
        scrollcalendar:currentDayTextColor="@android:color/darker_gray"
        scrollcalendar:customFont="fonts/montserrat-light.otf"
        scrollcalendar:disabledBackgroundColor="@android:color/transparent"
        scrollcalendar:disabledTextColor="@android:color/darker_gray"
        scrollcalendar:fontColor="@android:color/black"
        scrollcalendar:fontSize="18dp"
        scrollcalendar:selectedBackground="@drawable/scrollcalendar_circle_full"
        scrollcalendar:selectedBackgroundBeginning="@drawable/scrollcalendar_range_start"
        scrollcalendar:selectedBackgroundEnd="@drawable/scrollcalendar_range_end"
        scrollcalendar:selectedBackgroundMiddle="@drawable/scrollcalendar_range_middle"
        scrollcalendar:selectedTextColor="@android:color/white"
        scrollcalendar:unavailableBackground="@drawable/scrollcalendar_strikethrough"
        scrollcalendar:unavailableTextColor="@android:color/darker_gray" />
```

Reference the widget in your Activity/Fragment and set callback

```java
ScrollCalendar scrollCalendar = (ScrollCalendar) findViewById(R.id.scrollCalendar);
scrollCalendar.setOnDateClickListener(new OnDateClickListener() {
    @Override
    public void onCalendarDayClicked(int year, int month, int day) {
        // user clicked on a specific date on the calendar
    }
});
scrollCalendar.setDateWatcher(new DateWatcher() {
    @Override
    public int getStateForDate(int year, int month, int day) {
        //    CalendarDay.DEFAULT,
        //    CalendarDay.DISABLED,
        //    CalendarDay.TODAY,
        //    CalendarDay.UNAVAILABLE,
        //    CalendarDay.SELECTED,
        return CalendarDay.DEFAULT;
    }
});
scrollCalendar.setMonthScrollListener(new MonthScrollListener() {
    @Override
    public boolean shouldAddNextMonth(int lastDisplayedYear, int lastDisplayedMonth) {
        // return false if you don't want to show later months
        return true;
    }
    @Override
    public boolean shouldAddPreviousMonth(int firstDisplayedYear, int firstDisplayedMonth) {
        // return false if you don't want to show previous months
        return true;
    }
});
```

## Default behavior
* Endless scrolling to the future. By default you can scroll the calendar
Endlessly. If you want to stop adding months to the bottom of the adapter
set MonthScrollListener callback and return false for the last month
that you want to appear in shouldAddNextMonth method.
* Endless scrolling to the past. By default you can not scroll to previous
months. If you want to keep adding previous months potentially to infinity
set MonthScrollListener callback and return true in shouldAddPreviousMonth
method

## States
Calendar supports Different states that a day can have in a month.
Each state can be expressed by applying specific drawable background and
a text color.
* CalendarDay.DEFAULT - Regular day with no background
```
scrollcalendar:fontColor="@android:color/black"
```
* CalendarDay.DISABLED - When you want to indicate that the date
is not available.
```
scrollcalendar:disabledBackgroundColor="@android:color/transparent"
scrollcalendar:disabledTextColor="@android:color/darker_gray"
```
* CalendarDay.TODAY - for expressing current day
```
scrollcalendar:currentDayBackground="@drawable/scrollcalendar_circle_outline"
scrollcalendar:currentDayTextColor="@android:color/darker_gray"
```
* CalendarDay.UNAVAILABLE - when day is not available take, or invalid.
By default this state is expressed by strikethrough.
```
scrollcalendar:unavailableBackground="@drawable/scrollcalendar_strikethrough"
scrollcalendar:unavailableTextColor="@android:color/darker_gray"
```
* CalendarDay.SELECTED - When a day is selected. By default it's a ble
circle with white text. In order to display ranges properly you need
to set Beginning, middle and end drawables according to your style.
Proper drawables will be used automatically when two or more selected
dates are placed next to each other.
```
scrollcalendar:selectedBackground="@drawable/scrollcalendar_circle_full"
scrollcalendar:selectedBackgroundBeginning="@drawable/scrollcalendar_range_start"
scrollcalendar:selectedBackgroundEnd="@drawable/scrollcalendar_range_end"
scrollcalendar:selectedBackgroundMiddle="@drawable/scrollcalendar_range_middle"
scrollcalendar:selectedTextColor="@android:color/white"
```

## Contributing

* File [bug report](https://github.com/RafalManka/ScrollCalendar/issues/new)
* Request [feature](https://github.com/RafalManka/ScrollCalendar/issues/new)
* Create [Pull request](https://github.com/RafalManka/ScrollCalendar/pulls)

## Authors

* **Rafal Manka** - [Linkedin](https://www.linkedin.com/in/rafał-mańka-40ba2b5b)


## License

This project is licensed under the Apache 2.0 License.

## Acknowledgments

* Inspiration - Airbnb

