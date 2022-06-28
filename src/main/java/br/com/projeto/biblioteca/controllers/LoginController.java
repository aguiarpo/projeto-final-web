package br.com.projeto.biblioteca.controllers;

import br.com.projeto.biblioteca.dto.UserRegister;
import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.models.enums.TypeUsers;
import br.com.projeto.biblioteca.repository.UserRepository;
import br.com.projeto.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping()
    @RequestMapping("/register")
    public String register(@ModelAttribute("user") UserRegister user) {
        return "register";
    }

    @PostMapping("/register/create")
    public String register(@Valid @ModelAttribute("user") UserRegister user, BindingResult result) {

        if(result.hasErrors()) {
            return "register";
        }
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping()
    @RequestMapping("/type")
    public String type(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        if(user.getType() == TypeUsers.USER){
            return "redirect:/";
        } else if (user.getType() == TypeUsers.ADMIN) {
            return "redirect:/admin/console/user";
        }
        return "redirect:/login";
    }
}
