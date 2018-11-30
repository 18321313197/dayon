package com.dayon.common.socket.rpc;

import java.io.Serializable;

public class RpcParam implements Serializable {
	private static final long serialVersionUID = -1258134878050900722L;
	private String apiMethod;
	private Object[] params;

	public RpcParam setMethodStr(String apiMethod) {
		this.apiMethod = apiMethod;
		return this;
	}

	public RpcParam setParams(Object[] params) {
		this.params = params;
		return this;
	}

	public String getApiMethod() {
		return this.apiMethod;
	}

	public Object[] getParams() {
		return this.params;
	}
	
}
