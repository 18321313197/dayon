package com.dayon.build.framework;

import java.util.ArrayList;
import java.util.List;

import com.build.base.data.MavenProjectBuildInfo;
import com.dayon.build.framework.info.FrameworkMavenParentInfo;
import com.dayon.build.framework.info.FrameworkMavenApiAppInfo;
import com.dayon.build.framework.info.FrameworkMavenServiceAppInfo;
import com.dayon.build.framework.info.FrameworkMavenWebAppInfo;
import com.dayon.build.framework.info.InitListenerInfo;
import com.dayon.build.framework.info.SpringContextWebInfo;
import com.dayon.build.framework.info.WebInfo;
import com.dayon.build.framework.util.BuildUtil;

public class Main {

	public static void main(String[] args) {
		String outPath = "output";

		String frameWorkGroupId = "com.chaohe.framework",
				frameWorkParentArtifactId = "chaohe-framework-parent", 
				frameWorkParentDirName = "framework";
		
		String frameWorkApiArtifactId = "chaohe-framework-api", 
				frameWorkApiDirName = "api",
				frameWorkApiPackage = "com.chaohe.framework.api";
		
		String frameWorkServiceArtifactId = "chaohe-framework-service", 
				frameWorkServiceDirName = "service",
				frameWorkServicePackage = "com.chaohe.framework.service";
		
		String frameWorkWebArtifactId = "chaohe-framework-web", 
				frameWorkWebDirName = "web",
				frameWorkWebPackage = "com.chaohe.framework.web";

		FrameworkMavenParentInfo frameworkMavenParentInfo = new FrameworkMavenParentInfo(frameWorkGroupId,
				frameWorkParentArtifactId, frameWorkParentDirName);

		frameworkMavenParentInfo.getModules().add(frameWorkServiceDirName);
		frameworkMavenParentInfo.getModules().add(frameWorkApiDirName);
		frameworkMavenParentInfo.getModules().add(frameWorkWebDirName);
		frameworkMavenParentInfo.getChildApiAppArtifactIds().add(frameWorkApiArtifactId);
		frameworkMavenParentInfo.getChildApiAppArtifactIds().add(frameWorkServiceArtifactId);
		frameworkMavenParentInfo.getChildApiAppArtifactIds().add(frameWorkWebArtifactId);

		List<MavenProjectBuildInfo> mavenAppInfos = new ArrayList<>();
		FrameworkMavenApiAppInfo mavenApiAppInfo = frameworkMavenParentInfo
				.createChildMavenApiAppInfo(frameWorkApiArtifactId, frameWorkApiDirName, frameWorkApiPackage);
		mavenAppInfos.add(mavenApiAppInfo);
		FrameworkMavenServiceAppInfo mavenServiceAppInfo = frameworkMavenParentInfo.createChildMavenServiceAppInfo(
				frameWorkServiceArtifactId, frameWorkServiceDirName, frameWorkServicePackage);
		mavenAppInfos.add(mavenServiceAppInfo);
		FrameworkMavenWebAppInfo mavenWebAppInfo = frameworkMavenParentInfo
				.createChildMavenWebAppInfo(frameWorkWebArtifactId, frameWorkWebDirName, frameWorkWebPackage);
		mavenWebAppInfo.getJavaFileBuildInfos().add(new InitListenerInfo(frameWorkWebPackage + ".listener"));
		mavenWebAppInfo.getJavaFileBuildInfos().add(new WebInfo(frameWorkWebPackage + ".listener.InitListener"));
		mavenWebAppInfo.getJavaFileBuildInfos().add(new SpringContextWebInfo(frameWorkWebPackage.substring(0,frameWorkWebPackage.lastIndexOf('.'))));
	
		mavenAppInfos.add(mavenWebAppInfo);

		BuildUtil.buildMavenProject(outPath, frameworkMavenParentInfo, mavenAppInfos);
	}

}
