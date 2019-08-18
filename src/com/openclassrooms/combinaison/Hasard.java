package com.openclassrooms.combinaison;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/** 
 * Classe fournissant trois m�thodes pour g�n�rer des entiers al�atoires
 * @author Michel Lopez
 * @version 1.0
 */

public class Hasard {
	private static final Logger logger = LogManager.getLogger(Hasard.class);
	public Random r = new Random();
/**
 * Retourne un nombre entier inf�rieur � 10 et sup�rieur � un entier founi en param�tre
 * @param val
 * 				Valeur enti�re indiquant la valeur mininmum du digit demand�
 * @return Entier compris entre la valeur donn�e en param�tre et 9 inclus
 */

	public int plus(int val) {
		logger.info("M�thode plus() - D�termination digit valeur sup�rieur");
		int hasard = (r.nextInt(9 - (val + 1) + 1) + (val + 1));  // [(max - min + 1) + min] avec max=9 et min=val+1

		//System.out.println("Une valeur sup�rieure � " + val + ":" + hasard);
		return hasard;

	}
	
	/**
	 * Retourne un nombre entier inf�rieur � l'entier pass� en param�tre et z�ro inclus 
	 * @param val
	 * 				Valeur enti�re indiquant la valeur maximale du digit demand�
	 * @return Entier compris entre la valeur donn�e en param�tre et 0 inclus
	 */

	public int moins(int val) {
		logger.info("M�thode moins() - D�termination digit valeur inf�rieure");
		int hasard = (r.nextInt(val));
		//System.out.println("Une valeur inf�rieure � " + val + ":" + hasard);
		return hasard;

	}
	
	/** 
	 * Fourni un tableau d'entier compris entre 0 et 9 inclus. La taille du tableau est d�finie 
	 * par l'entier pass� en param�tre 
	 * @param nbDigit
	 * 				Nombre de digit contenus dans la combinaison requise
	 * @return Un tableau de int contenant les digits de la combinaison tir�e al�atoirement
	 */

	public int[] aleatoire(int nbDigit) {
		logger.info("M�thode aleatoire() - D�termination digit al�atoirement");
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