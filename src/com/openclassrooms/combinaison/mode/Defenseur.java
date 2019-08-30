package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Définit le mode de jeu défenseur.<br>
 * Hérite de la classe Mode.<br>
 * Fonctionnement: la Machine cherche à deviner une combinaison 
 * choisie aléatoirement par le joueur.<br>
 * Redéfinit la méthode abstraite jouer() de la classe Mode. 
 * @author Michel Lopez
 *  @version 1.0
 *
 */
public class Defenseur extends Mode {

	static Logger logger = LogManager.getLogger(Defenseur.class);
	/** 
	 * Constructeur 
	 * @param nbDigit
	 * @param nbTentatives
	 */
	
	public Defenseur (int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Constructeur Defenseur()");
		this.nbDigit = nbDigit;
		this.nbTentatives=nbTentatives;
		this.dev=dev;
		this.attaquant = "Machine";
		this.defenseur = "Joueur";
		
	}
	/**
	 *  Implément la méthode abstraite de la classe Mode pour définir
	 *  la séquence de jeu selon le mode Défenseur
	 *  @return resultatFinal
	 *					Indique l'issue de la partie (gagnée/perdue)
	 */
	@Override
	public boolean jouer() {
		logger.debug("Méthode jouer() - Mode Defenseur");
		int tentatives = getNbTentatives();
		//int[] nouvelleCombinaison = new int[Partie.getNbDigit()];
		System.out.println("Mode défenseur actif\n");
		System.out.println("Entrez une combinaison secrète de " + getNbDigit() + " chiffres");
		combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit()); // Le joueur
																										// propose sa
																										// combinaison
																										// secrète
		this.combDefenseur = convertCombString(this.combJoueur);
		propMachine = alea.aleatoire(getNbDigit());// La machine choisit/propose une combinaison
		// aléatoirement
		String propositionMachine = "";
		for (int i = 0; i < getNbDigit(); i++) { // Affichage du tableau de décompasition de la combinaison
														// machine

			propositionMachine = propositionMachine + propMachine[i];

		}
		System.out.printf("Combinaison machine: %s\n", propositionMachine);

		resMachine = this.evaluer(combJoueur, propMachine,"Machine","Joueur"); 	// Evaluation du résultat associé à la
																		// combinaison proposée par la machine
																		// System.out.println("Résultat:"+Mode.resMachine);
		Partie.afficheur.resultat(getResMachine(), getNbDigit()); // On affiche le résultat de l'évaluation: "+-+...-+"
		tentatives--;
		
		do {

			propMachine = this.tirage(propMachine, getResMachine());
			resMachine = this.evaluer(combJoueur, propMachine,"Machine","Joueur");
			Partie.afficheur.resultat(getResMachine(), getNbDigit()); // On affiche le résultat de l'évaluation: "+-+...-+"
			tentatives--;
		}

		while (tentatives > 0 && !isResultatFinal());
		setPerdant(this.attaquant);
		return resultatFinal;

	}

}