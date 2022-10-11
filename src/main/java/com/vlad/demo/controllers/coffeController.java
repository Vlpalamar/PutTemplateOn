package com.vlad.demo.controllers;

import com.vlad.demo.repos.blog.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class coffeController {

    PostRepository postRepository;

    coffeController(PostRepository postRepository)
    {
        this.postRepository= postRepository;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("posts" , postRepository.findAll());
        return "index";
    }

    @GetMapping("/about")
    public String about()
    {
        return "about";
    }

    @GetMapping("/products")
    public String product()
    {
        return "products";
    }

    @GetMapping("/profile")
    public String store()
    {
        return "profile";
    }

}
