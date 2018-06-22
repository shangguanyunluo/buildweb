package com.lenovo.cloudbuild.controller;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.io.FileUtils;
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

	@Autowired
	private BuildImageService buildService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findall() throws IOException {
		// 文件路径需要根据实景情况来进行修改：普通的文件路径/服务器下的路径
		File buildDirectory = new File("src/main/resources/static/builds");
		Iterable<BuildImage> builds = buildService.listDirectory(buildDirectory);
		// Iterable<BuildImage> builds = buildService.findAll();
		return new ModelAndView("buildpage/builds", "builds", builds);
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) throws IOException {
		String baseDir = "src/main/resources/static/builds/";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		String dfileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
		headers.setContentDispositionFormData("attachment", dfileName);
		File downLoadFile = new File(baseDir, fileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downLoadFile), headers, HttpStatus.CREATED);
	}

	/*
	 * @RequestMapping("/download") public ResponseEntity<byte[]>
	 * download(String fileName, File file) throws IOException {
	 * 
	 * String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	 * headers.setContentDispositionFormData("attachment", dfileName);
	 * 
	 * return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
	 * headers, HttpStatus.CREATED); }
	 */
}
