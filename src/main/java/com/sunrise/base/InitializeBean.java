package com.sunrise.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class InitializeBean implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(InitializeBean.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String scbmConfigLocation = sce.getServletContext().getInitParameter("scbmConfigLocation");
		if (scbmConfigLocation == null) {
			throw new NullPointerException("scbmConfigLocation Path is not configed");
		}
		org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
		if (logger.isDebugEnabled()) {
			logger.debug("loading properties...");
		}
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource(scbmConfigLocation);
		InputStream is = null;
		Properties prop = new Properties();
		
		sce.getServletContext().setAttribute("base", "/SRBase"); 

		try {
			is = resource.getInputStream();
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SystemConstants.IS_OPEN_SMS = Boolean.valueOf(prop.getProperty("IS_OPEN_SMS", "false"));

		SystemConstants.IS_DEBUG = Boolean.valueOf(prop.getProperty("IS_DEBUG", "true"));

		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (prop != null) {
			prop.clear();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("loaded properties...");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
