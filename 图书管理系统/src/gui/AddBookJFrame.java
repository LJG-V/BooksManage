package gui;

import sql.achieve_api.BooksAchi;
import sql.entity.Books;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookJFrame extends JFrame {

    private JPanel contentPane;
    private JTextField bid;
    private JTextField bname;
    private JTextField bpress;
    private JTextField bauthor;
    private JTextField bprice;
    private JTextField bsurplus;


//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    AddBookJFrame frame = new AddBookJFrame();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public AddBookJFrame() {
        setTitle("添加书籍信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500,200,440,510);
        contentPane= new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        bid = new JTextField();
        bid.setBounds(195, 57, 128, 21);
        contentPane.add(bid);
        bid.setColumns(14);

        bname = new JTextField();
        bname.setBounds(195, 110, 128, 21);
        contentPane.add(bname);
        bname.setColumns(14);

        bpress = new JTextField();
        bpress.setBounds(195, 152, 128, 21);
        contentPane.add(bpress);
        bpress.setColumns(14);

        bauthor = new JTextField();
        bauthor.setBounds(195, 195, 128, 21);
        contentPane.add(bauthor);
        bauthor.setColumns(14);

        bprice = new JTextField();
        bprice.setBounds(195, 238, 128, 21);
        contentPane.add(bprice);
        bprice.setColumns(14);

        bsurplus = new JTextField();
        bsurplus.setBounds(195, 281, 128, 21);
        contentPane.add(bsurplus);
        bsurplus.setColumns(14);

        JLabel labelID = new JLabel("书籍编号：");
        labelID.setBounds(100, 60, 66, 15);
        contentPane.add(labelID);

        JLabel labelName = new JLabel("书籍名称：");
        labelName.setBounds(100, 113, 66, 15);
        contentPane.add(labelName);

        JLabel labelPress = new JLabel("出版社：");
        labelPress.setBounds(107, 155, 66, 15);
        contentPane.add(labelPress);

        JLabel labelAuthor = new JLabel("作者：");
        labelAuthor.setBounds(110, 198, 66, 15);
        contentPane.add(labelAuthor);

        JLabel labelPrice = new JLabel("定价/元：");
        labelPrice.setBounds(100, 241, 66, 15);
        contentPane.add(labelPrice);

        JLabel labelSurplus = new JLabel("上架数量：");
        labelSurplus.setBounds(100, 284, 66, 15);
        contentPane.add(labelSurplus);

        JButton buttonAdd = new JButton("添加");
        buttonAdd.setFont(new Font("黑体", Font.BOLD, 16));
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookActionPerformed(e);
            }
        });
        buttonAdd.setBounds(100, 360, 80, 23);
        contentPane.add(buttonAdd);

        JButton buttonCancel = new JButton("取消");
        buttonCancel.setFont(new Font("黑体", Font.BOLD, 16));
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelActionPerformed(e);
            }
        });
        buttonCancel.setBounds(240, 360, 80, 23);
        contentPane.add(buttonCancel);
    }

    private void addBookActionPerformed(ActionEvent event) {
        Books book = new Books();
        BooksAchi booksAchi = new BooksAchi();
        try {
            book.setBid(Integer.parseInt(bid.getText()));
            book.setBname(bname.getText());
            book.setBpress(bpress.getText());
            book.setBauthor(bauthor.getText());
            book.setBprice(Double.parseDouble(bprice.getText()));
            book.setBsurplus(Integer.parseInt(bsurplus.getText()));
            booksAchi.addBooks(book);
            JOptionPane.showMessageDialog(this, "插入成功！","添加图书信息",JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            this.dispose();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "插入失败！","添加图书信息",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void cancelActionPerformed(ActionEvent event) {
        this.setVisible(false);
        this.dispose();
    }
}
