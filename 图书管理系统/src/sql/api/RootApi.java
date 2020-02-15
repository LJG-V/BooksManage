package sql.api;

import sql.entity.Root;
import java.util.List;

public interface RootApi {

    public boolean certifyRoot(String rname,String rpassword);
}
