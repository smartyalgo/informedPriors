package co.hgt;

import co.hgt.controller.InjestRequestController;
import co.hgt.service.ProcessRequest;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import spark.Spark;

@SpringBootApplication
public class InformedPriorsApplication {
    public int port = 8080;

    @Bean
    InjestRequestController requestController() {
        return new InjestRequestController();
    }

    @Bean
    ProcessRequest processRequest() {
        return new ProcessRequest();
    }

    @Bean
    public SparkConfig sparkConfig() {

        return new SparkConfig();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(InformedPriorsApplication.class);
        Spark.staticFileLocation("/public");
        app.setWebEnvironment(false);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
    }
}
