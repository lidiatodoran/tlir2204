package tlir2204MV.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tlir2204MV.exception.NotAbleToCreateStatisticsException;
import tlir2204MV.exception.NotAbleToCreateTestException;
import tlir2204MV.model.Statistica;

import static org.junit.Assert.assertEquals;

public class AppControllerTestF03 {

    private AppController appController;
    private static final String fisier = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari.txt";
    private static final String fisierEmpty = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\fisierEmpty.txt";

    @Before
    public void init() {
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testValid() {

        appController.loadIntrebariFromFile(fisier);

        try {
            Statistica statistica = appController.getStatistica();
            assertEquals(statistica.getIntrebariDomenii().size(),5);
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!",e.getMessage());
        }
    }

    @Test
    public void testInvalid() {

        appController.loadIntrebariFromFile(fisierEmpty);

        try {
            Statistica statistica = appController.getStatistica();
            assertEquals(statistica.getIntrebariDomenii().size(),0);
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!",e.getMessage());
        }
    }
}