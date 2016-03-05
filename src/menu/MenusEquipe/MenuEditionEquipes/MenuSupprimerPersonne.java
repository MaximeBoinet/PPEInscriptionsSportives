package menu.MenusEquipe.MenuEditionEquipes;

import inscriptions.Candidat;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.util.SortedSet;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuSupprimerPersonne {
	
	public static Menu RecupMenuDesinscrirePersonnes(Candidat unCandidat) {
		Menu menu = new Menu("Desinscrire un utilisateur de l'equipe "+unCandidat.getNom());
		
		SortedSet<Candidat> mesCandidats = Inscriptions.getInscriptions().getCandidats();
		int cpt = 1;
		for (Candidat unCandidat2 : mesCandidats) {
			if (unCandidat2 instanceof Personne){
				menu.ajoute(getOptionDesinscrire(unCandidat, unCandidat2, cpt));
				cpt++;
			}
		}
		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionDesinscrire(Candidat unCandidat, Candidat unCandidat2, int cpt) {
		return new Option("Desinscrire"+unCandidat2.getNom()+ "de l'équipe", Integer.toString(cpt), getActionDesinscrire(unCandidat, unCandidat2));
	}
	
	static Action getActionDesinscrire(Candidat unCandidat, Candidat unCandidat2) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				((Equipe)unCandidat).remove((Personne)unCandidat2);
			}
		};
	}
}
