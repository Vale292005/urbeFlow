package com.urbeflow.api.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Servlet;

@Configuration
@ConditionalOnProperty(prefix = "spring.h2.console", name = "enabled", havingValue = "true")
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<Servlet> h2ConsoleServlet() {
        Servlet servlet = createH2ConsoleServlet();
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(servlet, "/h2-console/*");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    private Servlet createH2ConsoleServlet() {
        try {
            Class<?> servletClass = Class.forName("org.h2.server.web.JakartaWebServlet");
            return (Servlet) servletClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException("No se pudo inicializar la consola web de H2", ex);
        }
    }
}