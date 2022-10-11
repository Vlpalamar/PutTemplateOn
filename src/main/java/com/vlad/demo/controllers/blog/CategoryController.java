package com.vlad.demo.controllers.blog;

import com.vlad.demo.entities.blog.Category;
import com.vlad.demo.entities.blog.Post;
import com.vlad.demo.repos.blog.CategoryRepository;
import com.vlad.demo.repos.blog.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CategoryController {

    CategoryRepository categoryRepository;
    PostRepository postRepository;

    public CategoryController(PostRepository postRepository, CategoryRepository categoryRepository)
    {
        this.postRepository= postRepository;
        this.categoryRepository= categoryRepository;
    }



//    @GetMapping("/category")
//    public String index(Model model)
//    {
//        model.addAttribute("categories", categoryRepository.findAll());
//        return "blog/category/index";
//    }

//    @GetMapping("/category/create")
//    public String create(Model model)
//    {
//      //  return "blog/category/create";
//    }

    @PostMapping("/category/store")
    public RedirectView store(
            @RequestParam(value = "newCategory") String newCategory,
           // @RequestParam(value = "post_id") Long post_id,
            Authentication authentication,
            Category category)
    {
        category.setName(newCategory);
//        Set<Post> posts = new HashSet<>();
//        posts= category.getPosts();
//        posts.add(postRepository.getReferenceById(post_id));
//        category.setPosts(posts);
        categoryRepository.save(category);

       return  new RedirectView("/post/create");
    }

}
