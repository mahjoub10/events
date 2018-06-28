package com.events.events.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Speaker entity.
 */
@Entity
@DiscriminatorValue("speaker")
public class Speaker extends  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName ;

    private String lastName ;

    private String mobile ;


    @ManyToMany(mappedBy = "speakers")
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
