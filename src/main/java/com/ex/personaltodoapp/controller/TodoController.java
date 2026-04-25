package com.ex.personaltodoapp.controller;

import com.ex.personaltodoapp.model.entity.Todo;
import com.ex.personaltodoapp.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String listTodo(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        model.addAttribute("todo", new Todo());

        return "list";
    }

    @PostMapping("/add")
    public String addTodo(@Valid @ModelAttribute("todo") Todo todo,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("todos", todoRepository.findAll());
            return "list";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }
}
