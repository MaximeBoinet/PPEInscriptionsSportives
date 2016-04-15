package test;

import static org.junit.Assert.*;

import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.util.Set;

public class CandidatTest {
	Inscriptions inscriptionTest = Inscriptions.getInscriptions();
	Equipe equipeTest = inscriptionTest.createEquipe("L'EQUIPE TEST",-1);
	Personne personneTest = inscriptionTest.createPersonne("TEST", "test","tTEST@gmail.com",-1);
	Competition competitionSoloTest = inscriptionTest.createCompetition("CompetSoloTest", null, false,-1);
	
	
	@Test
	public void testGetNom() {
		assertTrue(equipeTest.getNom() == "L'EQUIPE TEST" && personneTest.getNom() == "TEST");
	}

	@Test
	public void testSetNom() {
		equipeTest.setNom("");
		personneTest.setNom("");
		assertTrue(equipeTest.getNom() == "" && personneTest.getNom() == "");
	}

	@Test
	public void testGetCompetitions() {
		competitionSoloTest.add(personneTest);
		Set<Competition>setCompetitionTest = personneTest.getCompetitions();
		assertTrue(setCompetitionTest.contains(competitionSoloTest));
	}

	@Test
	public void testAdd() {
		competitionSoloTest.add(personneTest);
		Set<Candidat>setCandidatTest = competitionSoloTest.getCandidats();
		assertTrue(setCandidatTest.contains(personneTest));
	}

	@Test
	public void testRemove() {
		competitionSoloTest.add(personneTest);
		Set<Candidat>setCandidatTest = competitionSoloTest.getCandidats();
		competitionSoloTest.remove(personneTest);
		assertFalse(setCandidatTest.contains(personneTest));
	}

	@Test
	public void testDelete() {
		Set<Candidat>setCandidatTest = inscriptionTest.getCandidats();
		personneTest.delete();
		assertFalse(setCandidatTest.contains(personneTest));
	}

	@Test
	public void testCompareTo() {
		Personne personneTest2 = inscriptionTest.createPersonne("TEST", "","TEST@gmail.com",-1);
		assertTrue(personneTest.compareTo(personneTest2) == 0);
	}

}
