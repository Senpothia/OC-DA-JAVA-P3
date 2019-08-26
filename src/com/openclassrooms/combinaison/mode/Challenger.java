package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Définit le mode de jeu Challenger
 * Hérite de la classe Mode
 * Fonctionnement: l'utilisateur cherche à deviner une combinaison 
 * choisie aléatoirement par la Machine
 * Redefinit la méthode abstraite jouer() de la classe Mode 
 * @author Michel Lopez
 * @version 1.0
 *
 */


public class Challenger extends Mode {
	static Logger logger = LogManager.getLogger(Challenger.class);
	/** 
	 * Constructeur 
	 * @param nbDigit
	 * @param nbTentatives
	 * @param dev
	 */
	
	public Challenger (int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Constructeur Challenger()");
		this.nbDigit = nbDigit;
		this.nbTentatives=nbTentatives;
		this.dev = dev;
		this.attaquant = "Joueur";
		this.defenseur = "Machine";
		
	}
/**
 *  Implémente la méthode abstraite de la classe Mode pour définir
 *  la séquence de jeu selon le mode challenger
 *  @return resultatFinal
 *  				Indique l'issue de la partie (gagnée/perdue)
 */
	@Override
	public boolean jouer() {
		logger.debug("Méthode jouer() - Mode Challenger");
		int tentatives = this.getNbTentatives();

		System.out.println("Mode Challenger actif");
		this.combMachine = alea.aleatoire(getNbDigit());// La machine choisit une combinaison aléatoire
		this.combDefenseur = convertCombString(this.combMachine);
		if (dev) {System.out.println("Combinaison Machine: " + this.combDefenseur ); // On affiche la combinaison
		}																	// Machine

		do {
			if (tentatives > 1) {

				System.out.printf("Tentative %d\n", this.getNbTentatives() - tentatives + 1);
			} else {
				System.out.printf("Attention dernière tentative!\n");
			}

			System.out.printf("Entrez une combinaison de %d chiffres\n", this.getNbDigit());
			this.combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(this.getNbDigit()),this.getNbDigit()); // Le joueur
																											// propose
																											// sa
																											// combinaison
			resJoueur = this.evaluer(this.combMachine, this.combJoueur,"Joueur","Machine"); // Evaluation du résultat associé à la
																			// combinaison proposée par le Joueur
			tentatives--;
			Partie.afficheur.resultat(getResJoueur(), getNbDigit()); // On affiche le résultat de l'évaluation

		}

		while (tentatives > 0 && ! isResultatFinal());
		setPerdant(this.attaquant);
		return resultatFinal;
	}
}