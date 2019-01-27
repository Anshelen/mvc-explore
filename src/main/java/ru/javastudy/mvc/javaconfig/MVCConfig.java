package ru.javastudy.mvc.javaconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import ru.javastudy.mvc.core.interceptors.SiteInterceptor;

import java.util.List;
import java.util.Locale;

/**
 * Created for JavaStudy.ru on 28.05.2016.
 * mvc-config.xml analogue
 */
@EnableWebMvc  //<mvc:annotation-driven>
@Configuration
@ComponentScan(basePackages = {"ru.javastudy.mvc.core"}) //<context:component-scan base-package=''>
@EnableTransactionManagement
@EnableGlobalMethodSecurity(
    prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class MVCConfig extends WebMvcConfigurerAdapter {

    /**
     * <mvc:resources mapping="/resources/**" location="/resources/" />
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     *     <mvc:view-controller path="/about.html" view-name="/about/about"/>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/index.html").setViewName("/index");
        registry.addViewController("/login.html").setViewName("/form/login");
        registry.addViewController("/about.html").setViewName("/about/about");
        registry.addViewController("/file.html").setViewName("/file/file");
        registry.addViewController("/jdbc.html").setViewName("/jdbc/jdbc");
        registry.addViewController("/email.html").setViewName("/email/email");
        registry.addViewController("/rest.html").setViewName("/rest/rest");
        registry.addViewController("/orm.html").setViewName("/orm/orm");
        registry.addViewController("/jstl.html").setViewName("/jstl/jstl");
        registry.addViewController("/scope.html").setViewName("/scope/scope");
        registry.addViewController("/cookie.html").setViewName("/cookie/cookieView");
        registry.addViewController("/security.html").setViewName("/security/security");
        registry.addViewController("/security/admin.html").setViewName("/security/admin");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getJacksonHttpMessageConverter());
    }

    @Bean(name = "jacksonHttpMessageConverter")
    public MappingJackson2HttpMessageConverter getJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setPrettyPrint(true);
        return converter;
    }

    /**
     * <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(1000000);
        return cmr;
    }

    /**
     *  <bean class="org.springframework.web.servlet.view.XmlViewResolver">
     */
    @Bean(name = "xmlViewResolver")
    public XmlViewResolver getXmlViewResolver() {
        XmlViewResolver xmlViewResolver = new XmlViewResolver();
        //note it in java resources, not webapp
        Resource resource = new ClassPathResource("excel-pdf-config.xml");
        xmlViewResolver.setOrder(0);
        xmlViewResolver.setLocation(resource);
        return xmlViewResolver;
    }

    /**
     *  <mvc:interceptors>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(siteInterceptor).addPathPatterns("/interceptorCall/*");
        registry.addInterceptor(getLocaleChangeInterceptor()).addPathPatterns("/*");
    }

    @Bean(name = "localeChangeInterceptor")
    public LocaleChangeInterceptor getLocaleChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("languageVar");
        return localeChangeInterceptor;
    }

    @Autowired
    private SiteInterceptor siteInterceptor;

    /**
     *  <bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver">
     */
    @Bean(name = "localeResolver")
    public CookieLocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("ru"));
        cookieLocaleResolver.setCookieMaxAge(100000);
        return cookieLocaleResolver;
    }

    /**
     * <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/locales/messages");
        resource.setCacheSeconds(1);
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
}
