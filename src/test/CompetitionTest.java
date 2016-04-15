package test;

import static org.junit.Assert.*;

import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.time.LocalDate;
import java.util.Set;


public class CompetitionTest {
	
	Inscriptions inscriptionTest = Inscriptions.getInscriptions();
	Competition competitionEquipeTest = inscriptionTest.createCompetition("CompetEquipeTest", null, true,-1);
	Competition competitionSoloTest = inscriptionTest.createCompetition("CompetSoloTest", null, false,-1);
	Personne personneTest = inscriptionTest.createPersonne ("TEST", "test", "tTEST@gmail.com",-1);
	Equipe equipeTest = inscriptionTest.createEquipe("L'EQUIPE TEST",-1);

	@Test
	public void testGetNom() {
		assertEquals("CompetEquipeTest", competitionEquipeTest.getNom());
	}

	@Test
	public void testGetDateCloture() {
		assertEquals(null, competitionEquipeTest.getDateCloture());
	}

	@Test
	public void testEstEnEquipe() {
		assertEquals(true, competitionEquipeTest.estEnEquipe());
	}

	@Test
	public void testSetDateCloture() {
		competitionEquipeTest.setDateCloture(LocalDate.of(2017, 02, 11));
		assertEquals(LocalDate.of(2017, 02, 11), competitionEquipeTest.getDateCloture());
	}
	
	@Test
	public void testInscriptionEstOuverte(){
		LocalDate dateTest = LocalDate.of(2018,05,05);
		LocalDate dateTest2 = LocalDate.of(2012,05,05);
		
		competitionEquipeTest.setDateCloture(dateTest);
		competitionSoloTest.setDateCloture(dateTest2);
		assertTrue(competitionEquipeTest.inscriptionsOuvertes() && !competitionSoloTest.inscriptionsOuvertes());
	}
	
	@Test
	public void testGetCandidats() {
		competitionEquipeTest.add(equipeTest);
		competitionSoloTest.add(personneTest);
		Set<Candidat>setCandidatTest = competitionEquipeTest.getCandidats();
		Set<Candidat>setCandidatTest2 = competitionSoloTest.getCandidats();
		assertTrue(setCandidatTest.contains(equipeTest) && setCandidatTest2.contains(personneTest));
	}

	@Test
	public void testAddPersonne() {
		competitionSoloTest.add(personneTest);
		Set<Candidat>setCandidatTest = competitionSoloTest.getCandidats();
		assertTrue(setCandidatTest.contains(personneTest));
	}

	@Test
	public void testAddEquipe() {
		competitionEquipeTest.add(equipeTest);
		Set<Candidat>setCandidatTest = competitionEquipeTest.getCandidats();
		assertTrue(setCandidatTest.contains(equipeTest));
	}

	@Test
	public void testRemove() {
		Equipe equipeTest2 = inscriptionTest.createEquipe("",-1);
		competitionEquipeTest.add(equipeTest);
		competitionEquipeTest.add(equipeTest2);
		Set<Candidat>setCandidatTest = competitionEquipeTest.getCandidats();
		competitionEquipeTest.remove(equipeTest2);
		assertFalse(setCandidatTest.contains(equipeTest2));
	}

	@Test
	public void testDelete() {
		Set<Competition> setCompetitionTest = inscriptionTest.getCompetitions();
		competitionEquipeTest.delete();
		assertFalse(setCompetitionTest.contains(competitionEquipeTest));
	}

	@Test
	public void testCompareTo() {
		Competition competitionEquipeTest2 = inscriptionTest.createCompetition("CompetEquipeTest", null, true,-1);
		assertTrue(competitionEquipeTest.compareTo(competitionEquipeTest2)== 0);
		
	}


}
