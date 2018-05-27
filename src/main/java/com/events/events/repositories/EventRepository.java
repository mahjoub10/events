package com.events.events.repositories;

import com.events.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * DAO repository for events.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Find event using id.
     *
     * @param id
     * @return
     */
    Optional<Event> findOneById(long id);
}
