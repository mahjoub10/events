package com.events.events.web;


import com.events.events.models.RequestStatus;
import com.events.events.services.RequestService;
import com.events.events.web.dto.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Request controller that groups the WS endpoints.
 */
@RequestMapping("/api/requests")
@RestController
public class RequestResource {

    private Logger logger = LoggerFactory.getLogger(RequestResource.class);

    @Autowired
    private RequestService requestService;


    @PostMapping("/add")
    public ResponseEntity<Object> addRequest(@RequestBody RequestDto dto) {
        logger.info("WS call to add new request");

        RequestDto result = requestService.addRequest(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> changeRequestStatus(@RequestParam("requestId") long requestId, @RequestParam("status") RequestStatus status) {
        logger.info("WS call to change request status : '{}' for the request  : '{}", status, requestId);

        boolean result = requestService.changeRequestStatus(requestId, status);
        return  ResponseEntity.ok(result);

    }

    @GetMapping("/get")
    public ResponseEntity<List<RequestDto>> getRequestList(){
        logger.info("WS call to get request list");

        List<RequestDto> requests = requestService.findALlRequest();
        return  ResponseEntity.ok(requests);
    }
}
