package menu;

import utilitaires.*;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;
import menu.MenusCompets.MenuCompet;
import menu.MenusEquipe.MenuEquipe;
import menu.MenusPers.MenuPersonnes;

public class MenuPrincipal {
	
	public static Menu RecupMenuPrincipal() {
		Menu menu = new Menu("Bienvenue à InscriptionsSportivesM2L");
		menu.ajoute(getOptionGererLesCompets());
		menu.ajoute(getOptionGererLesPersonnes());
		menu.ajoute(getOptionGererLesEquipes());
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionGererLesCompets() {
		return new Option("Gerer les compétitions", "1", getActionGererLesCompets());
	}
	
	static Option getOptionGererLesPersonnes() {
		return new Option("Gerer les Personnes", "2", getActionGererPersonnes());
	}
	
	static Option getOptionGererLesEquipes() {
		return new Option("Gerer les equipes", "3", getActionGererLesEquipes());
	}
	
	static Action getActionGererLesCompets() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuCompet.RecupMenuCompet().start();
			}
		};
	}
	
	static Action getActionGererPersonnes(){
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuPersonnes.RecupMenuPersonnes().start();
			}
		};
	}
	
	static Action getActionGererLesEquipes() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuEquipe.RecupMenuEquipe().start();
			}
		};
	}
	

}
