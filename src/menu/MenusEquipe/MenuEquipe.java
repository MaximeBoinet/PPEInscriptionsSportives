package menu.MenusEquipe;

import inscriptions.Candidat;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

import java.util.SortedSet;

import utilitaires.EntreesSorties;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuEquipe {
	
	public static Menu RecupMenuEquipe() {
		Menu menu = new Menu("Gestion Des Equipes");
		menu.ajoute(getOptionCreerUneEquipe());
		menu.ajoute(getOptionGererLesEquipes());
		menu.ajoute(getOptionSupprimerEquipe());
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionCreerUneEquipe() {
		return new Option("Creer une equipe", "1", getActionCreerUneEquipe());
	}
	
	static Option getOptionGererLesEquipes() {
		return new Option("Gerer une equipe", "2", getActionGererLesEquipes());
	}
	
	static Option getOptionEdition(Candidat unCandidat, int numero) {
		return new Option(unCandidat.getNom(), Integer.toString(numero), getActionEditerUneEquipe(unCandidat));
	}
	
	static Option getOptionSupprimerEquipe() {
		return new Option("Supprimer une equipe", "3", getActionSupprimerEquipe());
	}
	
	static Action getActionCreerUneEquipe() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				Inscriptions.getInscriptions().createEquipe(EntreesSorties.getString("Nom de l'équipe?: "));
				System.out.println("Votre équipe à bien été crée");
			}
		};
	}
	
	static Action getActionGererLesEquipes() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Candidat> mesCandidats = Inscriptions.getInscriptions().getCandidats();
				int cpt = 1;
				Menu menu = new Menu("Editer une equipe");
				for (Candidat  unCandidat : mesCandidats) {
					if (unCandidat instanceof Equipe) {
						menu.ajoute(getOptionEdition(unCandidat, cpt));
						cpt++;
					}
				}
			}
		};
	}
	
	static Action getActionEditerUneEquipe(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuEditionEquipe.RecupMenuEditionEquipe(unCandidat).start();
			}
		};
	}
	
	
	static Action getActionSupprimerEquipe() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuSupprimEquip.RecupMenuSupprimEquip().start();
			}
		};
	}
}
