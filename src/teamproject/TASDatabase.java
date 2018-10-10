/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

/**
 *
 * @author Chase Sparks
 */

import teamproject.Shift;
import teamproject.Punch;
import teamproject.Badge;
import java.sql.*;

public class TASDatabase {
         
         /* Class.forName("com.mysql.jdbc.Driver").newInstance();
          String url = "jdbc:mysql://localhost/dbname";
          Connection conn = DriverManager.getConnection(url, username, password);
          Statement stmt = conn.createStatement( );
          ResultSet result = stmt.executeQuery("SELECT * FROM badge WHERE id='3282F212'");
          if ( result != null ){
                    result.next();
                    String id = result.getString("id");
                    String desc = result.getString("description");
          }
          result.close( );
          stmt.close( );
          conn.close( );*/
          
          Shift getShift(int punchtypeid){
                    
          }
          
          Shift getShift(String Badge){
                    
          }
          
          Badge getBadge(String Badge){
                    
          }
          
          Punch getPunch(int punchtypeid){
                    
          }

          //close(){
                    
          //}
          
}
