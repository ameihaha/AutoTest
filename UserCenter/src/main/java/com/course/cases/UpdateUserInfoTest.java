package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws IOException {

        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfo","1");

        int result = getResult(updateUserInfoCase);
        User user =  session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);

        Assert.assertNotNull(user);
        Assert.assertNotNull(result);


    }
    @Test(dependsOnGroups = "loginTure",description = "删除用户信息")
    public  void deleteUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfo",2);
        int result = getResult(updateUserInfoCase);
        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);

        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());

        post.setHeader("content-type","application/json");
        StringEntity entity =  new StringEntity(param.toString(),"utf-8");

        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result ;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        return Integer.parseInt(result);

    }
}
