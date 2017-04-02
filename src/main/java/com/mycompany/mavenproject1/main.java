package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        List<Integer> randomNodes = new ArrayList<>(20);
        while(true){
            if (randomNodes.size() == 20)
                break;
            
            Random rnd = new Random();
            int value = rnd.nextInt(1001);
            if (!randomNodes.contains(value))
                randomNodes.add(value);
        }
        
        IInterface mysql = new mysql();
        IInterface neo4j = new neo4j();
        
        
        List<Float> tempTimes = new ArrayList<>();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(neo4j, neo4j.DepthOne(startNode)));
        });
        System.out.println("Neo4j Depth 1 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size());
        
        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(neo4j, neo4j.DepthTwo(startNode)));
        });
        System.out.println("Neo4j Depth 2 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size());        
   
        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(neo4j, neo4j.DepthThree(startNode)));
        });
        System.out.println("Neo4j Depth 3 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size()); 

        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(neo4j, neo4j.DepthFour(startNode)));
        });
        System.out.println("Neo4j Depth 4 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size()); 

        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(neo4j, neo4j.DepthFive(startNode)));
        });
        System.out.println("Neo4j Depth 5 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size());  
        
        
        
        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(mysql, mysql.DepthOne(startNode)));
        });
        System.out.println("MySQL Depth 1 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size());
        
        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(mysql, mysql.DepthTwo(startNode)));
        });
        System.out.println("MySQL Depth 2 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size());        
   
        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(mysql, mysql.DepthThree(startNode)));
        });
        System.out.println("MySQL Depth 3 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size()); 

        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(mysql, mysql.DepthFour(startNode)));
        });
        System.out.println("MySQL Depth 4 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size()); 

        tempTimes.clear();
        randomNodes.forEach((Integer startNode)->{
            tempTimes.add(run(mysql, mysql.DepthFive(startNode)));
        });
        System.out.println("MySQL Depth 5 - Median: " + tempTimes.get(10) + " Average: " + GetSum(tempTimes)/randomNodes.size());          
    }
    private static float run(IInterface d, String query){
        return d.ExecuteQuery(query);
    }
    
    private static float GetSum(List<Float> array){
        long tempSum = 0;
        for(int i=0; i<array.size(); i++){
            tempSum += array.get(i);
        }
        return tempSum;
    }
}