package com.the.harbor.service.interfaces;

import java.sql.Timestamp;

import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeFavorite;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.be.param.DoBeView;
import com.the.harbor.api.be.param.GiveHBReq;

public interface IBeBusiSV {

	String createBe(BeCreateReq beCreateReq);

	void processDoBeLikesMQ(DoBeLikes doBELikes);

	void processDoBeComment(DoBeComment doBeComment);

	long getBesCount(String userId);

	void giveHaibei(GiveHBReq giveHBReq);

	void processDoBeFavoriteMQ(DoBeFavorite doBeFavorite);

	void processDoBeView(DoBeView doBeView);

	void processBeDelete(String beId);

	void topBe(String beId, String topFlag, Timestamp topDate);
}
