/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.ScreenMode;
import Theater.Enumerated.ScreenResult;

/**
 *
 * @author Manuel López Corchado
 */
public class SuccessScreen extends Screen{
    public SuccessScreen(){
        this.setScreenMode(ScreenMode.messageMode);
        this.setTitle("Pago validado");
        this.setDescription("Ya puede retirar la tarjeta de crédito");
    }
    
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c) {
        return ScreenResult.exitScreen; 
    }
}