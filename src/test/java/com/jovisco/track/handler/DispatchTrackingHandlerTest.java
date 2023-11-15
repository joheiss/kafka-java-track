package com.jovisco.track.handler;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static java.util.UUID.randomUUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jovisco.dispatch.message.DispatchPreparing;
import com.jovisco.track.service.TrackService;
import com.jovisco.track.util.TestEventData;

public class DispatchTrackingHandlerTest {

    private TrackService trackServiceMock;
    private DispatchTrackingHandler handler;

    @BeforeEach
    public void setUp() {
        trackServiceMock = mock(TrackService.class);
        handler = new DispatchTrackingHandler(trackServiceMock);
    }

    @Test
    public void listen_Success() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(randomUUID());
        handler.listen(testEvent);
        verify(trackServiceMock, times(1)).process(testEvent);
    }
    
    @Test
    public void listen_Fail() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(randomUUID());
        doThrow(new RuntimeException("Service failed")).when(trackServiceMock).process(testEvent);
        handler.listen(testEvent);
        verify(trackServiceMock, times(1)).process(testEvent);
    }

}
