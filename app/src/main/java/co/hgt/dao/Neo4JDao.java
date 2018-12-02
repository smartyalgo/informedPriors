package co.hgt.dao;

import co.hgt.domain.PoSOutput;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class Neo4JDao {
    String uri = "";
    String user = "";
    String password = "";

    private final Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));

    public void writePoint(PoSOutput entry) {
        "CREATE (a: " + entry.getObject() + "");
    }

}
