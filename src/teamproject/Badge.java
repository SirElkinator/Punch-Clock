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
public class Badge {
    
    private String id;
    private String description;
    
      public void setID(String newID){
        this.id = newID;
    }
    public String getID(){
        return id;
    }
    public void setDescrip(String newDescrip){
        this.description = newDescrip;
    }
    public String getDescrip(){
        return description;
    }

    
    public Badge(String id, String description){
        this.id = id;
        this.description = description;
        
    }
    
    @Override
    public String toString(){
           String badge;
           badge = "#"+id +" ("+ description+")";
           return badge;

          
    }
}
