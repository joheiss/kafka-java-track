package com.jovisco.track.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jovisco.dispatch.message.DispatchPreparing;
import com.jovisco.track.service.TrackService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DispatchTrackingHandler {

    @Autowired
    private final TrackService trackService;

     @KafkaListener(
        id = "dsipatchTrackingConsumerClient",
        topics = "dispatch.tracking",
        groupId = "tracking.dispatch.tracking.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing dispatchPreparing) {
        log.info("DISPATCH TRACKING Message received: " + dispatchPreparing);
        try {
            trackService.process(dispatchPreparing);
        } catch (Exception e) {
            log.error("Processing failed", e);
        }
    }
    
}
