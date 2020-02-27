package com.example.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BudgetController {


    @GetMapping("/budget")
    public String showBudget(@ModelAttribute Budget budget, HttpSession s, Model m) {
        String username = (String) s.getAttribute("logger");
        if(username != null){
                if (s.getAttribute("budget") != null) {
                 m.addAttribute("budget", s.getAttribute("budget"));
                    return "budget";
                }
            return "budget";
        }

        return "login2";
    }


    @PostMapping("/budget")
    public String setValues(@ModelAttribute Budget budget, HttpSession s) {
        s.setAttribute("budget", budget);


        return "budget";
    }

}
