package id.ac.unikom.admintajwid.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateConverter {

    public Date changeFormat(Date date) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);
        return dateFormat.parse(strDate);
    }

    public Date convert(String gmt) throws ParseException{
        Date date = Calendar.getInstance().getTime(); // tanggal dan waktu sekarang
        DateFormat gmtFormat = new SimpleDateFormat();
        TimeZone gmtTime = TimeZone.getTimeZone(gmt);
        gmtFormat.setTimeZone(gmtTime);
        String dateGmt = gmtFormat.format(date);
        return new SimpleDateFormat().parse(dateGmt);
    }
}
