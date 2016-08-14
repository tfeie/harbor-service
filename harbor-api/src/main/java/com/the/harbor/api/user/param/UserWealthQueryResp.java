package com.the.harbor.api.user.param;

import com.the.harbor.base.vo.Response;

public class UserWealthQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	private String userId;

	private long totalBeishang;

	private long totalJiangli;

	private long totalDashang;

	private long totalGongyi;

	private long totalDianzan;

	// 可用海贝
	private long hbBalance;

	// 可用现金 分
	private long cashBlance;

	// 可用现金 元
	private String cashBlanceYuan;

	// 益友
	private int yiyou;

	// 助人
	private int zhuren;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getTotalBeishang() {
		return totalBeishang;
	}

	public void setTotalBeishang(long totalBeishang) {
		this.totalBeishang = totalBeishang;
	}

	public long getTotalJiangli() {
		return totalJiangli;
	}

	public void setTotalJiangli(long totalJiangli) {
		this.totalJiangli = totalJiangli;
	}

	public long getTotalDashang() {
		return totalDashang;
	}

	public void setTotalDashang(long totalDashang) {
		this.totalDashang = totalDashang;
	}

	public long getTotalGongyi() {
		return totalGongyi;
	}

	public void setTotalGongyi(long totalGongyi) {
		this.totalGongyi = totalGongyi;
	}

	public long getTotalDianzan() {
		return totalDianzan;
	}

	public void setTotalDianzan(long totalDianzan) {
		this.totalDianzan = totalDianzan;
	}

	public long getHbBalance() {
		return hbBalance;
	}

	public void setHbBalance(long hbBalance) {
		this.hbBalance = hbBalance;
	}

	public long getCashBlance() {
		return cashBlance;
	}

	public void setCashBlance(long cashBlance) {
		this.cashBlance = cashBlance;
	}

	public String getCashBlanceYuan() {
		return cashBlanceYuan;
	}

	public void setCashBlanceYuan(String cashBlanceYuan) {
		this.cashBlanceYuan = cashBlanceYuan;
	}

	public int getYiyou() {
		return yiyou;
	}

	public void setYiyou(int yiyou) {
		this.yiyou = yiyou;
	}

	public int getZhuren() {
		return zhuren;
	}

	public void setZhuren(int zhuren) {
		this.zhuren = zhuren;
	}

}
