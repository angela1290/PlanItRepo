package com.example.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    AllUsers allUsers;

    public RegisterController(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    @GetMapping("/register")
    public String showRegister(Model m, @ModelAttribute User user) {
        m.addAttribute("allUser", allUsers.getAllUsers());
        m.addAttribute("tempRegisterUser", user);

        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(Model m, @ModelAttribute User user, @RequestParam String password2) throws WrongPasswordException, SameUserNameException {
        boolean isInDatabase = userRepository.existsUserByUsername(user.getUsername());

        if (isInDatabase) {
            allUsers.addNewUser(user);
            m.addAttribute("tempRegisterUser", user);
            throw new SameUserNameException();
        }

        if (!user.getPassword().equals(password2)) {
            allUsers.addNewUser(user);
            m.addAttribute("tempRegisterUser", user);
            throw new WrongPasswordException();
        }

            Budget budget = new Budget();
            budgetRepository.save(budget);
            user.setBudget(budget);
            userRepository.save(user);


        m.addAttribute("allUser", allUsers.getAllUsers());
        return "login2";
    }

    @ExceptionHandler(WrongPasswordException.class)
    String inValidNumber(Model model) {
        model.addAttribute("tempRegisterUser", allUsers.getTempararyUser());
        model.addAttribute("invalidPassword", "Password doesn't match");
        allUsers.removeTempararyUser();
        return "register";
    }

    @ExceptionHandler(SameUserNameException.class)
    String invalidUsername(Model model) {
        model.addAttribute("tempRegisterUser", allUsers.getTempararyUser());
        model.addAttribute("invalidUserName", String.format("User %s already exists", allUsers.getTempararyUser().getUsername()));
        allUsers.removeTempararyUser();
        return "register";
    }


}
