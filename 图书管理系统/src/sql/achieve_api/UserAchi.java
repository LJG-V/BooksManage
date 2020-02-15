package sql.achieve_api;

import sql.DbUtil;
import sql.api.UserApi;
import sql.entity.User;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAchi implements UserApi {
    @Override
    public boolean addUser(User user) {
        String insert = "insert into userID(uname,upassword) values('"+user.getUname()+"','"+user.getUpassword()+"')";
        try {
            DbUtil.runUpdate(insert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean certifyUser(String uname, String upassword) {
        String select="select * from userID where uname='"+uname+"' and upassword='"+upassword+"'";
        boolean isCertifyUser = false;
        try {
            ResultSet rs = DbUtil.runQuery(select);
            if(rs!=null)
            {
                isCertifyUser = rs.next();
                DbUtil.realeaseAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCertifyUser;
    }

    @Override
    public List<User> getAllUBook() {
        String select="select * from user";
        try {
            List<User> ubooks = new ArrayList<>();
            ResultSet rs = DbUtil.runQuery(select);  //执行sql查询语句
            while (rs.next()){
                User ubook = new User();
                ubook.setUbid(rs.getInt("ubid"));
                ubook.setUbname(rs.getString("ubname"));
                ubook.setUbpress(rs.getString("ubpress"));
                ubook.setUbauthor(rs.getString("ubauthor"));
                ubook.setUbprice(rs.getDouble("ubprice"));
                ubooks.add(ubook);
            }
            DbUtil.realeaseAll();
            return ubooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delBookbyID(int id) {
        String delete = "delete from user where ubid = '"+id+"'";
        String addNum = "update books set bsurplus = bsurplus+1 where bid = '"+id+"'";
        String musNum = "update books set blend = blend-1 where bid = '"+id+"'";
        try {
            int op = JOptionPane.showConfirmDialog(null, "确定归还此书籍吗?", "归还书籍",JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                DbUtil.runUpdate(delete);
                DbUtil.runUpdate(addNum);
                DbUtil.runUpdate(musNum);
                JOptionPane.showMessageDialog(null, "归还成功！", "归还书籍", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setLendBooks(User ubook) {
        String insert = "insert into user(ubid,ubname,ubpress,ubauthor,ubprice) "
                + "values('" + ubook.getUbid() + "','" + ubook.getUbname() + "','" + ubook.getUbpress() + "','" +
                ubook.getUbauthor() + "','" + ubook.getUbprice() + "')";
        try {
            DbUtil.runUpdate(insert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
