package com.example.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ListController {

    @GetMapping("/list")
    String showList(HttpSession session){
        String username = (String) session.getAttribute("logger");
        if(username != null){
            return "list";
        }else {
            // session.getAttribute("logger");
            return "login2";
        }

    }
}
