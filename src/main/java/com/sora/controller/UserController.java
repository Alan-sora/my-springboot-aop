package com.sora.controller;

import com.sora.aspect.UserAccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sora
 * @create 2021-05-10 10:20
 */
@RestController
public class UserController {
    @RequestMapping("/first")
    public String first(){
        return "first controller";
    }

    @RequestMapping("/doError")
    public Object doError(){
        return 1/0;
    }

    @RequestMapping("/second")
    @UserAccess
    public Object second(){
        return "second controller";

    }
}
