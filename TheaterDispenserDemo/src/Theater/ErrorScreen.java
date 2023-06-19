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
public class ErrorScreen extends Screen{
    public ErrorScreen(){
        this.setScreenMode(ScreenMode.messageMode);
        this.setTitle("Error de pago");
        this.setDescription("Ha ocurrido un error en el procesamiento de pago, inténtelo de nuevo más tarde");
    }
    
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c) {
        return ScreenResult.exitScreen; 
    }
}
