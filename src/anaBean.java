import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cenk.akdeniz on 16.12.2015.
 */
@ManagedBean
public class anaBean {
    String bilet,whiteList,sonuc;
    biletOkuma bo = new biletOkuma();

    public String getBilet() {
        return bilet;
    }

    public void setBilet(String bilet) {
        this.bilet = bilet;
    }

    public String getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(String whiteList) {
        this.whiteList = whiteList;
    }

    public String getSonuc() {
        return sonuc;
    }

    public void setSonuc(String sonuc) {
        this.sonuc = sonuc;
    }
    public void yazdır() throws IOException {

        List list = new ArrayList();


           // list= bo.kiyasla(bo.biletOku(bilet),bo.biletOku(whiteList));
            //bo.biletYaz(sonuc,list);


    }
}
