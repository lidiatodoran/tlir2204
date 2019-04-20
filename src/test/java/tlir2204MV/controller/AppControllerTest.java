package tlir2204MV.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tlir2204MV.exception.NotAbleToCreateTestException;
import tlir2204MV.model.Intrebare;

import java.util.List;

import static org.junit.Assert.*;

public class AppControllerTest {

    private AppController appController;
    private static final String fisier4 = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari4.txt";
    private static final String fisier4Domenii = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari4domenii.txt";
    private static final String fisier = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari.txt";

    @Before
    public void init() {
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void verificaNrIntrebari() {

        appController.loadIntrebariFromFile(fisier4);


        try {
            tlir2204MV.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente intrebari pentru crearea unui test!(5)",e.getMessage());
        }
    }

    @Test
    public void verificaNrDomenii() {

        appController.loadIntrebariFromFile(fisier4Domenii);

        try {
            tlir2204MV.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",e.getMessage());
        }
    }

    @Test
    public void creeazaTest() {

        appController.loadIntrebariFromFile(fisier);
        try {
            tlir2204MV.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }
}