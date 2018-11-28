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
                    int numberOfMins = 0;
                    if (dailypunchlist.size() < 2){
                              return 0;
                    }
                    for (int i = 0; i < dailypunchlist.size(); i=i+2){
                              Punch clockIn = (Punch) dailypunchlist.get(i);
                              Punch clockOut = (Punch) dailypunchlist.get(i+1);
                              
                              if ((clockIn.getPunchtypeid()!=2) && (clockOut.getPunchtypeid()!=2)){
                                         long clockDifference = clockOut.gcal2.getTimeInMillis()-clockIn.gcal2.getTimeInMillis();
                                         numberOfMins = numberOfMins + (int) (clockDifference/60000);
                              }
                              if ((numberOfMins > shift.getDeduct()) && (clockIn.getLunchFlag() == false)){
                                        int numberOfLunchMins = numberOfMins - shift.getLunchTime();
                                        return numberOfLunchMins;
                             }
                    }
                    
                    return numberOfMins;
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
          
          public static double calculateAbsenteeism(ArrayList<Punch> punchlist, Shift shift){
                    double a = 0;
                    
                    return a;
          }
}