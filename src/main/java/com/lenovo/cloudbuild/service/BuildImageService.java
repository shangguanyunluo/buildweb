package com.lenovo.cloudbuild.service;

import java.io.File;
import java.io.IOException;

import com.lenovo.cloudbuild.model.BuildImage;

public interface BuildImageService {

	/*
	 * 列出指定目录下（包括其子目录）的所有文件
	 */
	Iterable<BuildImage> listDirectory(File dir) throws IOException;

	Iterable<BuildImage> findAll();

	BuildImage getBuildById(Long id);

	String getBuildBaseDir();

}