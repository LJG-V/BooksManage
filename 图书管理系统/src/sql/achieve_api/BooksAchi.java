package sql.achieve_api;

import sql.DbUtil;
import sql.api.BooksApi;
import sql.entity.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksAchi implements BooksApi {

    @Override
    public boolean addBooks(Books book) {

        String insert = "insert into books(bid,bname,bpress,bauthor,bprice,bsurplus,blend) "
                + "values('" + book.getBid() + "','" + book.getBname() + "','" + book.getBpress() + "','" +
                book.getBauthor() + "','" + book.getBprice() + "','" + book.getBsurplus() + "','" + book.getBlend() + "')";
        try {
            DbUtil.runUpdate(insert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBooks(Books book) {
        String update = "update books set bid='" + book.getBid()+ "',bname='"+book.getBname()+"',bpress='"+
                book.getBpress() + "',bauthor='" + book.getBauthor() + "',bprice='"+book.getBprice()+"',bsurplus='"
                + book.getBsurplus() + "' where binit="+book.getBinit();
        try {
            DbUtil.runUpdate(update);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delBookbyID(int id) {
        String delete = "delete from books where bid = '"+id+"'";
        try {
            DbUtil.runUpdate(delete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Books> getAllBook() {
        String select="select * from books";
        try {
            List<Books> books = new ArrayList<>();
            ResultSet rs = DbUtil.runQuery(select);  //执行sql查询语句
            while (rs.next()){
                Books book = new Books();
                book.setBinit(rs.getInt("binit"));
                book.setBid(rs.getInt("bid"));
                book.setBname(rs.getString("bname"));
                book.setBpress(rs.getString("bpress"));
                book.setBauthor(rs.getString("bauthor"));
                book.setBprice(rs.getDouble("bprice"));
                book.setBsurplus(rs.getInt("bsurplus"));
                book.setBlend(rs.getInt("blend"));
                books.add(book);
            }
            DbUtil.realeaseAll();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Books> getSomeBooks() {
        String select="select * from books";
        try {
            List<Books> books = new ArrayList<>();
            ResultSet rs = DbUtil.runQuery(select);  //执行sql查询语句
            while (rs.next()){
                Books book = new Books();

                book.setBid(rs.getInt("bid"));
                book.setBname(rs.getString("bname"));
                book.setBpress(rs.getString("bpress"));
                book.setBauthor(rs.getString("bauthor"));
                book.setBprice(rs.getDouble("bprice"));
                book.setBsurplus(rs.getInt("bsurplus"));
                books.add(book);
            }
            DbUtil.realeaseAll();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean lendBooks(int id) {
        String  update = "update books set bsurplus = bsurplus-1 where bid = '"+id+"'";
        String  update2 = "update books set blend = blend+1 where bid = '"+id+"'";
        try {
            DbUtil.runUpdate(update);
            DbUtil.runUpdate(update2);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
