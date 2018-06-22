package com.lenovo.cloudbuild.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		File buildDirectory = new File("src/main/resources/static/builds");
		Iterable<BuildImage> builds = buildService.listDirectory(buildDirectory);
//		Iterable<BuildImage> builds = buildService.findAll();
		return new ModelAndView("buildpage/builds", "builds",builds);
	}
}
