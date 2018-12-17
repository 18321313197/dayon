package com.dayon.common.server.manager;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ServerData implements Serializable {
	private static final long serialVersionUID = -5240430753629859473L;
	private String name;
	private String ip;
	private int port;
	private Set<String> serviceNames = new HashSet<>();

	public ServerData(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Set<String> getServiceNames() {
		return serviceNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
