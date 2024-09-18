/**
 * Name: WorkEntry                                 Last Updated: 11/18/24
 * 
 * Creator: D.Balin
 * 
 * Purpose: Class to story date and hours worked
 */


import java.time.LocalDate;

class WorkEntry {
    private LocalDate date;
    private long hoursWorked;

    public WorkEntry(LocalDate date, long hoursWorked) {
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getHoursWorked() {
        return hoursWorked;
    }
}
