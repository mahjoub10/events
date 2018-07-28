package com.events.events.repositories;

import com.events.events.models.Request;
import com.events.events.models.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findOneById(long id);

    @Query(value = "select req from Request req where req.status=:status and  req.event.organizer.id=:organizerId and req.event.start  > CURRENT_DATE")
    List<Request> findByStatusAndOrganizer(@Param(("status")) RequestStatus status, @Param("organizerId") long organizerId);

    @Query(value = "Select req from Request  req where req.speaker.id=:speakerId and req.event.id=:eventId")
    List<Request> findByEventAndSpeaker(@Param("eventId") long eventId, @Param("speakerId") long speakerId);
}
