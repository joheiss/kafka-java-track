package com.jovisco.track.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jovisco.dispatch.message.DispatchPreparing;
import com.jovisco.dispatch.message.TrackingStatusUpdated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackService {

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(DispatchPreparing dispatchPreparing) throws Exception {
        log.info("Received dispatch preparing message: " + dispatchPreparing);

        TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
            .orderId(dispatchPreparing.getOrderId())
            .status(TrackingStatus.PREPARING)
            .build();

        kafkaProducer.send(TRACKING_STATUS_TOPIC, trackingStatusUpdated).get();
    }
    
}
