package com.xiaohongchun.xcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Neo on 17/2/22.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class, args);
    }
}
