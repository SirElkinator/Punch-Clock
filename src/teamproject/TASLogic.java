/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;

/**
 *
 * @author Chase Sparks, Richard Arredondo, Benjamin Baker, Raushaod Merritt, Andrew Reese
 */
public class TASLogic {
          public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
                    
          }
          public static String getPunchListAsJSON(ArrayList dailypunchlist) {
                    
                    /* Create ArrayList Object */
                    ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();
                    
                    //iterate through punchlist for punches
                    for (int i = 0; i < dailypunchlist.size(); i++){
                              
                              Punch punch = (Punch) dailypunchlist.get(i);
                              
                              /* Create HashMap Object (one for every Punch!) */
                              HashMap<String, String> punchData = new HashMap<>();

                              /* Add Punch Data to HashMap */
                              punchData.put("id", String.valueOf(punch.getId()));
                              punchData.put("badgeid",String.valueOf(punch.getBadgeid()));
                              punchData.put("terminalid",String.valueOf(punch.getTerminalid()));
                              punchData.put("punchtypeid",String.valueOf(punch.getPunchtypeid()));
                              punchData.put("punchdata",String.valueOf(punch.getPunchData()));
                              punchData.put("originaltimestamp",String.valueOf(punch.getOriginaltimestamp()));
                              punchData.put("adjustedtimestamp",String.valueOf(punch.getAdjustedtimestamp()));
                              /* ... continue in the same way with the remaining Punch data ...*/

                              /* Append HashMap to ArrayList */
                              jsonData.add(punchData);
                    }
                    
                    String json = JSONValue.toJSONString(jsonData);
                    return json;
          }
}