package com.course.server;

import org.springframework.web.bind.annotation.*;

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

    /**
     * 需要携带参数的get请求
     * 第一种实现方式 URL：key=value&key=value
     * 模拟获取商品列表
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    public Map<String, Integer> getList(@RequestParam Integer start, @RequestParam Integer end) {

        Map<String, Integer> myList = new HashMap<>();

        myList.put("面包", 30);
        myList.put("牛奶", 10);
        myList.put("火腿", 20);
        return  myList;

    }

    /**
     * 第二种需要带参数的get请求
     * URL：IP：port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    public Map<String,Integer> myGetList(@PathVariable Integer start,@PathVariable Integer end){

        Map<String ,Integer> myList = new HashMap<>();

        myList.put("面包", 30);
        myList.put("牛奶", 10);
        myList.put("火腿", 20);
        return  myList;

    }


}
