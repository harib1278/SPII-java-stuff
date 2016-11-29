package date;

public interface Date {
    // public Date(int year, int month, int day);
    // public Date(); // today
    int getDay();
    int getMonth();
    int getYear();
    void addDays(int days); // advances by days
    int daysInMonth();
    String dayOfWeek(); // e.g. "Sunday"
    boolean equals(Object o);
    boolean isLeapYear();
    void nextDay(); // advances by 1 day
    String toString();
}
