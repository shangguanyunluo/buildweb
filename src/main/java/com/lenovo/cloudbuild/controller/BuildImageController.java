package com.lenovo.cloudbuild.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lenovo.cloudbuild.model.BuildImage;
import com.lenovo.cloudbuild.model.Directory;
import com.lenovo.cloudbuild.service.BuildImageService;

/**
 * @ClassName BuildImageController
 * @Description
 * @author Zhang
 * @date 2018年6月21日
 */
@Controller
@RequestMapping(value = "/build")
public class BuildImageController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BuildImageService buildService;

	@RequestMapping(value = "/directories", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Directory> findDirectories(@RequestParam(required = false) Long parentId) throws IOException {

		File buildDirectory = new File(buildService.getBuildBaseDir());
		buildService.getDirectorys(buildDirectory, null); // 初始化数据，当数据存入数据库以后需要调整
		Collection<Directory> directorys = buildService.getDirectorys(parentId);
		log.info("parentId : " + parentId + " size : " + directorys.size());
		return directorys;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listBuildImages(@RequestParam(required = false) String[] directoryName) throws IOException {

		System.out.println(directoryName);
		File buildDirectory = new File(buildService.getBuildBaseDir());

		// 初始化数据，每次都会遍历整个目录，性能会有损耗，当数据存入数据库以后需要调整
		Iterable<BuildImage> builds = buildService.listDirectory(buildDirectory);

		return new ModelAndView("buildpage/builds", "builds", builds);
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam Long id) throws IOException {
		BuildImage buildImage = buildService.getBuildById(id);

		String fileName = buildImage.getName();
		File downLoadFile = new File(buildImage.getAbsolutePath());
		if (!downLoadFile.exists()) {
			log.error(fileName + " can't be found.");
			throw new FileNotFoundException(fileName + " can't be found.");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		String dfileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
		headers.setContentDispositionFormData("attachment", dfileName);

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downLoadFile), headers, HttpStatus.CREATED);
	}

}
