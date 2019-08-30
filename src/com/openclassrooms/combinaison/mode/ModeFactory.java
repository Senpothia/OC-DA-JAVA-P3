package com.openclassrooms.combinaison.mode;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Classe dédiée à la contruction des modes de jeu
 * 
 * @author Michel Lopez
 *
 */
public class ModeFactory {

	static Logger logger = LogManager.getLogger(ModeFactory.class);
	
	/**
	 * Classe dediée à la génération du mode de jeu.<br>
	 * Utilisée par la classe Partie pour obtenir une instance de l'attribut mode.
	 * @param choixMode
	 * @param nbDigit
	 * @param nbTentatives
	 * @param dev
	 * @return Retourne à la classe cliente le mode de jeu souhaité 
	 */
	
	public Mode getMode(int choixMode, int nbDigit, int nbTentatives, boolean dev) {
		logger.debug("Méthode getMode() - Classe ModeFactory");
		Mode mode = null;

		switch (choixMode) { // On configure la partie dans le mode voulu

		case 1:

			mode = new Challenger(nbDigit, nbTentatives, dev);
			break;

		case 2:

			mode = new Defenseur(nbDigit, nbTentatives, dev);
			break;

		case 3:

			mode = new Duel(nbDigit, nbTentatives, dev);
			break;

		case 4:

			mode = new Humains(nbDigit, nbTentatives, dev);
			break;

		}

		return mode;
	}

}