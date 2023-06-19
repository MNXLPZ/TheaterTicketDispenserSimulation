/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.ScreenMode;
import Theater.Enumerated.ScreenResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Manuel López Corchado
 */
public class SeatSelectionScreen extends Screen {
    private TheaterAreaState theaterArea;
    private int price=0;

    public TheaterAreaState getTheaterArea() {
        return theaterArea;
    }

    public void setTheaterArea(TheaterAreaState theaterArea) {
        this.theaterArea = theaterArea;
    }
    public SeatSelectionScreen (TheaterAreaState theaterArea, DispenserManager dispenser){
        this.theaterArea=theaterArea;
        this.setScreenMode(ScreenMode.theaterMode);
        ArrayList <String> options = new ArrayList();
        options.add("Comprar entradas");
        this.setOptions(options);
        this.setTitle("Escoja máx 4 asientos del area: ");
        this.setDispenserManager(dispenser);
    }
    @Override
    public ScreenResult seatButtonPressed(DispenserHardware dispenserHardware, char c){
        int numSeats=0;
        boolean exit=false;
        ArrayList<int[]> selectedSeats = new ArrayList();
        while (!exit){
            if (c!='A' & c!='B' & c!=0 &c!='1'){ 
            byte col = (byte)(c & 0xFF);
            byte row = (byte)((c & 0xFF00) >> 8);
            
            int seat[] = {col,row}; 
            boolean addable = true;
            boolean diselectable = false;
            int index=0;
            for(int s[]:selectedSeats){
                ++index;
                int sCol=s[0];
                int sRow=s[1];
                if (col==sCol & row==sRow){
                    addable=false;
                    diselectable=true;
                            break;
                }
            }
            
            if (addable & numSeats<4){
                selectedSeats.add(seat);
                String info=OperationInfo.getInfo();
                OperationInfo.setInfo(info+"§"+ seat[1]+"§"+seat[0]);
                ++numSeats;
                this.getDispenserManager().getDispenser().markSeat(row, col, 1);
                System.out.println(Arrays.toString(OperationInfo.getInfo().split("§")));
                this.price+=Integer.parseInt(OperationInfo.getInfo().split("§")[4].split("€")[0]);
            }else if (diselectable){
                this.getDispenserManager().getDispenser().markSeat(row, col, 2);
                this.price-=Integer.parseInt(OperationInfo.getInfo().split("§")[4].split("€")[0]);
                --numSeats;
                selectedSeats.remove(index-1);
                List<String> newInfo = new ArrayList(Arrays.asList(OperationInfo.getInfo().split("§")));
                newInfo.remove(4+index*2);
                newInfo.remove(4+(index*2)-1);
                OperationInfo.setInfo(String.join("§", newInfo));
                System.out.println(Arrays.toString(OperationInfo.getInfo().split("§")));
            }
            }else if (c=='A' || c==0){
                exit=true;
            }else if (c=='B' & this.price!=0){
                PaymentScreen paymentScreen = new PaymentScreen(this.getDispenserManager(),this.price,selectedSeats);
                this.getDispenserManager().showScreen(paymentScreen);
                c = this.getDispenserManager().getDispenser().waitEvent(30);
                paymentScreen.optionButtonPressed(dispenserHardware, c);
                exit=true;
            }
            if (!exit){
            c = this.getDispenserManager().getDispenser().waitEvent(30);
        }
    }
        return ScreenResult.exitScreen;
    }
    
}
