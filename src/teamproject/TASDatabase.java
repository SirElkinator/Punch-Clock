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
import java.text.SimpleDateFormat;
import java.util.*;

public class TASDatabase {
          private TASDatabase db;  
         
         public static void TASDatabase(){
                   
         }
         
         public void setting(){
              db = new TASDatabase();
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
                              ResultSet result = stmt.executeQuery("SELECT *, UNIX_TIMESTAMP(originaltimestamp)*1000 "
                                                                    + "AS ts FROM punch WHERE id="+punchId+"");
                              if ( result != null ){
                                        result.next();
                                        int id = result.getInt("id");
                                        long ts = result.getLong("ts");
                                        int punchTypeId = result.getInt("punchtypeid");
                                        int terminalid = result.getInt("terminalid");
                                        String badgeId = result.getString("badgeid");
                                        Badge badge = getBadge(badgeId);
                                        punch = new Punch(badge, terminalid, punchTypeId);
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
          
          public int insertPunch(Punch p){
                    
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                    String query;
                    int updateCount = 0;
                   Punch punch = p;
                   
                  int id = 0;
                  Long ts = punch.getOriginaltimestamp();
                  int punchTypeId = punch.getPunchtypeid();
                  String badgeId = punch.getBadgeid();
                  int terminalid = punch.getTerminalid();
                    
                   try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                           
                               
                              Statement stmt = conn.createStatement( );
                              
                              query = "INSERT INTO punch ( badgeid, terminalid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)";
                              pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                     
                              pstUpdate.setString(1,badgeId);
                              pstUpdate.setInt(2, terminalid);                        
                             pstUpdate.setLong(3,ts);
                              pstUpdate.setInt(4,punchTypeId);
                               
                                 // Get New Key; Print To Console
                              updateCount = pstUpdate.executeUpdate();
                              if (updateCount > 0) {
            
                                    resultset = pstUpdate.getGeneratedKeys();
                                    

                              }
                              ResultSet result = stmt.executeQuery("SELECT * FROM punch ORDER BY id DESC limit 1;");
                              if (result != null){
                                        result.next();
                                        id = result.getInt("id");
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
                   
                    return id;
                    
          }
          
          public ArrayList<Punch> getDailyPunchList(Badge b, long ts){
              
            String b_id = b.getID();
            int id = 0;
            ArrayList<Punch> p_list = new ArrayList<>();
            
              GregorianCalendar ts1 = new GregorianCalendar();
              GregorianCalendar ts2 = new GregorianCalendar();
         
              ts1.setTimeInMillis(ts);
              ts2.setTimeInMillis(ts);
                           // System.out.println(ts);
              
             ts1.set(Calendar.HOUR_OF_DAY, 0);
             ts1.set(Calendar.MINUTE, 0);
             ts1.set(Calendar.SECOND, 0);
                            //System.out.println("ts1=" + ts1.getTimeInMillis());
              
              ts2.set(Calendar.HOUR_OF_DAY, 23);
              ts2.set(Calendar.MINUTE, 59);
              ts2.set(Calendar.SECOND, 59);
                           // System.out.println("ts2=" + ts2.getTimeInMillis());
              
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                    String query;
                    boolean hasresults;
                    try{ 
                             
                        String url = "jdbc:mysql://localhost/tas";
                        String username = "tasuser";
                        String password = "teamE";


                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        conn = DriverManager.getConnection(url, username, password);

                        query = "SELECT *, UNIX_TIMESTAMP(originaltimestamp)*1000 AS ts\n" +
                                                                    "FROM punch\n" +
                                                                    "WHERE badgeid=\"" + b_id + "\"\n" +
                                                                    "HAVING ts >= " + ts1.getTimeInMillis() + "\n" +
                                                                    "AND ts <= " + ts2.getTimeInMillis() + "\n"+
                                                                    "ORDER BY originaltimestamp;\n";
                        pstSelect = conn.prepareStatement(query);
                        hasresults = pstSelect.execute(); 
                        while(hasresults){
                        resultset = pstSelect.getResultSet();

                            while (resultset.next()){
                                    //clock in time
                                    id = resultset.getInt("id");
                                   // System.out.println("Punch added = " + getPunch(id).printOriginalTimestamp());
                                    p_list.add(getPunch(id));                                
                            }  
                        hasresults = pstSelect.getMoreResults();
                        conn.close( ); 
                        }                           
                    }
                    catch (Exception e){ 
                              System.err.println(e.toString());
                    }
                   
                   finally { 
            
                              if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
                              if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
                              if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
                    }
                    
        return p_list;
        }
          
          public ArrayList<Punch> getPayPeriodPunchList(Badge b, long ts){
                    String b_id = b.getID();
            int id = 0;
            ArrayList<Punch> p_list = new ArrayList<>();
            
              GregorianCalendar ts1 = new GregorianCalendar();
              GregorianCalendar ts2 = new GregorianCalendar();
         
              ts1.setTimeInMillis(ts);
              ts2.setTimeInMillis(ts);
                           // System.out.println(ts);
              ts1.set(Calendar.DAY_OF_WEEK, 1);
             ts1.set(Calendar.HOUR_OF_DAY, 0);
             ts1.set(Calendar.MINUTE, 0);
             ts1.set(Calendar.SECOND, 0);
                            //System.out.println("ts1=" + ts1.getTimeInMillis());
              ts2.set(Calendar.DAY_OF_WEEK, 7);
              ts2.set(Calendar.HOUR_OF_DAY, 23);
              ts2.set(Calendar.MINUTE, 59);
              ts2.set(Calendar.SECOND, 59);
                           // System.out.println("ts2=" + ts2.getTimeInMillis());
              
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                    String query;
                    boolean hasresults;
                    try{ 
                             
                        String url = "jdbc:mysql://localhost/tas";
                        String username = "tasuser";
                        String password = "teamE";


                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        conn = DriverManager.getConnection(url, username, password);

                        query = "SELECT *, UNIX_TIMESTAMP(originaltimestamp)*1000 AS ts\n" +
                                                                    "FROM punch\n" +
                                                                    "WHERE badgeid=\"" + b_id + "\"\n" +
                                                                    "HAVING ts >= " + ts1.getTimeInMillis() + "\n" +
                                                                    "AND ts <= " + ts2.getTimeInMillis() + "\n"+
                                                                    "ORDER BY originaltimestamp;\n";
                        pstSelect = conn.prepareStatement(query);
                        hasresults = pstSelect.execute(); 
                        while(hasresults){
                        resultset = pstSelect.getResultSet();

                            while (resultset.next()){
                                    //clock in time
                                    id = resultset.getInt("id");
                                   // System.out.println("Punch added = " + getPunch(id).printOriginalTimestamp());
                                    p_list.add(getPunch(id));                                
                            }  
                        hasresults = pstSelect.getMoreResults();
                        conn.close( ); 
                        }                           
                    }
                    catch (Exception e){ 
                              System.err.println(e.toString());
                    }
                   
                   finally { 
            
                              if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
                              if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
                              if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
                    }
                    
        return p_list;
          }
          
          public Absenteeism getAbsenteeism(String badgeid, long ts){
                    
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                     Absenteeism absenteeism = null;
                    try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                              
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                   
                             
                              Statement stmt = conn.createStatement( );
                              ResultSet result = stmt.executeQuery("SELECT * FROM absenteeism WHERE badgeid= '"+badgeid+"'");
                              if ( result != null ){
                                        result.next();
                                        //String badgeid = result.getString("badgeid");
                                        Timestamp pay = result.getTimestamp("payperiod");
                                        double percentage = result.getDouble("percentage");
                                        long payperiod = pay.getTime();
                                        absenteeism = new Absenteeism(badgeid,payperiod,percentage);
                                          
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
                    
                    return absenteeism;
          }
          
          public void insertAbsenteeism(Absenteeism absent){
                    Connection conn = null;
                    PreparedStatement pstSelect = null, pstUpdate = null;
                    ResultSet resultset = null;
                    String query;
                    int updateCount = 0;
                   
                  
                  String badgeid = absent.getId();
                  long payperiod = absent.getPayPeriod();
                  double percentage = absent.getPercentage();
                  Timestamp pay = null;
                  
                    
                   try{ 
                             
                              String url = "jdbc:mysql://localhost/tas";
                              String username = "tasuser";
                              String password = "teamE";
                
          
                              Class.forName("com.mysql.jdbc.Driver").newInstance();
                              conn = DriverManager.getConnection(url, username, password);
                           
                               
                              Statement stmt = conn.createStatement( );
                              pay.setTime(payperiod);
                              query = "INSERT INTO absenteeism (badgeid, payperiod, percentage) VALUES (?, ?, ?)";
                              pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                              
                              
                              pstUpdate.setString(1,badgeid);
                              pstUpdate.setTimestamp(2, pay);                        
                             pstUpdate.setDouble(3,percentage);
                               
                                 // Get New Key; Print To Console
                              updateCount = pstUpdate.executeUpdate();
                              if (updateCount > 0) {
            
                                    resultset = pstUpdate.getGeneratedKeys();
                                    

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
          }
          
}
	