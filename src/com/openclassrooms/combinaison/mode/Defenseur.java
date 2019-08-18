package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * D�finit le mode de jeu d�fenseur
 * H�rite de la classe Mode
 * Fonctionnement: la Machine cherche � deviner une combinaison 
 * choisie al�atoirement par le joueur
 * Redefinit la m�thode abstraite jouer() de la classe Mode 
 * @author Michel Lopez
 *  @version 1.0
 *
 */
public class Defenseur extends Mode {

	/** 
	 * Constructeur 
	 * @param nbDigit
	 * @param nbTentatives
	 */
	static Logger logger = LogManager.getLogger(Defenseur.class);
	public Defenseur (int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Constructeur Defenseur()");
		this.nbDigit = nbDigit;
		this.nbTentatives=nbTentatives;
		this.dev=dev;
		
	}
	/**
	 *  Impl�ment la m�thode abstraite de la classe Mode pour d�finir
	 *  la s�quence de jeu selon le mode D�fenseur
	 *  @return resultatFinal
	 *					Indique l'issue de la partie (gagn�e/perdue)
	 */
	@Override
	public boolean jouer() {
		logger.debug("M�thode jouer() - Mode Defenseur");
		int tentatives = getNbTentatives();
		//int[] nouvelleCombinaison = new int[Partie.getNbDigit()];
		System.out.println("Mode d�fenseur actif\n");
		System.out.println("Entrez une combinaison secr�te de " + getNbDigit() + " chiffres");
		combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit()); // Le joueur
																										// propose sa
																										// combinaison
																										// secr�te
		propMachine = alea.aleatoire(getNbDigit());// La machine choisit/propose une combinaison
		// al�atoirement
		String propositionMachine = "";
		for (int i = 0; i < getNbDigit(); i++) { // Affichage du tableau de d�compasition de la combinaison
														// machine

			propositionMachine = propositionMachine + propMachine[i];

		}
		System.out.printf("Combinaison machine: %s\n", propositionMachine);

		resMachine = this.evaluer(combJoueur, propMachine,"Machine"); 	// Evaluation du r�sultat associ� � la
																		// combinaison propos�e par la machine
																		// System.out.println("R�sultat:"+Mode.resMachine);
		Partie.afficheur.resultat(getResMachine(), getNbDigit()); // On affiche le r�sultat de l'�valuation: "+-+...-+"
		tentatives--;
		
		do {

			propMachine = this.tirage(propMachine, getResMachine());
			resMachine = this.evaluer(combJoueur, propMachine,"Machine");
			Partie.afficheur.resultat(getResMachine(), getNbDigit()); // On affiche le r�sultat de l'�valuation: "+-+...-+"
			tentatives--;
		}

		while (tentatives > 0 && !isResultatFinal());

		return resultatFinal;

	}

}