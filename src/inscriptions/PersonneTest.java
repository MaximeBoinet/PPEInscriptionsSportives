package inscriptions;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Set;

public class PersonneTest {
	
	Inscriptions inscriptionTest = Inscriptions.getInscriptions();
	Personne personneTest = inscriptionTest.createPersonne ("TEST", "test", "tTEST@gmail.com");
	

	@Test
	public void testDelete() {
		Equipe equipeTest = inscriptionTest.createEquipe("L'EQUIPE TEST");
		equipeTest.add(personneTest);
		Set<Equipe> setEquipesTest = personneTest.getEquipes();
		personneTest.delete();
		assertFalse(setEquipesTest.contains(personneTest));
	}

	@Test
	public void testGetPrenom() {
		assertEquals("test", personneTest.getPrenom());
	}

	@Test
	public void testSetPrenom() {
		personneTest.setPrenom("test1");
		assertEquals("test1", personneTest.getPrenom());
	}

	@Test
	public void testGetMail() {
		assertEquals("tTEST@gmail.com", personneTest.getMail());
	}

	@Test
	public void testSetMail() {
		personneTest.setMail("TEST@gmail.com");
		assertEquals("TEST@gmail.com", personneTest.getMail());
	}

	@Test
	public void testGetEquipes() {
		Equipe equipeTest = inscriptionTest.createEquipe("L'EQUIPE TEST");
		equipeTest.add(personneTest);
		Set<Equipe> setEquipesTest = personneTest.getEquipes();
		assertTrue(setEquipesTest.contains(equipeTest));
	}

}
