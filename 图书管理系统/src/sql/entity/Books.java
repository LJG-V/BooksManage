package sql.entity;

public class Books {
    private int binit;
    private int bid;
    private String bname;
    private String bpress;
    private String bauthor;
    private double bprice;
    private int blend;
    private int bsurplus;

    public Books(){

    }

    public Books(int bid, String bname, String bpress, String bauthor, double bprice, int blend, int bsurplus) {
        this.bid = bid;
        this.bname = bname;
        this.bpress = bpress;
        this.bauthor = bauthor;
        this.bprice = bprice;
        this.blend = blend;
        this.bsurplus = bsurplus;
    }

    public int getBinit() {
        return binit;
    }

    public void setBinit(int binit) {
        this.binit = binit;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpress() {
        return bpress;
    }

    public void setBpress(String bpress) {
        this.bpress = bpress;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public double getBprice() {
        return bprice;
    }

    public void setBprice(double bprice) {
        this.bprice = bprice;
    }

    public int getBlend() {
        return blend;
    }

    public void setBlend(int blend) {
        this.blend = blend;
    }

    public int getBsurplus() {
        return bsurplus;
    }

    public void setBsurplus(int bsurplus) {
        this.bsurplus = bsurplus;
    }

}
