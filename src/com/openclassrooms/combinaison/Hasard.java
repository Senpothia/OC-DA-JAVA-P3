package com.openclassrooms.combinaison;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/** 
 * Classe fournissant trois méthodes pour générer des entiers aléatoires
 * @author Michel Lopez
 * @version 1.0
 */

public class Hasard {
	private static final Logger logger = LogManager.getLogger(Hasard.class);
	public Random r = new Random();
/**
 * Retourne un nombre entier inférieur à 10 et supérieur à un entier founi en paramètre
 * @param val
 * 				Valeur entière indiquant la valeur mininmum du digit demandé
 * @return Entier compris entre la valeur donnée en paramètre et 9 inclus
 */

	public int plus(int val) {
		logger.info("Méthode plus() - Détermination digit valeur supérieur");
		int hasard = (r.nextInt(9 - (val + 1) + 1) + (val + 1));  // [(max - min + 1) + min] avec max=9 et min=val+1

		//System.out.println("Une valeur supérieure à " + val + ":" + hasard);
		return hasard;

	}
	
	/**
	 * Retourne un nombre entier inférieur à l'entier passé en paramètre et zéro inclus 
	 * @param val
	 * 				Valeur entière indiquant la valeur maximale du digit demandé
	 * @return Entier compris entre la valeur donnée en paramètre et 0 inclus
	 */

	public int moins(int val) {
		logger.info("Méthode moins() - Détermination digit valeur inférieure");
		int hasard = (r.nextInt(val));
		//System.out.println("Une valeur inférieure à " + val + ":" + hasard);
		return hasard;

	}
	
	/** 
	 * Fourni un tableau d'entier compris entre 0 et 9 inclus. La taille du tableau est définie 
	 * par l'entier passé en paramètre 
	 * @param nbDigit
	 * 				Nombre de digit contenus dans la combinaison requise
	 * @return Un tableau de int contenant les digits de la combinaison tirée aléatoirement
	 */

	public int[] aleatoire(int nbDigit) {
		logger.info("Méthode aleatoire() - Détermination digit aléatoirement");
		int[] tabDigit = new int[nbDigit];

		for (int i = 0; i < nbDigit; i++) {
			int min = 0;
			int max = 9;
			tabDigit[i] = r.nextInt(max - min + 1) + min;
			// System.out.println(tabDigit[i]);

		}

		return tabDigit;
	}

}