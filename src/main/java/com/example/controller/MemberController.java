package com.example.controller;

import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 아래와 같이 쓸 수 있겠지만,
    // 멤버 서비스는 여러개 만들 필요없이, 하나 만들고 다른 컨트롤러에서 공유하면 된다.
    // 그래서 스프링 컨테이너한테 등록을 하고 쓰는게 낫다. -> 하나만 등록된다.
    //private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    @Autowired
    // MemberController 는 스프링 컨테이너가 뜰 때 자동으로 생성되는데
    // @Autowired 가 있으면 스프링 컨테이너에 있는 멤버서비스를 생성자의 파라미터와 딱 연결시켜준다. -> 이게 DI 지
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
