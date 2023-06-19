/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Theater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Manuel López Corchado
 */
public class Translator {
    private String activeLanguage = new String();
    private HashMap<String,ArrayList<String>> languages = new HashMap();
    private String defaultLanguage = "Castellano";
    
    public Translator(){
        this.activeLanguage=this.defaultLanguage;
        String line = null;
        try (FileReader fr = new FileReader("../Languages.txt");
             BufferedReader br = new BufferedReader(fr)) {
                line = br.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        String[] avaiableLanguages = line.split("§");
        for (int i=0; i<avaiableLanguages.length; ++i){
            String messages = null;
            try (FileReader fr = new FileReader("../"+avaiableLanguages[i]+".txt");
            BufferedReader br = new BufferedReader(fr)) {
                messages = br.readLine();
            } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            }
            ArrayList<String> aux = new ArrayList();
            aux.addAll(Arrays.asList(messages.split("§")));//Cambiar regex
            this.languages.put(avaiableLanguages[i], aux);
        }
    }
    
    /**
     *
     * @param word
     * @return
     */
    public String Translate(String word){
        if (this.defaultLanguage.equals(this.activeLanguage)){
            return word;
        }else{
            ArrayList<String> words = this.languages.get(this.defaultLanguage);
            int index = words.indexOf(word);
            ArrayList<String> wordsToTranslate = this.languages.get(this.activeLanguage);
            String translatedWord = wordsToTranslate.get(index);
            return translatedWord;
        }
    }
    
    public void setActiveLanguage(String language){
        this.activeLanguage=language;
    }
    public String getActiveLanguage(){
        return this.activeLanguage;
    }
    
    public ArrayList<String> getLanguages(){
        return new ArrayList<>((Set<String>) languages.keySet());
    }
}
