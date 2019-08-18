package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * D�finit le mode de jeu Humains
 * H�rite de la classe Mode
 * Fonctionnement: deux joueurs humains cherchent � deviner la combinaison 
 * secr�te choisie par l'autre joueur. 
 * Redefinit la m�thode abstraite jouer() de la classe Mode 
 * @author Michel Lopez
 * @version 1.0
 *
 */


public class Humains extends Mode {

	/** 
	 * Constructeur 
	 * @param nbDigit
	 * @param nbTentatives
	 */
	static Logger logger = LogManager.getLogger(Humains.class);
	public Humains (int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Constructeur Humains()");
		this.nbDigit = nbDigit;
		this.nbTentatives=nbTentatives;
		
	}
	
	/**
	 *  Impl�ment la m�thode abstraite de la classe Mode pour d�finir
	 *  la s�quence de jeu selon le mode Humains
	 *  @return resultatFinal
	 *  						Indiquer l'issue de la partie (gagn�e/perdue)
	 */

	public boolean jouer() {
		logger.debug("M�thode jouer() - Mode Humains");
		int tentatives = getNbTentatives();
		System.out.println("Mode Humains actif");

		// Joueur1 (Joueur) choisit une combinaison1 secr�te
		System.out.printf("Joueur 1: Entrez une combinaison secr�te de %d chiffres\n", getNbDigit());
		// D�composition combinaison1 secr�te
		combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
		// Joueur2 (Machine) choisit une combinaison2 secr�te
		System.out.printf("Joueur 2: Entrez une combinaison secr�te de %d chiffres\n", getNbDigit());
		// D�composition combinaison2 secr�te

		combMachine = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());


		do {

			// Label1:
			// Joueur1 fait une proposition
			System.out.printf("Joueur 1: Entrez une combinaison de %d chiffres\n", getNbDigit());
			// D�composition-Evaluation propsition1
			propJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
			resJoueur = this.evaluer(combMachine, propJoueur,"Joeur1");

			// Afficher r�sultat1
			Partie.afficheur.resultat(getResJoueur(), getNbDigit());

			// Joueur2 fait une proposition
			System.out.printf("Joueur 2: Entrez une combinaison de %d chiffres\n", getNbDigit());
			// D�composition-Evaluation propsition2
			propMachine = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
			resMachine = this.evaluer(combJoueur, propMachine,"Joueur2");
			// Afficher r�sultat2
			Partie.afficheur.resultat(getResMachine(), getNbDigit());
			// D�cr�meneter nbre de tentatives
			tentatives--;
			// Reboucler label1

		} while (tentatives > 0 && !isResultatFinal());

		return resultatFinal;

	}

}