package com.vlad.demo.controllers.blog;

import com.vlad.demo.entities.blog.Category;
import com.vlad.demo.entities.blog.Post;
import com.vlad.demo.repos.blog.CategoryRepository;
import com.vlad.demo.repos.reg.UserRepository;
import com.vlad.demo.repos.blog.PostRepository;


import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import java.nio.file.Paths;


@Controller
public class PostItemController {


    PostRepository postRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public PostItemController (CategoryRepository categoryRepository, PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository= userRepository;
        this.categoryRepository= categoryRepository;

    }

    //показать все
    @GetMapping("/post")
    public String index(Model model)
    {
        model.addAttribute("posts" , postRepository.findAll());
        return "blog/post/index";
    }


    //вывод формы  для создания
    @GetMapping("/post/create")
    public String create(Model model)
    {
        model.addAttribute("categories",categoryRepository.findAll() );
        return "blog/post/create";
    }


    // сохранение в базу
    @PostMapping("/post/store")
    public RedirectView store(@RequestParam(value = "title") String title,
                              @RequestParam(value = "recipe") String recipe,
                              @RequestParam(value = "tags") String tags,
                              @RequestParam(value = "img") MultipartFile file,
                              @RequestParam(value = "category_id") Long[] categories_id,
                              Authentication authentication ,  Post post)  {
        post.setTitle(title);
        post.setRecipe(recipe);
        post.setTags(tags);
        post.setUser(userRepository.findByUsername(authentication.getName()));

        if (file!=null)
        {
            if (!file.isEmpty())
            {
                Path destinationFile= Paths
                        .get(uploadPath+"post/"+ file.getOriginalFilename())
                        .normalize()
                        .toAbsolutePath();

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destinationFile,
                            StandardCopyOption.REPLACE_EXISTING);

                    post.setImgPath("post/"+ file.getOriginalFilename());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


        }

        if (categories_id !=null)
        {

            Set<Category> categories = new HashSet<>();
            for (int i = 0; i < categories_id.length; i++) {
                Category category = categoryRepository.findById(categories_id[i]).get(); //!!
                categories.add(category);
            }
            post.setCategories(categories);
        }



        postRepository.save(post);

        return  new RedirectView("/post");
    }




    //редактировать
    @GetMapping("/post/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id)
    {
        Post p= postRepository.findById(id).get();
        model.addAttribute("post", p);
        return "blog/post/edit";

    }

    //удалить
    @GetMapping("/post/delete/{id}")
    public RedirectView delete(@PathVariable(name = "id") Long id)
    {
        postRepository.deleteById(id);
        return new RedirectView("/post");
    }


}
