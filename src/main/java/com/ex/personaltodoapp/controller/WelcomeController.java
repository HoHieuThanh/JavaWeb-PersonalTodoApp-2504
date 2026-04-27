package com.ex.personaltodoapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String showForm() {
        return "welcome";
    }

    @PostMapping("/welcome")
    public String saveName(@RequestParam("name") String name,
                           HttpSession session,
                           Model model) {

        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("error", "Tên không được để trống");
            return "welcome";
        }

        session.setAttribute("ownerName", name);

        return "redirect:/";
    }
}

