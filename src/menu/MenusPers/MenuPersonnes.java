package menu.MenusPers;

import inscriptions.Candidat;
import inscriptions.Personne;
import inscriptions.Inscriptions;

import java.util.SortedSet;
import java.util.regex.Pattern;
import utilitaires.EntreesSorties;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuPersonnes {

	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static Menu RecupMenuPersonnes() {
		Menu menu = new Menu("Gestion Des Personnes");
		menu.ajoute(getOptionCreerUnePersonne());
		menu.ajoute(getOptionGererLesPersonnes());
		menu.ajoute(getOptionSupprimerPersonne());
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionCreerUnePersonne() {
		return new Option("Creer une compétitions", "1", getActionCreerUnePersonne());
	}
	
	static Option getOptionGererLesPersonnes() {
		return new Option("Creer une compétitions", "2", getActionGererLesPersonnes());
	}
	
	static Option getOptionEdition(Candidat unCandidat, int numero) {
		return new Option(unCandidat.getNom(), Integer.toString(numero), getActionEditerUnePersonne(unCandidat));
	}
	
	static Option getOptionSupprimerPersonne() {
		return new Option("Creer une compétitions", "3", getActionSupprimerPersonne());
	}
	
	static Action getActionCreerUnePersonne() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				String nom, prenom, mail = null;
				nom = (EntreesSorties.getString("Nom de la Personnes?: "));
				prenom = (EntreesSorties.getString("Prenom de la Personnes?: "));
				do {
					mail = EntreesSorties.getString("Mail de la Personnes?: ");
				} while (Pattern.compile(EMAIL_PATTERN).matcher(mail).matches());
				Inscriptions.getInscriptions().createPersonne(nom, prenom, mail);
				System.out.println("La personnes à bien été crée");
			}
		};
	}
	
	static Action getActionGererLesPersonnes() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Candidat> mesCandidats = Inscriptions.getInscriptions().getCandidats();
				int cpt = 1;
				Menu menu = new Menu("Editer une Personnes");
				for (Candidat  unCandidat : mesCandidats) {
					if (unCandidat instanceof Personne) {
						menu.ajoute(getOptionEdition(unCandidat, cpt));
						cpt++;
					}
				}
			}
		};
	}
	
	static Action getActionEditerUnePersonne(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuEditionPersonnes.RecupMenuEditionPersonnes(unCandidat).start();
			}
		};
	}
	
	
	static Action getActionSupprimerPersonne() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuSupprimPersonne.RecupMenuSupprimPersonne().start();
			}
		};
	}
}
