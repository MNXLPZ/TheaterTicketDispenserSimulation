/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Manuel López Corchado
 */
public class Theater {
 
    private HashMap<String,TheaterArea> areas = new HashMap();
    private int plus;
    
    public HashMap<String, TheaterArea> getAreas() {
        return this.areas;
    }

    public void setAreas(HashMap<String, TheaterArea> areas) {
        this.areas = areas;
    }
    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }
    
    public Theater(){
        String line = null;
        try (FileReader fr = new FileReader("../Theater.txt");
             BufferedReader br = new BufferedReader(fr)) {
                line = br.readLine();
                this.plus = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        String[] areaNames = line.split(" ");
        
        for (String area: areaNames){
            this.areas.put(area, new TheaterArea("../"+area+".txt"));
        }
    }
    
}
