package com.vlad.demo.controllers;

import com.vlad.demo.entities.blog.Category;
import com.vlad.demo.entities.reg.User;
import com.vlad.demo.repos.blog.CategoryRepository;
import com.vlad.demo.repos.blog.PostRepository;
import com.vlad.demo.repos.reg.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class coffeController {

    PostRepository postRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    coffeController(CategoryRepository categoryRepository, UserRepository userRepository, PostRepository postRepository)
    {
        this.userRepository= userRepository;
        this.postRepository= postRepository;
        this.categoryRepository= categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("posts" , postRepository.findAll());
        return "index";
    }

    @GetMapping("/home/{category_name}")
    public String homeByCategory(Model model, @PathVariable(name = "category_name") String category_name)
    {


        model.addAttribute("posts" , postRepository.findByCategoriesName(category_name));
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
    public String profile(Model model, Authentication authentication)
    {
            User user=  userRepository
                              .findByUsername(authentication.getName());

        model.addAttribute("MyPosts",postRepository
                        .findByUserId(user.getId()));


//                .findAll()
//                .stream()
//                .filter(post -> post.getUser()
//                        .getId().equals(userRepository
//                                .findByUsername(authentication.getName()).getId())));

        return "profile";

    }

    @GetMapping("/profile/{user_name}")
    public String profileByUser(Model model, @PathVariable(name = "user_name") String user_name)
    {
        User user= userRepository.findByUsername(user_name);

        model.addAttribute("MyPosts",postRepository
                .findByUserId(user.getId()));
        return "profile";

    }



}
