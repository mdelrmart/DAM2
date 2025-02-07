package pojos;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class OrderData implements Comparator<Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /*
    // NO ES MUY BUENA IDEA COMPARAR STRINGS
    @Override
    public int compare(Date o1, Date o2) {
        return sdf.format(o2).compareTo(sdf.format(o1));
    }
    */

   /*
   @Override
   public int compare(Date o1, Date o2) {
        return o1.compareTo(o2) * -1;
   }
   */

    @Override
    public int compare(Date o1, Date o2) {
        return ((Comparator<Date>) Comparator.reverseOrder()).compare(o1, o2);
    }

}
