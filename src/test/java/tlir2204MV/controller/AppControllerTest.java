package tlir2204MV.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tlir2204MV.exception.NotAbleToCreateTestException;

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
            appController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void verificaNrDomenii() {

        appController.loadIntrebariFromFile(fisier4Domenii);
        try {
            appController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void creeazaTest() {

        appController.loadIntrebariFromFile(fisier);
        try {
            appController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }
}