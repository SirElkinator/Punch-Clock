/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;
/**
 *
 * @author Chase Sparks, Richard Arredondo, Benjamin Baker, Raushaod Merritt, Andrew Reese
 */
public class Shift {
    private String id;
    private String description;
    private Time start;
    private Time stop;
    private int interval;
    private int grace;
    private int dock;
    private Time lunchStart;
    private Time lunchStop;
    private int deduct;
  
          Shift(String id, String desc, Time start, Time stop, int interval, int grace, int dock, Time lunchStart, Time lunchStop, int deduct){
                    this.id = id;
                    this.description = desc;
                    this.start= start;
                    this.stop = stop;
                    this.interval = interval;
                    this.grace = grace;
                    this.dock = dock;
                    this.lunchStart = lunchStart;
                    this.lunchStop = lunchStop;
                    this.deduct = deduct;
                   
                   
          }
          @Override
    public String toString(){
           String shift;
           
           shift =  description+": "+start+" - "+stop.getHours()+":"+stop.getMinutes()+ " (" +((stop.getTime()-start.getTime()) /1000 /60)+" minutes); Lunch: " + lunchStart.getHours()+":"+lunchStart.getMinutes()+ " - " + lunchStop.getHours()+":"+ lunchStop.getMinutes()+" (" +((lunchStop.getTime()-lunchStart.getTime()) /1000 /60)+" minutes)";
           return shift;
    }
}
