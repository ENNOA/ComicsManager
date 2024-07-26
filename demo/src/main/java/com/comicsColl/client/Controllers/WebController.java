package com.comicsColl.client.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Comic Collection DORKS!");
        model.addAttribute("message2", "Welcome to the Comic Collection NERDS!");
        model.addAttribute("message3", "Welcome to the Comic Collection!");
        return "home";
    }
    
    @GetMapping("/index")
    public String indexPage(Model model) {
        // Add attributes to the model as needed
        model.addAttribute("message", "This is a message for the index page.");
        return "index"; // This will look for src/main/resources/templates/index.html
    }
}
