package sql.entity;

public class Root {
    private int rid;
    private String rname;
    private String rpassword;

    public Root() {
    }
    public Root(int rid, String rname, String rpassword) {
        this.rid = rid;
        this.rname = rname;
        this.rpassword = rpassword;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }
}
