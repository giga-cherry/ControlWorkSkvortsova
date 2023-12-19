package org.example.model;

import java.time.LocalDateTime;

public class Booking {

    Long id;

    Long userId;

    Long exhibitionId;

    LocalDateTime timeFrom;

    LocalDateTime timeTo;

    Long type;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public Long getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Booking(Long id, Long userId, Long exhibitionId, LocalDateTime timeFrom, LocalDateTime timeTo, Long type) {
        this.id = id;
        this.userId = userId;
        this.exhibitionId = exhibitionId;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.type = type;
    }


}
