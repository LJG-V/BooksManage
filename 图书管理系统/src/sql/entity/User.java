package sql.entity;

public class User {
    private int uid;
    private String uname;
    private String upassword;
    private int ubinit;
    private int ubid;
    private String ubname;
    private String ubpress;
    private String ubauthor;
    private double ubprice;

    public User() {
    }

    public User(String uname, String upassword) {
        this.uname = uname;
        this.upassword = upassword;
    }

    public User(String ubname, String ubpress, String ubauthor, double ubprice) {
        this.ubname = ubname;
        this.ubpress = ubpress;
        this.ubauthor = ubauthor;
        this.ubprice = ubprice;
    }

    public int getUbid() {
        return ubid;
    }

    public void setUbid(int ubid) {
        this.ubid = ubid;
    }

    public String getUbname() {
        return ubname;
    }

    public void setUbname(String ubname) {
        this.ubname = ubname;
    }

    public String getUbpress() {
        return ubpress;
    }

    public void setUbpress(String ubpress) {
        this.ubpress = ubpress;
    }

    public String getUbauthor() {
        return ubauthor;
    }

    public void setUbauthor(String ubauthor) {
        this.ubauthor = ubauthor;
    }

    public double getUbprice() {
        return ubprice;
    }

    public void setUbprice(double ubprice) {
        this.ubprice = ubprice;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
}
