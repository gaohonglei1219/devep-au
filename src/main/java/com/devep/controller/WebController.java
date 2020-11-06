package com.devep.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin
public class WebController {

    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView hello(HttpSession session){
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }

    @RequestMapping("/indexM")
    @ResponseBody
    public ModelAndView hello1(HttpSession session){
        ModelAndView mv = new ModelAndView("indexM.html");
        return mv;
    }

    @RequestMapping("/project")
    @ResponseBody
    public ModelAndView hello2(HttpSession session){
        ModelAndView mv = new ModelAndView("projectProcess.html");
        return mv;
    }


}
