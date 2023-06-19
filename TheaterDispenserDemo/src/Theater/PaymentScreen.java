/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.ScreenMode;
import Theater.Enumerated.ScreenResult;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.CommunicationException;
import urjc.UrjcBankServer;

/**
 *
 * @author Manuel López Corchado
 */
public class PaymentScreen extends Screen{
    private UrjcBankServer urjcbankServer = new UrjcBankServer();
    private int payment;
    private ArrayList<int[]> seats;
    public PaymentScreen(DispenserManager dispenser, int price, ArrayList<int[]> seats){
            this.seats=seats;
            this.payment=price;
            this.setScreenMode(ScreenMode.messageMode);
            this.setDispenserManager(dispenser);
            this.setTitle("Pantalla de pago"); //translator
            this.setDescription(dispenser.getTranslator().Translate("Por favor, introduzca la tarjeta. Precio a cobrar : ") + price + "€");
    }
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c) {
        boolean success=false;
        if (c=='1'){
            this.getDispenserManager().getDispenserHardware().retainCreditCard(false);
            if (this.urjcbankServer.comunicationAvaiable()){
                try {
                    success=this.urjcbankServer.doOperation(this.getDispenserManager().getDispenserHardware().getCardNumber(), this.payment);
                    if (success){
                    dispenserHardware.printTicket(OperationInfo.getInfo());
                    this.updateTheater();
                    this.getDispenserManager().showScreen(new SuccessScreen());
                    }else{
                        this.getDispenserManager().showScreen(new ErrorScreen());
                    }
                    int value=30;
                    if (!this.getDispenserManager().getDispenserHardware().expelCreditCard())
                        this.getDispenserManager().getDispenserHardware().retainCreditCard(true);
                        value=1;
                    this.getDispenserManager().getDispenser().waitEvent(value);
                } catch (CommunicationException ex) {
                }
            }
        }

    return ScreenResult.exitScreen;
    }
        
    private void updateTheater (){
        ArrayList<int[]> selectedSeats=this.seats;
        String[] operationInfo = OperationInfo.getInfo().split("§");
        String date = operationInfo[2];
        String area = operationInfo[3];
        TheaterState theaterState = new TheaterState(null,null);
        String[] formattedDate = date.split(" / "); 
        theaterState.load(String.join("_", formattedDate));
        TheaterAreaState theaterAreaState = theaterState.getArea(area);
        ArrayList<Seat> areaSeats = (ArrayList<Seat>)theaterAreaState.getSeats().clone();
        for (int[] seat: selectedSeats){
            for (Seat areaSeat: theaterAreaState.getSeats()){
                if (areaSeat.getCol()==seat[0]-1 & areaSeat.getRow()==seat[1]-1){
                    areaSeats.remove(areaSeat);
                    areaSeats.add(new Seat(seat[1]-1,seat[0]-1,1));
                }
            }
        }
        theaterAreaState.setSeats(areaSeats);
        HashMap<String, TheaterAreaState> theaterAreas = theaterState.getAreas();
        theaterAreas.remove(area);
        theaterAreas.put(area,theaterAreaState);
        theaterState.setAreas(theaterAreas);
        theaterState.save();
    }
}
