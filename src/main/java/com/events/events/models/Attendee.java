package com.events.events.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Attendee entity
 */
@Entity
@DiscriminatorValue("attendee")
public class Attendee  extends  User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String firstName ;

    private String lastName ;




    @ManyToMany(mappedBy = "attendees")
    private Set<Event> events = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
