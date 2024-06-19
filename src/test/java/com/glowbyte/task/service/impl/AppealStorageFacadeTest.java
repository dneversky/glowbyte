package com.glowbyte.task.service.impl;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.service.AppealPriceCalculator;
import com.glowbyte.task.service.LocalStorageService;
import com.glowbyte.task.service.RemoteStorageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.glowbyte.task.util.Factory.appeal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class AppealStorageFacadeTest {

    @Mock
    LocalStorageService<String> localStorageService;

    @Mock
    RemoteStorageService remoteStorageService;

    @Mock
    AppealPriceCalculator appealPriceCalculator;

    @InjectMocks
    AppealStorageFacade appealStorageFacade;

    @Test
    void store() {
        Appeal appeal = appeal();

        when(localStorageService.store(any(), any())).thenReturn("/home");
        appealStorageFacade.store(appeal);

        verify(appealPriceCalculator).calculate(appeal);
        verify(localStorageService).store(any(), any());
        verify(remoteStorageService).store(any(), any());
    }
}
