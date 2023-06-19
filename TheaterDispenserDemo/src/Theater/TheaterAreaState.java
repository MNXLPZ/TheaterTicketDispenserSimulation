/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.SeatState;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Manuel López Corchado
 */
class TheaterAreaState implements Serializable{
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
    
    public TheaterAreaState(TheaterArea theaterArea){
        this.rows=theaterArea.getRows();
        this.cols=theaterArea.getCols();
        this.seats=theaterArea.getSeats();
        this.price=theaterArea.getPrice();
        this.name=theaterArea.getName();
    }
    public SeatState getSeatState(int row, int col){
        for (Seat seat:this.seats){
            if (seat.getCol()==col & seat.getRow()==row){
                if (seat.getState()==2){
                    return SeatState.free;
                }else{
                    return SeatState.occupied;
                }
            }
        }
        return SeatState.unexisting;
    }
    public void modifySeat(int row, int col, SeatState state){
        int intState = 0;
        if (state==SeatState.free){
            intState=2;
        }else if (state==SeatState.occupied){
            intState=1;
        }
        for (Seat seat:this.seats){
            if (seat.getCol()==col & seat.getRow()==row){
                this.seats.remove(seat);
                this.seats.add(new Seat(row,col,intState));
                break;
            }
        }    
    }   
}
