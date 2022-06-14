package com.elther.springmvc.servlet.web.frontcontroller.v2.controller;

import com.elther.springmvc.servlet.web.frontcontroller.MyView;
import com.elther.springmvc.servlet.web.frontcontroller.v1.ControllerV1;
import com.elther.springmvc.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}