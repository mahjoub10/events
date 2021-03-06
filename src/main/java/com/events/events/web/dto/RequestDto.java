package com.events.events.web.dto;

import com.events.events.models.RequestStatus;
import com.events.events.models.RequestType;

import java.util.Date;

public class RequestDto {

    private long id;

    private long speakerId;

    private String speakerFullName ;

    private String eventSubject ;

    private long eventId ;

    private RequestType type ;

    private RequestStatus status ;

    private Date date ;

    private String description ;

    public long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(long speakerId) {
        this.speakerId = speakerId;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getSpeakerFullName() {
        return speakerFullName;
    }

    public void setSpeakerFullName(String speakerFullName) {
        this.speakerFullName = speakerFullName;
    }

    public String getEventSubject() {
        return eventSubject;
    }

    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
