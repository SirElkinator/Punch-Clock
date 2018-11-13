package Feature7;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONValue;

import teamproject.Shift;
import teamproject.TASDatabase;
import teamproject.TASLogic;
import teamproject.Punch;
import teamproject.Badge;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature7 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testJSONShift1Weekday() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536061711000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536062400000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3279\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536080562000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536080400000\",\"punchdata\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3333\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536148008000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536148800000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3395\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536166946000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536166800000\",\"punchdata\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3461\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536168395000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536168600000\",\"punchdata\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3462\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536186824000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536186600000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3498\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536234366000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536235200000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3523\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536253414000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536253200000\",\"punchdata\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3569\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536254854000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536255000000\",\"punchdata\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3570\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536273201000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536273000000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3597\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536321035000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536321600000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3634\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536339834000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536339600000\",\"punchdata\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3687\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536341021000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536341400000\",\"punchdata\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3688\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536352453000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536352200000\",\"punchdata\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"3716\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1536404136000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536404400000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3756\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1536426217000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1536426000000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3839\"},{\"absenteeism\":\"2.50%\",\"totalminutes\":\"2340\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(3634);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
		
        /* Get Daily Punch List */
        
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, p.getOriginaltimestamp());
        
        /* Adjust Punches */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
        }
        
        /* JSON Conversion */
        
        String actualJSON = TASLogic.getPunchListPlusTotalsAsJSON(punchlist, s);
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testJSONShift1Weekend() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1533556457000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533556800000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"508\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1533587538000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533587400000\",\"punchdata\":\"Shift Stop\",\"terminalid\":\"105\",\"id\":\"565\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1533642882000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533643200000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"619\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1533677567000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533677400000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"702\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1533729270000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533729600000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"739\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1533763961000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533763800000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"814\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1533815638000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533816000000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"851\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1533846796000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533846600000\",\"punchdata\":\"Shift Stop\",\"terminalid\":\"105\",\"id\":\"927\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1533902065000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533902400000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"975\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1533933335000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533933000000\",\"punchdata\":\"Shift Stop\",\"terminalid\":\"105\",\"id\":\"1074\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1533984898000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1533985200000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1087\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1534007042000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1534006800000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1162\"},{\"absenteeism\":\"-20.00%\",\"totalminutes\":\"2880\"}]";
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(1087);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        /* Get Daily Punch List */
        
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, p.getOriginaltimestamp());
        
        /* Adjust Punches */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
        }
        
        /* JSON Conversion */
        
        String actualJSON = TASLogic.getPunchListPlusTotalsAsJSON(punchlist, s);
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testJSONShift2Weekend() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1537201837000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537201800000\",\"punchdata\":\"None\",\"terminalid\":\"104\",\"id\":\"4809\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1537234326000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537234200000\",\"punchdata\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"4880\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1537289973000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537290000000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"4943\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1537324227000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537324200000\",\"punchdata\":\"None\",\"terminalid\":\"104\",\"id\":\"5004\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1537376871000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537377300000\",\"punchdata\":\"Shift Dock\",\"terminalid\":\"104\",\"id\":\"5091\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1537414265000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537414200000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"5162\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1537462650000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537462800000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"5228\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1537500631000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537500600000\",\"punchdata\":\"None\",\"terminalid\":\"104\",\"id\":\"5307\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1537548728000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537549200000\",\"punchdata\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"5383\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1537579851000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537579800000\",\"punchdata\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"5455\"},{\"punchtypeid\":\"1\",\"originaltimestamp\":\"1537613340000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537613100000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"5463\"},{\"punchtypeid\":\"0\",\"originaltimestamp\":\"1537635855000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1537635600000\",\"punchdata\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"5541\"},{\"absenteeism\":\"-27.50%\",\"totalminutes\":\"3060\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(4943);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        /* Get Daily Punch List */
        
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, p.getOriginaltimestamp());
        
        /* Adjust Punches */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
        }
        
        /* JSON Conversion */
        
        String actualJSON = TASLogic.getPunchListPlusTotalsAsJSON(punchlist, s);
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
}