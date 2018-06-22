package com.lenovo.cloudbuild.service;

import java.io.File;
import java.io.IOException;

import com.lenovo.cloudbuild.model.BuildImage;
import com.lenovo.cloudbuild.model.Directory;

public interface BuildImageService {

	/*
	 * 列出指定目录下（包括其子目录）的所有文件
	 */
	Iterable<BuildImage> listDirectory(File dir) throws IOException;
	
	public Iterable<Directory> getDirectorys(File dir, Long parentId);

	Iterable<BuildImage> findAll();

	BuildImage getBuildById(Long id);

	String getBuildBaseDir();

}