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
public class Badge {
    
    private String id;
    private String description;
    
      public void setID(){
        this.id = "021890C0";
    }
    public String getID(){
        return id;
    }
    public void setDescrip(){
        this.description = "Chapell, George R";
    }
    public String getDescrip(){
        return description;
    }

    
    public Badge(String id, String description){
        this.id = id;
        this.description = description;
        
    }
          
}
