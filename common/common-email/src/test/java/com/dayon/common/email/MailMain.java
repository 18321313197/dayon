package com.dayon.common.email;

import com.dayon.common.email.MailInfo;
import com.dayon.common.email.MailSender;

public class MailMain {
	public static void main(String[] args) {
		
		String ccEmails="18321313197@163.com";
		String sendText="这是一个例子";
		String sendTitle="测试";
		String username="18321313197@163.com";
		String password="12345qwe";
		String sendEmail="18321313197@163.com";
		String recvEmail="523998693@qq.com";
		
		MailInfo minfo = new MailInfo();
		if (ccEmails != null) {
			for (String ccEmail : ccEmails.split(",")) {
				if (!ccEmail.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
					System.out.println("ccEmails error");
					return;
				} else {
					minfo.addCarbonCopy(ccEmail);
				}
			}
		}

		minfo.setContent(sendText);
		minfo.setSubject(sendTitle);
		minfo.setUserName(username);
		minfo.setPassword(password);
		minfo.setMailServerHost("smtp.163.com");
		minfo.setMailServerPort("25");
		minfo.setValidate(true);
		minfo.setFromAddress(sendEmail);
		minfo.setToAddress(recvEmail);
		MailSender.sendTextMail(minfo);
		
	}
}
