package com.kuyt1819.webservice.web;

import com.kuyt1819.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/helloworld")
    public String welcome(String name, Model model) {
        //model.addAttribute("name", name);
        model.addAttribute("name", "korea");
        //model.addAttribute("age", age);
        //System.out.println("name : " + name + " age :" + age);
        System.out.println("name : " + name);
        return "welcome";
    }

    @GetMapping("/helloworld2")
    public String welcome2() {
        return "index";
    }


    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        System.out.println("★★★★★★★★★★");
        System.out.println(postsService.findAllDesc().toString());
        return "index";
    }


    /*
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }
    */
}