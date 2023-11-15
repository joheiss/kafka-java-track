package com.jovisco.track.util;

import java.util.UUID;

import com.jovisco.dispatch.message.DispatchPreparing;

public class TestEventData {

    public static DispatchPreparing buildDispatchPreparingEvent(UUID orderId) {
        return DispatchPreparing.builder()
                .orderId(orderId)
                .build();
    }
}
