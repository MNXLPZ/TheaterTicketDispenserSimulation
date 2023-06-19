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
public class TranslatorScreen extends Screen{
    public TranslatorScreen (DispenserManager dispenser){
        this.setDispenserManager(dispenser);
        ArrayList<String> languages = this.getDispenserManager().getTranslator().getLanguages();
        languages.remove(this.getDispenserManager().getTranslator().getActiveLanguage());
        this.setOptions(languages);
        this.setScreenMode(ScreenMode.optionsMode);
        this.setTitle("Selección de idioma");
        this.setImage(" ");
        this.setDescription(" ");
    }
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c){
        if (c=='A'){
            this.getDispenserManager().getTranslator().setActiveLanguage(this.getOption(0));
        }else if(c=='B'){
            this.getDispenserManager().getTranslator().setActiveLanguage(this.getOption(1));
        }else if(c=='C'){
            this.getDispenserManager().getTranslator().setActiveLanguage(this.getOption(2));
        }
        return ScreenResult.exitScreen;
    }
}
