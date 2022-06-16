package com.elther.springmvc.servlet.web.springmvc.v2;

import com.elther.springmvc.servlet.domain.member.Member;
import com.elther.springmvc.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newform(){
        return new ModelAndView("new-form");
    }

    @RequestMapping
    public ModelAndView members(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelAndView modelView = new ModelAndView("members");
        modelView.addObject("members", members);

        return modelView;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter(("age")));
        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView modelView = new ModelAndView("save-result");
        modelView.addObject("member", member);
        return modelView;
    }
}