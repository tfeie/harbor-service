package com.the.harbor.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.OrgMode;
import com.the.harbor.base.enumeration.hygo.PayMode;
import com.the.harbor.base.enumeration.hygo.Status;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.commons.util.AmountUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoDetail;
import com.the.harbor.dao.mapper.bo.HyGoTags;
import com.the.harbor.dao.mapper.interfaces.HyGoDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoTagsMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.util.HarborSeqUtil;

@Component
@Transactional
public class GoBusiSVImpl implements IGoBusiSV {

	@Autowired
	private transient HyGoDetailMapper hyGoDetailMapper;

	@Autowired
	private transient HyGoTagsMapper hyGoTagsMapper;

	@Autowired
	private transient HyGoMapper hyGoMapper;

	@Override
	public String createGo(GoCreateReq goCreateReq) {
		String goId = HarborSeqUtil.createGoId();
		Timestamp sysdate = DateUtil.getSysDate();
		/* 1.活动主表 */
		HyGo go = new HyGo();
		go.setGoId(goId);
		go.setUserId(goCreateReq.getUserId());
		go.setGoType(goCreateReq.getGoType());
		go.setTopic(goCreateReq.getTopic());
		go.setInviteMembers(
				GoType.GROUP.getValue().equals(goCreateReq.getGoType()) ? goCreateReq.getInviteMembers() : null);
		go.setExpectedStartTime(
				GoType.GROUP.getValue().equals(goCreateReq.getGoType()) ? goCreateReq.getExpectedStartTime() : null);
		go.setExpectedDuration(goCreateReq.getExpectedDuration());
		go.setPayMode(goCreateReq.getPayMode());
		if (PayMode.AA.getValue().equals(goCreateReq.getPayMode())
				|| PayMode.FIXED_FEE.getValue().equals(goCreateReq.getPayMode())) {
			go.setFixedPrice(Long.parseLong(AmountUtils.changeY2F(goCreateReq.getPrice())));
		}
		go.setOrgMode(goCreateReq.getOrgMode());
		go.setLocation(OrgMode.OFFLINE.getValue().equals(goCreateReq.getOrgMode()) ? goCreateReq.getLocation() : null);
		go.setMyStory(GoType.ONE_ON_ONE.getValue().equals(goCreateReq.getGoType()) ? goCreateReq.getMyStory() : null);
		go.setCreateDate(sysdate);
		go.setStatus(Status.ING.getValue());
		hyGoMapper.insert(go);
		/* 2.活动明细 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoDetails())) {
			for (GoDetail d : goCreateReq.getGoDetails()) {
				HyGoDetail gd = new HyGoDetail();
				BeanUtils.copyProperties(d, gd);
				gd.setCreateDate(sysdate);
				gd.setGoId(goId);
				gd.setId(HarborSeqUtil.createGoDetailId());
				gd.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				hyGoDetailMapper.insert(gd);
			}
		}
		/* 3.活动标签 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoTags())) {
			int sortId = 0;
			for (GoTag d : goCreateReq.getGoTags()) {
				HyGoTags record = new HyGoTags();
				BeanUtils.copyProperties(d, record);
				record.setGoId(goId);
				record.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
				record.setSortId(sortId);
				record.setTagId(StringUtil.isBlank(d.getTagId()) ? HarborSeqUtil.createTagId(TagType.GO.getValue())
						: d.getTagId());
				record.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				sortId++;
				hyGoTagsMapper.insert(record);
			}
		}

		return goId;
	}

}
