package com.the.harbor.service.interfaces;

import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeLikes;

public interface IBeBusiSV {

	String createBe(BeCreateReq beCreateReq);

	void processDoBeLikesMQ(DoBeLikes doBELikes);

	void processDoBeComment(DoBeComment doBeComment);
}
