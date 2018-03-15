package com.example.demo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String listMessage(Model model){
        Message message  = new Message("01/20/2018","Hey this is the first message.", "desire@mpondo");
        messageRepository.save(message);

        message  = new Message("01/20/2018","Hey this is the first message.", "desire@mpondo");
        messageRepository.save(message);

        message  = new Message("01/20/2018","Hey this is the first message.", "desire@mpondo");
        messageRepository.save(message);

        message  = new Message("01/20/2018","Hey this is the first message.", "desire@mpondo");
        messageRepository.save(message);

        message  = new Message("01/20/2018","Hey this is the first message.", "desire@mpondo");
        messageRepository.save(message);

        model.addAttribute("messages", messageRepository.findAll());
        return  "list";
    }

    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "messageform";
        }
        messageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return  "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return "messageform";
    }

    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id){
        messageRepository.delete(id);
        return "redirect:/";
    }
}
