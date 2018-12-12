package com.dayon.common.server.manager;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class WorkServer {
	private static final String ROOT_NODE = "/dayon";// 永久节点
	private static final String SERVER_PATH = ROOT_NODE + "/server";// 永久节点
	private boolean running = false;
	private ZkClient zkClient;
	private ServerData serverData;
	private IZkChildListener serverChildListener;
	private String nodeName;

	public WorkServer(String zkIpPort, ServerData serverData) {
		this.zkClient = new ZkClient(zkIpPort, 5000, 5000);
		this.serverData = serverData;
		this.serverChildListener = new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				for (String currentChild : currentChilds) {
					System.out.println(currentChild);
				}
				System.out.println("____________");
			}
		};

	}

	public synchronized void start() throws Exception {
		if (running) {
			throw new Exception("服务不可重复启动");
		}
		running = true;
		init();
		zkClient.subscribeChildChanges(SERVER_PATH, serverChildListener);

		nodeName = zkClient.create(SERVER_PATH + "/" + serverData.getName(), serverData,
				CreateMode.EPHEMERAL_SEQUENTIAL);
	}

	public synchronized void stop() throws Exception {
		if (!running) {
			throw new Exception("服务不可重复停止");
		}
		running = false;
		zkClient.unsubscribeChildChanges(SERVER_PATH, serverChildListener);
		zkClient.delete(SERVER_PATH + "/" + nodeName);
	}

	public void release() {
		zkClient.close();
	}

	private void init() {
		if (!zkClient.exists(SERVER_PATH)) {
			if (!zkClient.exists(ROOT_NODE)) {
				zkClient.create(ROOT_NODE, "root", CreateMode.PERSISTENT);
			}
			zkClient.create(SERVER_PATH, "server home", CreateMode.PERSISTENT);
		}
	}

}
