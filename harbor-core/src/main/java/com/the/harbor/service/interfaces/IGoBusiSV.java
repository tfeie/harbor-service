package com.the.harbor.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.the.harbor.api.go.param.CheckUserOrderGoReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.DoGoComment;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoJoinConfirm;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.go.param.GiveHBReq;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoJoin;
import com.the.harbor.api.go.param.GoOrder;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.GroupApplyReq;
import com.the.harbor.api.go.param.GroupApplyResp;
import com.the.harbor.api.go.param.QueryMyJointGoReq;
import com.the.harbor.api.go.param.SubmitGoHelpReq;
import com.the.harbor.api.go.param.UpdateGoJoinPayReq;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoJoin;
import com.the.harbor.dao.mapper.bo.HyGoOrder;

public interface IGoBusiSV {

	String createGo(GoCreateReq goCreateReq);

	HyGoOrder getHyGoOrder(String userId, String goId);

	String orderOneOnOne(GoOrderCreateReq goOrderCreateReq);

	String createGoPaymentOrder(CreateGoPaymentOrderReq createGoPaymentOrderReq);

	HyGoOrder getHyGoOrder(String orderId);

	HyGoJoin getHyGoJoin(String orderId);

	List<HyGoJoin> getHyGoJoins(String userId, String goId);

	HyGo getHyGo(String goId);

	void updateGoOrderPay(UpdateGoOrderPayReq updateGoOrderPayReq);

	void confirmGoOrder(GoOrderConfirmReq goOrderConfirmReq);

	void setGoOrderMeetLocaltion(GoOrderMeetLocaltionReq goOrderMeetLocaltionReq);

	void confirmGoOrderMeetLocaltion(GoOrderMeetLocaltionConfirmReq goOrderMeetLocaltionConfirmReq);

	void finishGoOrder(GoOrderFinishReq goOrderFinishReq);

	void processDoGoFavoriteMQ(DoGoFavorite doGoFavorite);

	void processDoGoView(DoGoView doGoView);

	void processDoGoComment(DoGoComment doGoComment);

	int getOrderCount(String goId, String goType);

	GroupApplyResp applyGroup(GroupApplyReq groupApplyReq);

	void updateGoJoinPay(UpdateGoJoinPayReq updateGoJoinPayReq);

	boolean checkUserOrderGo(CheckUserOrderGoReq checkUserOrderGoReq);

	void processDoGoJoinConfirm(DoGoJoinConfirm doGoJoinConfirm);

	int getMyJointGoCount(String userId, String goType);

	List<HyGoOrder> getMyJointGroupGoes(QueryMyJointGoReq req);

	List<HyGoJoin> getMyJointOnOGoes(QueryMyJointGoReq req);

	void submitGoHelp(SubmitGoHelpReq submitGoHelpReq);

	void giveHaibei(GiveHBReq giveHBReq);

	List<GoJoin> getGoJoins(String goId);

	List<GoOrder> getGoOrders(String goId);

	void doGoFavorite(GroupApplyReq groupApplyReq);

	void processGoDelete(String goId, String goType);

	void topGo(String goId, String topFlag, Timestamp topDate);

	void hideGo(String goId, String hideFlag);

	int getGoYiYouCount(String userId);

	int getZhuRenCount(String userId);

	int getGoHelpCount(String goId, String goType);

	void pushGoToOpenSearch(String goId);

	void pushAllGoToOpenSearch();

	Go getGoInfo(String goId);

	void resetAllGo2Redis();

	void fillGoInfo(Go go);
}
