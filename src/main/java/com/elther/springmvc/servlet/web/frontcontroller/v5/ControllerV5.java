package com.elther.springmvc.servlet.web.frontcontroller.v5;

import java.util.Map;

public interface ControllerV5 {

    /**
     *
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
