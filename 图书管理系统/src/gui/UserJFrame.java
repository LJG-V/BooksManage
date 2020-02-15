package gui;

import sql.achieve_api.BooksAchi;
import sql.achieve_api.UserAchi;
import sql.entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserJFrame  extends JFrame {
    private JPanel contentPane;
    private List<String> columnNames;
    private List<User> rows;
    private UserAchi userAchi;
    private BooksAchi booksAchi;
    private JTable jTable;
    private SimpleModel<User> simpleModel;
    private JButton bookBtn;
    private JButton retBtn;
    private JButton lendBtn;
    private JButton refBtn;
    private TableColumn column = null;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    UserJFrame frame = new UserJFrame();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public UserJFrame() {
        setTitle("用户中心");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 1000, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);
        columnNames = new ArrayList<String>();
        columnNames.add("编号");
        columnNames.add("书籍名称");
        columnNames.add("出版社");
        columnNames.add("作者");
        columnNames.add("价格");

        userAchi = new UserAchi();
        rows = userAchi.getAllUBook();
        simpleModel = new SimpleModel<User>(columnNames, rows);

        JLabel jLabel = new JLabel();
        jLabel.setText("读者借阅情况表");
        jLabel.setFont(new Font("黑体", Font.BOLD, 36));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(jLabel, BorderLayout.PAGE_START);

        jTable = new JTable();
        jTable.setModel(simpleModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane, BorderLayout.CENTER);

        column = jTable.getColumnModel().getColumn(0);  //设置列宽
        column.setMinWidth(60);
        column.setMaxWidth(60);

        jTable.setRowHeight(30); //设置行高

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);    //设置内容居中
        jTable.setDefaultRenderer(Object.class, r);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu user = new JMenu();
        user.setText("个人中心");
        JMenuItem updateID = new JMenuItem();
        updateID.setText("修改账户密码");
        updateID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                upDateIDActionPerformed(e);
            }
        });
        user.add(updateID);
        jMenuBar.add(user);
        setJMenuBar(jMenuBar);

        refBtn = new JButton("刷新");
        refBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshActionPerformed(e);
            }
        });
        bookBtn = new JButton("书库");
        bookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allBooksActionPerformed(e);
            }
        });
        retBtn = new JButton("归还图书");
        retBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnActionPerformed(e);
            }
        });
        lendBtn = new JButton("续借图书");
        lendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                lendActionPerformed(e);
            }
        });
        refBtn.setFont(new Font("黑体", Font.BOLD, 20));
        bookBtn.setFont(new Font("黑体", Font.BOLD, 20));
        retBtn.setFont(new Font("黑体", Font.BOLD, 20));
        lendBtn.setFont(new Font("黑体", Font.BOLD, 20));
        JPanel left = new JPanel();
        left.add(bookBtn);
        left.add(refBtn);
        JPanel down = new JPanel();
        down.add(retBtn);
        down.add(lendBtn);
        this.add(left, BorderLayout.LINE_START);
        this.add(down, BorderLayout.PAGE_END);
    }

    public void refreshActionPerformed(ActionEvent actionEvent) {
        rows = userAchi.getAllUBook();
        simpleModel.setRows(rows);
        simpleModel.fireTableDataChanged();
    }

    public void returnActionPerformed(ActionEvent actionEvent) {
        try {
            User uBook = rows.get(jTable.getSelectedRow());
            userAchi.delBookbyID(uBook.getUbid());
            rows = userAchi.getAllUBook();
            simpleModel.setRows(rows);
            simpleModel.fireTableDataChanged();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "归还失败!请选中书籍...", "归还书籍", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allBooksActionPerformed(ActionEvent actionEvent) {
        new AllBookJFrame().setVisible(true);
    }
}
