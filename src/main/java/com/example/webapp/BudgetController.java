package com.example.webapp;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/budget")
    public String showBudget(@ModelAttribute Budget budget, HttpSession s, Model m) {


        String username = (String) s.getAttribute("logger");
        if (username != null) {

            User u = userRepository.findUserByUsername(username);

            m.addAttribute("userId", u.getId());

            m.addAttribute("budget", u.getBudget());

            return "budget";
        }

        return "login2";
    }


    @PostMapping("/budget")
    public String setValues(@ModelAttribute Budget budget, Model m, @RequestParam long userId) {

        User user = userRepository.findById(userId).get();
        budgetRepository.save(budget);
        user.setBudget(budget);

        userRepository.save(user);

        m.addAttribute("budget", user.getBudget());

        return "budget";
    }

}
