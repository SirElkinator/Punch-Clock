/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 *
 * @author Chase Sparks, Richard Arredondo, Benjamin Baker, Raushaod Merritt, Andrew Reese
 */
public class Punch {
         GregorianCalendar gcal = new GregorianCalendar();
         GregorianCalendar gcal2 = new GregorianCalendar();
        private String badgeid;
        private int punchtype;
        private int terminal;
        private int id;
        private long ts;
        private long ats;
        private String eventdata;
        private boolean lunchFlag = false;
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
                   
                    gcal = new GregorianCalendar();
                    gcal.setTimeInMillis(ts);
                   
                    long originalTimeStampMillis = gcal.getTimeInMillis();
        
                    gcal2.setTimeInMillis(originalTimeStampMillis);
        
                    shiftStart.setTimeInMillis(originalTimeStampMillis);
                    shiftStart.set(Calendar.HOUR_OF_DAY, s.getStart().getHours());
                    shiftStart.set(Calendar.MINUTE, s.getStart().getMinutes());
                    shiftStart.set(Calendar.SECOND, 0);
                    long shiftStartMillis = shiftStart.getTimeInMillis();

                    startDock.setTimeInMillis(shiftStartMillis);
                    startDock.add(Calendar.MINUTE, s.getDock());
                    long startDockMillis = startDock.getTimeInMillis();

                    startInterval.setTimeInMillis(shiftStartMillis);
                    startInterval.add(Calendar.MINUTE, -s.getInterval());
                    long startIntervalMillis = startInterval.getTimeInMillis();

                    startGrace.setTimeInMillis(shiftStartMillis);
                    startGrace.add(Calendar.MINUTE, s.getGrace());
                    long startGraceMillis = startGrace.getTimeInMillis();

                    shiftStop.setTimeInMillis(originalTimeStampMillis);
                    shiftStop.set(Calendar.HOUR_OF_DAY, s.getStop().getHours());
                    shiftStop.set(Calendar.MINUTE, s.getStop().getMinutes());
                    shiftStop.set(Calendar.SECOND, 0);
                    long shiftStopMillis = shiftStop.getTimeInMillis();

                    stopDock.setTimeInMillis(shiftStopMillis);
                    stopDock.add(Calendar.MINUTE, -s.getDock());
                    long stopDockMillis = stopDock.getTimeInMillis();

                    stopInterval.setTimeInMillis(shiftStopMillis);
                    stopInterval.add(Calendar.MINUTE, s.getInterval());
                    long stopIntervalMillis = stopInterval.getTimeInMillis();

                    stopGrace.setTimeInMillis(shiftStopMillis);
                    stopGrace.add(Calendar.MINUTE, -s.getGrace());
                    long stopGraceMillis = stopGrace.getTimeInMillis();
        
                    lunchStart.setTimeInMillis(originalTimeStampMillis);
                    lunchStart.set(Calendar.HOUR_OF_DAY, s.getLunchStart().getHours());
                    lunchStart.set(Calendar.MINUTE, s.getLunchStart().getMinutes());
                    lunchStart.set(Calendar.SECOND, 0);
                    long lunchStartMillis = lunchStart.getTimeInMillis();
                    lunchStop.setTimeInMillis(originalTimeStampMillis);
                    lunchStop.set(Calendar.HOUR_OF_DAY, s.getLunchStop().getHours());
                    lunchStop.set(Calendar.MINUTE, s.getLunchStop().getMinutes());
                    lunchStop.set(Calendar.SECOND, 0);        
                    long lunchStopMillis = lunchStop.getTimeInMillis();
        
                    int interval = s.getInterval();

