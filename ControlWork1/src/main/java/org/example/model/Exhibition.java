package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exhibition {

    Long id;

    LocalTime startWorkingTime;

    LocalTime endWorkingTime;

    LocalDate startDate;

    LocalDate endDate;

    public Exhibition(Long id, LocalTime startWorkingTime, LocalTime endWorkingTime, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startWorkingTime = startWorkingTime;
        this.endWorkingTime = endWorkingTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(LocalTime startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public LocalTime getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(LocalTime endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
