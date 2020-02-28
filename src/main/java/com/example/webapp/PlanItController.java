package com.example.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class PlanItController {

    @Autowired
    UserRepository userRepository;

    AllUsers allUsers;

    public PlanItController(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    @GetMapping("/login")
    public String showLoginSite(Model model){
        model.addAttribute("user", new User());
        return "login2";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, @RequestParam String username , @RequestParam String password, BindingResult result, Model model) throws WrongUserNameAndPasswordException {
       LoginValidator loginValidator = new LoginValidator();
       if(loginValidator.supports(user.getClass())){
           loginValidator.validate(user,result);
       }
       if(result.hasErrors()){
           model.addAttribute("errorMes", "Failed!");
           return "login2";

       }

         user = userRepository.findUserByUsername(username);
       if(user.getUsername().equals(username) && user.getPassword().equals(password)){
           session.setAttribute("logger", username);
           return "dash";
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
