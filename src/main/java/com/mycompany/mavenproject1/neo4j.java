package com.mycompany.mavenproject1;

import org.neo4j.driver.v1.*;

public class neo4j implements IInterface {

    private String url = "bolt://localhost:7687";
    private String username = "neo4j";
    private String pwd = "class";
    private Driver driver = null;
    private Session session = null;

    public neo4j() {
    }

    public void Init() {
        driver = GraphDatabase.driver(url, AuthTokens.basic(username, pwd));
        session = driver.session();
    }

    public float ExecuteQuery(String query) {
        Init();
        float tempEstimatedTime = 0;

        try {
            long startTime = System.currentTimeMillis();
            StatementResult result = session.run(query);
            long endTime = System.currentTimeMillis();
            
            tempEstimatedTime = (endTime - startTime) / 1000.0f;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            CleanUp();
        }

        return tempEstimatedTime;
    }

    public String DepthOne(int id) {
        return "MATCH (a:Person {id:" + id + "})-[r:ENDORSES*1]->(b:Person)\n"
                + "RETURN r, a, b\n"
                + "limit 100;";
    }

    public String DepthTwo(int id) {
        return "MATCH (a:Person {id:" + id + "})-[r:ENDORSES*1..2]->(b:Person)\n"
                + "RETURN r, a, b\n"
                + "limit 100;";
    }

    public String DepthThree(int id) {
        return "MATCH (a:Person {id:" + id + "})-[r:ENDORSES*1..3]->(b:Person)\n"
                + "RETURN r, a, b\n"
                + "limit 100;";
    }

    public String DepthFour(int id) {
        return "MATCH (a:Person {id:" + id + "})-[r:ENDORSES*1..4]->(b:Person)\n"
                + "RETURN r, a, b\n"
                + "limit 100;";
    }

    public String DepthFive(int id) {
        return "MATCH (a:Person {id:" + id + "})-[r:ENDORSES*1..5]->(b:Person)\n"
                + "RETURN r, a, b\n"
                + "limit 100;";
    }

    private void CleanUp() {
        if (session != null) {
            session.close();
        }

        if (driver != null) {
            driver.close();
        }
    }
}