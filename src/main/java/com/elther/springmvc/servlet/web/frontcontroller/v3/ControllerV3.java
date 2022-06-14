package com.elther.springmvc.servlet.web.frontcontroller.v3;

import com.elther.springmvc.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
