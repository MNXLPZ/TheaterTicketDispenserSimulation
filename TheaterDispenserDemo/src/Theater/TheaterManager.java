/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;


/**
 *
 * @author Manuel López Corchado
 */
public class TheaterManager {
    public void run(){
        DispenserManager dispenser = new DispenserManager();
        WelcomeScreen welcomeScreen = new WelcomeScreen(dispenser);
        while (true){
        dispenser.showScreen(welcomeScreen);
        char c=dispenser.getDispenser().waitEvent(30);
        welcomeScreen.optionButtonPressed(dispenser.getDispenserHardware(), c);
        }   
    }
}
