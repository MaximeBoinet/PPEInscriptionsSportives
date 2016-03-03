package menu.MenusCompets.MenusEditionCompet;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;

import java.util.Set;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuInscrireCandidat {

	public static Menu RecupMenuInscrireCandidat(Competition uneCompet) {
		Set<Candidat> mesCandidats = uneCompet.getCandidats();
		int cpt = 1;
		Menu menu = new Menu("Inscrire un candidat");
		
		for (Candidat  monCandidat : mesCandidats) {
			if (!uneCompet.getCandidats().contains(monCandidat)) {
				if (uneCompet.estEnEquipe() && monCandidat instanceof Equipe)
				{
					menu.ajoute(getOptionInscrireEquipe(monCandidat, cpt, uneCompet));
					cpt++;
				}
				else if(!uneCompet.estEnEquipe() && monCandidat instanceof Personne) {
					menu.ajoute(getOptionInscrirePersonne(monCandidat, cpt, uneCompet));
					cpt++;
				}
			}
			
		}
		
		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionInscrireEquipe(Candidat unCandidat, int cpt, Competition uneCompet) {
		return new Option ("Inscrire le Candidat " + unCandidat.getNom(), Integer.toString(cpt), getActionInscrireEquipe(unCandidat, uneCompet));
	}
	
	static Option getOptionInscrirePersonne(Candidat unCandidat, int cpt, Competition uneCompet) {
		return new Option ("Inscrire le Candidat " + unCandidat.getNom(), Integer.toString(cpt), getActionInscrirePersonne(unCandidat, uneCompet));
	}
	
	static Action getActionInscrireEquipe(Candidat unCandidat, Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				uneCompet.add((Equipe)unCandidat);
				
			}
		};
	}
	
	static Action getActionInscrirePersonne(Candidat unCandidat, Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				uneCompet.add((Personne)unCandidat);
			}
		};
	}
}
