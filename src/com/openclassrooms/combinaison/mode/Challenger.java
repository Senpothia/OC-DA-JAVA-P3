package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * D�finit le mode de jeu Challenger
 * H�rite de la classe Mode
 * Fonctionnement: l'utilisateur cherche � deviner une combinaison 
 * choisie al�atoirement par la Machine
 * Redefinit la m�thode abstraite jouer() de la classe Mode 
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
 *  Impl�mente la m�thode abstraite de la classe Mode pour d�finir
 *  la s�quence de jeu selon le mode challenger
 *  @return resultatFinal
 *  				Indique l'issue de la partie (gagn�e/perdue)
 */
	@Override
	public boolean jouer() {
		logger.debug("M�thode jouer() - Mode Challenger");
		int tentatives = this.getNbTentatives();

		System.out.println("Mode Challenger actif");
		this.combMachine = alea.aleatoire(getNbDigit());// La machine choisit une combinaison al�atoire
		this.combDefenseur = convertCombString(this.combMachine);
		if (dev) {System.out.println("Combinaison Machine: " + this.combDefenseur ); // On affiche la combinaison
		}																	// Machine

		do {
			if (tentatives > 1) {

				System.out.printf("Tentative %d\n", this.getNbTentatives() - tentatives + 1);
			} else {
				System.out.printf("Attention derni�re tentative!\n");
			}

			System.out.printf("Entrez une combinaison de %d chiffres\n", this.getNbDigit());
			this.combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(this.getNbDigit()),this.getNbDigit()); // Le joueur
																											// propose
																											// sa
																											// combinaison
			resJoueur = this.evaluer(this.combMachine, this.combJoueur,"Joueur","Machine"); // Evaluation du r�sultat associ� � la
																			// combinaison propos�e par le Joueur
			tentatives--;
			Partie.afficheur.resultat(getResJoueur(), getNbDigit()); // On affiche le r�sultat de l'�valuation

		}

		while (tentatives > 0 && ! isResultatFinal());
		setPerdant(this.attaquant);
		return resultatFinal;
	}
}