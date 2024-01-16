import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Display_admin {
    private JFrame frame;

    public Display_admin() {
        frame = new JFrame("Book Browser -admin Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setLayout(null);
        frame.setVisible(true);

        JButton mainPage = new JButton("Back");
        mainPage.setBounds(20, 20, 70, 30);
        frame.add(mainPage);

        mainPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    new Display_main();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JLabel title = new JLabel("관리자 페이지");
        title.setBounds(615, 100, 300, 50);
        frame.add(title);


        JButton DBsettingButton = new JButton("데이터베이스 설정");
        DBsettingButton.setBounds(400, 300, 500, 100);
        frame.add(DBsettingButton);
        DBsettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Display_DatabaseConnection();
            }
        });


        JButton BookManagementButton = new JButton("도서 관리");
        BookManagementButton.setBounds(400, 450, 500, 100);
        frame.add(BookManagementButton);

    }
}
