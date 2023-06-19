/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Manuel López Corchado
 */
public class TheaterState implements Serializable{
    private HashMap<String,TheaterAreaState> areas = new HashMap();
    private String date; 
    private int dayOfWeek;
    
    public TheaterState(Theater theater, String date){ 
        if (theater!=null & date!=null){
        String[] splittedDate=date.split("_");
        this.date=date;
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1])-1,Integer.parseInt(splittedDate[2]));
        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        HashMap<String,TheaterArea> model = theater.getAreas();
        for (String areaName:model.keySet()){
            TheaterAreaState area = new TheaterAreaState(model.get(areaName));
            if (this.dayOfWeek==Calendar.SATURDAY || this.dayOfWeek==Calendar.SUNDAY){
                int price = area.getPrice();
                area.setPrice(price*theater.getPlus());
            }
            this.areas.put(areaName, area); 
        }
        }
    }
    public String getDate(){
        return this.date;
    }
    public TheaterAreaState getArea(String name){
        return this.areas.get(name);
    }
    public ArrayList<String> getAreaNames(){
        return new ArrayList(this.areas.keySet());
    }
    public HashMap<String,TheaterAreaState> getAreas(){
        return this.areas;
    }
    public void setAreas(HashMap<String,TheaterAreaState> areas){
        this.areas=areas;
    }
    public void save(){ 
        try {
            FileOutputStream fileOut = new FileOutputStream("../"+this.date+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void load(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream("../"+fileName+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            TheaterState instance = (TheaterState) in.readObject();
            this.areas=instance.getAreas();
            this.date=instance.getDate();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
