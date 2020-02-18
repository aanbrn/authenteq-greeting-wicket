package com.authenteq.greeting.pages;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class BaseTests {

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

}
