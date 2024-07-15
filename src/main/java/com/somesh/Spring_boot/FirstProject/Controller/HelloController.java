package com.somesh.Spring_boot.FirstProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController            // @Component, @Controller and will return a @ResponseBody
public class HelloController {

    // @RequestMapping(value = "/", method = RequestMethod.GET)  //whenever we go to localhost:8080 this particular method will be called
    @GetMapping("/")  // Instead of using @RequestMapping(value = "/", method = RequestMethod.GET) for get request we can use this
    public String helloWorld(){
        return "Welcome to MyPage AAAA";
    }
}
