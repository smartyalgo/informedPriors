package co.hgt;

import static spark.Spark.port;
import static spark.Spark.post;

import co.hgt.controller.InjestRequestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

class SparkConfig {
    private static final Logger logger = LoggerFactory.getLogger(SparkConfig.class);

    @Autowired
    InjestRequestController injestRequestController;

    SparkConfig() {
        logger.info("Init path");

        port(8080);

        post("/journal",
                (req, res) -> injestRequestController.processDoc(req, res)
                                                     .handle(req, res));
        }
}
