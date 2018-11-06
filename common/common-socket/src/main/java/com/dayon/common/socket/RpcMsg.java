package com.dayon.common.socket;

import java.io.Serializable;

public class RpcMsg implements Serializable {
	private static final long serialVersionUID = -1258134878050900722L;
	private String methodStr;
	private Object[] params;
	private Object result;

	public RpcMsg setMethodStr(String methodStr) {
		this.methodStr = methodStr;
		return this;
	}

	public RpcMsg setParams(Object[] params) {
		this.params = params;
		return this;
	}

	public String getMethodStr() {
		return this.methodStr;
	}

	public Object[] getParams() {
		return this.params;
	}

	public Object getResult()  {
			return this.result;
	}
	public void setResult(Object result){
			this.result=result;
	}
	
}
