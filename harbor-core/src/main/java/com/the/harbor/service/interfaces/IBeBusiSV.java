package com.the.harbor.service.interfaces;

import java.sql.Timestamp;

import com.the.harbor.api.be.param.Be;
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

	void hideBe(String beId, String hideFlag);

	void rebuildAllBesOpenSearchIndex();

	void pushBeToOpenSearch(String beId);

	void hideBeToOpenSearch(String beId, String hideFlag);

	void topBeToOpenSearch(String beId, String topFlag);

	void deleteBeToOpenSearch(String beId);

	void resetAllBe2Redis();

	Be queryOneBeFromRDS(String beId);

	void fillBeInfo(Be be);
}
