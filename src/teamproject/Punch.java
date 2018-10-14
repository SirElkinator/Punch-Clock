/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;
            //import java.sql.Time;
/**
 *
 * @author Chase Sparks
 */
public class Punch {
        
        private Badge id;
        private int punchtype;
        //private Time original_timestamp;
          Punch(Badge badge, int terminalid, int punchtypeid){
               this.id = badge;
               this.punchtype = punchtypeid;
          }
          
          public String printOriginalTimestamp(){
              String punch;
              punch = "#"+id+" "+punchtype;
              return punch;
          }
}
