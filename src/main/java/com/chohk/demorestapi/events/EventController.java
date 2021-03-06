package com.chohk.demorestapi.events;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static com.chohk.demorestapi.consts.EventConst.HAL_JSON_VALUE_UTF8;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = HAL_JSON_VALUE_UTF8)
public class EventController {

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {   //ResponseEntity는 HttpEntity를 상속받음으로써 HttpHeader와 body를 가질 수 있다.

        URI createdUri = linkTo(EventController.class).slash("{id}").toUri();
        event.setId(10);
        return ResponseEntity.created(createdUri).body(event);
    }
}
