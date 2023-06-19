/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.util.ArrayList;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author Manuel López Corchado
 */
public class DispenserHardware {
    private TheaterTicketDispenser dispenser;

    public TheaterTicketDispenser getTheaterTicketDispenser() {
        return dispenser;
    }

    public void setTheaterTicketDispenser(TheaterTicketDispenser theaterTicketDispenser) {
        this.dispenser = theaterTicketDispenser;
    }
    
    public void printTicket(String info){
        String[] splittedInfo = info.split("§");
        ArrayList<String> ticketInfo = new ArrayList();
        ticketInfo.add("Obra: "+splittedInfo[0]);
        ticketInfo.add("Fecha y hora: "+splittedInfo[2]+"  a las "+splittedInfo[1]);
        ticketInfo.add("Zona: " + splittedInfo[3]);
        
        String payment = "Precio cobrado: " + splittedInfo[4];
        
        for(int i=5; i<splittedInfo.length; i+=2){
            ticketInfo.add("Asiento: fila "+splittedInfo[i]+" butaca "+splittedInfo[i+1]);
            ticketInfo.add(payment);
            dispenser.print(ticketInfo);
            ticketInfo.remove(3);
            ticketInfo.remove(3);
        }
    }
    public long getCardNumber(){
        return dispenser.getCardNumber();
    }
    public boolean expelCreditCard(){
        return dispenser.expelCreditCard(30);
    }
    public void retainCreditCard(boolean b){
        dispenser.retainCreditCard(b);
    }
    public DispenserHardware(TheaterTicketDispenser dispenser){
        this.dispenser=dispenser;
    }
}
