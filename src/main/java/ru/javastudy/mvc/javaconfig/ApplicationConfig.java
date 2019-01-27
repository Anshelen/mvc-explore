package ru.javastudy.mvc.javaconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.client.RestTemplate;
import ru.javastudy.mvc.core.scheduling.CronQuartzTask;
import ru.javastudy.mvc.core.scheduling.QuartzTask;

import java.util.Properties;


/**
 * Created for JavaStudy.ru on 28.05.2016.
 * application-context.xml analogue
 */
@Configuration
@PropertySource(value = "classpath:util.properties") //<context:property-placeholder location=".." />
@EnableScheduling
public class ApplicationConfig {

    /**
     * @PropertySource annotation does not automatically
     * register a PropertySourcesPlaceholderConfigurer with Spring.
     * So we need to initialize this bean.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${jdbc.hsqldb.driverClass}")
    private String driverClass;
    @Value("${jdbc.hsqldb.url}")
    private String jdbcUrl;
    @Value("${jdbc.hsqldb.username}")
    private String jdbcUserName;
    @Value("${jdbc.hsqldb.password}")
    private String jdbcPassword;

    @Value("classpath:dbschema.sql")
    private Resource dbschemaSqlScript;
    @Value("classpath:test-data.sql")
    private Resource testDataSqlScript;

    /**
     * <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
     */
    @Bean(name = "dataSource")
    public DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    /**
     * <jdbc:initialize-database data-source="dataSource">
     * initialize Embedded DataSource. Встроенная база данных
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(getDriverManagerDataSource());
        initializer.setDatabasePopulator(getDatabasePopulator());
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbschemaSqlScript);
        populator.addScript(testDataSqlScript);
        return populator;
    }

    /**
     *  Java Mail Configuration
     */
    @Value("${java.mail.username}") String mailUsername;
    @Value("${java.mail.password}") String mailPassword;
    @Value("${java.mail.host}") String mailHost;

    /**
     *  <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
     */
    @Bean(name = "mailSender")
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);
        javaMailSender.setPort(465);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.starttls.required", true);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.host", mailHost);

        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

    /**
     * <bean id="velocityEngine" class="org.springframework.ui.velocity.VelocityEngineFactoryBean">
     */
    @Bean(name = "velocityEngine")
    public VelocityEngineFactoryBean getVelocityEngineFactoryBean() {
        VelocityEngineFactoryBean bean = new VelocityEngineFactoryBean();
        bean.setResourceLoaderPath("/WEB-INF/email-templates/");
        return bean;
    }

    /**
     *  <bean id="restTemplate" class="org.springframework.web.client.RestTemplate"/>
     */
    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * <bean id="entityManagerFactory"
     class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" >
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(new String[] {"ru.javastudy.mvc.core.bean","ru.javastudy.mvc.core.rest.model"});
        em.setDataSource(getDriverManagerDataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter)vendorAdapter).setGenerateDdl(true);
        ((HibernateJpaVendorAdapter)vendorAdapter).setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.HSQLDialect");
        jpaProperties.put("hibernate.show_sql",true);
        jpaProperties.put("hibernate.format_sql","false");
        jpaProperties.put("hibernate.hbm2ddl.auto","update");

        em.setJpaProperties(jpaProperties);
        return em;
    }

    @Bean(name = "jpaTransactionManager")
    public JpaTransactionManager getJpaTransactionManager() {
        JpaTransactionManager jpa = new JpaTransactionManager();
        jpa.setEntityManagerFactory(getLocalContainerEntityManagerFactoryBean().getNativeEntityManagerFactory());
        return jpa;
    }

    /* Quartz scheduling configuration */

    /**
     *   <bean id="simpleTrigger" class="org.springframework.scheduling.quartz.SimpleTriggerFactoryBean">
     */
    @Bean(name = "simpleTrigger")
    public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean stfb = new SimpleTriggerFactoryBean();

        stfb.setJobDetail(getSimpleQuartzJob().getObject());

        stfb.setRepeatInterval(1000);
        stfb.setStartDelay(1000);
        return stfb;
    }

    /**
     *  <bean id="cronTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
     */
    @Bean(name = "cronTrigger")
    public CronTriggerFactoryBean getCronTriggerFactoryBean() {
        CronTriggerFactoryBean ctfb = new CronTriggerFactoryBean();

        ctfb.setJobDetail(getQuartzCronJob().getObject());

        ctfb.setCronExpression("0/30 * * * * ?");
        return ctfb;
    }

    /**
     *  <bean id="simpleQuartzJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
     */
    @Bean(name = "simpleQuartzJob")
    public MethodInvokingJobDetailFactoryBean getSimpleQuartzJob() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetBeanName("simpleQuartzTask");
        factoryBean.setTargetMethod("simpleTaskMethod");
        return factoryBean;
    }
    /**
     *  <bean id="quartzCronJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
     */
    @Bean(name = "quartzCronJob")
    public MethodInvokingJobDetailFactoryBean getQuartzCronJob() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetBeanName("cronQuartzTask");
        factoryBean.setTargetMethod("cronTaskMethod");
        return factoryBean;
    }

    /**
     *  <bean id="simpleQuartzTask" class="ru.javastudy.mvcHtml5Angular.mvc.quartz.QuartzTask" />
     */
    @Bean(name = "simpleQuartzTask")
    public QuartzTask getSimpleQuartzTask() {
        return new QuartzTask();
    }

    /**
     *   <bean id="cronQuartzTask" class="ru.javastudy.mvcHtml5Angular.mvc.quartz.CronQuartzTask" />
     */
    @Bean(name = "cronQuartzTask")
    public CronQuartzTask getCronQuartzTask() {
        return new CronQuartzTask();
    }

    /**
     *  <bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
     */
    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean getSchedulerFactoryBean() {
        SchedulerFactoryBean scheduler  = new SchedulerFactoryBean();
        scheduler.setTriggers(getSimpleTriggerFactoryBean().getObject(), getCronTriggerFactoryBean().getObject());
        return scheduler;
    }
}
