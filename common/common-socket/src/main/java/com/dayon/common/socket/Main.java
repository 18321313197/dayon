package com.dayon.common.socket;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main  {
	
	public static void main(String[] args) throws Exception {
		RpcInvocationHandler hd=new RpcInvocationHandler("127.0.0.1",8989,5);
        Object proxy = Proxy.newProxyInstance(Main.class.getClassLoader(), 
                new Class[]{Test.class},hd );
       Object obj= ((Test)proxy).testInt(1,2,3);
       System.out.println(obj);	
       obj =((Test)proxy).testStr("str");
		System.out.println(obj);	
		Thread.sleep(1000L);
	   obj= ((Test)proxy).testInt(1,2,3);
       System.out.println(obj);	
       obj =((Test)proxy).testStr("str");
		System.out.println(obj);	
		
	}
	
}
interface Test{
	String arg1="123";
	int arg2=234;
	Object testInt(int num1,int num2,int num3);
	String testStr(String str);
}


class TestHandler implements InvocationHandler{
	private Class<?> classType;
	
	TestHandler(Class<?> classType) {
		this.classType = classType;
	}
    public Class<?> getClassType() {
        return classType;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	Class<?> c=proxy.getClass();
    	System.out.println(c);
    	System.out.print(method.getName()+"( ");
    	for (Object object : args) {
    		System.out.print(object+" ");
		}
    	System.out.print(")");
    	System.out.println();
    	System.out.println(classType);
    	
    /*	RpcMsg rm=new RpcMsg();
    	rm.setMethodStr(method.toString());
    	rm.setParams(args);
    	rm.getResult(socketSession)*/
    	
    	return null;
    }
}
