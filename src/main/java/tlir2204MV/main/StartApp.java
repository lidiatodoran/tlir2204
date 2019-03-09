package tlir2204MV.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import tlir2204MV.exception.DuplicateIntrebareException;
import tlir2204MV.exception.InputValidationFailedException;
import tlir2204MV.exception.NotAbleToCreateTestException;
import tlir2204MV.model.Intrebare;
import tlir2204MV.model.Statistica;

import tlir2204MV.controller.AppController;
import tlir2204MV.exception.NotAbleToCreateStatisticsException;
import tlir2204MV.model.Test;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "F:\\Semestrul 2\\Validare si verificare\\Laborator 1\\tlir2204\\src\\main\\java\\tlir2204MV\\intrebari.txt";

	public static void main(String[] args) throws IOException {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		AppController appController = new AppController();

		boolean activ = true;
		String optiune = null;

		Scanner scanner = new Scanner(System.in);

		while(activ){

			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");

			optiune = console.readLine();

			switch(optiune){
				case "1" : {
				    appController.loadIntrebariFromFile(file);

					System.out.println("Enunt: ");
					String enunt = scanner.next();
					System.out.println("Varianta 1: ");
					String varianta1 = scanner.next();
					System.out.println("Varianta 2: ");
					String varianta2 = scanner.next();
					System.out.println("Varianta 3: ");
					String varianta3 = scanner.next();
					System.out.println("Varianta corecta: ");
					String variantaCorecta = scanner.next();
					System.out.println("Domeniu: ");
					String domeniu = scanner.next();

					try {
						Intrebare intrebare = new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
						appController.addNewIntrebare(intrebare, file);
					} catch (InputValidationFailedException | DuplicateIntrebareException e) {
						System.out.println(e.getMessage());
					}

					break;
                }
				case "2" : {
                    appController.loadIntrebariFromFile(file);

					try {
						Test test = appController.createNewTest();
						System.out.println("TEST");
						for (Intrebare intrebare : test.getIntrebari()) {
	                        System.out.println(intrebare.getEnunt() + " " + intrebare.getVarianta1() + " " + intrebare.getVarianta2() + " " + intrebare.getVarianta3());
						}

					} catch (NotAbleToCreateTestException e) {
						System.out.println(e.getMessage());
					}

					break;
				}
				case "3" : {
                    appController.loadIntrebariFromFile(file);
                    Statistica statistica;
                    try {
                        statistica = appController.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
				case "4" :
					activ = false;
					break;
				default:
					break;
			}
		}

	}

}
