package gui;

import sql.achieve_api.RootAchi;
import sql.achieve_api.UserAchi;
import sql.entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame {
    private JPanel contentPane;
    private JTextField userName;
    private JPasswordField userPassword;
    private JTextField rootName;
    private JPasswordField rootPassword;

    public LoginJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 200, 450, 300);
        CardLayout cardLayout = new CardLayout();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu landingOptions = new JMenu("用户选项");
        landingOptions.setFont(new Font("黑体", Font.BOLD, 18));
        menuBar.add(landingOptions);

        JMenuItem userOption = new JMenuItem("读者登陆");
        userOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cardLayout.first(contentPane);
            }
        });
        JMenuItem rootOption = new JMenuItem("管理员登陆");
        rootOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cardLayout.last(contentPane);
            }
        });
        landingOptions.add(userOption);
        landingOptions.add(rootOption);
        contentPane = new JPanel();     //实例化一个面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//设置组件边框
        setContentPane(contentPane);    //添加面板整个窗口
        contentPane.setLayout(cardLayout);

        /**
         * 普通用户登录
         */
        JPanel userPanel = new JPanel();
        this.setTitle("登陆图书读者中心");
        contentPane.add(userPanel);
        userPanel.setLayout(null);

        userName = new JTextField();
        userName.setBounds(148, 55, 122, 21);
        userPanel.add(userName);
        userName.setColumns(10);

        userPassword = new JPasswordField();
        userPassword.setBounds(148, 96, 122, 21);
        userPanel.add(userPassword);

        JButton userButton1 = new JButton("登陆");
        userButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                userLoginActionPerformed(event);
            }
        });
        userButton1.setBounds(72, 159, 93, 23);
        userPanel.add(userButton1);

        JButton userButton2 = new JButton("注册");
        userButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                userRegisterActionPerformed(event);
            }
        });
        userButton2.setBounds(220, 159, 93, 23);
        userPanel.add(userButton2);

        JLabel lb1 = new JLabel("读者账户：");
        lb1.setBounds(72, 58, 101, 15);
        userPanel.add(lb1);

        JLabel lb2 = new JLabel("密码：");
        lb2.setBounds(72, 99, 54, 15);
        userPanel.add(lb2);

        /**
         * root用户登录
         */
        JPanel rootPanel = new JPanel();
        this.setTitle("用户登录");
        contentPane.add(rootPanel);
        rootPanel.setLayout(null);

        rootName = new JTextField();
        rootName.setBounds(190, 48, 129, 21);
        rootPanel.add(rootName);
        rootName.setColumns(10);

        rootPassword = new JPasswordField();
        rootPassword.setBounds(190, 91, 129, 21);
        rootPanel.add(rootPassword);

        JButton rootButton = new JButton("登陆");
        rootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                rootLoginActionPerformed(event);
            }
        });
        rootButton.setBounds(152, 151, 93, 23);
        rootPanel.add(rootButton);

        JLabel lb3 = new JLabel("管理员名：");
        lb3.setForeground(Color.BLUE);
        lb3.setBounds(79, 51, 101, 15);
        rootPanel.add(lb3);

        JLabel lb4 = new JLabel("管理员密码：");
        lb4.setForeground(Color.BLUE);
        lb4.setBounds(79, 94, 101, 15);
        rootPanel.add(lb4);
    }

    /**
     * 读者登录与注册账号
     * @param event
     */
    private void userLoginActionPerformed(ActionEvent event) {
        String uname=userName.getText();
        String upassword=userPassword.getText();
        UserAchi userDaoImpl=new UserAchi();
        if(userDaoImpl.certifyUser(uname, upassword))
        {
            JOptionPane.showMessageDialog(this, "登录成功");
            UserJFrame userJFrame = new UserJFrame();
            userJFrame.setBounds(200, 100, 1000, 800);
            userJFrame.setVisible(true);
            this.setVisible(false);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "登录失败，账号或密码错误！","登陆读者中心",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void userRegisterActionPerformed(ActionEvent event) {
        String uname=userName.getText();
        String upassword=userPassword.getText();
        User user=new User(uname,upassword);
        UserAchi userAchi = new UserAchi();
        if(userAchi.addUser(user)) {
            JOptionPane.showMessageDialog(this, "注册成功");
        }
        else {
            JOptionPane.showMessageDialog(this, "注册失败!","注册学生管理系统",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *root登录
     */
    private void rootLoginActionPerformed(ActionEvent event) {
        String rname=rootName.getText();
        String rpassword=rootPassword.getText();
        RootAchi rootAchi=new RootAchi();
        if(rootAchi.certifyRoot(rname, rpassword))
        {
            JOptionPane.showMessageDialog(this, "登录成功");
            RootJFrame rootJFrame = new RootJFrame();
            rootJFrame.setBounds(200, 100, 1000, 800);
            rootJFrame.setVisible(true);
            this.setVisible(false);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "登录失败，账号或密码错误！","登陆图书管理系统",JOptionPane.ERROR_MESSAGE);
        }
    }
}
