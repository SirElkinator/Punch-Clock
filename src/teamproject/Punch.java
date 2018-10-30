/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 *
 * @author Chase Sparks, Richard Arredondo, Benjamin Baker, Raushaod Merritt, Andrew Reese
 */
public class Punch {
        
        private String badgeid;
        private int punchtype;
        private int terminal;
        private int id;
        private long ts;
        private long ats;
        GregorianCalendar gcal;
        
        //private Time original_timestamp;
          public Punch(Badge badge, int terminalid, int punchtypeid){
               
               this.badgeid = badge.getID();
               this.punchtype = punchtypeid;
               this.id = id;
               this.terminal = terminalid;
               this.ts = ts;
               
          }
          
          public void setTS(long ts){
                    this.ts = ts;
          }
          
          public void setId(int id){
                    this.id = id;
          }
          public String printOriginalTimestamp(){
              
              String punch = null;
              gcal = new GregorianCalendar();
              gcal.setTimeInMillis(ts);
              
              if (punchtype == 0){
              punch = "#"+badgeid+" CLOCKED OUT: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
              }
              if (punchtype == 1){
              punch = "#"+badgeid+" CLOCKED IN: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
              }
              if (punchtype == 2){
              punch = "#"+badgeid+" TIMED OUT: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
              }
              return punch.toUpperCase();
              
          }
          
          public void adjust(Shift s) {
        
                    GregorianCalendar shiftStart = new GregorianCalendar();
                    GregorianCalendar startDock = new GregorianCalendar();
                    GregorianCalendar startInterval = new GregorianCalendar();
                    GregorianCalendar startGrace = new GregorianCalendar();
                    GregorianCalendar shiftStop = new GregorianCalendar();
                    GregorianCalendar stopDock = new GregorianCalendar();
                    GregorianCalendar stopInterval = new GregorianCalendar();
                    GregorianCalendar stopGrace = new GregorianCalendar();
                    GregorianCalendar lunchStart = new GregorianCalendar();
                    GregorianCalendar lunchStop = new GregorianCalendar();
                   
        }
                    
          public String printAdjustedTimestamp(){
                     String punch = null;
                    gcal = new GregorianCalendar();
                    gcal.setTimeInMillis(ats);
              
                    if (punchtype == 0){
                              punch = "#"+badgeid+" CLOCKED OUT: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                    }
                    if (punchtype == 1){
                              punch = "#"+badgeid+" CLOCKED IN: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                    }
                    if (punchtype == 2){
                              punch = "#"+badgeid+" TIMED OUT: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                    }
                    return punch.toUpperCase();
          }
          
          public int getTerminalid(){
              return this.terminal;
          }
          public int getPunchtypeid(){
              return this.punchtype;
          }
          public String getBadgeid(){
              return this.badgeid;
          }
          public long getOriginaltimestamp(){
              return this.ts;
          }
          public int getId(){
                    return this.id;
          }
          
}