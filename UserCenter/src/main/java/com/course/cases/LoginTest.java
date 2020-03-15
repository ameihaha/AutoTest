package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.InsufficientResourcesException;
import java.io.IOException;

public class LoginTest {

    private  String getResult(LoginCase loginCase) throws IOException {
        //发送httpPost请求
        //新建HttpPost对象
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        //添加post数据
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //设置头信息
        post.setHeader("content-type","application/json");
        //post entity(string类型)数据
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //接收返回结果
        HttpResponse httpResponse = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;

    }




    @BeforeTest(groups = "loginTrue",description = "测试准备，获取HttpClient对象")
    public void beforeTest(){
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "登录成功接口测试")
    public  void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);

        //发送请求
        String  result = getResult(loginCase);
        //断言
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @Test(groups = "loginFalse",description = "用户登陆失败接口测试")
    public void loginFalse() throws IOException {

        //创建sqlsession对象
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase =  session.selectOne("loginCase",2);

        String result  = getResult(loginCase);
        //断言
        Assert.assertEquals(loginCase.getExpected(),result);


    }

}
