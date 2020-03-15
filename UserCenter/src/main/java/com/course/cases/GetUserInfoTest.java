package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
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
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取useId为1的用户信息")
    public GetUserInfoTest() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase",1);

        JSONArray resultJson = getJsonResult(getUserInfoCase);

        User user = session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);//查询数据库的结果

        List useList = new ArrayList();
        //查询出的User对象放到useList中
        useList.add(user);
        JSONArray jsonArray = new JSONArray(useList);

        Assert.assertEquals(jsonArray,resultJson);

    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoCase.getUserId());

        post.setHeader("content-type","application/json");
        StringEntity entity =  new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList = Arrays.asList(result);//将result转换成list
        JSONArray array = new JSONArray(resultList);

        return  array;
    }
}
