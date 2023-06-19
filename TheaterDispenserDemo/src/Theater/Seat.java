/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.io.Serializable;

/**
 *
 * @author Manuel López Corchado
 */
public class Seat implements Serializable{

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public Seat(int row, int col, int state){
        this.col=col;
        this.row=row;
        this.state=state;
    }
    private int row;
    private int col;
    private int state;
}
