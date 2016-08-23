package com.the.harbor.vo;

import com.the.harbor.api.be.param.BeIndexModel;

public class BeIndexOperate {

	private String cmd;

	private BeIndexModel fields;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public BeIndexModel getFields() {
		return fields;
	}

	public void setFields(BeIndexModel fields) {
		this.fields = fields;
	}

}
