package pl.rafman.scrollcalendar.contract;

/**
 * Created by rafal.manka on 18/09/2017
 */
public interface SubTitleWatcher {
    /**
     * Get subtitle for the date to shown in view
     * @param year year of the date
     * @param month month of the date
     * @param day day number of the date
     * @return subtitle. If "" or null, subtitle will be hidden
     */
    String getSubTitleForDate(int year, int month, int day);
}
