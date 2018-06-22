package com.lenovo.cloudbuild.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 
 * @ClassName: ErrorPageConfig
 * @Description: 配置错误页面
 * @author zhang
 * @date 2018年6月21日 下午2:38:49
 */
@Configuration
public class ErrorPageConfig {
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		
		ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/errorPages/400");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errorPages/401");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPages/404");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPages/500");
        factory.addErrorPages(error400Page, error401Page, error404Page, error500Page);
		return factory;
	}
}
