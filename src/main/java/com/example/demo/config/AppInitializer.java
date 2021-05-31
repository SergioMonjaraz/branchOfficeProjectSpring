package com.example.demo.config;

import com.example.demo.DemoApplication;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { DemoApplication.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
