package com.dayon.common.other;  
import java.util.ArrayList;
import java.util.List;
/**  
* 发送邮件需要使用的基本信息  
*/   
import java.util.Properties;   
public class MailInfo {   
    // 发送邮件的服务器的IP和端口   
    private String mailServerHost;   
    private String mailServerPort;   
    // 邮件发送者的地址   
    private String fromAddress;   
    // 邮件接收者的地址   
    private String toAddress;   
    // 登陆邮件发送服务器的用户名和密码   
    private String userName;   
    private String password;   
    // 是否需要身份验证   
    private boolean validate = false;   
    // 邮件主题   
    private String subject;   
    // 邮件的文本内容   
    private String content;   
    // 邮件附件的文件名   
    private String[] attachFileNames;
    
    private List<String> carbonCopys=new ArrayList<>();
    
    public void addCarbonCopy(String carbonCopy){
    	if(carbonCopy!=null){
    		carbonCopys.add(carbonCopy);
    	}
    }
    public String getCarbonCopysStr(){
    	if(carbonCopys.size()>0){
    		StringBuilder sb=new StringBuilder();
    		for (String email : carbonCopys) {
				sb.append(email).append(",");
			}
    		sb.deleteCharAt(sb.length()-1);
    		return sb.toString();
    	}
    	return "";
    }
    
    
    /**  
      * 获得邮件会话属性  
      */   
    public Properties getProperties(){   
      Properties p = new Properties();
      p.setProperty("eemail.protocol", "smtp"); 
      p.setProperty("mail.smtp.starttls.enable", "true");
      p.setProperty("mail.smtp.EnableSSL.enable","true");
      p.setProperty("mail.smtp.socketFactory.fallback","false");
      
      p.setProperty("mail.smtp.host", this.mailServerHost);   
      p.setProperty("mail.smtp.port", this.mailServerPort);   
      p.setProperty("mail.smtp.auth", validate ? "true" : "false");   
      return p;   
    }   
    public String getMailServerHost() {   
      return mailServerHost;   
    }   
    public void setMailServerHost(String mailServerHost) {   
      this.mailServerHost = mailServerHost;   
    }  
    public String getMailServerPort() {   
      return mailServerPort;   
    }  
    public void setMailServerPort(String mailServerPort) {   
      this.mailServerPort = mailServerPort;   
    }  
    public boolean isValidate() {   
      return validate;   
    }  
    public void setValidate(boolean validate) {   
      this.validate = validate;   
    }  
    public String[] getAttachFileNames() {   
      return attachFileNames;   
    }  
    public void setAttachFileNames(String[] fileNames) {   
      this.attachFileNames = fileNames;   
    }  
    public String getFromAddress() {   
      return fromAddress;   
    }   
    public void setFromAddress(String fromAddress) {   
      this.fromAddress = fromAddress;   
    }  
    public String getPassword() {   
      return password;   
    }  
    public void setPassword(String password) {   
      this.password = password;   
    }  
    public String getToAddress() {   
      return toAddress;   
    }   
    public void setToAddress(String toAddress) {   
      this.toAddress = toAddress;   
    }   
    public String getUserName() {   
      return userName;   
    }  
    public void setUserName(String userName) {   
      this.userName = userName;   
    }  
    public String getSubject() {   
      return subject;   
    }  
    public void setSubject(String subject) {   
      this.subject = subject;   
    }  
    public String getContent() {   
      return content;   
    }  
    public void setContent(String textContent) {   
      this.content = textContent;   
    }   
}   
