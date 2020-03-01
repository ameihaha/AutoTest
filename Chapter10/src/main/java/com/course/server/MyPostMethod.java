package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是全部的post请求")
@RequestMapping("/v1")//这里的uri映射，在该类下面的所有方法请求的uri的第一个路由都是"/v1"
public class MyPostMethod {

    //这个变量用来装cookie信息
    private static Cookie cookie;
    //用户登录成功获取到cookies，然后携带本cookies访问其他接口获取返回信息
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value ="登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value =  "userName",required = true) String userName,
                        @RequestParam(value =  "passWord",required = true) String passWord){

        if(userName.equals("amei")&&passWord.equals("123456")){

            cookie = new Cookie("login","ture");
            response.addCookie(cookie);
            return "addCookie login success ！";
        }
        return "用户名或者密码错误";

    }


}
