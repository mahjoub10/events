package com.events.events.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Event entity.
 */
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date start;

    private String subject ;

    private String description;


    @ManyToMany()
    @JoinTable(name = "events_attendees",
      joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "attendee_id", referencedColumnName = "id")})
    private Set<Attendee> attendees = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "events_speakers",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "speaker_id", referencedColumnName = "id")})
    private Set<Speaker> speakers = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Request> requests = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}
