package com.threatfabric.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(com.threatfabric.config.AppConfig.class);
        context.refresh();

        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("app", servlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

    }
}
