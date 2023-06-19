/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Manuel López Corchado
 * This class provides the predefined structures of the Theatre
 */
public class TheaterArea {

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private int rows;
    private int cols;
    private ArrayList<Seat> seats=new ArrayList();
    private int price;
    private String name;
    
    public TheaterArea(String fileName){ //meter filas y columnas directamente en el archivo?
        String line;
        try (FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr)) {
            int rowCount=0;
            int colCount=0;
            this.name=br.readLine();
            this.price=Integer.parseInt(br.readLine());
            while((line = br.readLine()) != null){
                char[] seatArray =line.toCharArray();
                colCount=0;
                for (char c:seatArray){
                    if (c=='*'){
                        this.seats.add(new Seat(rowCount, colCount, 2));
                    }else{
                        this.seats.add(new Seat(rowCount, colCount, 0));
                    }
                    ++colCount;
                }
             ++rowCount;  
            }
            this.rows=rowCount;
            this.cols=colCount;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    
}
