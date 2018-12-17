package com.dayon.common.server.manager;

import java.net.InetAddress;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import com.dayon.common.log4j.Log4jLogFactory;

public class WorkServer {
	private static final String ROOT_NODE = "/dayon";// 永久节点
	private static final String SERVER_PATH = ROOT_NODE + "/server";// 永久节点
	private boolean running = false;
	private ZkClient zkClient;
	private ServerData serverData;
	private IZkChildListener serverChildListener;
	private String nodePath;

	public WorkServer(String zkIpPort, String serverName) {
		this.zkClient = new ZkClient(zkIpPort, 5000, 5000);
		this.serverData = new ServerData(serverName);
		this.serverData.setName(serverName);
		this.serverChildListener = new IZkChildListener() {
			@Override
			public synchronized void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				if (currentChilds == null) {
					crateServerNode();
					return;
				}

				boolean isDead = true;// 当前节点是否被删除
				StringBuilder sb = new StringBuilder("Zookeeper服务节点 - ").append(parentPath).append(":");

				for (String currentChild : currentChilds) {
					sb.append(" ").append(currentChild);
					if (nodePath.endsWith(currentChild)) {
						isDead = false;
					}
				}
				Log4jLogFactory.getLogger().info(sb);
				if (isDead) {
					crateServerNode();
					Log4jLogFactory.getLogger().info("Zookeeper服务节点 - 创建服务节点 " + nodePath);
				}

			}
		};

	}

	public synchronized void start() throws Exception {
		if (running) {
			throw new Exception("服务不可重复启动");
		}
		running = true;
		InetAddress inetAddress = InetAddress.getLocalHost();
		this.serverData.setIp(inetAddress.getHostAddress());
		this.serverData.setPort(0);
		zkClient.subscribeChildChanges(SERVER_PATH, serverChildListener);
		Log4jLogFactory.getLogger().info("Zookeeper服务节点 - 创建服务节点监听 " + nodePath);
		crateServerNode();
		Log4jLogFactory.getLogger().info("Zookeeper服务节点 - 创建服务节点 " + nodePath);

	}

	private synchronized void crateServerNode() {
		if (!zkClient.exists(SERVER_PATH)) {
			if (!zkClient.exists(ROOT_NODE)) {

				zkClient.create(ROOT_NODE, "root", CreateMode.PERSISTENT);
			}
			zkClient.create(SERVER_PATH, "server home", CreateMode.PERSISTENT);
		}
		nodePath = zkClient.create(SERVER_PATH + "/" + serverData.getName(), serverData,
				CreateMode.EPHEMERAL_SEQUENTIAL);
	}

	public synchronized void stop() throws Exception {
		if (!running) {
			throw new Exception("服务不可重复停止");
		}
		running = false;
		zkClient.unsubscribeChildChanges(SERVER_PATH, serverChildListener);
		Log4jLogFactory.getLogger().info("Zookeeper服务节点 - 删除服务节点监听 " + nodePath);
		zkClient.delete(nodePath);
		Log4jLogFactory.getLogger().info("Zookeeper服务节点 - 删除服务节点 " + nodePath);
	}

	public synchronized void release() {
		zkClient.close();
	}

}
