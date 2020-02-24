package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    //提取公共部分，用来存储cookieStore信息的变量
    private CookieStore store;
    @BeforeTest
    public  void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA); //ResourceBundle用于获取配置文件的地址，application是配置文件名
        url = bundle.getString("test.url");

    }
    //获取cookies
    @Test
    public  void  testGetCookies() throws IOException {
        String result;

        //从配置文件中拼接测试URL
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url +uri;

        //测试逻辑代码,通过httpclient客户端发送请求
        HttpGet get = new HttpGet(testUrl);
//        HttpClient client = new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


        //获取cookies信息
        this.store =  client.getCookieStore();
        List<Cookie> cookieList =  store.getCookies();

        for (Cookie  cookie : cookieList){
            String name =  cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookieName = "+name +"; cookieValue = "+value);

        }
    }
    //请求加cookies信息
    @Test(dependsOnMethods = {"testGetCookies"})
    public  void  testGetWithCookies() throws IOException {
        String uri =  bundle.getString("test.get.with.cookies");
        String testUrl = this.url +uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);


        HttpResponse response = client.execute(get);

        //获取响用的状态码
        int statusCode =  response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);
        if (statusCode ==200){
            String result =  EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);

        }

    }


}
