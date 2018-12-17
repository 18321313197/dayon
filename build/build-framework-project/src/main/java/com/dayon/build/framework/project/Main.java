package com.dayon.build.framework.project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.dayon.build.framework.project.data.Dependencie;
import com.dayon.build.framework.project.data.Table;
import com.dayon.build.framework.project.info.AppInfo;
import com.dayon.build.framework.project.info.EntityInfo;
import com.dayon.build.framework.project.info.IndexControllerInfo;
import com.dayon.build.framework.project.info.MapperInfo;
import com.dayon.build.framework.project.info.MapperXmlInfo;
import com.dayon.build.framework.project.info.MavenAppInfo;
import com.dayon.build.framework.project.info.MavenManagerInfo;
import com.dayon.build.framework.project.info.ServiceImplInfo;
import com.dayon.build.framework.project.info.ServiceInfo;
import com.dayon.build.framework.project.info.SpringDubboReferenceXmlInfo;
import com.dayon.build.framework.project.info.SpringDubboServiceXmlInfo;
import com.dayon.build.framework.project.util.BuildUtil;
import com.dayon.build.framework.project.util.MySqlDBPool;

public class Main {

	public static void main(String[] args) {

		String dataBaseIpPort = "127.0.0.1:3306";
		String dataBaseUser = "root";
		String dataBasePassword = "12345qwe";
		String dataBaseDataName = "b2b2c";

		String managerGroupId = "com.dayon.test";
		String managerArtifactId = "test-parent";
		String managerDirName = "test-project";

		String frameWorkGroupId = "com.dayon.framework";
		String frameWorkVersion = "1.0.0";
		String frameWorkApiArtifactId = "framework-api";
		String frameWorkServerArtifactId = "framework-center";
		String frameWorkWebArtifactId = "framework-web";

		String outPath = "output";

		String[] tableLikes = new String[] { "t_auth_%", "t_user_%" };

		String[][] appApis = new String[][] {
				// artifactId dirName packageName 数据表名
				new String[] { "test-api-auth", "test-api-auth", managerGroupId + ".api.auth", tableLikes[0] },
				new String[] { "test-api-user", "test-api-user", managerGroupId + ".api.user", tableLikes[1] } };
		String[][] appServers = new String[][] {
				// artifactId dirName packageName 引用appApis中的数据表名(必须存在)
				new String[] { "test-server-auth", "test-server-auth", managerGroupId + ".server.auth", appApis[0][0] },
				new String[] { "test-server-user", "test-server-user", managerGroupId + ".server.user",
						appApis[1][0] } };
		String[][] appWebs = new String[][] {
				// artifactId dirName packageName
				new String[] { "test-web-manage", "test-web-manage", managerGroupId + ".web.manage" },
				new String[] { "test-web-member", "test-web-member", managerGroupId + ".web.member" }, };

		Map<String, Collection<Table>> tablesMap = new HashMap<>();
		MySqlDBPool mySqlDBPool = new MySqlDBPool(dataBaseIpPort, dataBaseUser, dataBasePassword, dataBaseDataName);
		for (String tableLike : tableLikes) {
			Collection<Table> tables = mySqlDBPool.getMySqlTablesInfo(tableLike);
			tablesMap.put(tableLike, tables);
		}

		MavenManagerInfo managerInfo = new MavenManagerInfo(managerGroupId, managerArtifactId, frameWorkGroupId,
				frameWorkVersion, frameWorkApiArtifactId, frameWorkServerArtifactId, frameWorkWebArtifactId,
				managerDirName);

		Map<String, MavenAppInfo> mavenAppInfoMap = new HashMap<>();
		Map<String, EntityInfo> entityMap = new HashMap<>();
		Map<String, ServiceInfo> serviceMap = new HashMap<>();
		for (String[] appApi : appApis) {

			MavenAppInfo appApiInfo = managerInfo.createChildMavenAppInfo(appApi[0], appApi[1], appApi[2]);
			managerInfo.getChildApiAppArtifactIds().add(appApiInfo.getArtifactId());
			managerInfo.getModules().add(appApiInfo.getProjectDirName());
			Collection<Table> tables = tablesMap.get(appApi[3]);

			EntityInfo ei = new EntityInfo(appApiInfo.getPackageName() + ".entity", tables);
			ServiceInfo si = new ServiceInfo(appApiInfo.getPackageName() + ".service", ei.getPackageName(), tables);

			appApiInfo.getJavaFileBuildInfos().add(ei);
			appApiInfo.getJavaFileBuildInfos().add(si);

			entityMap.put(appApiInfo.getArtifactId(), ei);
			serviceMap.put(appApiInfo.getArtifactId(), si);

			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId(frameWorkGroupId);
			dependencie.setArtifactId(frameWorkApiArtifactId);
			appApiInfo.getDependencies().add(dependencie);
			mavenAppInfoMap.put(appApiInfo.getArtifactId(), appApiInfo);

		}

		for (String[] appServer : appServers) {
			MavenAppInfo appServerInfo = managerInfo.createChildMavenAppInfo(appServer[0], appServer[1], appServer[2]);
			managerInfo.getModules().add(appServerInfo.getProjectDirName());
			EntityInfo entityInfo = entityMap.get(appServer[3]);
			ServiceInfo serviceInfo = serviceMap.get(appServer[3]);
			MapperInfo mi = new MapperInfo(appServerInfo.getPackageName() + ".mapper", entityInfo.getPackageName(),
					entityInfo.getTables());
			MapperXmlInfo mix = new MapperXmlInfo(mi.getPackageName(), entityInfo.getPackageName(),
					entityInfo.getTables());
			ServiceImplInfo sii = new ServiceImplInfo(appServerInfo.getPackageName() + ".service.impl",
					entityInfo.getPackageName(), mi.getPackageName(), serviceInfo.getPackageName(),
					entityInfo.getTables());
			AppInfo ai = new AppInfo(appServer[2]);
			SpringDubboServiceXmlInfo sdsxi = new SpringDubboServiceXmlInfo(serviceInfo.getPackageName(),
					entityInfo.getTables());

			SpringDubboReferenceXmlInfo sdrxi = new SpringDubboReferenceXmlInfo();
			for (ServiceInfo si : serviceMap.values()) {
				if (si != serviceInfo) {
					sdrxi.getServiceInfos().add(si);
				}
			}

			appServerInfo.getJavaFileBuildInfos().add(mi);
			appServerInfo.getJavaFileBuildInfos().add(mix);
			appServerInfo.getJavaFileBuildInfos().add(sii);
			appServerInfo.getJavaFileBuildInfos().add(sdsxi);
			appServerInfo.getJavaFileBuildInfos().add(sdrxi);
			appServerInfo.getJavaFileBuildInfos().add(ai);

			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId(frameWorkGroupId);
			dependencie.setArtifactId(frameWorkServerArtifactId);
			appServerInfo.getDependencies().add(dependencie);

			for (String[] appApi : appApis) {
				dependencie = new Dependencie();
				dependencie.setGroupId("${project.groupId}");
				dependencie.setArtifactId(appApi[0]);
				appServerInfo.getDependencies().add(dependencie);
			}
			mavenAppInfoMap.put(appServerInfo.getArtifactId(), appServerInfo);
		}

		for (String[] appWeb : appWebs) {
			MavenAppInfo appWebInfo = managerInfo.createChildMavenWebAppInfo(appWeb[0], appWeb[1], appWeb[2]);
			managerInfo.getModules().add(appWebInfo.getProjectDirName());
			IndexControllerInfo indexControllerInfo = new IndexControllerInfo(
					appWebInfo.getPackageName() + ".controller");

			SpringDubboReferenceXmlInfo sdrxi = new SpringDubboReferenceXmlInfo();
			for (ServiceInfo si : serviceMap.values()) {
				sdrxi.getServiceInfos().add(si);
			}
			appWebInfo.getJavaFileBuildInfos().add(sdrxi);
			appWebInfo.getJavaFileBuildInfos().add(indexControllerInfo);

			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId(frameWorkGroupId);
			dependencie.setArtifactId(frameWorkWebArtifactId);
			dependencie.setType("war");

			appWebInfo.getDependencies().add(dependencie);

			dependencie = new Dependencie();
			dependencie.setGroupId(frameWorkGroupId);
			dependencie.setArtifactId(frameWorkWebArtifactId);
			dependencie.setClassifier("api");

			appWebInfo.getDependencies().add(dependencie);

			dependencie = new Dependencie();
			dependencie.setGroupId("javax.servlet");
			dependencie.setArtifactId("javax.servlet-api");
			appWebInfo.getDependencies().add(dependencie);

			dependencie = new Dependencie();
			dependencie.setGroupId("javax.servlet.jsp");
			dependencie.setArtifactId("javax.servlet.jsp-api");
			appWebInfo.getDependencies().add(dependencie);

			dependencie = new Dependencie();
			dependencie.setGroupId("javax.servlet");
			dependencie.setArtifactId("jstl");
			appWebInfo.getDependencies().add(dependencie);

			for (String[] appApi : appApis) {
				dependencie = new Dependencie();
				dependencie.setGroupId("${project.groupId}");
				dependencie.setArtifactId(appApi[0]);
				appWebInfo.getDependencies().add(dependencie);
			}

			mavenAppInfoMap.put(appWebInfo.getArtifactId(), appWebInfo);

		}

		BuildUtil.buildMavenProject(outPath, managerInfo, mavenAppInfoMap.values());

	}

}
