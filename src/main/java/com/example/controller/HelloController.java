package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // mvc 방식
    @GetMapping("hello")
    public String hello(Model model) { // Spring 이 모델이라는 거를 만들어 넣어줌, 이 모델을 업데이트하여 나중에 view 에서 쓴다.
        model.addAttribute("data", "hello!!!");
        return "hello"; // 그러면 뷰 리졸버가 resources/templates/ 안에서 hello.html 를 찾는다.
    }

    // mvc 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 외부에서 parameter 를 받는다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    // Api 방식 1
    @GetMapping("hello-string")
    @ResponseBody // http 의 body 부분에 리턴값을 그대로 넣어주겠다는 의미, 리턴값을 template engine 과 같은 View 없이 그대로 내려주는 것이다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    // Api 방식 2
    @GetMapping("hello-api")
    @ResponseBody // 얘가 있으면 기존의 뷰 리졸버에게 던지는 방식이 아닌, HttpMessageConverter 가 작동한다.
                  // 그래서 문자면  StringHttpMessageConverter , 객체이면 MappingJackson2HttpMessageConverter
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // json 방식으로 객체가 리턴된다.
    }

    static class Hello {

        private String name;

        // java bean 규약??
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
