package com.vlad.demo.controllers.trello;

import com.vlad.demo.entities.trello.Post;
import com.vlad.demo.repos.trello.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class PostItemController {



//    public PostItemController (PostRepository postRepository) {
//       /* this.postRepository = postRepository;*/
//    }

    //показать все
    @GetMapping("/post")
    public String index(Model model)
    {
        //model.addAttribute("columns", postRepository.findAll());
        return "trello/post/index";
    }


    //вывод формы  для создания
    @GetMapping("/post/create")
    public String create(Model model)
    {
        return "trello/post/create";
    }


    // сохранение в базу
    @PostMapping("/post/store")
    public RedirectView store(@RequestParam(value = "title") String title, Post post)
    {
        post.setTitle(title);

       // postRepository.save(post);
        return  new RedirectView("/post");
    }


    //редактировать
    @GetMapping("/post/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id)
    {
       // Post c= postRepository.findById(id).get();
       // model.addAttribute("column", c);
        return "trello/post/edit";

    }

    //удалить
    @GetMapping("/post/delete/{id}")
    public RedirectView delete(@PathVariable(name = "id") Long id)
    {
       // postRepository.deleteById(id);
        return new RedirectView("/post");
    }


}
