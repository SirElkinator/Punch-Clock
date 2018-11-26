package Feature6;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

import teamproject.Shift;
import teamproject.TASDatabase;
import teamproject.TASLogic;
import teamproject.Punch;
import teamproject.Badge;
import teamproject.Absenteeism;
public class Feature6 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testAbsenteeismShift1Weekday() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(3634);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        /* Get Pay Period Punch List */
        
        long ts = p.getOriginaltimestamp();
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, ts);

        /* Adjust Punches */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
        }
        
        /* Compute Pay Period Total Absenteeism */
        
        double percentage = TASLogic.calculateAbsenteeism(punchlist, s);
        
        /* Insert Absenteeism Into Database */
        
        Absenteeism a1 = new Absenteeism(b.getId(), ts, percentage);
        db.insertAbsenteeism(a1);
        
        /* Retrieve Absenteeism From Database */
        
        Absenteeism a2 = db.getAbsenteeism(b.getId(), ts);
        
        /* Compare to Expected Value */
        
        assertEquals("#28DC3FB8 (Pay Period Starting 09-02-2018): 2.50%", a2.toString());
        
    }
    
    @Test
    public void testAbsenteeismShift1Weekend() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(1087);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        /* Get Pay Period Punch List */
        
        long ts = p.getOriginaltimestamp();
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, ts);

        /* Adjust Punches */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
        }
        
        /* Compute Pay Period Total Absenteeism */
        
        double percentage = TASLogic.calculateAbsenteeism(punchlist, s);
        
        /* Insert Absenteeism Into Database */
        
        Absenteeism a1 = new Absenteeism(b.getId(), ts, percentage);
        db.insertAbsenteeism(a1);
        
        /* Retrieve Absenteeism From Database */
        
        Absenteeism a2 = db.getAbsenteeism(b.getId(), ts);
        
        /* Compare to Expected Value */
        
        assertEquals("#F1EE0555 (Pay Period Starting 08-05-2018): -20.00%", a2.toString());
        
    }
    
    @Test
    public void testAbsenteeismShift2Weekend() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(4943);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        /* Get Pay Period Punch List */
        
        long ts = p.getOriginaltimestamp();
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, ts);

        /* Adjust Punches */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
        }
        
        /* Compute Pay Period Total Absenteeism */
        
        double percentage = TASLogic.calculateAbsenteeism(punchlist, s);
        
        /* Insert Absenteeism Into Database */
        
        Absenteeism a1 = new Absenteeism(b.getId(), ts, percentage);
        db.insertAbsenteeism(a1);
        
        /* Retrieve Absenteeism From Database */
        
        Absenteeism a2 = db.getAbsenteeism(b.getId(), ts);
        
        /* Compare to Expected Value */
        
        assertEquals("#08D01475 (Pay Period Starting 09-16-2018): -27.50%", a2.toString());
        
    }
    
}