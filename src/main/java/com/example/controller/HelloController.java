package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // Spring 이 모델이라는 거를 만들어 넣어줌
        model.addAttribute("data","hello!!!");
        return "hello"; // 우선 resources/templates/ 안에서 hello.html 를 찾는다. 이는 뷰리졸버가 처리한다.
    }
}
