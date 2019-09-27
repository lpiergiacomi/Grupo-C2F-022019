package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.model.holiday.HolidayChecker;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class HolidayCheckerTest {

    @Test
    public void isHolidayTest() {
        HolidayChecker holidayChecker = new HolidayChecker(2019);
        LocalDateTime July9Holiday = LocalDateTime.of(2019, 7, 9, 12, 0);
        assertTrue(holidayChecker.isHoliday(July9Holiday));
    }

    @Test
    public void notHolidayTest() {
        HolidayChecker holidayChecker = new HolidayChecker(2019);
        LocalDateTime workDay = LocalDateTime.of(2019, 9, 18, 12, 0);
        assertFalse(holidayChecker.isHoliday(workDay));
    }

    @Test
    public void isWeekendTest() {
        HolidayChecker holidayChecker = new HolidayChecker(2019);
        LocalDateTime sunday = LocalDateTime.of(2019, 9, 22, 12, 0);
        assertTrue(holidayChecker.isWeekend(sunday));
    }

    @Test
    public void notWeekendTest() {
        HolidayChecker holidayChecker = new HolidayChecker(2019);
        LocalDateTime workDay = LocalDateTime.of(2019, 9, 23, 12, 0);
        assertFalse(holidayChecker.isWeekend(workDay));
    }

}



