package com.dayon.common.socket;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public abstract class SocketSession {
	private String ip;
	private int port;
	private Socket socket;
	private ObjectOutputStream os;
	private ObjectInputStream is;

	public SocketSession(Socket socket) throws IOException {
		this.socket = socket;
		this.ip = socket.getInetAddress().getHostName();
		this.port = socket.getPort();
		this.os = new ObjectOutputStream(socket.getOutputStream());
		this.is = new ObjectInputStream(socket.getInputStream());

		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("socket服务:启动");
					while (true) {
						// 阻塞读取输入流
						Object requestObj = SocketSession.this.is.readObject();
						if (requestObj == null) {
							break;
						}
						// 调用实现的回调方法处理业务
						Object responseObj = SocketSession.this.onRecv(SocketSession.this.ip, SocketSession.this.port,
								requestObj);
						// 如果返回空就结束会话
						if (responseObj != null) {
							SocketSession.this.send(responseObj);
						}
					}
					// 正常关闭会话
					System.out.println("socket关闭:正常关闭");
				} catch (EOFException e) {
					// 请求方关闭回话
					System.out.println("socket关闭:远程异常");
				} catch (SocketException e) {
					System.out.println("socket关闭:远程异常 ");
					e.printStackTrace();
				} catch (IOException e) {
					// IO异常
					System.out.println("IOException: " + e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					// 其它异常
					System.out.println("Exception: " + e.getMessage());
					e.printStackTrace();
				}
				// 关闭会话
				SocketSession.this.close();
			}
		}.start();

	}

	public void send(Object sendObj) throws IOException {
		synchronized (this.os) {
			this.os.writeObject(sendObj);
		}
	}

	/**
	 * @param ip        客户端IP
	 * @param port      客户端端口
	 * @param requstObj 请求消息
	 * @return responseObj 返回消息 如果对象为空则结束会话
	 */
	protected abstract Object onRecv(String ip, int port, Object requestObj);

	public synchronized void close() {
		try {
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.ip + ":" + this.port;
	}

}
