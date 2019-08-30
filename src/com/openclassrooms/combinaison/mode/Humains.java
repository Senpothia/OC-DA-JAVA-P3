package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Définit le mode de jeu Humains
 * Hérite de la classe Mode.<br>
 * Fonctionnement: deux joueurs humains cherchent à deviner la combinaison 
 * secrète choisie par l'autre joueur.<br> 
 * Redéfinit la méthode abstraite jouer() de la classe Mode<br>
 * @author Michel Lopez
 * @version 1.0
 *
 */


public class Humains extends Mode {

	static Logger logger = LogManager.getLogger(Humains.class);
	/** 
	 * Constructeur 
	 * @param nbDigit
	 * @param nbTentatives
	 */
	
	public Humains (int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Constructeur Humains()");
		this.nbDigit = nbDigit;
		this.nbTentatives=nbTentatives;
		this.attaquant = "";
		this.defenseur = "";
		
	}
	
	/**
	 *  Implément la méthode abstraite de la classe Mode pour définir
	 *  la séquence de jeu selon le mode Humains
	 *  @return resultatFinal
	 *  						Indiquer l'issue de la partie (gagnée/perdue)
	 */

	public boolean jouer() {
		logger.debug("Méthode jouer() - Mode Humains");
		int tentatives = getNbTentatives();
		System.out.println("Mode Humains actif");

		// Joueur1 (Joueur) choisit une combinaison1 secrète
		
		System.out.printf("Joueur 1: Entrez une combinaison secrète de %d chiffres\n", getNbDigit());
		// Décomposition combinaison1 secrète
		combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
		// Joueur2 (Machine) choisit une combinaison2 secrète
		;
		System.out.printf("Joueur 2: Entrez une combinaison secrète de %d chiffres\n", getNbDigit());
		// Décomposition combinaison2 secrète

		combMachine = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());


		do {

			// Label1:
			// Joueur1 fait une proposition
			this.attaquant = "Joueur1";
			this.defenseur = "Joueur2";
			System.out.printf("Joueur 1: Entrez une combinaison de %d chiffres\n", getNbDigit());
			// Décomposition-Evaluation propsition
			propJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
			resJoueur = this.evaluer(combMachine, propJoueur,"Joueur1", "Joueur2");

			// Afficher résultat
			Partie.afficheur.resultat(getResJoueur(), getNbDigit());

			// Joueur2 fait une proposition
			this.attaquant = "Joueur2";
			this.defenseur = "Joueur1";
			System.out.printf("Joueur 2: Entrez une combinaison de %d chiffres\n", getNbDigit());
			// Décomposition-Evaluation propsition2
			propMachine = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
			resMachine = this.evaluer(combJoueur, propMachine,"Joueur2", "Joueur1");
			// Afficher résultat
			Partie.afficheur.resultat(getResMachine(), getNbDigit());
			// Décrémeneter nbre de tentatives
			tentatives--;
			// Reboucler label1

		} while (tentatives > 0 && !isResultatFinal());
		
		setPerdant("Combinaison non trouvée: aucun vainqueur");
		return resultatFinal;

	}

}