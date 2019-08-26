package com.openclassrooms.combinaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gestion des messages d�livr�s � l'utilisateur pour informer, saisir les
 * donn�es utiles au d�roulement du programme
 * 
 * @author Michel Lopez
 * @version 1.0
 */

public class Afficheur {
	/**
	 * Afficher le message d'accueil au lancement du programme
	 * 
	 * @param dev          flag du mode active. True: mode d�v�loppeur; False: mode
	 *                     standard
	 * @param statusInit   flag indiquant le traitement d'une configuration par
	 *                     d�faut � partir d'une fichier de configuration properties
	 * @param nbDigit      Nombre de digits requis pour la combinaion de jeu
	 * @param nbTentatives Nombre de tentatives permises pour d�couvrir la
	 *                     combinaison du d�fenseur
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
		System.out.println("2. Mode d�fenseur");
		System.out.println("3. Mode duel");
		System.out.println("4. Mode humains");
		System.out.println("");

		logger.info("M�thode accueil - Affichage message d'initialisation");

		if (statusInit) {

			System.out.printf(
					"Les param�tres par d�faut sont activ�s:\n\nTaille de la combinaison: %d\nNombre de tentatives: %d\n\n",
					nbDigit, nbTentatives);

		}

		if (dev) {
			System.out.println("MODE DEVELOPPEUR ACTIF!");
		}
		System.out.println("Bonjour!\nVeuillez svp choisir un mode de jeu!");

	}

	/**
	 * Affiche la demande li�e � la taille de combinaison souhait�e
	 */
	public void nbdigit() {
		logger.info("M�thode nbdigit() - Demande du nombre de digit de la combinaison");
		System.out.println("");
		System.out.println("Entrer la taille de la combinaison (1 � 9).");

	}

	/**
	 * Affiche la demande li�e au nombre de tentatives souhait�es dans la s�quence
	 * de jeu
	 */

	public void tentatives() {
		logger.info("M�thode tentatives - Demande nombres de tentatives");
		System.out.println("");
		System.out.println("Entrer le nombre de tentatives.");
	}

	/**
	 * Affiche le r�sultat � la proposition de combinaison transmise
	 * 
	 * @param resultat Tableau de String � convertir en symboles +, -
	 * @param nbDigit  Nombre de digit de la combinaison analys�e
	 */
	public void resultat(String[] resultat, int nbDigit) {
		logger.info("M�thode resultat - Affichage r�sultat de la tentative");
		String[] tabResJoueur = new String[nbDigit];
		String resJoueur = "";
		tabResJoueur = resultat;

		for (int i = 0; i < nbDigit; i++) {

			resJoueur = resJoueur + tabResJoueur[i];

		}

		System.out.println(resJoueur);

	}

	/**
	 * Affiche le r�sultat final d'une partie
	 * 
	 * @param boolean resultat indique si la partie est gagn�e ou perdue
	 * @param String  vainqueur indique le nom du vainqueur
	 */
	void resultatFinal(boolean resultat, String vainqueur, String Perdant, String ComDefenseur) {
		logger.info("M�thode resultat - Affichage r�sultat final de la partie");
		System.out.println("");

		if (resultat) {

			System.out.printf("Bravo, %s! Vous avez gagn�!", vainqueur);

		}

		else {

			System.out.println("");
			System.out.printf("Dommage! %s, vous avez perdu!\n", Perdant);
			System.out.println("Voici la combinaison d�fenseur: " + ComDefenseur );
		}

	}

	/**
	 * Affiche le message pour demander � l'utilsateur s'il veut rejouer une partie
	 * avec les m�mes param�tres de configuration
	 */
	void finDePartie() {
		logger.info("M�thode finDePartie() - Interroge sur renouvellement de la partie");
		System.out.println("");
		System.out.println("Voulez-vous rejouer? o/n");

	}

}