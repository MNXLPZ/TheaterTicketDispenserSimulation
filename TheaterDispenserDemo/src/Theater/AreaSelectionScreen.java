/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.ScreenMode;
import Theater.Enumerated.ScreenResult;
import java.util.ArrayList;

/**
 *
 * @author Manuel López Corchado
 */
public class AreaSelectionScreen extends Screen{
    private TheaterState selectedDate = new TheaterState(null,null);
    private ArrayList<String> defaultLanguageOptions = new ArrayList();
    public AreaSelectionScreen (String selectedDate, DispenserManager dispenser){
        this.selectedDate.load(selectedDate);
        this.setDescription(" ");
        this.setTitle("Seleccione un área del teatro");
        this.setImage(" "); 
        this.setScreenMode(ScreenMode.optionsMode);
        ArrayList<String> options = new ArrayList();
        this.setDispenserManager(dispenser);
        for (String areaName:this.selectedDate.getAreaNames()){
            this.defaultLanguageOptions.add(areaName + " " + this.selectedDate.getArea(areaName).getPrice() + "€");
            options.add(dispenser.getTranslator().Translate(areaName) + " " + this.selectedDate.getArea(areaName).getPrice() + "€");
        }
        this.setOptions(options);
    }
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c){
        String chosenOption = null;
        boolean jump = false;
        if (c=='A'){
            chosenOption=this.defaultLanguageOptions.get(0);
            jump=true;
        }else if (c=='B'){
            chosenOption=this.defaultLanguageOptions.get(1);
            jump=true;
        }else if (c=='C'){
            chosenOption=this.defaultLanguageOptions.get(2);
            jump=true;
        }else if (c=='D'){
            chosenOption=this.defaultLanguageOptions.get(3);
            jump=true;
        }
        if (jump){
        String chosenArea = chosenOption.split(" ")[0];
        SeatSelectionScreen seatSelectionScreen = new SeatSelectionScreen(this.selectedDate.getArea(chosenArea), this.getDispenserManager());
        this.getDispenserManager().showScreen(seatSelectionScreen);
        c = this.getDispenserManager().getDispenser().waitEvent(30);
        String info=OperationInfo.getInfo();
        OperationInfo.setInfo(info+"§"+ chosenArea+"§"+chosenOption.split(" ")[1]);
        seatSelectionScreen.seatButtonPressed(dispenserHardware, c);

        }
    return ScreenResult.exitScreen;
    }
}
