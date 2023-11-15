package com.jovisco.dispatch.message;

import java.util.UUID;

import com.jovisco.track.service.TrackingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingStatusUpdated {

    UUID orderId;
    TrackingStatus status;
}
