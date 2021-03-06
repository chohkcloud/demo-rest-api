package com.chohk.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest  // web에 관련된 테스트만 할 수 있다. slicing test : mockMvc를 주입받아 사용할 수 있다.
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc; // 요청검증을 할 수 있는 매우 유용한 클래스 웹서버를 띄우지 않고 디스패처 서블릿으로 확인

    @Autowired
    ObjectMapper objectMapper;

    /**
     *  한글깨짐 현상 setup()까지.
     */
//    @Autowired
//    private WebApplicationContext ctx;
//
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
//                .alwaysDo(print())
//                .build();
//    }



    @Test
    public void createEvent() throws Exception {
        // Event builder를 통해 요청할 본문을 전달하기
        Event event = Event.builder()
                .name("Spring")
                .description("REST API")
                .beginEnrollmentDateTime(LocalDateTime.of(2021,3,6,10,00))
                .closeEnrollmentDateTime(LocalDateTime.of(2021,3,6,11,00))
                .beginEventDateTime(LocalDateTime.of(2021,3,7,9,00))
                .endEventDateTime(LocalDateTime.of(2021,3,7,23,00))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();


        // perform 안에 주는 것이 요청 즉 post로 요청
        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(event)))  // 본문을 objectMapper를 사용해 json형태로 만들어 줌.
                .andDo(print())
                .andExpect(status().isCreated())    //status().is("201") == status().isCreated() : 뒤의 것이 typesafe
                .andExpect(jsonPath("id").exists()) ;
    }


}
