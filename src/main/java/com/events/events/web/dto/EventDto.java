package com.events.events.web.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Event dto representation.
 */
public class EventDto {

    private long id;

    private String name;

    private Date start;

    private String subject;

    private List<Long> speakerIds;

    private String description;

    private Set<AttendeeDto> attendees = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }


    public List<Long> getSpeakerIds() {
        return speakerIds;
    }

    public void setSpeakerIds(List<Long> speakerIds) {
        this.speakerIds = speakerIds;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<AttendeeDto> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<AttendeeDto> attendees) {
        this.attendees = attendees;
    }
}
