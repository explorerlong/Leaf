package com.sankuai.inf.leaf.ext.model;

public class BizModule {
	private String code;
	
	public BizModule(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean match(String rawCode) {
		return code != null && code.equals(rawCode);
	}
}
