package com.chohk.demorestapi.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest  // web에 관련된 테스트만 할 수 있다. slicing test : mockMvc를 주입받아 사용할 수 있다.
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc; // 요청검증을 할 수 있는 매우 유용한 클래스 웹서버를 띄우지 않고 디스패처 서블릿으로 확인

    @Test
    public void createEvent() throws Exception {
        // perform 안에 주는 것이 요청 즉 post로 요청
        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaTypes.HAL_JSON)
                )
                .andExpect(status().isCreated());        //status().is("201") == status().isCreated() : 뒤의 것이 typesafe
    }


}
