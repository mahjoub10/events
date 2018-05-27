package com.events.events.repositories;

import com.events.events.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    /**
     * Find speaker using id.
     *
     * @param id
     * @return
     */
    Optional<Speaker> findOneById(long id);
}
