package com.workhard.wenshu.controller;

import com.workhard.wenshu.service.Crawler;
import com.workhard.wenshu.service.Participle;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController {

    @PostMapping("test")
    public String text(@RequestParam("text") String text) {
        try {
            Participle participle = new Participle(text);
            String location = "location : " + participle.getLocationSet();
            System.out.println(location);
            return location;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }
}
