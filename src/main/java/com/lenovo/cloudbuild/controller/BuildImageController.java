package com.lenovo.cloudbuild.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
import org.springframework.web.servlet.ModelAndView;

import com.lenovo.cloudbuild.model.BuildImage;
import com.lenovo.cloudbuild.service.BuildImageService;

/**
 * @ClassName BuildImageController
 * @Description
 * @author Zhang
 * @date 2018年6月21日
 */
@Controller
@RequestMapping(value = "build")
public class BuildImageController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BuildImageService buildService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findall() throws IOException {
		
		File buildDirectory = new File(buildService.getBuildBaseDir());

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
