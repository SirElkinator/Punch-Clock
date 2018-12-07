package feature8;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import teamproject.Shift;
import teamproject.TASDatabase;
import teamproject.TASLogic;
import teamproject.Punch;
import teamproject.Badge;
import teamproject.Absenteeism;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature8 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testTemporaryOverrideAllEmployees() {
        
        /* Create Timestamp and Badge Objects */
        
        GregorianCalendar gc = new GregorianCalendar();
        Badge b = db.getBadge("D2CC71D4");
        
        /* PART ONE */
        
        /* Get Shift Object for Pay Period Starting 08-26-2018 (regular Shift 1 schedule) */
        
        gc.set(Calendar.DAY_OF_MONTH, 26);
        gc.set(Calendar.YEAR, 2018);
        gc.set(Calendar.MONTH, 7);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        
        Shift s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #1 */
        
        ArrayList<Punch> p1 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p1) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 08-26-2018 Absenteeism */
        
        double percentage = TASLogic.calculateAbsenteeism(p1, s);
        Absenteeism a1 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#D2CC71D4 (Pay Period Starting 08-26-2018): -17.50%", a1.toString());
        
        /* PART TWO */
        
        /* Get Shift Object for Pay Period Starting 09-02-2018 (should include Labor Day override) */
        
        gc.set(Calendar.DAY_OF_MONTH, 2);
        gc.set(Calendar.MONTH, 8);
        
        s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #2 */
        
        ArrayList<Punch> p2 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p2) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-02-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p2, s);
        Absenteeism a2 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#D2CC71D4 (Pay Period Starting 09-02-2018): -29.69%", a2.toString());
        
        /* PART THREE */
        
        /* Get Shift Object for Pay Period Starting 09-09-2018 (regular Shift 1 schedule) */
        
        gc.set(Calendar.DAY_OF_MONTH, 9);
        gc.set(Calendar.MONTH, 8);
        
        s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #3 */
        
        ArrayList<Punch> p3 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p3) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-09-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p3, s);
        Absenteeism a3 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#D2CC71D4 (Pay Period Starting 09-09-2018): -4.38%", a3.toString());
        
    }
    
    @Test
    public void testTemporaryOverrideIndividualEmployee() {
        
        /* Create Timestamp and Badge Objects */
        
        GregorianCalendar gc = new GregorianCalendar();
        Badge b = db.getBadge("0FFA272B");
        
        /* PART ONE */
        
        /* Get Shift Object for Pay Period Starting 09-02-2018 (should include Labor Day override) */
        
        gc.set(Calendar.DAY_OF_MONTH, 2);
        gc.set(Calendar.YEAR, 2018);
        gc.set(Calendar.MONTH, 8);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        
        Shift s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #1 */
        
        ArrayList<Punch> p1 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p1) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-02-2018 Absenteeism */
        
        double percentage = TASLogic.calculateAbsenteeism(p1, s);
        Absenteeism a1 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#0FFA272B (Pay Period Starting 09-02-2018): 28.13%", a1.toString());
        
        /* PART TWO */
        
        /* Get Shift Object for Pay Period Starting 09-09-2018 (should include temporary "Wednesday Off" override) */
        
        gc.set(Calendar.DAY_OF_MONTH, 9);
        gc.set(Calendar.MONTH, 8);
        
        s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #2 */
        
        ArrayList<Punch> p2 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p2) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-09-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p2, s);
        Absenteeism a2 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#0FFA272B (Pay Period Starting 09-09-2018): -0.78%", a2.toString());
        
        /* PART THREE */
        
        /* Get Shift Object for Pay Period Starting 09-09-2018 (should NOT include temporary "Wednesday Off" override) */
        
        gc.set(Calendar.DAY_OF_MONTH, 9);
        gc.set(Calendar.MONTH, 8);
        
        Badge b2 = db.getBadge("76B87761");
        
        s = db.getShift(b2, gc.getTimeInMillis());
        
        /* Retrieve Punch List #3 */
        
        ArrayList<Punch> p3 = db.getPayPeriodPunchList(b2, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p3) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-09-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p3, s);
        Absenteeism a3 = new Absenteeism(b2.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#76B87761 (Pay Period Starting 09-09-2018): 15.00%", a3.toString());
        
        /* PART FOUR */
        
        /* Get Shift Object for Pay Period Starting 09-16-2018 (regular Shift 1 schedule) */
        
        gc.set(Calendar.DAY_OF_MONTH, 16);
        gc.set(Calendar.MONTH, 8);
        
        s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #4 */
        
        ArrayList<Punch> p4 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p4) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-16-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p4, s);
        Absenteeism a4 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#0FFA272B (Pay Period Starting 09-16-2018): 55.00%", a4.toString());
        
    }
    
    @Test
    public void testRecurringOverrideIndividualEmployee() {
        
        /* Create Timestamp and Badge Objects */
        
        GregorianCalendar gc = new GregorianCalendar();
        Badge b = db.getBadge("3282F212");
        
        /* PART ONE */
        
        /* Get Shift Object for Pay Period Starting 09-09-2018 (regular Shift 1 schedule) */
        
        gc.set(Calendar.DAY_OF_MONTH, 9);
        gc.set(Calendar.YEAR, 2018);
        gc.set(Calendar.MONTH, 8);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        
        Shift s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #1 */
        
        ArrayList<Punch> p1 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p1) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-09-2018 Absenteeism */
        
        double percentage = TASLogic.calculateAbsenteeism(p1, s);
        Absenteeism a1 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#3282F212 (Pay Period Starting 09-09-2018): -23.75%", a1.toString());
        
        /* PART TWO */
        
        /* Get Shift Object for Pay Period Starting 09-16-2018 (should include "Leave Early on Friday" override) */
        
        gc.set(Calendar.DAY_OF_MONTH, 16);
        gc.set(Calendar.MONTH, 8);
        
        s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #2 */
        
        ArrayList<Punch> p2 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p2) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-16-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p2, s);
        Absenteeism a2 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#3282F212 (Pay Period Starting 09-16-2018): -42.31%", a2.toString());
        
        /* PART THREE */
        
        /* Get Shift Object for Pay Period Starting 09-23-2018 (should include "Leave Early on Friday" override) */
        
        gc.set(Calendar.DAY_OF_MONTH, 23);
        gc.set(Calendar.MONTH, 8);
        
        s = db.getShift(b, gc.getTimeInMillis());
        
        /* Retrieve Punch List #3 */
        
        ArrayList<Punch> p3 = db.getPayPeriodPunchList(b, gc.getTimeInMillis());
        
        /* Adjust Punches */
        
        for (Punch p : p3) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 09-23-2018 Absenteeism */
        
        percentage = TASLogic.calculateAbsenteeism(p3, s);
        Absenteeism a3 = new Absenteeism(b.getId(), gc.getTimeInMillis(), percentage);
        
        assertEquals("#3282F212 (Pay Period Starting 09-23-2018): -39.74%", a3.toString());
        
    }
    
}