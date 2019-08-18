package com.openclassrooms.combinaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe principale. Lance l'application et traite les paramètres transmis.
 * paramètre:
 * dev : active le mode dévéloppeur (affiche la combinaison défenseur dès le départ)
 * aucun paramètre : active mode standard
 * 
 * @author Michel Lopez
 * @version 1.0
 *
 */

public class Gameplay {

	private static final Logger logger = LogManager.getLogger(Gameplay.class);

	public static void main(String[] args) {


		logger.info("Lancement application");

		int status = 0;

		Partie partie = new Partie();
		partie.init();

		if (args.length > 0 && args[0].equals("dev")) {
			// System.out.println("Argument dev=true");
			partie.setDev(true);
			logger.debug("Application mode développeur");
		} else {
			logger.debug("Mode standard");
		}
		while (status == 0) {

			status = partie.jouer();
			logger.debug("Lancement de la partie");
			switch (status) {

			case 0: // On rejoue la partie dans le même mode
				logger.debug("Répétition de la partie sans changement de mode");
				break;

			case 1: // On rejoue dans un autre mode
				status = 0;
				partie.setModeActif(false);
				logger.debug("Répétition de la partie après changement de mode");
				break;

			case 2: // On arrête la partie
				System.out.println("Au revoir!");
				logger.debug("Fin du programme");
				status = 1;
			}

		}

	}
}
