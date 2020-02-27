package com.example.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PlanItController {

    AllUsers allUsers;

    public PlanItController(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    @GetMapping("/login")
    public String showLoginSite(){
        //System.out.println(allUsers.getAllUsers().size());
        //System.out.println(allUsers.getAllUsers());
        return "login2";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Budget budget, HttpSession session,@RequestParam String username , @RequestParam String password) throws WrongUserNameAndPasswordException {

        for (User user : allUsers.getAllUsers()) {
            System.out.println(user.getUsername());
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                session.setAttribute("logger", username);
                return "dash";
            }
        }
        throw new WrongUserNameAndPasswordException();
    }

    @GetMapping("/dash")
    public String showDash(HttpSession session, @ModelAttribute Budget budget, Model model){

        model.addAttribute("budget", session.getAttribute("budget"));

        String username = (String) session.getAttribute("logger");
        if(username != null){
            return "dash";
        }else {
            // session.getAttribute("logger");
            return "login2";
        }
    }

/*    @GetMapping("/list")
    public String showList(){
        return "list";
    }*/

    @GetMapping("/")
    String logOutDashboard(HttpSession session){
        session.removeAttribute("logger");
        session.invalidate();
        return "login2";
    }

    @ExceptionHandler(WrongUserNameAndPasswordException.class)
    String inValidNumber(Model model){
        model.addAttribute("invalidUser", "Invalid Username or password");
        return"login2";
    }
}
