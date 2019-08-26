package com.openclassrooms.combinaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gestion des messages délivrés à l'utilisateur pour informer, saisir les
 * données utiles au déroulement du programme
 * 
 * @author Michel Lopez
 * @version 1.0
 */

public class Afficheur {
	/**
	 * Afficher le message d'accueil au lancement du programme
	 * 
	 * @param dev          flag du mode active. True: mode dévéloppeur; False: mode
	 *                     standard
	 * @param statusInit   flag indiquant le traitement d'une configuration par
	 *                     défaut à partir d'une fichier de configuration properties
	 * @param nbDigit      Nombre de digits requis pour la combinaion de jeu
	 * @param nbTentatives Nombre de tentatives permises pour découvrir la
	 *                     combinaison du défenseur
	 */

	private static final Logger logger = LogManager.getLogger(Afficheur.class);

	public void accueil(boolean dev, boolean statusInit, int nbDigit, int nbTentatives) {

		System.out.println("****************************************************");
		System.out.println("*                                                  *");
		System.out.println("*                    GAMEPLAY                      *");
		System.out.println("*                                                  *");
		System.out.println("****************************************************");
		System.out.println("");

		System.out.println("1. Mode challenger");
		System.out.println("2. Mode défenseur");
		System.out.println("3. Mode duel");
		System.out.println("4. Mode humains");
		System.out.println("");

		logger.info("Méthode accueil - Affichage message d'initialisation");

		if (statusInit) {

			System.out.printf(
					"Les paramètres par défaut sont activés:\n\nTaille de la combinaison: %d\nNombre de tentatives: %d\n\n",
					nbDigit, nbTentatives);

		}

		if (dev) {
			System.out.println("MODE DEVELOPPEUR ACTIF!");
		}
		System.out.println("Bonjour!\nVeuillez svp choisir un mode de jeu!");

	}

	/**
	 * Affiche la demande liée à la taille de combinaison souhaitée
	 */
	public void nbdigit() {
		logger.info("Méthode nbdigit() - Demande du nombre de digit de la combinaison");
		System.out.println("");
		System.out.println("Entrer la taille de la combinaison (1 à 9).");

	}

	/**
	 * Affiche la demande liée au nombre de tentatives souhaitées dans la séquence
	 * de jeu
	 */

	public void tentatives() {
		logger.info("Méthode tentatives - Demande nombres de tentatives");
		System.out.println("");
		System.out.println("Entrer le nombre de tentatives.");
	}

	/**
	 * Affiche le résultat à la proposition de combinaison transmise
	 * 
	 * @param resultat Tableau de String à convertir en symboles +, -
	 * @param nbDigit  Nombre de digit de la combinaison analysée
	 */
	public void resultat(String[] resultat, int nbDigit) {
		logger.info("Méthode resultat - Affichage résultat de la tentative");
		String[] tabResJoueur = new String[nbDigit];
		String resJoueur = "";
		tabResJoueur = resultat;

		for (int i = 0; i < nbDigit; i++) {

			resJoueur = resJoueur + tabResJoueur[i];

		}

		System.out.println(resJoueur);

	}

	/**
	 * Affiche le résultat final d'une partie
	 * 
	 * @param boolean resultat indique si la partie est gagnée ou perdue
	 * @param String  vainqueur indique le nom du vainqueur
	 */
	void resultatFinal(boolean resultat, String vainqueur, String Perdant, String ComDefenseur) {
		logger.info("Méthode resultat - Affichage résultat final de la partie");
		System.out.println("");

		if (resultat) {

			System.out.printf("Bravo, %s! Vous avez gagné!", vainqueur);

		}

		else {

			System.out.println("");
			System.out.printf("Dommage! %s, vous avez perdu!\n", Perdant);
			System.out.println("Voici la combinaison défenseur: " + ComDefenseur );
		}

	}

	/**
	 * Affiche le message pour demander à l'utilsateur s'il veut rejouer une partie
	 * avec les mêmes paramètres de configuration
	 */
	void finDePartie() {
		logger.info("Méthode finDePartie() - Interroge sur renouvellement de la partie");
		System.out.println("");
		System.out.println("Voulez-vous rejouer? o/n");

	}

}