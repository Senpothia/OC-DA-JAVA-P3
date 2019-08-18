package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * D�finit le mode de jeu Duel
 * H�rite de la classe Mode
 * Fonctionnement: la Machine cherche � deviner une combinaison 
 * choisie al�atoirement par le joueur
 * Redefinit la m�thode abstraite jouer() de la classe Mode 
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
	 *  Impl�ment la m�thode abstraite de la classe Mode pour d�finir
	 *  la s�quence de jeu selon le mode Duel
	 *  @return resultatFinal
	 *  					Indique l'issue de la partie (gagn�e/perdue)
	 */

	@Override
	public boolean jouer() {

		logger.debug("M�thode jouer() - Mode Duel");
		System.out.println("Mode Duel actif");
		int tentatives = getNbTentatives();

		// Le joueur entre une combinaison secr�te
		System.out.printf("Entrez une combinaison secr�te de %d chiffres\n", getNbDigit());
		// lecture-D�composition combinaison secr�te Joueur1
		combJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
		// La Machine choisit une combinaison secr�te
		combMachine = alea.aleatoire(getNbDigit());

		// Affichage combinaison Machine
		if (dev) {
			System.out.println("Combinaison secr�te de la Machine: " + convertCombString(combMachine));
		}

		System.out.printf("Joueur: Entrez une combinaison de %d chiffres\n", getNbDigit());
		// Joueur fait une proposition
		propJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
		// D�composition-Evaluation Joueur
		resJoueur = this.evaluer(combMachine,propJoueur,"");
		// Afficher r�sultat Joueur
		Partie.afficheur.resultat(getResJoueur(), getNbDigit());
		// Machine fait une proposition
		propMachine = alea.aleatoire(getNbDigit());
		System.out.println("Proposition Machine: " + convertCombString(propMachine));
		resMachine = this.evaluer(combJoueur, propMachine,"Machine");
		Partie.afficheur.resultat(getResMachine(), getNbDigit());// On affiche le r�sultat de l'�valuation: "+-+...-+"
		tentatives--;
		do {

			// Label1:
			// Joueur fait une proposition
			System.out.printf("Joueur: Entrez une combinaison de %d chiffres\n", getNbDigit());
			propJoueur = Partie.lecteur.decomposition(Partie.lecteur.lecture(getNbDigit()),getNbDigit());
			// D�composition-Evaluation Joueur
			resJoueur = this.evaluer(combMachine, propJoueur,"");

			// Afficher r�sultat Joueur
			Partie.afficheur.resultat(getResJoueur(), getNbDigit());

			// Machine fait une proposition
			propMachine = this.tirage(propMachine, getResMachine());
			resMachine = this.evaluer(combJoueur, propMachine,"Machine");
			Partie.afficheur.resultat(getResMachine(), getNbDigit()); // On affiche le r�sultat de l'�valuation: "+-+...-+"

			tentatives--;
			// Reboucler label1

		} while (tentatives > 0 && !isResultatFinal());


		return resultatFinal;
	}

}