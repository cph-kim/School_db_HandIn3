# School_db_HandIn3

MySQL at depth 4 and 5 took too long for my computer to process, hence the reason I haven't included the results at that depth.

I'm wondering why there is such fast response when processing the Neo4J queries. I speculate that the reason is when I'm executing 
**session.run(query);** the returned **Result stream** has not fully completed the query, instead it lets me access the nodes which 
is available right away.

I have not tested my speculation yet.

Result

![Alt text](result.JPG?raw=true)
