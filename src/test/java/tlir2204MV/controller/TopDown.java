package tlir2204MV.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tlir2204MV.exception.DuplicateIntrebareException;
import tlir2204MV.exception.InputValidationFailedException;
import tlir2204MV.exception.NotAbleToCreateStatisticsException;
import tlir2204MV.exception.NotAbleToCreateTestException;
import tlir2204MV.model.Intrebare;
import tlir2204MV.model.Statistica;

import static org.junit.Assert.assertEquals;

public class TopDown {

    private AppController appController;
    private static final String file = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari.txt";
    private static final String fisier4Domenii = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari4domenii.txt";
    private static final String fisier = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari.txt";
    private static final String fisierTest = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\fisierTest.txt";

    @Before
    public void init() {
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void functionalitate1() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void functionalitate2() {

        appController.loadIntrebariFromFile(fisier4Domenii);

        try {
            tlir2204MV.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",e.getMessage());
        }
    }

    @Test
    public void functionalitate3() {

        appController.loadIntrebariFromFile(fisier);
        try {
            Statistica statistica = appController.getStatistica();
            assertEquals(statistica.getIntrebariDomenii().size(),6);
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!",e.getMessage());
        }
    }

    @Test
    public void test1() throws InputValidationFailedException, DuplicateIntrebareException {

        //P->A A valid
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        Intrebare intrebare2 = new Intrebare("Enunt test2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu2");
        Intrebare intrebare3 = new Intrebare("Enunt test3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu3");
        appController.addNewIntrebare(intrebare, fisierTest);
        appController.addNewIntrebare(intrebare2, fisierTest);
        appController.addNewIntrebare(intrebare3, fisierTest);
        assertEquals(3, appController.getIntrebari().size());
    }

    @Test
    public void test2() throws InputValidationFailedException, DuplicateIntrebareException {

        //P->A->B A valid, B invalid
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        Intrebare intrebare2 = new Intrebare("Enunt test2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu2");
        Intrebare intrebare3 = new Intrebare("Enunt test3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu3");
        appController.addNewIntrebare(intrebare, fisierTest);
        appController.addNewIntrebare(intrebare2, fisierTest);
        appController.addNewIntrebare(intrebare3, fisierTest);
        assertEquals(3, appController.getIntrebari().size());

        appController.loadIntrebariFromFile(fisierTest);
        try {
            tlir2204MV.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",e.getMessage());
        }
    }

    @Test
    public void test3() throws InputValidationFailedException, DuplicateIntrebareException {

        //P->A->B->C A valid, B invalid, C valid
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        Intrebare intrebare2 = new Intrebare("Enunt test2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu2");
        Intrebare intrebare3 = new Intrebare("Enunt test3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu3");
        appController.addNewIntrebare(intrebare, fisierTest);
        appController.addNewIntrebare(intrebare2, fisierTest);
        appController.addNewIntrebare(intrebare3, fisierTest);
        assertEquals(3, appController.getIntrebari().size());

        appController.loadIntrebariFromFile(fisierTest);
        try {
            tlir2204MV.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",e.getMessage());
        }

        appController.loadIntrebariFromFile(fisierTest);
        try {
            Statistica statistica = appController.getStatistica();
            assertEquals(statistica.getIntrebariDomenii().size(),3);
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!",e.getMessage());
        }
    }
}