package com.ex.personaltodoapp.controller;

import com.ex.personaltodoapp.model.entity.Todo;
import com.ex.personaltodoapp.repository.TodoRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String listTodo(Model model, HttpSession session) {
        String ownerName = (String) session.getAttribute("ownerName");

        if (ownerName == null) {
            return "redirect:/welcome";
        }
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("todos", todoRepository.findAll());
        model.addAttribute("todo", new Todo());
        return "list";
    }


    @PostMapping("/save")
    public String saveTodo(@Valid @ModelAttribute("todo") Todo todo,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("todos", todoRepository.findAll());
            return "list";
        }

        todoRepository.save(todo);

        redirectAttributes.addFlashAttribute("message", "Lưu thành công!");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy công việc!");
            return "redirect:/";
        }
        model.addAttribute("todo", optionalTodo.get());
        model.addAttribute("todos", todoRepository.findAll());
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {

        if (!todoRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("message", "ID không tồn tại!");
            return "redirect:/";
        }
        todoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/";
    }
}
