package gui;

import sql.achieve_api.BooksAchi;
import sql.entity.Books;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class RootJFrame extends JFrame {

    private JPanel contentPane;
    private List<String> columnNames;
    private List<Books> rows;
    private BooksAchi booksAchi;
    private JTable jTable;
    private SimpleModel<Books> simpleModel;
    private JTextField queryTxt;
    private JButton queryBtn;
    private JButton allBtn;
    private JButton refBtn;
    private TableColumn column = null;

    public RootJFrame() {
        setTitle("图书管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 1000, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0,0));

        setContentPane(contentPane);
        columnNames = new ArrayList<>();
        columnNames.add("编号");
        columnNames.add("书籍名称");
        columnNames.add("出版社");
        columnNames.add("作者");
        columnNames.add("定价");
        columnNames.add("在馆数");
        columnNames.add("借出数");
        booksAchi = new BooksAchi();
        rows = booksAchi.getAllBook();
        simpleModel = new SimpleModel<Books>(columnNames,rows);


        JLabel jLabel = new JLabel();
        jLabel.setText("图书管理系统");
        jLabel.setFont(new Font("黑体",Font.BOLD,36));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(jLabel,BorderLayout.PAGE_START);
        JScrollPane jScrollPane = new JScrollPane();
        jTable = new JTable();

        jTable.setModel(simpleModel);
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane, BorderLayout.CENTER);

        column = jTable.getColumnModel().getColumn(0);  //设置列宽
        column.setMinWidth(60);
        column.setMaxWidth(60);

        jTable.setRowHeight(30); //设置行高

        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);    //设置内容居中
        jTable.setDefaultRenderer(Object.class,r);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu();
        file.setText("文件");
        JMenuItem quit = new JMenuItem();
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitActionPerformed(e);
            }
        });
        quit.setText("退出");
        file.add(quit);
        jMenuBar.add(file);

        JMenu edit = new JMenu();
        edit.setText("编辑");
        edit.setToolTipText("");
        JMenuItem insert = new JMenuItem();
        insert.setText("添加");
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookActionPerformed(e);
            }
        });
        edit.add(insert);
        jMenuBar.add(edit);

        JMenuItem update = new JMenuItem();
        update.setText("更新");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookActionPerformed(e);
            }
        });
        edit.add(update);

        JMenuItem delete = new JMenuItem("删除");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delBookActionPerformed(e);
            }
        });
        edit.add(delete);

        JMenu statistics = new JMenu();
        statistics.setText("统计");
        jMenuBar.add(statistics);
        JMenu help = new JMenu();
        help.setText("帮助");
        JMenuItem about = new JMenuItem();
        about.setText("关于");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutActionPerformed(e);
            }
        });
        help.add(about);
        jMenuBar.add(help);
        setJMenuBar(jMenuBar);

        JPanel down = new JPanel();
        JPanel right = new JPanel();
        queryTxt = new JTextField(25);
        JRadioButton rdoMan = new JRadioButton("按书名查询");
        JRadioButton rdoWoman = new JRadioButton("按编号查询");
        ButtonGroup group = new ButtonGroup();
        group.add(rdoMan);
        group.add(rdoWoman);
        // 设置默认选择
        rdoMan.setSelected(true);
        down.add(rdoMan,BorderLayout.AFTER_LINE_ENDS);
        down.add(rdoWoman,BorderLayout.AFTER_LINE_ENDS);
        queryBtn = new JButton("查询");
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if (rdoMan.isSelected()){
                   queryByNameActionPerformed(e);
               }
               else if (rdoWoman.isSelected()){
                   queryByIDActionPerformed(e);
               }
            }
        });
        allBtn = new JButton("全部");
        allBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            queryAllActionPerformed(e);
        }
    });
        refBtn = new JButton("刷新");
        refBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshActionPerformed(e);
            }
        });

        queryTxt.setFont(new Font("黑体", Font.BOLD, 20));
        rdoMan.setFont(new Font("黑体", Font.BOLD, 25));
        rdoWoman.setFont(new Font("黑体", Font.BOLD, 25));
        queryBtn.setFont(new Font("黑体", Font.BOLD, 25));
        allBtn.setFont(new Font("黑体", Font.BOLD, 25));
        refBtn.setFont(new Font("黑体", Font.BOLD, 25));
        down.add(queryTxt);
        down.add(queryBtn);
        down.add(allBtn);
        this.add(down,BorderLayout.PAGE_END);
        right.add(refBtn);
        this.add(right,BorderLayout.LINE_START);

    }
    private void queryByIDActionPerformed(ActionEvent evt) {
        rows=booksAchi.getAllBook();
        simpleModel.setRows(rows);
        int id = Integer.parseInt(this.queryTxt.getText());
        List<Books> rows = simpleModel.getRows();
        int index = -1;
        for(int i=0;i<rows.size();i++)
        {
            if(id==rows.get(i).getBid()) index = i;
        }
        if (index == -1){
            JOptionPane.showMessageDialog(this, "未找该书籍！", "按编号查询书籍",JOptionPane.WARNING_MESSAGE );
        }else {
            jTable.changeSelection(index, 0, false, false);
        }
    }

    private void queryAllActionPerformed(ActionEvent actionEvent) {
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

    private void queryByNameActionPerformed(ActionEvent actionEvent) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) jTable.getRowSorter();
        if(sorter == null) {
            sorter = new TableRowSorter<>(jTable.getModel());
            jTable.setRowSorter(sorter);
        }
        String text = queryTxt.getText();

        if (text.length() == 0) {
            JOptionPane.showMessageDialog(this, "请输入具体书籍...","按书名查询书籍",JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "查询成功！","按书名查询书籍",JOptionPane.INFORMATION_MESSAGE);
            //设置RowFilter 用于从模型中过滤条目，使得这些条目不会在视图中显示
            sorter.setRowFilter(RowFilter.regexFilter(text,1));
        }
    }

    private void aboutActionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "图书管理系统 ver 1.2.0\n\n  版权：ljg","关于图书管理" +
                "系统",JOptionPane.PLAIN_MESSAGE);
    }

    private void addBookActionPerformed(ActionEvent actionEvent) {
        new AddBookJFrame().setVisible(true);
    }

    public void refreshActionPerformed(ActionEvent actionEvent) {
        rows=booksAchi.getAllBook();
        simpleModel.setRows(rows);
        simpleModel.fireTableDataChanged();
    }

    private void updateBookActionPerformed(ActionEvent actionEvent) {
        try {
            rows = booksAchi.getAllBook();
            simpleModel.setRows(rows);
            int row = jTable.getSelectedRow(); //获得行位置
            jTable.setRowSelectionInterval(row, row);//高亮显示
            Books book = rows.get(jTable.convertRowIndexToModel(row));
            new UpdataBookJFrame(book).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "请选中具体书籍...","更新书籍信息",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delBookActionPerformed(ActionEvent actionEvent) {
        try {
            Books student=rows.get(jTable.getSelectedRow());
            booksAchi.delBookbyID(student.getBid());
            JOptionPane.showMessageDialog(this, "删除成功", "删除书籍信息", JOptionPane.INFORMATION_MESSAGE);
            rows=booksAchi.getAllBook();
            simpleModel.setRows(rows);
            simpleModel.fireTableDataChanged();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "删除失败!请选中书籍...", "删除书籍信息", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitActionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
        this.dispose();
    }
}