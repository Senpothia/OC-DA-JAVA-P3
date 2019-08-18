package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Définit le mode de jeu Duel
 * Hérite de la classe Mode
 * Fonctionnement: la Machine cherche à deviner une combinaison 
 * choisie aléatoirement par le joueur
 * Redefinit la méthode abstraite jouer() de la classe Mode 
 * @author Michel Lopez
 *  @version 1.0
 *
 */

public class Duel extends Mode {
	
	/** 
	 * Constructeur 
	 * @param nbDigit
	 * @param nbTentatives
	 */
	static Logger logger = LogManager.getLogger(Duel.class);
	public Duel (int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Constructeur Duel()");
		this.nbDigit = nbDigit;
		this.nbTentatives = nbTentatives;
		this.dev = dev;
		
	}
	/**
	 *  Implément la méthode abstraite de la classe Mode pour définir
	 *  la séquence de jeu selon le mode Duel
	 *  @return resultatFinal
	 *  					Indique l'issue de la partie (gagnée/perdue)
	 */

	@Override
	public boolean jouer() {

		logger.debug("Méthode jouer() - Mode Duel");
		System.out.println("Mode Duel actif");
		int tentatives = getNbTentatives();

		// Le joueur entre une combinaison secrète
		System.out.printf("Entrez une combinaison secrète de %d chiffres\n", getNbDigit());
		// lecture-Décomposition combinaison secrète Joueur1
		combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
		// La Machine choisit une combinaison secrète
		combMachine = alea.aleatoire(getNbDigit());

		// Affichage combinaison Machine
		if (dev) {
			System.out.println("Combinaison secrète de la Machine: " + convertCombString(combMachine));
		}

		System.out.printf("Joueur: Entrez une combinaison de %d chiffres\n", getNbDigit());
		// Joueur fait une proposition
		propJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
		// Décomposition-Evaluation Joueur
		resJoueur = this.evaluer(combMachine,propJoueur,"");
		// Afficher résultat Joueur
		Partie.afficheur.resultat(getResJoueur(), getNbDigit());
		// Machine fait une proposition
		propMachine = alea.aleatoire(getNbDigit());
		System.out.println("Proposition Machine: " + convertCombString(propMachine));
		resMachine = this.evaluer(combJoueur, propMachine,"Machine");
		Partie.afficheur.resultat(getResMachine(), getNbDigit());// On affiche le résultat de l'évaluation: "+-+...-+"
		tentatives--;
		do {

			// Label1:
			// Joueur fait une proposition
			System.out.printf("Joueur: Entrez une combinaison de %d chiffres\n", getNbDigit());
			propJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
			// Décomposition-Evaluation Joueur
			resJoueur = this.evaluer(combMachine, propJoueur,"");

			// Afficher résultat Joueur
			Partie.afficheur.resultat(getResJoueur(), getNbDigit());

			// Machine fait une proposition
			propMachine = this.tirage(propMachine, getResMachine());
			resMachine = this.evaluer(combJoueur, propMachine,"Machine");
			Partie.afficheur.resultat(getResMachine(), getNbDigit()); // On affiche le résultat de l'évaluation: "+-+...-+"

			tentatives--;
			// Reboucler label1

		} while (tentatives > 0 && !isResultatFinal());


		return resultatFinal;
	}

}