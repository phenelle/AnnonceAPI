package com.cubitux.utils;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pierre on 2016-05-26.
 */
public class DateUtilTest extends TestCase {

    @Test
    public void testDateToString() {
        String expectedValue = "2016-05-26 16:30:01";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 01);

        Date date = calendar.getTime();

        // Convert Date to String
        String convertedValue = DateUtil.dateToString(date);
        assertEquals("Converted value is different than expected value", convertedValue, expectedValue);
    }


    @Test
    public void testStringToDate() throws Exception {

        String stringToConvert = "2016-05-26 16:30:01";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 01);
        Date expectedDate = calendar.getTime();

        // Convert String to Date
        Date convertedDate = DateUtil.stringToDate(stringToConvert);

        assertEquals("Converted value is different than expected value", convertedDate.toString(), expectedDate.toString());

    }
}
