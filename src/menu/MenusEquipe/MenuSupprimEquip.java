package menu.MenusEquipe;

import inscriptions.Candidat;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

import java.util.SortedSet;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuSupprimEquip {

	public static Menu RecupMenuSupprimEquip() {
		
		Menu menu = new Menu("Supprimer une equipe:");
		SortedSet<Candidat> mesCandidats = Inscriptions.getInscriptions().getCandidats();
		int cpt = 1;
		
		for (Candidat unCandidat : mesCandidats) {
			if (unCandidat instanceof Equipe) {
				menu.ajoute(getOptionSupprimer(unCandidat, cpt));
				cpt++;
			}
		}

		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionSupprimer(Candidat unCandidat, int cpt) {
		return new Option("Supprimer "+ unCandidat.getNom(), Integer.toString(cpt) , getActionSupprimer(unCandidat));
	}
	
	static Action getActionSupprimer(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				unCandidat.delete();
			}
		};
	}
}
