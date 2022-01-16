package com.workhard.wenshu.controller;

import com.workhard.wenshu.service.Split;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController {

    @PostMapping("/split")
    public String text(@RequestBody String string) {
        try {
            Split split = new Split(string);
            return split.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail to split.";
    }

}

