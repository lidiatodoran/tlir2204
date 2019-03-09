package tlir2204MV.controller;

import java.util.LinkedList;
import java.util.List;

import tlir2204MV.model.Intrebare;
import tlir2204MV.model.Statistica;
import tlir2204MV.model.Test;
import tlir2204MV.repository.IntrebariRepository;
import tlir2204MV.exception.DuplicateIntrebareException;
import tlir2204MV.exception.NotAbleToCreateStatisticsException;
import tlir2204MV.exception.NotAbleToCreateTestException;

public class AppController {
	
	private IntrebariRepository intrebariRepository;
	
	public AppController() {
		intrebariRepository = new IntrebariRepository();
	}
	
	public Intrebare addNewIntrebare(Intrebare intrebare, String file) throws DuplicateIntrebareException{
		
		intrebariRepository.addIntrebare(intrebare, file);
		
		return intrebare;
	}
	
	public boolean exists(Intrebare intrebare){
		return intrebariRepository.exists(intrebare);
	}
	
	public Test createNewTest() throws NotAbleToCreateTestException{

		if(intrebariRepository.getIntrebari().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		
		if(intrebariRepository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");
		
		List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
		List<String> domenii = new LinkedList<String>();
		Intrebare intrebare;
		Test test = new Test();
		
		while(testIntrebari.size() != 5){
			intrebare = intrebariRepository.pickRandomIntrebare();
			
			if(!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())){
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
			
		}
		
		test.setIntrebari(testIntrebari);
		return test;
	}
	
	public void loadIntrebariFromFile(String f){
		intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile(f));
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
		
		if(intrebariRepository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistica statistica = new Statistica();
		for(String domeniu : intrebariRepository.getDistinctDomains()){
			statistica.add(domeniu, intrebariRepository.getNumberOfIntrebariByDomain(domeniu));
		}
		
		return statistica;
	}

	public List<Intrebare> getIntrebari() {
		return intrebariRepository.getIntrebari();
	}
}
