/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package TheaterTest;

import Theater.Translator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manuel
 */
public class TranslatorTest {
    private Translator translator = new Translator();
    
    //@Before
    //public void testInit(){
    //    translator.init();
    //}
    @Test
    public void testTranslate(){
        this.translator.setActiveLanguage("Ingles");
        translator.Translate("Hola");
    }
    @Test
    public void testGetLanguages(){
        this.translator.getLanguages();
    }
    
}
