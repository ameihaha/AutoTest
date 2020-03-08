package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {


    public JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param =  new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore((TestConfig.store));

        //返回结果
        String result;

        HttpResponse response =TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        //将结果转成json数组
        JSONArray jsonArray = new JSONArray(result);
        return  jsonArray;
    }

    @Test(dependsOnGroups = "loginTrue",description = "获取性别男的用户信息")
    public void getUserListInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        //发送请求获取实际结果
        JSONArray resultJson = getJsonResult(getUserListCase);
        //验证
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);

        for (User u:userList){
            System.out.println("获取的user : "+u.toString());
        }
        //实际
        JSONArray userListJson = new JSONArray(userList);
        //断言预期返回json数组与实际的长度
        Assert.assertEquals(userListJson.length(),resultJson.length());

        for(int i=0;i<resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());

        }


    }


}
