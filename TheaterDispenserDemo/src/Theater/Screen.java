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
public class Screen {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ScreenMode getScreenMode() {
        return screenMode;
    }

    public void setScreenMode(ScreenMode screenMode) {
        this.screenMode = screenMode;
    }
    
    public String getOption(int index){
        return this.options.get(index);
    }
    public DispenserManager getDispenserManager() {
        return dispenserManager;
    }

    public void setDispenserManager(DispenserManager dispenserManager) {
        this.dispenserManager = dispenserManager;
    }
    
    private String title;
    private String description;
    private String image;
    private ArrayList<String> options;
    private ScreenMode screenMode;
    private DispenserManager dispenserManager;
    
    /**
     *
     * @param dispenserHardware
     * @param c
     * @return
     */
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c){
    return ScreenResult.continueInScreen;
    }
    
    /**
     *
     * @param dispenserHardware
     * @param c
     * @return
     */
    public ScreenResult seatButtonPressed(DispenserHardware dispenserHardware, char c){
    return ScreenResult.continueInScreen;
    }
    
    /**
     *
     * @param dispenserHardware
     * @param c
     * @return
     */
    public ScreenResult creditCardDetected(DispenserHardware dispenserHardware, char c){
    return ScreenResult.continueInScreen;
    }
 
}
