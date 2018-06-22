package com.lenovo.cloudbuild.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.cloudbuild.util.ResponseStatus;

/**
 * 
 * @ClassName ErrorPageController
 * @Description 错误页面控制器
 * @author Zhang
 * @date 2018年6月21日
 */
@Controller
@RequestMapping("/errorPages")
public class ErrorPageController {
	/**
	 * 日志管理
	 */
	private Logger log = LoggerFactory.getLogger(ErrorPageController.class);

	/**
	 * 
	 * @Title: notFound
	 * @Description: 配置404
	 * @return
	 */
	@RequestMapping(value = "/404")
	@ResponseBody
	public ResponseStatus notFound() {
		log.warn("没有找到对应的信息---404---");
		return ResponseStatus.createFailResponse("没有找到对应的信息");
	}
	@RequestMapping(value = "/400")
	@ResponseBody
	public ResponseStatus badRequest() {
		log.warn("400");
		return ResponseStatus.createFailResponse("400");
	}
	@RequestMapping(value = "/401")
	@ResponseBody
	public ResponseStatus badRequest1() {
		log.warn("401");
		return ResponseStatus.createFailResponse("401");
	}
	@RequestMapping(value = "/500")
	@ResponseBody
	public ResponseStatus badRequest2() {
		log.warn("500");
		return ResponseStatus.createFailResponse("500");
	}

}
