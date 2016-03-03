package menu.MenusCompets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import menu.MenusCompets.MenusEditionCompet.MenuInscrireCandidat;
import menu.MenusCompets.MenusEditionCompet.MenuSupprimCandidat;
import inscriptions.Competition;
import utilitaires.EntreesSorties;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuEditionCompet {
	
	public static Menu RecupMenuEditionCompet(Competition uneCompet) {
		Menu menu = new Menu("Competition: " + uneCompet.getNom() + ". Cloture:" 
				+ uneCompet.getDateCloture() + " Nombre de Candidat: " + uneCompet.getCandidats().size()
				+ " De type: " + (uneCompet.estEnEquipe() ? "En equipe" : "Solo"));
		
		menu.ajoute(getOptionGererLesCandidatsInscrit(uneCompet));
		menu.ajoute(getOptionChangerLaDate(uneCompet));
		menu.ajoute(getOptionInscrireUnCandidat(uneCompet));
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionGererLesCandidatsInscrit(Competition uneCompet) {
		return new Option("Supprimer un candidats inscrits", "1", getActionGererLesCandidatsInscrit( uneCompet));
	}
	
	static Option getOptionChangerLaDate(Competition uneCompet) {
		return new Option("Changer la date de cloture", "2", getActionChangerLaDate(uneCompet));
	}
	
	static Option getOptionInscrireUnCandidat(Competition uneCompet) {
		return new Option("Inscrire un candidat", "3", getActionInscrireUnCandidat(uneCompet));
	}
	
	static Action getActionGererLesCandidatsInscrit(Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuSupprimCandidat.RecupMenuSupprimCandidat(uneCompet).start();
			}
		};
	}
	
	static Action getActionChangerLaDate(Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				LocalDate laDate = null;
				boolean bonformat,bonnedate = false;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				do {
					String date = EntreesSorties.getString("Changer la date de cloture (ex: 2015-02-24)(doit être ultérieur à: "+uneCompet.getDateCloture()+") : ");
					bonformat = false;
					bonnedate = false;
					
					try {
						laDate = LocalDate.parse(date, formatter);
						bonformat = true;
					} catch (Exception e) {
						System.out.println("Date mauvais format");
						bonformat = false;
					}
					
					if (bonformat) {
						if (laDate.isAfter(uneCompet.getDateCloture())) {
							bonnedate = true;
						}
						else {
							System.out.println("La date est antérieur à celle actuel");
						}
					}
				} while (!bonformat || !bonnedate);
				
				uneCompet.setDateCloture(laDate);
			}
		};
	}
	
	static Action getActionInscrireUnCandidat(Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuInscrireCandidat.RecupMenuInscrireCandidat(uneCompet).start();
			}
		};
	}
}
