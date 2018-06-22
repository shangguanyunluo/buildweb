package com.lenovo.cloudbuild.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.cloudbuild.model.BuildImage;
import com.lenovo.cloudbuild.model.Directory;
import com.lenovo.cloudbuild.model.GlobalVariables;
import com.lenovo.cloudbuild.service.BuildImageService;

@Service
public class BuildImageServiceImpl implements BuildImageService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GlobalVariables globalVariables;

	private static AtomicLong counter = new AtomicLong();
	private final ConcurrentMap<Long, BuildImage> builds = new ConcurrentHashMap<Long, BuildImage>();
	private final ConcurrentMap<Long, Directory> directorys = new ConcurrentHashMap<Long, Directory>();

	/*
	 * 列出指定目录下（包括其子目录）的所有文件
	 */
	@Override
	public Iterable<BuildImage> listDirectory(File dir) throws IOException {
		if (!dir.exists())
			throw new IllegalArgumentException("Directory:" + dir + "not exists.");
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "isn't directory.");
		}

		// 如果要遍历子目录下的内容就需要构造File对象做递归操作，File提供了直接返回File对象的API
		File[] files = dir.listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory())
					// 递归
					listDirectory(file);
				else {
					BuildImage buildImage = new BuildImage(file.getName(), new Date(file.lastModified()), file.length(),
							"", file.getAbsolutePath());
					log.info("builds contains buildImage " + file.getName() + " :"
							+ builds.values().contains(buildImage));
					if (!builds.values().contains(buildImage)) {
						Long imageId = counter.incrementAndGet();
						buildImage.setId(imageId);
						builds.put(imageId, buildImage);
					}

				}
			}
		}
		return builds.values();
	}

	public Iterable<Directory> getDirectorys(File dir, Long parentId) {
		if (!dir.exists())
			throw new IllegalArgumentException("Directory:" + dir + "not exists.");
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "isn't directory.");
		}

		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				long directoryId = counter.incrementAndGet();
				directorys.put(directoryId, new Directory(directoryId, file.getName(), parentId));
				getDirectorys(file, directoryId);
			}
//			System.out.println(file);
		}
		return directorys.values();
	}

	@Override
	public Iterable<BuildImage> findAll() {
		return this.builds.values();
	}

	@Override
	public BuildImage getBuildById(Long id) {
		return builds.get(id);
	}

	@Override
	public String getBuildBaseDir() {
		return globalVariables.getBuildImageBaseDir();
	}

	public static void main(String[] args) throws IOException {
		BuildImageServiceImpl imageService = new BuildImageServiceImpl();
		Iterable<Directory> directorys = imageService.getDirectorys(new File("E:/builds"), null);
		System.out.println(directorys);

	}

}
