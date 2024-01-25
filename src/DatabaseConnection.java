import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseConnection {
    private Connection connection = null;

    public DatabaseConnection() {
        String tmp, url = null, username = null, password = null;

        //파일 열기
        try {
            File directory = new File("" + Paths.get("").toAbsolutePath());
            File file = new File(directory, "DatabasePath.txt");

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                tmp = reader.readLine();
                url = tmp; //jdbc:mysql://127.0.0.1:3306/manga_cafe
                tmp = reader.readLine();
                username = tmp; //root
                tmp = reader.readLine();
                password = tmp; //root

                reader.close();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }

        makeConnection(url, username, password);
    }

    public void makeConnection(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            if (connection == null) System.out.println("database connection failed");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("데이터베이스 정보를 설정해 주십시오.");
            //new Display_DatabaseConnection();
            e.printStackTrace();
        }
    }
    
    public Object[][] SearchBookTitle(String word) throws SQLException {
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

    public void SaveBook(String section, String title, String author1, String author2,
                         String volume, String genre1, String genre2, String rating)  {
        PreparedStatement preparedStatement = null;

        try {
            // 1. 잘못된 요청 처리하기

            // 2. 등록
            String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, section);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author1);
            preparedStatement.setString(4, author2);
            if (volume != null && !volume.isEmpty())  preparedStatement.setInt(5, Integer.parseInt(volume));
            else preparedStatement.setObject(5, null);
            preparedStatement.setString(6, genre1);
            preparedStatement.setString(7, genre2);
            if (rating != null && !rating.isEmpty()) preparedStatement.setInt(8, Integer.parseInt(rating));
            else preparedStatement.setObject(8, null);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("레코드 추가 실패");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void ModifyBook (String section, String title, String author1, String author2,
                            String volume, String genre1, String genre2, String rating)  {

        PreparedStatement preparedStatement = null;

        try {
            // 1. 대상 찾기

            // 2. 잘못된 요청 처리하기

            // 3. 업데이트(수정)

            String sql = "UPDATE book SET section=?, author2=?, volume=?, genre1=?, genre2=?, rating=? " +
                    "WHERE title=? AND author1=?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, section);
            preparedStatement.setString(2, author2);
            if (volume != null && !volume.isEmpty()) {
                preparedStatement.setInt(3, Integer.parseInt(volume));
            } else {
                preparedStatement.setObject(3, null);
            }
            preparedStatement.setString(4, genre1);
            preparedStatement.setString(5, genre2);
            if (rating != null && !rating.isEmpty()) {
                preparedStatement.setInt(6, Integer.parseInt(rating));
            } else {
                preparedStatement.setObject(6, null);
            }

            preparedStatement.setString(7, title);
            preparedStatement.setString(8, author1);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("레코드 수정 실패");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void DeleteBook (String title, String author1)  {
        Statement statement;
        try {
            statement = connection.createStatement();

            // 1. 대상 찾기

            // 2. 잘못된 요청 처리

            // 3. 삭제
            String sql = "delete from book where title = '" + title + "' and author1 = '" + author1 + "'";
            statement.executeUpdate(sql);

            statement.close();
        } catch (Exception e) {
            System.out.println("레코드 삭제 실패");
            throw new RuntimeException(e);
        }
    }
}
