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
public class WelcomeScreen extends Screen{
    private Play play = new Play();
    
    /**
     *
     * @param dispenser
     */
    public WelcomeScreen(DispenserManager dispenser){
        this.setDispenserManager(dispenser);

        this.setDescription(this.play.getDescription());
        this.setImage(this.play.getImage());
        this.setTitle(this.play.getTitle());
        this.setScreenMode(ScreenMode.optionsMode);
        ArrayList<String> optionsArray = new ArrayList();
        optionsArray.add("Cambiar idioma");
        optionsArray.add("Seleccionar día");
        this.setOptions(optionsArray);
    }
    
    
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c){
    if (c=='A'){
        TranslatorScreen translatorScreen = new TranslatorScreen(this.getDispenserManager());
        this.getDispenserManager().showScreen(translatorScreen);
        c = this.getDispenserManager().getDispenser().waitEvent(30);
        translatorScreen.optionButtonPressed(dispenserHardware, c);
    } else if (c=='B'){
        DateSelectionScreen dateSelectionScreen = new DateSelectionScreen(this.getDispenserManager());
        this.getDispenserManager().showScreen(dateSelectionScreen);
        c = this.getDispenserManager().getDispenser().waitEvent(30);
        OperationInfo.setInfo(play.getTitle()+"§"+play.getHour());
        dateSelectionScreen.optionButtonPressed(dispenserHardware, c);
    }
    
    return ScreenResult.exitScreen;
    }
}
