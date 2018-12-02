package co.hgt.service;

import co.hgt.domain.PoSOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ProcessRequest {
    private static final Logger logger = LoggerFactory.getLogger(ProcessRequest.class);
    ObjectMapper mapper = new ObjectMapper();



    public void handleRequest(String link, String body)
    throws IOException {
        // Call Python on 5000

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:5000/parse");

        ObjectNode node = mapper.createObjectNode();
        node.put("body", body);

        post.setEntity(new StringEntity(mapper.writeValueAsString(node),
                                        ContentType.APPLICATION_JSON));

        HttpResponse response = client.execute(post);
        InputStreamReader isr = new InputStreamReader(response.getEntity().getContent(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        String responseBodyString = buf.toString();

        List<String> tuples = Arrays.asList(responseBodyString.split("\\),"));

        for (String tuple : tuples) {
            String[] entry = tuple.split(",");
            PoSOutput output = new PoSOutput();
            output.setSubject(entry[0]);
            output.setObject(entry[1]);
            output.setVerb(entry[2]);
            output.setMagnitude(getScore(output.getVerb()));

            logger.info("PoS: {}", mapper.writeValueAsString(output));
        }




        // Persist to Neo4J


    }

    public double getScore(String verb) {
        File file = new File(verb);
        BufferedReader br = null;
        String st;
        double out = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                return Double.valueOf(st);
            }
        } catch (IOException e) {
            return 0;
        }
        return out;
    }

    public void persistPoint(PoSOutput output) {

    }


}
