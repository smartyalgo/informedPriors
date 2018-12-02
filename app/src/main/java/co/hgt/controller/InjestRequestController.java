package co.hgt.controller;

import co.hgt.domain.InjestRequest;
import co.hgt.service.ProcessRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;

public class InjestRequestController {
    private static final Logger logger = LoggerFactory.getLogger(InjestRequestController.class);

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ProcessRequest processRequest;

    public Route processDoc(Request req, Response res) {

        try {
            InjestRequest request = mapper.readValue(req.body(), InjestRequest.class);
            logger.info("Injested request");
            processRequest.handleRequest(request.getLink(), request.getText());
            logger.info("processed request");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Route route = new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                res.status(200);
                res.body("Completed persisting for entry");
                return null;
            }
        };
        return route;
    }

}
