package com.events.events.repositories;

import com.events.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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


    @Query("SELECT e FROM Event e WHERE e.start > CURRENT_DATE")
    List<Event> findEventByDate();

    List<Event> findEventBySpeakersIdIn( long id);

}
