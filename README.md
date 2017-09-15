# Scroll Calendar

Android widget to present calendar in a recycler view. The idea was to
replicate calendar the way calendar is presented in the amazing
Airbnb app.

![Example App](gif.gif)

## Installing

Improt the library into gradle

```
compile 'pl.rafalmanka:scroll-calendar:1.3.0', {
    exclude group: 'com.android.support', module: 'recyclerview-v7'
    exclude group: 'com.android.support', module: 'appcompat-v7'
}
```

### Getting Started

Define layout in your xml file

```
<pl.rafalmanka.scrollcalendar.ScrollCalendar
        android:id="@+id/scrollCalendar"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        scrollcalendar:backgroundColor="@android:color/transparent"
        scrollcalendar:currentDayBackground="@drawable/circle_outline"
        scrollcalendar:currentDayTextColor="@android:color/darker_gray"
        scrollcalendar:customFont="fonts/montserrat-light.otf"
        scrollcalendar:disabledBackgroundColor="@android:color/transparent"
        scrollcalendar:disabledTextColor="@android:color/darker_gray"
        scrollcalendar:fontColor="@android:color/black"
        scrollcalendar:selectedBackground="@drawable/circle_full"
        scrollcalendar:selectedTextColor="@android:color/white"
        scrollcalendar:unavailableBackground="@drawable/dash"
        scrollcalendar:unavailableTextColor="@android:color/darker_gray" />
```

Reference the widget in your Activity/Fragment and set callback

```
ScrollCalendar scrollCalendar = (ScrollCalendar) findViewById(R.id.scrollCalendar);
scrollCalendar.setCallback(new ScrollCalendarCallback() {

    @State
    @Override
    public int getStateForDate(int year, int month, int day) {
        //    CalendarDay.DEFAULT,
        //    CalendarDay.DISABLED,
        //    CalendarDay.TODAY,
        //    CalendarDay.UNAVAILABLE,
        //    CalendarDay.SELECTED,
        return CalendarDay.DEFAULT;
    }

    @Override
    public void onCalendarDayClicked(int year, int month, int day) {
        // user clicked on a specific date on the calendar
    }

    @Override
    public boolean shouldAddNextMonth(int lastDisplayedYear, int lastDisplayedMonth) {
        // return false if you don't want to show later months
        return true;
    }

});
```

## Known limitations

Right now the library does not support ranges. If you have need for
ranges please request that feature or create pull request.

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

