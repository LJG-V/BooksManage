package sql.api;

import sql.entity.Books;
import java.util.List;

public interface BooksApi {
    public boolean addBooks(Books book);

    public boolean updateBooks(Books book);

    public boolean delBookbyID(int id);

    public List<Books> getAllBook();

    public boolean lendBooks(int id);

}
