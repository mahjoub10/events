package com.events.events.repositories;

import com.events.events.models.Request;
import com.events.events.models.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findOneById(long id);

    List<Request> findByStatus(RequestStatus waiting);
}
