
import java.util.Comparator;
public class I implements Comparator<Proceso> {
    public static void main(String[] args) {   
    }
    public I(){

    }

    @Override
    public int compare(Proceso o1, Proceso o2) {
       return o1.getTiempoR()-o2.getTiempoR();
    }
    
}
