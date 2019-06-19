package demo.springdemo.controller;

import demo.springdemo.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${test.name}")
    private String name;
    @Value("${test.age}")
    private String age;
    @Value("${test.address}")
    private String address;

    @Autowired
    private TestBean testBean;

    @RequestMapping("/")
    public String hello() {
        return "name: " + this.name + " age: " + age + " address: " + this.address;
    }

    @RequestMapping("/test")
    public String testSetting() {
        return testBean.toString();
    }

}
