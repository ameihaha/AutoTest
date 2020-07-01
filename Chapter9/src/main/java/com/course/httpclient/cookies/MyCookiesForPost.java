package com.course.httpclient.cookies;

import netscape.javascript.JSObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultBackoffStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.IResultMap;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;

        //从配置文件中获取测试地址
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        //通过httpclient客户端发送请求
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("￥￥￥￥￥￥"+result);

        //获取cookies信息
        this.store = client.getCookieStore();

        //get information about cookies
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            System.out.println(cookie);
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookieName = " + name + "; cookieValue = " + value);
        }
    }
//    @Test(dependsOnMethods = {"testGetCookies"})
    @Test
    public void testPostMethod () throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        //测试地址
        String  testUrl  =  this.url + uri ;
        //声明一个client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();



        //声明一个方法，这个方法就是post方法
        HttpPost post =  new HttpPost(testUrl);

        //添加参数
        JSONObject param = new JSONObject();
//        param.put("name","huhansan");
//        param.put("age","18");
        param.put("username","urban123");
        param.put("password","urban1120_");

        //设置请求头信息
        post.setHeader("content-type","application/json;charset=utf-8");
        //将参数信息添加到方法中
        StringEntity entity =  new StringEntity(param.toString(), "utf-8");
        //将post方法绑定entity
        post.setEntity(entity);

        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse  response = client.execute(post);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("********** amei "+result);
        /*处理结果，返回结果是否符合预期*/
        //将返回的结果字符串传化为json对象
        JSONObject resultJson = new JSONObject(result);

        //具体判断返回结果的值
        //获取到json结果的value值
        String message = (String) resultJson.get("message");
        int code = (int) resultJson.get("code");
        Assert.assertEquals("success",message);//期望结果，实际结果
        Assert.assertEquals(200,code);
    }
}

