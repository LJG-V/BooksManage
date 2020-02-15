package sql.achieve_api;

import sql.DbUtil;
import sql.api.RootApi;
import sql.entity.Root;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class RootAchi implements RootApi {
    @Override
    public boolean certifyRoot(String rname, String rpassword) {
        String select="select * from root where rname='"+rname+"' and rpassword='"+rpassword+"'";
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
}
