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
    LoginValidator loginValidator;

    @Autowired
    UserRepository userRepository;

    AllUsers allUsers;

    public PlanItController(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    @GetMapping("/login")
    public String showLoginSite(Model model, @ModelAttribute User user){
        model.addAttribute("user", user);
        return "login2";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, @RequestParam String username , @RequestParam String password, BindingResult result, Model model) throws WrongUserNameAndPasswordException {
        if (loginValidator.supports(user.getClass())) {
            loginValidator.validate(user, result);
        }
        if (result.hasErrors()) {
            model.addAttribute("errorMes", "Failed!");
            return "login2";
        }
        User userQuery = userRepository.findUserByUsername(username);
        if (userQuery.getUsername().equals(username) && userQuery.getPassword().equals(password)) {
            session.setAttribute("logger", username);
            return "dash";
        } else {
            throw new WrongUserNameAndPasswordException();
        }
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
        model.addAttribute("user", new User());
        model.addAttribute("invalidUser", "Invalid Username or password");
        return"login2";
    }
}
