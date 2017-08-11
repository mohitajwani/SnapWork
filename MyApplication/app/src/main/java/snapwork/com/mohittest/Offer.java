package snapwork.com.mohittest;

/**
 * Created by snap34 on 11/8/17.
 */

public class Offer {
    String address;
    String name;
    double LAT;
    double LON;
    boolean isMarked;

    public Offer(String address, String name, double LAT, double LON) {
        this.address = address;
        this.name = name;
        this.LAT = LAT;
        this.LON = LON;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLAT() {
        return LAT;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }

    public double getLON() {
        return LON;
    }

    public void setLON(double LON) {
        this.LON = LON;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
