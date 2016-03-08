package menu.MenusCompets.MenusEditionCompet;

import inscriptions.Candidat;
import inscriptions.Competition;
import java.util.Set;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuSupprimCandidat {
	
	public static Menu RecupMenuSupprimCandidat(Competition uneCompet) {
		Set<Candidat> mesCandidats = uneCompet.getCandidats();
		int cpt = 1;
		Menu menu = new Menu("Supprimer un candidat");
		
		for (Candidat  monCandidat : mesCandidats) {
			menu.ajoute(getOptionSupprimer(monCandidat, cpt, uneCompet));
			cpt++;
		}
		
		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionSupprimer(Candidat unCandidat, int cpt, Competition uneCompet) {
		return new Option ("Supprimer le Candidat " + unCandidat.getNom(), Integer.toString(cpt), getActionSupprimer(unCandidat, uneCompet));
	}
	
	static Action getActionSupprimer(Candidat unCandidat, Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				uneCompet.remove(unCandidat);
			}
		};
	}
}
