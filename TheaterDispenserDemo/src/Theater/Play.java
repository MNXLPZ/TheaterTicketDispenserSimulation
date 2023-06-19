/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Manuel López Corchado
 */
public class Play {
    private String title;
    private String image;
    private String hour;
    private String description;
    
    public String getTitle(){
        return this.title;
    }
    
    public String getImage(){
        return this.image;
    }
    
    public String getHour(){
        return this.hour;
    }
    
    public String getDescription(){
        return this.description;
    }
    private void read(){
        try (FileReader fr = new FileReader("../Play.txt");
             BufferedReader br = new BufferedReader(fr)) {
                this.title = br.readLine();
                this.image = br.readLine();
                this.hour = br.readLine();
                this.description = br.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public Play(){
        this.read();
    }
}
