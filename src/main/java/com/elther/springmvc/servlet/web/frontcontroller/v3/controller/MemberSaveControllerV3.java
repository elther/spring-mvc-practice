package com.elther.springmvc.servlet.web.frontcontroller.v3.controller;

import com.elther.springmvc.servlet.domain.member.Member;
import com.elther.springmvc.servlet.domain.member.MemberRepository;
import com.elther.springmvc.servlet.web.frontcontroller.ModelView;
import com.elther.springmvc.servlet.web.frontcontroller.MyView;
import com.elther.springmvc.servlet.web.frontcontroller.v2.ControllerV2;
import com.elther.springmvc.servlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}
