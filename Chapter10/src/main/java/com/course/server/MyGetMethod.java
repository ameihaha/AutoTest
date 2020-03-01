package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "amei的get方法")//通过swagger接口生成器生成接口文档，Api作用在类上，ApiOperation作用在方法上
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {

//        HttpServletRequest 装请求信息的类
//        HttpServletResponse  装响应信息的类

        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
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
    @ApiOperation(value = "要求客户端携带参数才能访问的get方法一",httpMethod = "GET")
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
    @ApiOperation(value = "要求客户端携带参数才能访问的get方法二",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start,@PathVariable Integer end){

        Map<String ,Integer> myList = new HashMap<>();

        myList.put("面包", 30);
        myList.put("牛奶", 10);
        myList.put("火腿", 20);
        return  myList;

    }


}
