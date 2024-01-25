import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Display_DatabaseConnection {
    private JFrame frame;

    public Display_DatabaseConnection() {
        frame = new JFrame("Book Browser -Database connection management Page");
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
                new Display_admin();
            }
        });


        JLabel title = new JLabel("데이터베이스 연결 설정");
        title.setBounds(400, 200, 300, 50);
        frame.add(title);


        JLabel urlLabel = new JLabel("DB url: ");
        urlLabel.setBounds(400, 300, 100, 50);
        frame.add(urlLabel);
        JTextField urlTextField = new JTextField();
        urlTextField.setBounds(500, 300, 300, 50);
        frame.add(urlTextField);

        JLabel usernameLabel = new JLabel("username: ");
        usernameLabel.setBounds(400, 400, 100, 50);
        frame.add(usernameLabel);
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(500, 400, 300, 50);
        frame.add(usernameTextField);

        JLabel passwordLabel = new JLabel("password: ");
        passwordLabel.setBounds(400, 500, 100, 50);
        frame.add(passwordLabel);
        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(500, 500, 300, 50);
        frame.add(passwordTextField);


        JButton saveButton = new JButton("Save");
        saveButton.setBounds(600, 600, 70, 30);
        frame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File directory = new File("" + Paths.get("").toAbsolutePath());
                    if (!directory.exists()) directory.mkdir();

                    File file = new File(directory, "DatabasePath.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(urlTextField.getText() + "\n" + usernameTextField.getText() + "\n" + passwordTextField.getText());

                    writer.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("데이터베이스 정보를 다시 입력해주십시오.");
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
