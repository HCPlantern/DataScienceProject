package com.workhard.wenshu.controller;

import com.workhard.wenshu.service.Split;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController {

    @PostMapping("/split")
    public String text(@RequestParam("text") String text) {
        try {
            Split split = new Split(text);
            return "location : " + split.getLocationSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }
}

