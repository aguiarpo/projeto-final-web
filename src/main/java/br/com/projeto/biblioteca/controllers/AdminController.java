package br.com.projeto.biblioteca.controllers;

import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.statics.ModalAttribute;
import br.com.projeto.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("console")
    public String console(Model model) {
        return "redirect:/admin/console/user";
    }


    @GetMapping("console/user")
    public String getUser(Model model, Pageable pageable) {

        Page<User> users= userService.findAll(pageable);

        model = ModalAttribute.setAttribute(users, model, "USER");
        return "console";
    }

    @GetMapping("console/user/{email}")
    public String getUserEmail(@PathVariable String email, Model model, Pageable pageable) {

        Page<User> user= userService.findByEmailPage(email, pageable);
        model = ModalAttribute.setAttribute(user, model, "USER");
        return "console";
    }
}
