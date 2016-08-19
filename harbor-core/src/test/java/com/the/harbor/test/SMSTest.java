package com.the.harbor.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.aliyuncs.sms.SMSSender;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.util.HyCfgUtil;

public class SMSTest {

	public static void main(String[] args) {
		SMSSendRequest req = new SMSSendRequest();
		List<String> recNumbers = new ArrayList<String>();
		recNumbers.add("18601179558");
		JSONObject smsParams = new JSONObject();
		smsParams.put("goTopic","123");
		req.setRecNumbers(recNumbers);
		req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
		req.setSmsParams(smsParams);
		req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfGroupUserJoin());
		SMSSender.send(req);

	}

}
