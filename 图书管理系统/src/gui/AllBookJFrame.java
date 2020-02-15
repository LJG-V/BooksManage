package gui;

import sql.DbUtil;
import sql.achieve_api.BooksAchi;
import sql.achieve_api.UserAchi;
import sql.entity.Books;
import sql.entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;


public class AllBookJFrame extends JFrame {

    private JPanel contentPane;
    private List<String> columnNames;
    private List<Books> rows;
    private BooksAchi booksAchi;
    private JTable jTable;
    private SimpleModel<Books> simpleModel;
    private JTextField queryTxt;
    private JButton queryBtn;
    private JButton lendBtn;
    private JButton refBtn;
    private JButton allBtn;
    private TableColumn column = null;


    public AllBookJFrame() {
        setTitle("书库");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(280, 180, 1000, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);
        columnNames = new ArrayList<String>();
        columnNames.add("编号");
        columnNames.add("书籍名称");
        columnNames.add("出版社");
        columnNames.add("作者");
        columnNames.add("定价");
        columnNames.add("在馆数");

        booksAchi = new BooksAchi();
        rows = booksAchi.getSomeBooks();
        simpleModel = new SimpleModel<Books>(columnNames, rows);

        JLabel jLabel = new JLabel();
        jLabel.setText("书库借阅中心");
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

        allBtn = new JButton("全部");
        allBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               allActionPerformed(e);
            }
        });
        refBtn = new JButton("刷新");
        refBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshActionPerformed(e);
            }
        });
        lendBtn = new JButton("借阅");
        lendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lendBookActionPerformed(e);
            }
        });
        queryTxt = new JTextField(25);
        queryBtn = new JButton("查询");
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                query2ActionPerformed(e);
            }
        });
        allBtn.setFont(new Font("黑体", Font.BOLD, 20));
        refBtn.setFont(new Font("黑体", Font.BOLD, 20));
        queryTxt.setFont(new Font("黑体", Font.PLAIN, 15));
        queryBtn.setFont(new Font("黑体", Font.BOLD, 20));
        lendBtn.setFont(new Font("黑体", Font.BOLD, 20));
        JPanel down = new JPanel();
        down.add(queryTxt);
        down.add(queryBtn);
        down.add(lendBtn);
        down.add(refBtn);
        down.add(allBtn);
        this.add(down, BorderLayout.PAGE_END);
    }

    public void refreshActionPerformed(ActionEvent actionEvent) {
        rows = booksAchi.getSomeBooks();
        simpleModel.setRows(rows);
        simpleModel.fireTableDataChanged();
    }

    public void lendBookActionPerformed(ActionEvent actionEvent) {
        try {
            int row = jTable.getSelectedRow(); //获得行位置
            jTable.setRowSelectionInterval(row, row);//高亮显示
            Books book = rows.get(jTable.convertRowIndexToModel(row));
            int id = book.getBid();
            String query = "select * from user where ubid="+id;
            ResultSet rs = DbUtil.runQuery(query);
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"以借过此书籍，不可再借！","借阅书籍",JOptionPane.WARNING_MESSAGE);
            }
            else {
                booksAchi.lendBooks(book.getBid());
                String name = book.getBname();
                String press = book.getBpress();
                String author = book.getBauthor();
                double price = book.getBprice();
                addBookActionPerformed(id, name, press, author, price);
                JOptionPane.showMessageDialog(this, "借阅成功!", "借阅书籍", JOptionPane.INFORMATION_MESSAGE);
                rows = booksAchi.getSomeBooks();
                simpleModel.setRows(rows);
                simpleModel.fireTableDataChanged();
            }
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "借阅失败!请选中书籍...", "借阅书籍", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addBookActionPerformed(int id,String name,String press,String author,double price) {
        User book = new User();
        UserAchi userAchi = new UserAchi();
        book.setUbid(id);
        book.setUbname(name);
        book.setUbpress(press);
        book.setUbauthor(author);
        book.setUbprice(price);
        userAchi.setLendBooks(book);
    }

    public void query2ActionPerformed(ActionEvent actionEvent) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) jTable.getRowSorter();
        if(sorter == null) {
            sorter = new TableRowSorter<>(jTable.getModel());
            jTable.setRowSorter(sorter);
        }
        String text = queryTxt.getText();

        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            //设置RowFilter 用于从模型中过滤条目，使得这些条目不会在视图中显示
            sorter.setRowFilter(RowFilter.regexFilter(text,1));
        }
    }

    public void allActionPerformed(ActionEvent actionEvent) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) jTable.getRowSorter();
        if(sorter == null) {
            sorter = new TableRowSorter<>(jTable.getModel());
            jTable.setRowSorter(sorter);
        }
        String text = "";
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        }
    }

}


