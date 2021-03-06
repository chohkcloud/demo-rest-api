package com.chohk.demorestapi.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Inflean Spring dev")
                .description("REST API")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){

        // Given
        String name = "Event";
        String description = "Spring Rest Api";

        // when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}