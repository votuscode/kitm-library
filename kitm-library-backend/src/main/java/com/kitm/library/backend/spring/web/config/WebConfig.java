package com.kitm.library.backend.spring.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  private static final String API_CONTEXT_PATH = "/api";
  private static final String UI_CONTEXT_PATH = "/admin";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("", "/", UI_CONTEXT_PATH, UI_CONTEXT_PATH + "/", UI_CONTEXT_PATH + "/**")
        .addResourceLocations("classpath:/com/kitm/library/admin/")
        .resourceChain(false)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(String resourcePath, Resource location) throws IOException {
            System.out.println(resourcePath);
            Resource requestedResource = location.createRelative(resourcePath);
            boolean resourceFound = requestedResource.exists() && requestedResource.isReadable();
            return resourceFound ? requestedResource : new ClassPathResource("/com/kitm/library/admin/index.html");
          }
        });
  }
}
