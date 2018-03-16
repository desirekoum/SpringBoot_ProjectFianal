package com.example.demo;

import javax.validation.Valid;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listMessage(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return  "list";
    }

    @RequestMapping("/logout")
    public String admin(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return  "login";
    }

    @RequestMapping("/user")
    public String user(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return  "list";
    }

    @RequestMapping( "/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/add")
    public String processForm(@ModelAttribute Message message, @RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "redirect:/";
        }
        try{
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            message.setPicture(uploadResult.get("url").toString());
            messageRepository.save(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }

}
