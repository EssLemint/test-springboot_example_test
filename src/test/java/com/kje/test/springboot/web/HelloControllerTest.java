package com.kje.test.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
//Junit springrunner을 사용하겠다는 선언
@WebMvcTest(controllers =  HelloController.class)
//스프링 테스트 어노테이션
public class HelloControllerTest {

    @Autowired //빈등록
    private MockMvc mvc;
    //웹 API 테스트 사용
    //스프링 MVC의 시작점
    //위의 클래스를 통해서 HTTP의 POST,GET의 API테스트가 가능하다

    @Test
    public void hello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))  //get test
                .andExpect(status().isOk())     //200 검증
                .andExpect(content().string(hello));    //출력물
    }


    @Test
    public void helloDto리턴() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount) ));
                //import static org.hamcrest.Matchers.is;
                //hamcrest는 JUnit에 사용되는 Matcher 라이브러리

    }
}