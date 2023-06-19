/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.ScreenMode;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author Manuel López Corchado
 */
public class DispenserManager {

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public TheaterTicketDispenser getDispenser() {
        return dispenser;
    }

    public void setDispenser(TheaterTicketDispenser dispenser) {
        this.dispenser = dispenser;
    }
    
    private Translator translator = new Translator();
    private TheaterTicketDispenser dispenser = new TheaterTicketDispenser();
    private String goBackPhrase = "Cancelar";
    private DispenserHardware dispenserHardware = new DispenserHardware(this.dispenser);

    public DispenserHardware getDispenserHardware() {
        return dispenserHardware;
    }

    public void setDispenserHardware(DispenserHardware dispenserHardware) {
        this.dispenserHardware = dispenserHardware;
    }
    
    public void showScreen(Screen screen){
        ScreenMode mode = screen.getScreenMode();
        if (null!=mode)switch (mode) {
            case messageMode:
                dispenser.setMessageMode();
                dispenser.setTitle(translator.Translate(screen.getTitle()));
                if (screen.getClass()==PaymentScreen.class)
                    dispenser.setDescription(screen.getDescription());
                else
                dispenser.setDescription(translator.Translate(screen.getDescription()));
                dispenser.setOption(0,translator.Translate(this.goBackPhrase));
                dispenser.setOption(1,null);
                break;
            case optionsMode:
                dispenser.setMenuMode();
                dispenser.setTitle(translator.Translate(screen.getTitle()));
                dispenser.setImage(screen.getImage());
                dispenser.setDescription(translator.Translate(screen.getDescription()));
                if (screen.getClass()==DateSelectionScreen.class || screen.getClass()==AreaSelectionScreen.class){
                for (int i=0; i<screen.getOptions().size(); ++i){
                    dispenser.setOption(i,screen.getOption(i));
                }
                if (screen.getOptions().size()<4){
                    for(int i=screen.getOptions().size();i<5;i++)
                      dispenser.setOption(i, null);
                }
                dispenser.setOption(5,translator.Translate(this.goBackPhrase));
                }else{
                for (int i=0; i<screen.getOptions().size(); ++i){
                    dispenser.setOption(i,translator.Translate(screen.getOption(i)));
                }
                if (screen.getOptions().size()<4){
                    for(int i=screen.getOptions().size();i<5;i++)
                      dispenser.setOption(i, null);
                }
                if (screen.getClass()!=WelcomeScreen.class)
                dispenser.setOption(5,translator.Translate(this.goBackPhrase));
                else
                dispenser.setOption(5,null);
                }
                break;
            case theaterMode:
                SeatSelectionScreen castedScreen = (SeatSelectionScreen) screen;
                TheaterAreaState areaState = castedScreen.getTheaterArea();
                this.dispenser.setTitle(translator.Translate(castedScreen.getTitle())+translator.Translate(areaState.getName()));
                this.drawArea(areaState);
                this.dispenser.setOption(0, translator.Translate(this.goBackPhrase));
                this.dispenser.setOption(1, translator.Translate(castedScreen.getOption(0)));
                break;
            default:
                break;
        }
        
    }
    private void drawArea(TheaterAreaState theaterAreaState){
        this.dispenser.setTheaterMode(theaterAreaState.getRows(), theaterAreaState.getCols());//falta estado ocupado
        for (Seat seat:theaterAreaState.getSeats()){
            if (seat.getState()==0){
                dispenser.markSeat(seat.getRow()+1, seat.getCol()+1, 0);
            }else if (seat.getState()==1){
                dispenser.markSeat(seat.getRow()+1, seat.getCol()+1, 1);
            }
        }
    }

}
