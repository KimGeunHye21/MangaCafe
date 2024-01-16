import java.sql.*;

public class DatabaseConnection {
    private Connection connection = null;

    public DatabaseConnection() { //매개변수로 커넥션을 여는건 어떰? 음......
        makeConnection("jdbc:mysql://127.0.0.1:3306/manga_cafe", "root", "root");
        System.out.println("데이터베이스 연결됨");
    }
    public void makeConnection(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            if (connection == null) System.out.println("database connection failed");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Object[][] SearchBook(String word) throws SQLException{ //이거 이 클래스에서 할 필요있나?
        Statement statement = connection.createStatement();
        String sql;
        ResultSet resultSet;


        sql = "select count(*) from book where title like '%" + word + "%'";
        resultSet = statement.executeQuery(sql);
        resultSet.next();
        int resultSetCount = resultSet.getInt(1);


        sql = "select * from book where title like '%" + word + "%'";
        resultSet = statement.executeQuery(sql);
        resultSet.next();

        Object[][] data = new Object[resultSetCount][8];
        for (int i = 0; i<resultSetCount; i++, resultSet.next())
            for (int j = 0; j<8; j++) data[i][j] = resultSet.getString(j+1);


        resultSet.close();
        statement.close();
        return data;
    }

}
