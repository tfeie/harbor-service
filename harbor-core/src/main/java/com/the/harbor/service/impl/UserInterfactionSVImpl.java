package com.the.harbor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.base.vo.MNSBody;
import com.the.harbor.base.vo.MNSMQ;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IUserInterfactionSV;

@Component
@Transactional
public class UserInterfactionSVImpl implements IUserInterfactionSV {

	private static final Logger LOG = LoggerFactory.getLogger(UserInterfactionSVImpl.class);

	@Autowired
	private transient IBeBusiSV beBusiSV;

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Override
	public void process(MNSMQ mnsMQ) {
		LOG.debug("开始处理用户交互的消息");
		String mqType = mnsMQ.getMqType();
		MNSBody body = mnsMQ.getBody();
		if (MQType.MQ_HY_BE_LIKES.getValue().equals(mqType)) {
			// BE点赞行为
			DoBeLikes doBELikes = (DoBeLikes) body;
			beBusiSV.processDoBeLikesMQ(doBELikes);
		} else if (MQType.MQ_HY_GO_FAVORITE.getValue().equals(mqType)) {
			// GO收藏行为
			DoGoFavorite doGoFavorite = (DoGoFavorite) body;
			goBusiSV.processDoGoFavoriteMQ(doGoFavorite);
		} else if (MQType.MQ_HY_GO_VIEWS.getValue().equals(mqType)) {
			// GO浏览行为
			DoGoView doGoView = (DoGoView) body;
			goBusiSV.processDoGoView(doGoView);

		}

	}

}
