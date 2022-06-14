package com.elther.springmvc.servlet.web.frontcontroller.v4.controller;

import com.elther.springmvc.servlet.domain.member.Member;
import com.elther.springmvc.servlet.domain.member.MemberRepository;
import com.elther.springmvc.servlet.web.frontcontroller.Model;
import com.elther.springmvc.servlet.web.frontcontroller.ModelView;
import com.elther.springmvc.servlet.web.frontcontroller.v3.ControllerV3;
import com.elther.springmvc.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members";
    }
}
