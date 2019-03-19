package tlir2204MV.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tlir2204MV.exception.DuplicateIntrebareException;
import tlir2204MV.exception.InputValidationFailedException;
import tlir2204MV.model.Intrebare;

import static org.junit.Assert.*;

public class TestIntrebare {

    private AppController appController;
    private static final String file = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari.txt";

    @Before
    public void init() {
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test2() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test3() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Enunt test?", "a) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test4() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("A", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test5() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("A?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }
}