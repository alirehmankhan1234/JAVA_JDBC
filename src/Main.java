
import java.sql.*;

// DAO -> Data Access Object
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("JDBC DB Connectivity Example\n\n");

        String url = "jdbc:sqlserver://localhost:1433;databaseName=springDB;encrypt=true;trustServerCertificate=true";
        String uname = "alirehman";
        String pass = "alirehman";
        String query = "SELECT * FROM springDB.dbo.employees";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String userData = rs.getInt(1)+" "+rs.getString(2);
            System.out.println(userData);
            System.out.println("\n\n");
        }

        String insert = "insert into springDB.dbo.employees values('badal.khan@gmail.com','badal', 'khan')";
        Statement st2 = con.createStatement();
        int effectedRows = st.executeUpdate(insert);

        System.out.println(effectedRows + " rows effected\n\n");
        st.close();

        System.out.println("Prepared Statement(Used to Execute PL/SQL)\n\n");
        String plSqlQuery = "insert into springDB.dbo.employees values(?,?,?)";
        PreparedStatement prSt = con.prepareStatement(plSqlQuery);
        prSt.setString(1, "shahrukh.khan@gmail.com");
        prSt.setString(2, "shahrukh");
        prSt.setString(3, "khan");

        effectedRows = prSt.executeUpdate();
        System.out.println(effectedRows + " rows effected\n\n");
        prSt.close();

        con.close();
    }
}