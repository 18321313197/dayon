package com.dayon.build.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dayon.build.base.data.Dependencie;
import com.dayon.build.base.data.Table;
import com.dayon.build.base.info.EntityInfo;
import com.dayon.build.base.info.JavaFileBuildInfo;
import com.dayon.build.base.info.MapperInfo;
import com.dayon.build.base.info.MapperXmlInfo;
import com.dayon.build.base.info.MavenAppInfo;
import com.dayon.build.base.info.MavenManagerInfo;
import com.dayon.build.base.info.ServiceImplInfo;
import com.dayon.build.base.info.ServiceInfo;
import com.dayon.build.base.util.BuildUtil;
import com.dayon.build.base.util.MySqlDBPool;

public class Main {

	public static void main(String[] args) {

		String buildPath = "c:";
		String[] dataBaseInfo=new String[]{"127.0.0.1:3306", "root", "12345qwe", "b2b2c"};
		String[] appManager = new String[] { "com.dayon.b2b2c", "b2b2c-parent", "b2b2c" };
		String[][] appManagerProperties = new String[][] { new String[] { "framework.version", "1.0" } };

		String[][] appApis = new String[][] {
				new String[] { "b2b2c-api-auth", "b2b2c-api-auth", "com.dayon.b2b2c.api.auth" ,"t_auth_%"},
				new String[] { "b2b2c-api-user", "b2b2c-api-user", "com.dayon.b2b2c.api.user" ,"t_user_%"} };
		String[][] appCenters = new String[][] {
				new String[] { "b2b2c-center-auth", "b2b2c-center-auth", "com.dayon.b2b2c.center.auth" ,appApis[0][3]},
				new String[] { "b2b2c-center-user", "b2b2c-center-user", "com.dayon.b2b2c.center.user" ,appApis[1][3]}};
		String[][] appWebs = new String[][] {
				new String[] { "b2b2c-web-manage", "b2b2c-web-manage", "com.dayon.b2b2c.web.manage" },
				new String[] { "b2b2c-web-member", "b2b2c-web-member", "com.dayon.b2b2c.web.member" }, };
		
				
				
		MySqlDBPool mySqlDBPool = new MySqlDBPool(dataBaseInfo[0], dataBaseInfo[1], dataBaseInfo[2], dataBaseInfo[3]);
		
		
		Map<String,Map<String,JavaFileBuildInfo>> enittyPckageEntityInfoMap=new HashMap<>();
		MavenManagerInfo managerInfo = new MavenManagerInfo(appManager[0], appManager[1], appManager[2]);
		Set<MavenAppInfo> mavenAppInfos = new HashSet<>();
		for (String[] appManagerPropertie : appManagerProperties) {
			managerInfo.getProperties().put(appManagerPropertie[0], appManagerPropertie[1]);
		}

		
		for (String[] appApi : appApis) {

			MavenAppInfo appApiInfo = managerInfo.createChildMavenAppServiceInfo(appApi[0], appApi[1], appApi[2]);
			Collection<Table> tables=mySqlDBPool.getMySqlTablesInfo(appApi[3]);

			EntityInfo ei = new EntityInfo(appApiInfo.getPackageName() + ".entity", tables);
			ServiceInfo si =new ServiceInfo(appApiInfo.getPackageName() + ".service",ei.getPackageName(), tables);
			
			appApiInfo.getJavaFileBuildInfos().add(ei);
			appApiInfo.getJavaFileBuildInfos().add(si);
			
			
			Map<String,JavaFileBuildInfo> map=new HashMap<>();
			map.put("entity", ei);
			map.put("service", si);
			enittyPckageEntityInfoMap.put(appApi[3], map);
			
			

			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-api");
			dependencie.setScope("compile");
			dependencie.setVersion("${framework.version}");
			appApiInfo.getDependencies().add(dependencie);
			mavenAppInfos.add(appApiInfo);

		}

		for (String[] appCenter : appCenters) {
			MavenAppInfo appCenterInfo = managerInfo.createChildMavenAppServiceInfo(appCenter[0], appCenter[1],
					appCenter[2]);
			EntityInfo entityInfo = (EntityInfo)enittyPckageEntityInfoMap.get(appCenter[3]).get("entity");
			ServiceInfo serviceInfo = (ServiceInfo)enittyPckageEntityInfoMap.get(appCenter[3]).get("service");
			MapperInfo mi =new MapperInfo(appCenterInfo.getPackageName()+".dao", entityInfo.getPackageName(),entityInfo.getTables());
			MapperXmlInfo mix =new MapperXmlInfo(mi.getPackageName(), entityInfo.getPackageName(),entityInfo.getTables());
			ServiceImplInfo sii =new ServiceImplInfo(appCenterInfo.getPackageName()+".service.impl", entityInfo.getPackageName(),mi.getPackageName(),serviceInfo.getPackageName(),entityInfo.getTables());
			
			appCenterInfo.getJavaFileBuildInfos().add(mi);
			appCenterInfo.getJavaFileBuildInfos().add(mix);
			appCenterInfo.getJavaFileBuildInfos().add(sii);
			
			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-center");
			dependencie.setScope("compile");
			dependencie.setVersion("${framework.version}");
			appCenterInfo.getDependencies().add(dependencie);
			
			for (String[] appApi : appApis) {
				dependencie = new Dependencie();
				dependencie.setGroupId("${project.groupId}");
				dependencie.setArtifactId(appApi[0]);
				dependencie.setScope("compile");
				dependencie.setVersion("${project.version}");
				appCenterInfo.getDependencies().add(dependencie);
			}
			mavenAppInfos.add(appCenterInfo);
		}
		
		for (String[] appWeb : appWebs) {
			MavenAppInfo appWebInfo = managerInfo.createChildMavenAppWebInfo(appWeb[0], appWeb[1],
					appWeb[2]);
			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-web");
			dependencie.setVersion("${framework.version}");
			dependencie.setType("war");
			dependencie.setScope("compile");
			
			appWebInfo.getDependencies().add(dependencie);
			
			dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-web");
			dependencie.setVersion("${framework.version}");
			dependencie.setScope("provided");
			dependencie.setType("jar");
			dependencie.setClassifier("api");
			
			appWebInfo.getDependencies().add(dependencie);
			
			dependencie = new Dependencie();
			dependencie.setGroupId("org.apache.tomcat");
			dependencie.setArtifactId("tomcat-servlet-api");
			dependencie.setVersion("${tomcat.version}");
			dependencie.setScope("provided");
			dependencie.setType("jar");
			
			appWebInfo.getDependencies().add(dependencie);
			
			for (String[] appApi : appApis) {
				dependencie = new Dependencie();
				dependencie.setGroupId("${project.groupId}");
				dependencie.setArtifactId(appApi[0]);
				dependencie.setScope("compile");
				dependencie.setVersion("${project.version}");
				appWebInfo.getDependencies().add(dependencie);
			}
			mavenAppInfos.add(appWebInfo);
		}

		BuildUtil.buildMavenProject(buildPath, managerInfo, mavenAppInfos);

	}

}
