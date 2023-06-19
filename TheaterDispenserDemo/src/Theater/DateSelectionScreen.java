/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import Theater.Enumerated.ScreenMode;
import Theater.Enumerated.ScreenResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author Manuel López Corchado
 */
public class DateSelectionScreen extends Screen{

public DateSelectionScreen( DispenserManager dispenser){
    Theater theater = new Theater();
    this.setTitle("Seleccione una fecha");
    this.setDescription(" ");
    this.setImage(" ");
    this.setScreenMode(ScreenMode.optionsMode);
    this.setDispenserManager(dispenser);
    
    this.uploadDays(theater);
}
private void uploadDays(Theater theater){ 
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(formatter); 
        String[] dateTime = formattedDate.split(" ");
        String onlyDate = dateTime[0];
        ArrayList<String> options= new ArrayList();
        String[] splittedDate =onlyDate.split("-");
        int today = Integer.parseInt(splittedDate[2]);
        Calendar cal = Calendar.getInstance();

        for (int i=0;i<4;++i){
            cal.set(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1])-1, today+i);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day != Calendar.MONDAY){
            splittedDate[2]=String.valueOf(i+today);
            String jointDate=String.join("_", splittedDate);
        try{
           FileInputStream fileIn = new FileInputStream("../"+jointDate+".ser");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           in.close();
           fileIn.close();
        }catch(Exception e){
            try {
            FileOutputStream fileOut = new FileOutputStream("../"+jointDate+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            TheaterState instance= new TheaterState(theater,jointDate);
            out.writeObject(instance);
            out.close();
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        options.add(String.join(" / ", jointDate.split("_")));
            }
        }
        this.setOptions(options);

        
}
@Override
    public ScreenResult optionButtonPressed(DispenserHardware dispenserHardware, char c){
        String chosenDate = null;
        boolean jump = false;
        if (c=='A'){
            chosenDate=this.getOption(0);
            jump=true;
        }else if (c=='B'){
            chosenDate=this.getOption(1);
            jump=true;
        }else if (c=='C'){
            chosenDate=this.getOption(2);
            jump=true;
        }else if (c=='D'){
            chosenDate=this.getOption(3);
            jump=true;
        }
        if (jump){
        AreaSelectionScreen areaSelectionScreen = new AreaSelectionScreen(String.join("_", chosenDate.split(" / ")),this.getDispenserManager());
        this.getDispenserManager().showScreen(areaSelectionScreen);
        c = this.getDispenserManager().getDispenser().waitEvent(30);
        String info=OperationInfo.getInfo();
        OperationInfo.setInfo(info+"§"+ chosenDate);
        areaSelectionScreen.optionButtonPressed(dispenserHardware, c);
        
        }
        return ScreenResult.exitScreen;
    }
        
}