                    if(shiftStart.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || shiftStart.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                              
                              if(punchtype == 1){
                                        if(originalTimeStampMillis >= startIntervalMillis && originalTimeStampMillis <= shiftStartMillis + (s.getInterval() * 60000) ){
                                                  eventdata = "None";
                                        }
                                        else{
                                                  if(gcal.get(Calendar.MINUTE) % interval <= interval / 2){
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) - (gcal.get(Calendar.MINUTE) % interval));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  else{
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) + (interval - (gcal.get(Calendar.MINUTE) % interval)));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  eventdata = "Interval Round";
                                        }
                              }
                              
                              else if(punchtype == 0){
                                        if(originalTimeStampMillis <= stopIntervalMillis && originalTimeStampMillis >= shiftStopMillis + (s.getInterval() * 60000)){
                                                  eventdata = "None";
                                        }
                                        else{
                                                  if(gcal.get(Calendar.MINUTE) % interval >= interval / 2){
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) + (interval - (gcal.get(Calendar.MINUTE) % interval)));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  else{
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) - (gcal.get(Calendar.MINUTE) % interval));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  eventdata = "Interval Round";
                                        }
                              }
                              
                    }
        
                    else{
                              
                              if (punchtype == 1) {
                                        if(originalTimeStampMillis <= shiftStartMillis && originalTimeStampMillis >= startIntervalMillis){
                                                  gcal2.setTimeInMillis(shiftStartMillis);
                                                  eventdata = "Shift Start";
                                        }
                                        else if(originalTimeStampMillis >= shiftStartMillis && originalTimeStampMillis <= startGraceMillis){
                                                  gcal2.setTimeInMillis(shiftStartMillis);
                                                  eventdata = "Shift Start";
                                        }
                                        else if(originalTimeStampMillis >= lunchStartMillis && originalTimeStampMillis <= lunchStopMillis){
                                                  gcal2.setTimeInMillis(lunchStopMillis);
                                                  lunchFlag = true;
                                                  eventdata = "Lunch Stop";
                                        }
                                        else if(originalTimeStampMillis > startGraceMillis && gcal.get(Calendar.MINUTE) % interval > interval/2){
                                                  gcal2.setTimeInMillis(startDockMillis);
                                                  eventdata = "Shift Dock";
                                        }   
                                        else if(gcal.get(Calendar.HOUR_OF_DAY) == shiftStart.get(Calendar.HOUR_OF_DAY) + 1 && gcal.get(Calendar.MINUTE) == shiftStart.get(Calendar.MINUTE)){
                                                  eventdata = "None";
                                        }
                                        else{
                                                  if(gcal.get(Calendar.MINUTE) % interval <= interval / 2){
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) - (gcal.get(Calendar.MINUTE) % interval));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  else{
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) + (gcal.get(Calendar.MINUTE) % interval));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  eventdata = "Interval Round";
                                        }
                              }
                              
                              else if(punchtype == 0){
                                        if(originalTimeStampMillis >= stopGraceMillis && originalTimeStampMillis <= shiftStopMillis){
                                                  gcal2.setTimeInMillis(shiftStopMillis);
                                                  eventdata = "Shift Stop";
                                        }
                                        else if(originalTimeStampMillis <= stopIntervalMillis && originalTimeStampMillis >= shiftStopMillis){
                                                  gcal2.setTimeInMillis(shiftStopMillis);
                                                  eventdata = "Shift Stop";
                                        }
                
                                        else if(originalTimeStampMillis >= lunchStartMillis && originalTimeStampMillis < lunchStopMillis){
                                                  gcal2.setTimeInMillis(lunchStartMillis);
                                                  lunchFlag = true;
                                                  eventdata = "Lunch Start";
                                        }
                                        else if(originalTimeStampMillis < stopGraceMillis && gcal.get(Calendar.MINUTE) % interval < interval/2){
                                                  gcal2.setTimeInMillis(stopDockMillis);
                                                  eventdata = "Shift Dock";
                                        } 
                                        else if(gcal.get(Calendar.HOUR_OF_DAY) == shiftStop.get(Calendar.HOUR_OF_DAY) + 1 && gcal.get(Calendar.MINUTE) == shiftStop.get(Calendar.MINUTE)){
                                                  eventdata = "None";
                                                  gcal2.set(Calendar.SECOND,0);
                                        }
                                        else{
                                                  if(gcal.get(Calendar.MINUTE) % interval >= interval / 2){
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) + (gcal.get(Calendar.MINUTE) % interval) + 1);
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  else{
                                                            gcal2.set(Calendar.MINUTE, gcal.get(Calendar.MINUTE) - (gcal.get(Calendar.MINUTE) % interval));
                                                            gcal2.set(Calendar.SECOND, 0);
                                                  }
                                                  eventdata = "Interval Round";
                                        }
                              }       
                    }
                    this.ats = gcal2.getTimeInMillis();
          }
                    
          public String printAdjustedTimestamp(){
                     
                    String punch = null;
                    if (punchtype == 0){
                              punch = "#"+badgeid+" CLOCKED OUT: "+gcal2.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss"));
                    }
                    if (punchtype == 1){
                              punch = "#"+badgeid+" CLOCKED IN: "+gcal2.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss"));
                    }
                    if (punchtype == 2){
                              punch = "#"+badgeid+" TIMED OUT: "+gcal2.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss"));
                    }
                    return punch.toUpperCase()+" ("+eventdata+")";
                    
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
          public String getPunchData(){
                    return this.eventdata;
          }
          public long getAdjustedtimestamp(){
                    return this.ats;
          }
          public int getId(){
                    return this.id;
          }
          public boolean getLunchFlag(){
                    return this.lunchFlag;
          }
          
}