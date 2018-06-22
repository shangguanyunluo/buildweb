package com.lenovo.cloudbuild.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalVariables {
	
	@Value("${buildImageBaseDir}")
	public String buildImageBaseDir;

	public String getBuildImageBaseDir() {
		return buildImageBaseDir;
	}

	public void setBuildImageBaseDir(String buildImageBaseDir) {
		this.buildImageBaseDir = buildImageBaseDir;
	}
	

}
