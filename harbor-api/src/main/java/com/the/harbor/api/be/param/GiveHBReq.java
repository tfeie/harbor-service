package com.the.harbor.api.be.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class GiveHBReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "BE标识为空", groups = { IBeSV.GiveHaibei.class })
	private String beId;

	@NotNull(message = "打赏用户为空", groups = { IBeSV.GiveHaibei.class })
	private String fromUserId;

	@NotNull(message = "被打赏用户为空", groups = { IBeSV.GiveHaibei.class })
	private String toUserId;

	@NotNull(message = "打赏海贝数量为空", groups = { IBeSV.GiveHaibei.class })
	private long count;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
