package com.vlad.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class coffeController {


    @GetMapping("/")
    public String home()
    {
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

    @GetMapping("/store")
    public String store()
    {
        return "store";
    }

}
