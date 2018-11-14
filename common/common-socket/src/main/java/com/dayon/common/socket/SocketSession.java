package com.dayon.common.socket;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public abstract class SocketSession extends Thread {
	private String ip;
	private int port;
	private Socket s;
	private ObjectOutputStream os;
	private ObjectInputStream is;

	public SocketSession(Socket s) throws IOException  {
		this.s = s;
		this.ip = s.getInetAddress().getHostAddress();
		this.port = s.getPort();
		this.os = new ObjectOutputStream(s.getOutputStream());
		this.is = new ObjectInputStream(s.getInputStream());
	}

	public synchronized void send(Object sendObj) throws IOException {
		this.os.writeObject(sendObj);
	}

	private Object recv() throws IOException, ClassNotFoundException {
		return this.is.readObject();
	}

	@Override
	public void run() {
		try {
			while (true) {
				// 阻塞读取输入流
				Object requestObj =  this.recv();
				if(requestObj==null){
					break;
				}
				// 调用实现的回调方法处理业务
				Object responseObj = this.onRecv(this.ip, this.port, requestObj);
				// 如果返回空就结束会话
				if (responseObj != null) {
					this.os.writeObject(responseObj);
				} 
			}
			// 正常关闭会话
			System.out.println("socket关闭:正常关闭");
		} catch (EOFException e) {
			// 请求方关闭回话
			System.out.println("socket关闭:远程异常");
		} catch (SocketException e) {
			System.out.println("SocketException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// IO异常
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			// 其它异常
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		// 关闭会话
		this.close();
	}

	/**
	 * @param ip
	 *            客户端IP
	 * @param port
	 *            客户端端口
	 * @param requestObj
	 *            请求消息
	 * @return responseObj 返回消息 如果对象为空则结束会话
	 */
	protected abstract Object onRecv(String ip, int port, Object requestObj);

	public void close() {
		try {
			this.s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.ip + ":" + this.port;
	}

}
