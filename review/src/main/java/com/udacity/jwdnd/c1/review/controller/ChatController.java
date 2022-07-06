package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;
import com.udacity.jwdnd.c1.review.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;
    private UserService userService;

    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("newChatForm") ChatForm newChatForm, Model model) {
        model.addAttribute("messageList",messageService.getMessageList());
        return "chat";
    }

    @PostMapping
    public String addChat(@ModelAttribute("newChatForm") ChatForm newChatForm, Model model) {
        newChatForm.setUsername(userService.getCurrentUsername());
        messageService.addMessage(newChatForm);
        newChatForm.setMessageText("");
        model.addAttribute("messageList",messageService.getMessageList());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes() {
        return new String[]{"Say", "Shout", "Whisper"};
    }
}
