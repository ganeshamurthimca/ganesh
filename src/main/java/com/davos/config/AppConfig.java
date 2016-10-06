/**
 * 
 */
package com.davos.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.cache.CacheManager;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Configuration
@EnableWebMvc
@EnableAsync
// @EnableCaching
@ComponentScan(basePackages = { "com.davos" })
@Import(value = { AppSecurityConfig.class, MailConfiguration.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(new String[] { "/resources/**", "/img/**" })
				.addResourceLocations(new String[] { "/WEB-INF/resources/", "/WEB-INF/resources/img/" });
		super.addResourceHandlers(registry);
	}

	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver("/WEB-INF/views/",
				".jsp");
		return internalResourceViewResolver;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	// @Bean
	// public CacheManager cacheManager() {
	// return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	// }
	//
	// @Bean
	// public EhCacheManagerFactoryBean ehCacheCacheManager() {
	// EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
	// factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
	// factory.setShared(true);
	// return factory;
	// }

}
