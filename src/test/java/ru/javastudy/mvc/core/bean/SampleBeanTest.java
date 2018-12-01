package ru.javastudy.mvc.core.bean;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created for JavaStudy.ru on 04.03.2016.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-config.xml", "classpath:applicationContext.xml"})
public class SampleBeanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void sampleTest() {
        SampleBean sampleBean = applicationContext.getBean("sampleBean", SampleBean.class);
        Assert.assertNotNull(sampleBean);

        sampleBean = (SampleBean) applicationContext.getBean("sampleBean");
        Assert.assertNotNull(sampleBean);

        Assert.assertEquals(sampleBean.getNumber(), 42);
        Assert.assertEquals(sampleBean.getStringValue(), "postConstructValue");
    }
}
