import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Display_BookManagement {
    private JFrame frame;
    private DatabaseConnection dbConnection = new DatabaseConnection();
    public Display_BookManagement() {
        frame = new JFrame("Book Browser -Book management Page");
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


        JLabel title = new JLabel("도서 관리");
        title.setBounds(600, 100, 300, 50);
        frame.add(title);


        JLabel sectionLabel = new JLabel("책장: ");
        sectionLabel.setBounds(400, 200, 100, 40);
        frame.add(sectionLabel);
        JTextField sectionTextField = new JTextField();
        sectionTextField.setBounds(500, 200, 300, 40);
        frame.add(sectionTextField);

        JLabel titleLabel = new JLabel("*책 제목: ");
        titleLabel.setBounds(400, 250, 100, 40);
        frame.add(titleLabel);
        JTextField titleTextField = new JTextField();
        titleTextField.setBounds(500, 250, 300, 40);
        frame.add(titleTextField);

        JLabel author1Label = new JLabel("*작가이름1: ");
        author1Label.setBounds(400, 300, 100, 40);
        frame.add(author1Label);
        JTextField author1TextField = new JTextField();
        author1TextField.setBounds(500, 300, 300, 40);
        frame.add(author1TextField);

        JLabel author2Label = new JLabel("작가이름2: ");
        author2Label.setBounds(400, 350, 100, 40);
        frame.add(author2Label);
        JTextField author2TextField = new JTextField();
        author2TextField.setBounds(500, 350, 300, 40);
        frame.add(author2TextField);

        JLabel volumeLabel = new JLabel("권수: ");
        volumeLabel.setBounds(400, 400, 100, 40);
        frame.add(volumeLabel);
        JTextField volumeTextField = new JTextField();
        volumeTextField.setBounds(500, 400, 300, 40);
        frame.add(volumeTextField);

        JLabel genre1Label = new JLabel("장르1: ");
        genre1Label.setBounds(400, 450, 100, 40);
        frame.add(genre1Label);
        JTextField genre1TextField = new JTextField();
        genre1TextField.setBounds(500, 450, 300, 40);
        frame.add(genre1TextField);

        JLabel genre2Label = new JLabel("장르2: ");
        genre2Label.setBounds(400, 500, 100, 40);
        frame.add(genre2Label);
        JTextField genre2TextField = new JTextField();
        genre2TextField.setBounds(500, 500, 300, 40);
        frame.add(genre2TextField);

        JLabel ratingLabel = new JLabel("등급: ");
        ratingLabel.setBounds(400, 550, 100, 40);
        frame.add(ratingLabel);
        JTextField ratingTextField = new JTextField();
        ratingTextField.setBounds(500, 550, 300, 40);
        frame.add(ratingTextField);


        JButton saveButton = new JButton("저장");
        saveButton.setBounds(500, 650, 70, 30);
        frame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnection.SaveBook(sectionTextField.getText(), titleTextField.getText(), author1TextField.getText(),
                        author2TextField.getText(), volumeTextField.getText(), genre1TextField.getText(),
                        genre2TextField.getText(), ratingTextField.getText());
            }
        });


        JButton modifyButton = new JButton("수정");
        modifyButton.setBounds(600, 650, 70, 30);
        frame.add(modifyButton);
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnection.ModifyBook(sectionTextField.getText(), titleTextField.getText(), author1TextField.getText(),
                        author2TextField.getText(), volumeTextField.getText(), genre1TextField.getText(),
                        genre2TextField.getText(), ratingTextField.getText());
            }
        });


        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(700, 650, 70, 30);
        frame.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnection.DeleteBook(titleTextField.getText(), author1TextField.getText());
            }
        });

    }

}
