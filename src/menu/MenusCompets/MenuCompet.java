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
		menu.ajoute(getOptionAfficherLesCompets());
		menu.ajoute(getOptionCreerCompets());
		menu.ajoute(getOptionGererLesCompets());
		menu.ajoute(getOptionSupprimerUneCompet());
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	private static Option getOptionAfficherLesCompets() {
		return new Option("Afficher les compétition", "1", getActionAfficherLesCompets());
	}

	static Option getOptionCreerCompets() {
		return new Option("Creer une compétitions", "2", getActionCreerUneCompet());
	}
	
	static Option getOptionGererLesCompets() {
		return new Option("Editer les compétitions", "3", getActionGererLesCompets());
	}
	
	static Option getOptionEdition(Competition uneCompet, int numero) {
		return new Option(uneCompet.getNom(), Integer.toString(numero), getActionEditerUneCompet(uneCompet));
	}
	
	static Option getOptionSupprimerUneCompet() {
		return new Option("Supprimer une competition", "4", getActionSupprimerUneCompet());
	}
	

	private static Action getActionAfficherLesCompets() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Competition> mesCompets = Inscriptions.getInscriptions().getCompetitions();
				
				for (Competition maCompet : mesCompets) {
					System.out.println("Nom: " + maCompet.getNom() + " DateCloture:" + maCompet.getDateCloture()
							+" De type: " + (maCompet.estEnEquipe() ? "En equipe" : "Solo"));
				}
				
			}
		};
	}
	
	static Action getActionCreerUneCompet() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				LocalDate laDate = null;
				boolean bonformat = false;
				String nom = EntreesSorties.getString("Nom de la compétition?: ");
				System.out.println("le nom" + nom);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				do {
					String date = EntreesSorties.getString("Date de cloture? (ex: 2015-02-24): ");
					bonformat = false;
					
					try {
						laDate = LocalDate.parse(date, formatter);
						System.out.println("date: " + date + " format: " + laDate);
						bonformat = true;
					} catch (Exception e) {
						System.out.println("Date mauvais format");
						bonformat = false;
					}
				} while (!bonformat);
				
				String enEquipe = EntreesSorties.getString("En equipe [O/N]? : ");
				System.out.println("reponsé " + enEquipe );
				Inscriptions.getInscriptions().createCompetition(nom, laDate, enEquipe.toLowerCase().equals("o"),-1);
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
