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
		menu.ajoute(getOptionAfficherLesPersonnes());
		menu.ajoute(getOptionCreerUnePersonne());
		menu.ajoute(getOptionGererLesPersonnes());
		menu.ajoute(getOptionSupprimerPersonne());
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	private static Option getOptionAfficherLesPersonnes() {
		return new Option("Afficher les personnes", "1", getActionAfficherLesPersonnes());
	}

	static Option getOptionCreerUnePersonne() {
		return new Option("Creer une personne", "2", getActionCreerUnePersonne());
	}
	
	static Option getOptionGererLesPersonnes() {
		return new Option("Gerer les personnes", "3", getActionGererLesPersonnes());
	}
	
	static Option getOptionEdition(Candidat unCandidat, int numero) {
		return new Option(unCandidat.getNom(), Integer.toString(numero), getActionEditerUnePersonne(unCandidat));
	}
	
	static Option getOptionSupprimerPersonne() {
		return new Option("Supprimer une personnes", "4", getActionSupprimerPersonne());
	}
	
	private static Action getActionAfficherLesPersonnes() {
		return new Action() {		
			@Override
			public void optionSelectionnee() {
				SortedSet<Candidat> mesCandidat = Inscriptions.getInscriptions().getCandidats();
				for (Candidat candidat : mesCandidat) {
					if (candidat instanceof Personne) {
						System.out.println("Nom: " + ((Personne)candidat).getNom() + " Prenom: " 
								+((Personne)candidat).getPrenom() + " Mail: "+ ((Personne)candidat).getMail() 
								+ " Inscrite à "+ ((Personne)candidat).getCompetitions().size() +" compétition"
								+ " Faisant partie de " +  ((Personne)candidat).getEquipes().size() + " équipe(s)");
					}
				}
			}
		};
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
				} while (!Pattern.compile(EMAIL_PATTERN).matcher(mail).matches());
				Inscriptions.getInscriptions().createPersonne(nom, prenom, mail,-1);
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
				menu.ajouteRevenir("r");
				menu.ajouteQuitter("q");
				menu.start();
			}
		};
	}
	
	static Action getActionEditerUnePersonne(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				MenuEditionPersonne.RecupMenuEditionPersonne(unCandidat).start();
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
