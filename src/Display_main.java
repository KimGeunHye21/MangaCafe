import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Display_main {
    private JFrame frame;
    private String[] columnNames = {"section", "title", "author1", "author2", "volume", "genre1", "genre2", "rating"};
    private DatabaseConnection dbConnection = new DatabaseConnection();
    DefaultTableModel bookInfo;
    JTable table;
    JScrollPane scrollPane;

    public Display_main() throws SQLException {
        frame = new JFrame("Book Browser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setLayout(null);
        frame.setVisible(true);


        JButton adminPage = new JButton("admin");
        adminPage.setBounds(20, 20, 70, 30);
        frame.add(adminPage);

        adminPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Display_admin();
            }
        });


        JButton allBook = new JButton("모든 책 보기");
        allBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        JLabel title = new JLabel("도서검색대");
        title.setBounds(600, 100, 300, 50);
        frame.add(title);

        JTextField findText = new JTextField();
        findText.setBounds(400, 200, 400, 40);
        frame.add(findText);

        JButton searchButton = new JButton("찾기");
        searchButton.setBounds(810, 200, 60, 40);
        frame.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                String find = findText.getText();
                try {
                    showBookInfo(dbConnection.SearchBookTitle(find));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        showBookInfo(null);
    }

    public void showBookInfo(Object[][] data) {

        bookInfo = new DefaultTableModel(data, columnNames);
        table = new JTable(bookInfo);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(130);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 280, 800, 300);

        frame.add(scrollPane);
    }
}
