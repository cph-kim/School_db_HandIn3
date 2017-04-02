package com.mycompany.mavenproject1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mysql implements IInterface {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private String url = "jdbc:mysql://localhost:3306/school_db_exercise3?verifyServerCertificate=false&useSSL=true";
    private String user = "root";
    private String password = "pwd";

    public mysql() {
    }

    public void Init() {
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
            CleanUp();
        }
    }

    public float ExecuteQuery(String query) {
        Init();
        float tempEstimatedTime = 0;

        try {
            st = (Statement) con.createStatement();

            long startTime = System.currentTimeMillis();
            rs = st.executeQuery(query);
            long endTime = System.currentTimeMillis();
            
            tempEstimatedTime = (endTime - startTime) / 1000.0f;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            CleanUp();
        }

        return tempEstimatedTime;
    }

    public String DepthOne(int id) {
        return ""
                + "       SELECT\n"
                + "           name\n"
                + "       FROM \n"
                + "           t_user\n"
                + "       WHERE \n"
                + "           t_user.id \n"
                + "       IN\n"
                + "           (\n"
                + "               select \n"
                + "                   endorsed\n"
                + "               from\n"
                + "                   t_endorsements\n"
                + "               where\n"
                + "                   endorser = " + id + "\n"
                + "           )";
    }

    public String DepthTwo(int id) {
        return "SELECT\n"
                + "	*\n"
                + "FROM \n"
                + "	t_user\n"
                + "WHERE \n"
                + "	t_user.id \n"
                + "IN\n"
                + "(\n"
                + "	SELECT\n"
                + "		endorsed\n"
                + "	FROM\n"
                + "		t_endorsements\n"
                + "	WHERE\n"
                + "		endorser\n"
                + "	IN\n"
                + "	(\n"
                + "		select \n"
                + "			endorsed\n"
                + "		from\n"
                + "			t_endorsements\n"
                + "		where\n"
                + "			endorser = " + id + "\n"
                + "	) \n"
                + ")";
    }

    public String DepthThree(int id) {
        return "SELECT\n"
                + "	*\n"
                + "FROM \n"
                + "	t_user\n"
                + "WHERE \n"
                + "	t_user.id \n"
                + "IN\n"
                + "(\n"
                + "	SELECT\n"
                + "		endorsed\n"
                + "	FROM\n"
                + "		t_endorsements\n"
                + "	WHERE\n"
                + "		endorser\n"
                + "	IN \n"
                + "	(\n"
                + "		SELECT\n"
                + "			endorsed\n"
                + "		FROM\n"
                + "			t_endorsements\n"
                + "		WHERE\n"
                + "			endorser\n"
                + "		IN\n"
                + "		(\n"
                + "			select \n"
                + "				endorsed\n"
                + "			from\n"
                + "				t_endorsements\n"
                + "			where\n"
                + "				endorser = 3\n"
                + "		) \n"
                + "	)\n"
                + ")";
    }

    public String DepthFour(int id) {
        return "SELECT\n"
                + "	*\n"
                + "FROM \n"
                + "	t_user\n"
                + "WHERE \n"
                + "	t_user.id \n"
                + "IN\n"
                + "(\n"
                + "	SELECT\n"
                + "		endorsed\n"
                + "	FROM\n"
                + "		t_endorsements\n"
                + "	WHERE\n"
                + "		endorser\n"
                + "	IN \n"
                + "	(\n"
                + "		SELECT\n"
                + "			endorsed\n"
                + "		FROM\n"
                + "			t_endorsements\n"
                + "		WHERE\n"
                + "			endorser\n"
                + "		IN \n"
                + "		(\n"
                + "			SELECT\n"
                + "				endorsed\n"
                + "			FROM\n"
                + "				t_endorsements\n"
                + "			WHERE\n"
                + "				endorser\n"
                + "			IN\n"
                + "			(\n"
                + "				select \n"
                + "					endorsed\n"
                + "				from\n"
                + "					t_endorsements\n"
                + "				where\n"
                + "					endorser = " + id + "\n"
                + "			) \n"
                + "		)\n"
                + "	)\n"
                + "	\n"
                + ")";
    }

    public String DepthFive(int id) {
        return "SELECT\n"
                + "	*\n"
                + "FROM \n"
                + "	t_user\n"
                + "WHERE \n"
                + "	t_user.id \n"
                + "IN\n"
                + "(\n"
                + "	SELECT\n"
                + "		endorsed\n"
                + "	FROM\n"
                + "		t_endorsements\n"
                + "	WHERE\n"
                + "		endorser\n"
                + "	IN \n"
                + "		(\n"
                + "		SELECT\n"
                + "			endorsed\n"
                + "		FROM\n"
                + "			t_endorsements\n"
                + "		WHERE\n"
                + "			endorser\n"
                + "		IN \n"
                + "		(\n"
                + "			SELECT\n"
                + "				endorsed\n"
                + "			FROM\n"
                + "				t_endorsements\n"
                + "			WHERE\n"
                + "				endorser\n"
                + "			IN \n"
                + "			(\n"
                + "				SELECT\n"
                + "					endorsed\n"
                + "				FROM\n"
                + "					t_endorsements\n"
                + "				WHERE\n"
                + "					endorser\n"
                + "				IN\n"
                + "				(\n"
                + "					select \n"
                + "						endorsed\n"
                + "					from\n"
                + "						t_endorsements\n"
                + "					where\n"
                + "						endorser = " + id + "\n"
                + "				) \n"
                + "			)\n"
                + "		)\n"
                + "	)\n"
                + ")";
    }

    private void CleanUp() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        }
    }
}