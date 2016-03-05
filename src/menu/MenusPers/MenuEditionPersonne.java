package menu.MenusPers;

import java.util.regex.Pattern;

import menu.MenusEquipe.MenuEditionEquipes.MenuDesinscrireCompet;
import menu.MenusEquipe.MenuEditionEquipes.MenuInscrireCompet;
import inscriptions.Candidat;
import inscriptions.Personne;
import utilitaires.EntreesSorties;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuEditionPersonne {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static Menu RecupMenuEditionPersonne(Candidat unCandidat) {
		Menu menu = new Menu("Nom: " + ((Personne)unCandidat).getNom() + " Prenom: " + ((Personne)unCandidat).getPrenom()
				+ " Mail:" +((Personne)unCandidat).getMail());
		
		menu.ajoute(getOptionDesinscrireACompet(unCandidat));
		menu.ajoute(getOptionInscrireAUneCompet(unCandidat));
		menu.ajoute(getOptionChangerPrenom(unCandidat));
		menu.ajoute(getOptionChangerMail(unCandidat));
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}

	static Option getOptionDesinscrireACompet(Candidat unCandidat) {
		return new Option("Desinscrire d'une competition", "1", getActionDesinscrireACompet(unCandidat));
	}
	
	private static Option getOptionInscrireAUneCompet(Candidat unCandidat) {
		return new Option("Inscrire à une competition", "2", getActionInscrireAUneCompet(unCandidat));
	}


	private static Option getOptionChangerPrenom(Candidat unCandidat) {
		return new Option("Changer le Prenom", "3", getActionChangerPrenom(unCandidat));
	}
	
	private static Option getOptionChangerMail(Candidat unCandidat) {
		return new Option("Changer le Mail", "4", getActionChangerMail(unCandidat));
	}
	

	private static Action getActionDesinscrireACompet(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuDesinscrireCompet.RecupMenuDesinscrireCompet(unCandidat);	
			}
		};
	}
	
	private static Action getActionInscrireAUneCompet(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuInscrireCompet.RecupMenuInscrireCompet(unCandidat);
			}
		};
	}
	
	private static Action getActionChangerPrenom(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				((Personne)unCandidat).setPrenom(EntreesSorties.getString("Nouveau Prenom de la Personnes?: "));
				
			}
		};
	}
	
	private static Action getActionChangerMail(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				String mail;
				do {
					mail = EntreesSorties.getString("Nouveau Mail de la Personnes?: ");
				} while (Pattern.compile(EMAIL_PATTERN).matcher(mail).matches());
				
				((Personne)unCandidat).setMail(mail);
			}
		};
	}
}
