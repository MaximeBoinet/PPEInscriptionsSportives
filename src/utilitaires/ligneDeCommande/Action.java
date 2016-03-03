package utilitaires.ligneDeCommande;

import java.io.IOException;

import inscriptions.Inscriptions;

/**
 * Permet d'affecter des actions au choix d'un élément dans un menu.
 */

public interface Action
{
	/**
	 * Action prédéfinie permettant de quitter le programme.
	 */
	
	public static final Action 
	QUITTER = new Action()
	{
		@Override 
		public void optionSelectionnee()
		{
			try {
				Inscriptions.getInscriptions().sauvegarder();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
	};
	
	/**
	 * Action prédéfinie permettant de revenir au menu précédent.
	 */
	
	public static final Action REVENIR = new Action()
	{
		@Override 
		public void optionSelectionnee(){}
	};

	/**
	 * Fonction automatiquement exécutée quand une option est sélectionnée.
	 */
	
	public void optionSelectionnee();
}
