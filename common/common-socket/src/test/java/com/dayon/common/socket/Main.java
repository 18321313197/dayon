package com.dayon.common.socket;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.dayon.common.socket.rpc.RpcInvokeHandler;

public class Main  {
	
	public static void main(String[] args) throws Exception {
		RpcInvokeHandler hd=new RpcInvokeHandler("127.0.0.1",8989);
        Test test = (Test)Proxy.newProxyInstance(Main.class.getClassLoader(), 
                new Class[]{Test.class},hd );
        Object obj=test.testStr("123");
        System.out.println(obj);
		
	}
	
}
interface Test{
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
