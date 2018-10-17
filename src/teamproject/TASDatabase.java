/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

/**
 *
 * @author Chase Sparks, Richard Arredondo, Benjamin Baker, Raushaod Merritt, Andrew Reese
 */


import java.sql.*;

public class TASDatabase {
         
         
         public static void TASDatabase(){
                   //TASDatabase db = new TASDatabase();
                   
         }
         
          public Shift getShift(int shiftid){
                     
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                   Shift shift = null;
                    try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                               
                              Statement stmt = conn.createStatement( );
                              ResultSet result = stmt.executeQuery("SELECT * FROM shift WHERE id='"+shiftid+"'");
                              if ( result != null ){
                                        result.next();
                                        String id = result.getString("id");
                                        String desc = result.getString("description");
                                        Time start= result.getTime("start");
                                        Time stop= result.getTime("stop");
                                        int interval = result.getInt("interval");
                                        int gracePeriod = result.getInt("graceperiod");
                                        int dock = result.getInt("dock");
                                        Time lunchStart= result.getTime("lunchstart");
                                       
                                        Time lunchStop = result.getTime("lunchstop");
                                        
                                        int lunchDeduct = result.getInt("lunchDeduct");
                                        shift = new Shift(id,desc,start, stop, interval,gracePeriod,dock,lunchStart, lunchStop,lunchDeduct);
                                          
                              }
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
                    return shift;
          }
          
          public Shift getShift(Badge Badge){
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                     Shift shift = null;
                     
                    try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                              
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                   
                             
                              Statement stmt = conn.createStatement( );
                              String badgeid = Badge.getID();
                              ResultSet result = stmt.executeQuery("SELECT * FROM employee WHERE badgeid= '"+badgeid+"'");
                              String shiftid = null;
                              if (result != null){
                                        result.next();
                                        shiftid = result.getString("shiftid");
                              }
                              result = stmt.executeQuery("SELECT * FROM shift WHERE id= '"+shiftid+"'");
                              if ( result != null ){
                                        result.next();
                                        String id = result.getString("id");
                                        String desc = result.getString("description");
                                        Time start= result.getTime("start");
                                        Time stop = result.getTime("stop");
                                        int interval = result.getInt("interval");
                                        int gracePeriod = result.getInt("graceperiod");
                                        int dock = result.getInt("dock");
                                        Time lunchStart = result.getTime("lunchstart");
                                        Time lunchStop = result.getTime("lunchstop");
                                        int lunchDeduct = result.getInt("lunchDeduct");
                                         shift = new Shift(id,desc,start, stop,interval,gracePeriod,dock,lunchStart,lunchStop,lunchDeduct);
                                          
                              }
                             
                              
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
                    
                    return shift;
                   
          }
          
          public Badge getBadge(String badgeid){
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                     Badge badge = null;
                    try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                              
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                   
                             
                              Statement stmt = conn.createStatement( );
                              ResultSet result = stmt.executeQuery("SELECT * FROM badge WHERE id= '"+badgeid+"'");
                              if ( result != null ){
                                        result.next();
                                        String id = result.getString("id");
                                        String desc = result.getString("description");
                                        
                                        badge = new Badge(id,desc);
                                          
                              }
                             
                              
                              conn.close( );
                
                    }
                    
                    catch (Exception e){
                              System.err.println(e.toString());
                              return null;
                    }
                   
                   finally {
            
                              if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
                              if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
                              if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
                    }
                    
                    return badge;
                    
          }
          
          public Punch getPunch(int punchId){
                   
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                   Punch punch = null;
                    try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                           
                               
                              Statement stmt = conn.createStatement( );
                              ResultSet result = stmt.executeQuery("SELECT *, UNIX_TIMESTAMP(originaltimestamp)*1000 AS ts FROM punch WHERE id='"+punchId+"'");
                              if ( result != null ){
                                        result.next();
                                        int id = result.getInt("id");
                                        long ts = result.getLong("ts");
                                        int punchTypeId = result.getInt("punchtypeid");
                                        int terminalid = result.getInt("terminalid");
                                        String badgeId = result.getString("badgeid");
                                        Badge badge = getBadge(badgeId);punch = new Punch(badge, terminalid, punchTypeId);
                                          punch.setId(id);
                                          punch.setTS(ts);
                              }
                              
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
                    return punch;
          }
          
}
