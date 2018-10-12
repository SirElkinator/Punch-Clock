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


import java.sql.*;

public class TASDatabase {
         
         
         public static  void TASDatabase(){
                    
                   Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                   
                    try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                              System.out.println("Connecting to " + url + "...");
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                               if (conn.isValid(0)) {
                                        System.out.println("Connected Successfully!");
                               }
                               
                              /*Statement stmt = conn.createStatement( );
                              ResultSet result = stmt.executeQuery("SELECT * FROM badge WHERE id='3282F212'");
                              if ( result != null ){
                                        result.next();
                                        String id = result.getString("id");
                                        String desc = result.getString("description");
                              }*/
                              
                              conn.close( );
                              
                    }
                   
                   catch (Exception e){
                              System.err.println(e.toString());
                    }
                   
                   finally {
            
                              if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
                              if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
                              if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
                    }
                   
         }
         
          /*public Shift getShift(int punchtypeid){
                    
          }
          
          public Shift getShift(Badge Badge){
                    
          }
          
          public Badge getBadge(String Badge){
                    //return Badge;
          }
          
          public Punch getPunch(int punchtypeid){
                    
          }*/
          
}
