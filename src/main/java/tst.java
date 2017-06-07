import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by innoraft on 7/6/17.
 */
public class tst {

    public static void main(String[] args) throws IOException {

        Date date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedDate=dateFormat.format(date);
        System.out.print(formattedDate);

    }
}
