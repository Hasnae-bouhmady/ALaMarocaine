
package com.bridgelabz.alamarocaine.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration

/**
 * @Configuration annotation indicates that the class has @Bean definition
 *                methods. So Spring container can process the class and
 *                generate Spring Beans to be used in the application. This
 *                annotation is part of the spring core framework
 * 
 * 
 */
public class ApplicationConfigurations {

	/**
	 * To declare a bean, simply annotate a method with the @Bean annotation. When
	 * JavaConfig encounters such a method, it will execute that method and register
	 * the return value as a bean within a BeanFactory. By default, the bean name
	 * will be the same as the method name.
	 * 
	 * 
	 * 
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEncryption() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
