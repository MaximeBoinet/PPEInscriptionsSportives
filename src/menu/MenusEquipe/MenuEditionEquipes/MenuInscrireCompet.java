package menu.MenusEquipe.MenuEditionEquipes;

import java.util.SortedSet;
import inscriptions.Equipe;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuInscrireCompet {

	public static Menu RecupMenuInscrireCompet(Candidat unCandidat) {
		Menu menu = new Menu("Inscrire "+unCandidat.getNom()+" à une compétition");
		
		SortedSet<Competition> mesCompets = Inscriptions.getInscriptions().getCompetitions();
		int cpt = 1;
		for (Competition uneCompet : mesCompets) {
			if (!unCandidat.getCompetitions().contains(uneCompet)){
				menu.ajoute(getOptionInscrire(unCandidat, uneCompet, cpt));
				cpt++;
			}
		}
		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionInscrire(Candidat unCandidat, Competition uneCompet, int cpt) {
		return new Option("Inscrire à la compétition "+uneCompet.getNom(), Integer.toString(cpt), getActionInscrire(unCandidat, uneCompet));
	}
	
	static Action getActionInscrire(Candidat unCandidat, Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				uneCompet.add( (Equipe)unCandidat );
			}
		};
	}
}
