package sql.api;

import sql.entity.User;
import java.util.List;

public interface UserApi {
    public boolean addUser(User user);

    public boolean certifyUser(String uname,String upassword);

    public List<User> getAllUBook();

    public boolean delBookbyID(int id);
}
