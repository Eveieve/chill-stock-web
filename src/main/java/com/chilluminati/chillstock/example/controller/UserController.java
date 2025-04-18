package com.chilluminati.chillstock.example.controller;

import com.chilluminati.chillstock.example.dto.UserDtoExam;
import com.chilluminati.chillstock.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final ExampleService exampleService;

    @GetMapping("/example")
    public String Example() {
        return "example/example";
    }

    @PostMapping("/example")
    public String ExamplePost(UserDtoExam userDto) {
        exampleService.register(userDto);
        return "redirect:/example/list";
    }

    @GetMapping("/example/userById")
    public String example(Model model, String username) {
        model.addAttribute("userDto", exampleService.getByUserId(username));
        return "example/userByIdPost";
    }

    @GetMapping("/example/list")
    public String exampleList(Model model) {
        model.addAttribute("userList", exampleService.getAllUsers());
        return "example/list";
    }

    @PostMapping("example/delete")
    public String exampleDelete(String username) {
        exampleService.deleteByUserId(username);
        return "redirect:/example/list";
    }
}
