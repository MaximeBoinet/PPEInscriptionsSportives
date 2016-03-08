package menu.MenusEquipe;

import menu.MenusEquipe.MenuEditionEquipes.MenuDesinscrireCompet;
import menu.MenusEquipe.MenuEditionEquipes.MenuInscrireCompet;
import menu.MenusEquipe.MenuEditionEquipes.MenuInscrirePersonnes;
import menu.MenusEquipe.MenuEditionEquipes.MenuSupprimerPersonne;
import inscriptions.Candidat;
import utilitaires.EntreesSorties;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuEditionEquipe {
	
	public static Menu RecupMenuEditionEquipe(Candidat unCandidat) {
		Menu menu = new Menu("Equipe: " + unCandidat.getNom());
		
		menu.ajoute(getOptionDesinscrireACompet(unCandidat));
		menu.ajoute(getOptionInscrireAUneCompet(unCandidat));
		menu.ajoute(getOptionSupprimerMembre(unCandidat));
		menu.ajoute(getOptionAjouterMembre(unCandidat));
		menu.ajoute(getOptionRenommerEquipe(unCandidat));
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionDesinscrireACompet(Candidat unCandidat) {
		return new Option("Desinscrire d'une competition", "1", getActionDesinscrireACompet(unCandidat));
	}
	
	static Option getOptionInscrireAUneCompet(Candidat unCandidat) {
		return new Option("Inscrire à une competition", "2", getActionInscrireAUneCompet(unCandidat));
	}
	
	static Option getOptionSupprimerMembre(Candidat unCandidat) {
		return new Option("Supprimer un membre", "3", getActionSupprimerMenbre(unCandidat));
	}
	
	static Option getOptionAjouterMembre(Candidat unCandidat) {
		return new Option("Ajouter un membre", "4", getActionAjouterMenbre(unCandidat));
	}
	
	static Option getOptionRenommerEquipe(Candidat unCandidat) {
		return new Option("Renommer l'équipe", "5", getActionRenommerEquipe(unCandidat));
	}
	
	static Action getActionDesinscrireACompet(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuDesinscrireCompet.RecupMenuDesinscrireCompet(unCandidat).start();	
			}
		};
	}
	
	static Action getActionInscrireAUneCompet(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuInscrireCompet.RecupMenuInscrireCompet(unCandidat).start();
			}
		};
	}
	
	static Action getActionSupprimerMenbre(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuSupprimerPersonne.RecupMenuDesinscrirePersonnes(unCandidat).start();
			}
		};
	}
	
	static Action getActionAjouterMenbre(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuInscrirePersonnes.RecupMenuInscrirePersonnes(unCandidat).start();
			}
		};
	}
	
	static Action getActionRenommerEquipe(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				unCandidat.setNom(EntreesSorties.getString("Nouveau nom?: "));
			}
		};
	}
}
