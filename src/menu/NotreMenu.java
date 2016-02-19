package menu;

import java.util.SortedSet;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Candidat;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class NotreMenu {
	
	public static Menu RecupMenuPrincipal() {
		Menu menu = new Menu("Bienvenue à InscriptionsSportivesM2L");
		menu.ajoute(getOptionGererLesCompets());
		menu.ajoute(getOptionGererLesPersonnes());
		menu.ajoute(getOptionGererLesEquipes());
		menu.ajouteQuitter("q");
		return menu;
	}
	
	
	static Option getOptionGererLesPersonnes() {
		return new Option("Gerer les Personnes", "3", getActionAffichPersonnes());
	}
	
	static Option getOptionGererLesEquipes() {
		return new Option("Gerer les equipes", "4", getActionGererLesEquipes());
	}
	
	static Option getOptionGererLesCompets() {
		return new Option("Gerer les compétitions", "1", getActionGererLesCompets());
	}
	
	static Action getActionAffichPersonne() {
		return new Action()
		{
			@Override
			public void optionSelectionnee()
			{
				SortedSet<Candidat> newset = Inscriptions.getInscriptions().getCandidats();
				for (Candidat  mespersonnes : newset) {
					System.out.println(mespersonnes.getNom());
				}
			}
		};
	}
	
	static Action getActionGererLesEquipes() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				RecupMenuCompétition().start();
				
			}
		};
	}
	
	static Action getActionAfficherLesEquipes() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Candidat> newset = Inscriptions.getInscriptions().getCandidats();
				for (Candidat  mescandidat : newset) {
					if (true) {
						
					}
					System.out.println(mescandidat);
				}
			}
		};
	}
	
	static Action getActionGererLesCandidats() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				RecupMenuCandidat();
			}
		};
	}
	
	static Action getActionAfficherLesCandidats() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Candidat> newset = Inscriptions.getInscriptions().getCandidats();
				for (Candidat  mescandidat : newset) {
					System.out.println(mescandidat);
				}
			}
		};
	}
	
	static Action getActionGererLesCompet() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				RecupMenuCompétition().start();
			}
		};
	}
	
	static Action getActionAfficherLesCompétition() {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				SortedSet<Competition> newset = Inscriptions.getInscriptions().getCompetitions();
				for (Competition  mesequipes : newset) {
					System.out.println(mesequipes.getNom());
				}
			}
		};
	}
}
