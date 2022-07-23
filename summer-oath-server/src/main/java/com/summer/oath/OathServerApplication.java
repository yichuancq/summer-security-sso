package com.summer.oath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author yichuan
 */
@SpringBootApplication
@ServletComponentScan
public class OathServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OathServerApplication.class, args);
    }
}