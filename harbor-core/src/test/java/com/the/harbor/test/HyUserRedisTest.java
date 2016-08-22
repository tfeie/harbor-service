package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchSettings;
import com.the.harbor.service.interfaces.IUserInterfactionSV;
import com.the.harbor.service.interfaces.IUserManagerSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class HyUserRedisTest {

	@Autowired
	IUserInterfactionSV sv;

	@Autowired
	IUserManagerSV userManagerSV;

	@Test
	public void geta() {
		String[] users = new String[] { "hy00000272", "hy00000262", "hy00000254", "hy00000253", "hy00000252",
				"hy00000243", "hy00000242", "hy00000232", "hy00000227", "hy00000226", "hy00000225", "hy00000224",
				"hy00000223","hy00000222","hy00000213","hy00000212","hy00000202","hy00000193","hy00000192" };
		JSONArray arr = new JSONArray();
		for (String userId : users) {
			UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(userId);
			JSONObject fileds = JSONObject.parseObject(JSON.toJSONString(userInfo));
			JSONObject data = new JSONObject();
			data.put("cmd", "add");
			data.put("fields", fileds);
			arr.add(data);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc(OpenSearchSettings.getAppName(), client);
		try {
			String result = doc.push(arr.toJSONString(), "hy_user");
			System.out.println(result);
		} catch (Exception e) {
		}

	}

	@Test
	public void geta1() {
		userManagerSV.resetUsersInRedis();
	}
	
	@Test
	public void createInviteCode() {
		userManagerSV.batchCreateInviteCodeForAllUsers();
	}

}
