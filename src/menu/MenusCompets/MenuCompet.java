package menu.MenusCompets;

import inscriptions.Competition;
import inscriptions.Inscriptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.SortedSet;

import utilitaires.EntreesSorties;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuCompet {

	public static Menu RecupMenuCompet() {
		Menu menu = new Menu("Gestion Des Compétitions");
		menu.ajoute(getOptionCreerCompets());
		menu.ajoute(getOptionGererLesCompets());
		menu.ajoute(getOptionSupprimerUneCompet());
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionCreerCompets() {
		return new Option("Creer une compétitions", "1", getActionCreerUneCompet());
	}
	
	static Option getOptionGererLesCompets() {
		return new Option("Editer les compétitions", "2", getActionGererLesCompets());
	}
	
	static Option getOptionEdition(Competition uneCompet, int numero) {
		return new Option(uneCompet.getNom(), Integer.toString(numero), getActionEditerUneCompet(uneCompet));
	}
	
	static Option getOptionSupprimerUneCompet() {
		return new Option("Supprimer une competition", "3", getActionSupprimerUneCompet());
	}
	
	static Action getActionCreerUneCompet() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				LocalDate laDate = null;
				boolean bonformat = false;
				String nom = EntreesSorties.getString("Nom de la compétition?: ");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				do {
					String date = EntreesSorties.getString("Date de cloture? (ex: 2015-02-24): ");
					bonformat = false;
					
					try {
						laDate = LocalDate.parse(date, formatter);
						bonformat = true;
					} catch (Exception e) {
						System.out.println("Date mauvais format");
						bonformat = false;
					}
				} while (!bonformat);
				
				String enEquipe = EntreesSorties.getString("En equipe [O/N]? : ");
				Inscriptions.getInscriptions().createCompetition(nom, laDate, enEquipe.toLowerCase() == "o");
			}
		};
	}
	
	static Action getActionGererLesCompets() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Competition> mesCompets = Inscriptions.getInscriptions().getCompetitions();
				int cpt = 1;
				Menu menu = new Menu("Editer une competition");
				
				for (Competition  macompet : mesCompets) {
					menu.ajoute(getOptionEdition(macompet, cpt));
					cpt++;
				}
				
				menu.ajouteRevenir("r");
				menu.ajouteQuitter("q");
				menu.start();
			}
		};
	}
	
	static Action getActionEditerUneCompet(Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuEditionCompet.RecupMenuEditionCompet(uneCompet).start();
			}
		};
	}
	
	static Action getActionSupprimerUneCompet() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuSupprimCompet.RecupMenuSupprimCompet().start();
				
			}
		};
	}
}
