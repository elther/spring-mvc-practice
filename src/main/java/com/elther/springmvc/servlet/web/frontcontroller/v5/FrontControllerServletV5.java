package com.elther.springmvc.servlet.web.frontcontroller.v5;

import com.elther.springmvc.servlet.web.frontcontroller.ModelView;
import com.elther.springmvc.servlet.web.frontcontroller.MyView;
import com.elther.springmvc.servlet.web.frontcontroller.v3.ControllerV3;
import com.elther.springmvc.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.elther.springmvc.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.elther.springmvc.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.elther.springmvc.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.elther.springmvc.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.elther.springmvc.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.elther.springmvc.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.elther.springmvc.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdaper> handlerAdapers = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapers.add(new ControllerV3HandlerAdapter());
        handlerAdapers.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);
        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdaper adaper = getHandlerAdapter(handler);
        ModelView mv = adaper.handle(request, response, handler);
        String viewName = mv.getViewName();

        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdaper getHandlerAdapter(Object handler) {
        for (MyHandlerAdaper i : handlerAdapers) {
            if(i.supports(handler)){
                return i;
            }
        }
        throw new IllegalArgumentException("handler adapter not found");
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
