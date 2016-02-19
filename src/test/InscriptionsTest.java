package inscriptions;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Set;

public class InscriptionsTest {

	Inscriptions inscriptionTest = Inscriptions.getInscriptions();
	Competition competitionEquipeTest = inscriptionTest.createCompetition("CompetEquipeTest", null, true);
	Competition competitionSoloTest = inscriptionTest.createCompetition("CompetSoloTest", null, false);
	Equipe equipeTest = inscriptionTest.createEquipe("EquipeTest");
	Personne personneTest = inscriptionTest.createPersonne("TEST", "test", "tTEST@gmail.com");
	
	@Test
	public void testGetCompetitions() {
		Set<Competition> setCompetitionTest = inscriptionTest.getCompetitions();
		assertFalse(setCompetitionTest.isEmpty());
		
	}

	@Test
	public void testGetCandidats() {
		Set<Candidat> setCandidatTest = inscriptionTest.getCandidats();
		assertFalse(setCandidatTest.isEmpty());
	}

	@Test
	public void testCreateCompetition() {
		Competition competitionTest2 = inscriptionTest.createCompetition("Test", null, false);
		Set <Competition> setCompetitionTest = inscriptionTest.getCompetitions();
		assertTrue(setCompetitionTest.contains(competitionTest2));
	}

	@Test
	public void testCreatePersonne() {
		Personne personneTest2 = inscriptionTest.createPersonne("", "", "");
		Set<Candidat>setPersonneTest2 = inscriptionTest.getCandidats();
		assertTrue(setPersonneTest2.contains(personneTest2));
	}

	@Test
	public void testCreateEquipe() {
		Equipe equipeTest2 = inscriptionTest.createEquipe("");
		Set<Candidat>setEquipeTest2 = inscriptionTest.getCandidats();
		assertTrue(setEquipeTest2.contains(equipeTest2));
	}

	@Test
	public void testRemoveCompetition() {
		Set<Competition> setCompetitionTest = inscriptionTest.getCompetitions();
		competitionSoloTest.delete();
		assertFalse(setCompetitionTest.contains(competitionSoloTest));
		
	}

	@Test
	public void testRemoveCandidat() {
		Set<Candidat>setCandidatTest = inscriptionTest.getCandidats();
		personneTest.delete();
		assertFalse(setCandidatTest.contains(personneTest));
	}

	@Test
	public void testGetInscriptions() {
		assertEquals(inscriptionTest, Inscriptions.getInscriptions());
	}

	@Test
	public void testSauvegarder() {
		fail("Not yet implemented");
	}

}
