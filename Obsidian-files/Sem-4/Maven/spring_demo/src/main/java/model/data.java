package model ;

public class data {

    public String aname;
    public String aid;
    public String getAname() {
        return aname;
    }
    public void setAname(String aname) {
        this.aname = aname;
    }
    public String getAid() {
        return aid;
    }
    public void setAid(String aid) {
        this.aid = aid;
    }


    @Override
    public String toString() {
        return "data [aname=" + aname + ", aid=" + aid + "]";
    }

}
