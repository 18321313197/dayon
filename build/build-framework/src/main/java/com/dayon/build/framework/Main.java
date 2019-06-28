package com.dayon.build.framework;

import java.util.ArrayList;
import java.util.List;

import com.build.base.data.MavenProjectBuildInfo;
import com.dayon.build.framework.info.FrameworkManagerInfo;
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

		String frameWorkGroupId = "pers.dayon.framework";
		String frameWorkParentArtifactId = "gagent-framework-parent", frameWorkParentDirName = "gagent-framework";
		String frameWorkApiArtifactId = "gagent-framework-api", frameWorkApiDirName = "gagent-framework-api",
				frameWorkApiPackage = "pers.dayon.gagent.framework.api";
		String frameWorkServiceArtifactId = "gagent-framework-service", frameWorkServiceDirName = "gagent-framework-service",
				frameWorkServicePackage = "pers.dayon.gagent.framework.service";
		String frameWorkWebArtifactId = "gagent-framework-web", frameWorkWebDirName = "gagent-framework-web",
				frameWorkWebPackage = "pers.dayon.gagent.framework.web";

		FrameworkManagerInfo frameworkManagerInfo = new FrameworkManagerInfo(frameWorkGroupId,
				frameWorkParentArtifactId, frameWorkParentDirName);

		frameworkManagerInfo.getModules().add(frameWorkServiceDirName);
		frameworkManagerInfo.getModules().add(frameWorkApiDirName);
		frameworkManagerInfo.getModules().add(frameWorkWebDirName);
		frameworkManagerInfo.getChildApiAppArtifactIds().add(frameWorkApiArtifactId);
		frameworkManagerInfo.getChildApiAppArtifactIds().add(frameWorkServiceArtifactId);
		frameworkManagerInfo.getChildApiAppArtifactIds().add(frameWorkWebArtifactId);

		List<MavenProjectBuildInfo> mavenAppInfos = new ArrayList<>();
		FrameworkMavenApiAppInfo mavenApiAppInfo = frameworkManagerInfo
				.createChildMavenApiAppInfo(frameWorkApiArtifactId, frameWorkApiDirName, frameWorkApiPackage);
		mavenAppInfos.add(mavenApiAppInfo);
		FrameworkMavenServiceAppInfo mavenServiceAppInfo = frameworkManagerInfo.createChildMavenServiceAppInfo(
				frameWorkServiceArtifactId, frameWorkServiceDirName, frameWorkServicePackage);
		mavenAppInfos.add(mavenServiceAppInfo);
		FrameworkMavenWebAppInfo mavenWebAppInfo = frameworkManagerInfo
				.createChildMavenWebAppInfo(frameWorkWebArtifactId, frameWorkWebDirName, frameWorkWebPackage);
		mavenWebAppInfo.getJavaFileBuildInfos().add(new InitListenerInfo(frameWorkWebPackage + ".listener"));
		mavenWebAppInfo.getJavaFileBuildInfos().add(new WebInfo(frameWorkWebPackage + ".listener.InitListener"));
		mavenWebAppInfo.getJavaFileBuildInfos().add(new SpringContextWebInfo(frameWorkWebPackage.substring(0,frameWorkWebPackage.lastIndexOf('.'))));
	
		mavenAppInfos.add(mavenWebAppInfo);

		BuildUtil.buildMavenProject(outPath, frameworkManagerInfo, mavenAppInfos);
	}

}
