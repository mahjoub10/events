package com.events.events.repositories;

import com.events.events.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * DAO repository for attendees
 */
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    /**
     * Find an attendee using id.
     *
     * @param id
     * @return
     */
    Optional<Attendee> findOneById(long id);
}
