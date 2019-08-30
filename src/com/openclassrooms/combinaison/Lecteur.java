package com.openclassrooms.combinaison;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * D�finit un objet lecteur pour la saisie et le traitement des informations
 * entr�es au clavier par l'utilisateur
 * 
 * @author Michel Lopez
 * @version 1.0
 *
 */

public class Lecteur {

	Scanner sc = new Scanner(System.in);
	private static final Logger logger = LogManager.getLogger(Lecteur.class);
	String str;
	int combinaison;

	/**
	 * Fait la lecture de la combinaison du joueur
	 * 
	 * @param nbChiffres Nombre de caract�res � saisir au clavier
	 * @return La combinaison saisie au clavier par l'utilisateur ou un code associ�
	 *         � un choix selon le contexte
	 */

	public int lecture(int nbChiffres) {

		boolean combOk = false;
		int combinaison = 0;
		logger.info("M�thode lecture() - lecture combinaison saisie au clavier");
		do {

			str = sc.nextLine();

			try {

				combinaison = Integer.parseInt(str); // Saisie compatible avec un int?

				// traitement d'une saisie compatible avec un int

				if (nbChiffres == -1) { // On attend o/n mais on a tap� un chiffre

					System.out.println("R�ponse invalide! Voulez-vous rejouer la partie? o/n");
				}

				if (nbChiffres > 1) { // On attend la saisie d'un int (combinaison de jeu)

					if (str.length() == nbChiffres) { // Oui et longueur de saisie comforme
						combOk = true;

					} else // Longueur de saisie non-conforme

						System.out.printf(
								"R�ponse invalide!\nVeuillez entrez une combinaison de %d chiffres exactement.\n",
								nbChiffres);
				}

			}

			catch (java.lang.NumberFormatException e) {
				if (nbChiffres > 0) { // Erreur de saisie sur un nombre entier attendu. La saisie n'est pas
					// convertible en un int
					System.out.printf("R�ponse invalide!\nVeuillez entrez une combinaison de %d chiffres.\n",
							nbChiffres);

				} else {

					if (str.equals("o") || str.equals("O")) {
						combOk = true;
						return -1;

					}

					if (str.equals("n") || str.equals("N")) {
						combOk = true;
						return -2;
					} else {
						System.out.println("R�ponse invalide! Voulez-vous rejouer la partie? o/n");
					}
				}
			}

		} while (!combOk);

		return combinaison;

	}

	/**
	 * G�re la saisie au clavier d'un nombre entier attendu
	 * 
	 * @param nbChiffres Nombre de caract�res entiers attendus dans la saisie au
	 *                   clavier
	 * @return Valeur entier obtenue � partir du clavier
	 */

	public int lectureInt(int nbChiffres) {

		boolean combOk = false;
		int entier = 0;
		logger.info("M�thode lectureInt() - Lecture d'une valeur enti�re au clavier");

		do {

			try {

				entier = sc.nextInt();
				// System.out.println("Entier: "+entier);

				// traitement d'une saisie compatible avec un int

				if (nbChiffres == 1) { // On attend la saisie du mode de jeu (valeur de 1 � 4)

					if (entier == 99) {

						return -1;
					}

					if (entier >= 1 && entier <= 4) { // Oui et longueur de saisie comforme

						combOk = true;
						// sc.nextLine();

					} else {// Longueur de saisie non-conforme

						System.out.printf("R�ponse invalide!\nFaites un choix entre 1 et 4.\n", nbChiffres);
						// sc.nextLine();
					}
				}
				if (nbChiffres == -2) { // On attend un nbre entre 1 et 99 (nbre de tentatives)

					if (entier > 0 && entier < 100) { // Saisie du nbre de tentatives entre 1 et 99)
						combOk = true;
						// sc.nextLine();

					} else

					{
						System.out.println(
								"R�ponse invalide!\nVeuillez entrez une combinaison de 1 ou 2 chiffres exactement.");
						// sc.nextLine();
					}
				}
				if (nbChiffres == -1) { // On attend un nbre entre 1 et 9 (taille de la combinaison)

					if (entier > 0 && entier < 10) { // Saisie du nbre de tentatives entre 1 et 99)
						combOk = true;
						// sc.nextLine();

					} else

					{
						System.out.println(
								"R�ponse invalide!\nVeuillez entrez une combinaison de 1 ou 2 chiffres exactement.");
						// sc.nextLine();
					}
				}
			}

			catch (java.util.InputMismatchException e) { // Erreur de saisie
				// System.out.println("Entier: "+entier);
				System.out.printf("R�ponse invalide!\nVeuillez entrez une combinaison de %d chiffres.\n", nbChiffres);
				// sc.nextLine();
			}

			sc.nextLine();
		} while (!combOk);

		return entier;
	}

	/**
	 * D�compose un int correspondant � une combinaison de n chiffres en un tableau
	 * de taille n contenant chaque chiffre du int pass� en param�tre
	 * 
	 * @param combinaison Combinaison � d�composer
	 * @param nbDigit     Taille de la combinaison � d�composer
	 * @return un tableau de int de m�me taille que le int pass� en param�tre
	 */

	public int[] decomposition(int combinaison, int nbDigit) {

		double comb = combinaison;

		int[] digit = new int[nbDigit];
		double reste = 0;
		// System.out.println(combinaison);
		logger.info("M�thode decomposition() - Decomposition des digit saisis dans la combinaison");
		for (int i = 0; i < nbDigit; i++) {

			reste = comb % (Math.pow(10.0, (nbDigit - 1 - i)));

			digit[i] = (int) ((comb - reste) / (Math.pow(10.0, (nbDigit - 1 - i))));

			comb = reste;
		}

		return digit;
	}
}