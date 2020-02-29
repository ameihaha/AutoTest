package com.course.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response) {

//        HttpServletRequest 装请求信息的类
//        HttpServletResponse  装响应信息的类

        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "必须携带cookies信息！";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")) {

            }
        }

        return "携带cookies信息访问成功 ！";
    }
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    public Map<String, Integer> getList(@RequestParam Integer start, @RequestParam Integer end) {

        Map<String, Integer> myList = new HashMap<>();

        myList.put("面包", 30);
        myList.put("牛奶", 10);
        myList.put("火腿", 20);
        return  myList;


    }


}
