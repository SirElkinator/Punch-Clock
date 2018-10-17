/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
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
              punch = "#"+badgeid+" CLOCKED OUT: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));;
              }
              if (punchtype == 1){
              punch = "#"+badgeid+" CLOCKED IN: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));;
              }
              if (punchtype == 2){
              punch = "#"+badgeid+" TIMED OUT: "+gcal.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));;
              }
              return punch.toUpperCase();
          }
}
