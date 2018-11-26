/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import java.sql.Timestamp;

/**
 *
 * @author Chase Sparks
 */
public class Absenteeism{
          String badgeid;
          long payperiod;
          double percentage;
          
          public Absenteeism(String badgeid, long payperiod, double percentage){
                    this.badgeid = badgeid;
                    this.payperiod = payperiod;
                    this.percentage = percentage;
          }
          
          String getId(){
                    return this.badgeid;
          }
          void setId(String badgeid){
                    this.badgeid = badgeid;
          }
          long getPayPeriod(){
                    return this.payperiod;
          }
          void setPayPeriod(long payperiod){
                    this.payperiod = payperiod;
          }
         double getPercentage(){
                    return this.percentage;
          }
          void setPercentage(float percentage){
                    this.percentage = percentage;
          }
          @Override
          public String toString(){
                    String string = badgeid + payperiod + percentage ;
                    return string;
          }
          
}
