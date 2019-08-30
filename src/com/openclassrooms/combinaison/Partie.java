package com.openclassrooms.combinaison;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.openclassrooms.combinaison.mode.Challenger;
import com.openclassrooms.combinaison.mode.Defenseur;
import com.openclassrooms.combinaison.mode.Duel;
import com.openclassrooms.combinaison.mode.Humains;
import com.openclassrooms.combinaison.mode.Mode;
import com.openclassrooms.combinaison.mode.ModeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gère le déroulement intégrale de la partie depuis le lancement jusqu'au résultat final
 * 
 * @author Michel Lopez
 *
 */

public class Partie {
	
	static Logger logger = LogManager.getLogger(Partie.class);
	/**
	 * Fabrique du mode de jeu. Retourne une instance de type mode qui definit la
	 * stratégie de jeu
	 */
	
	private ModeFactory modeFactory = new ModeFactory();
	/**
	 * Objet Mode. Contient la stratégie/mode de jeu
	 */
	private Mode mode;
	/**
	 * Objet de classe Lecteur chargé de la saisie et traitement des informations
	 * saisie au clavier par l'utilisateur
	 */
	public static Lecteur lecteur = new Lecteur();
	/**
	 * Objet chargé de la gestion des messages pour interagir avec l'utlisateur
	 */
	public static Afficheur afficheur = new Afficheur();

	/**
	 * Flag pour gerer la fin de partie et renouvellement de la partie
	 */
	private int nouvellePartie = 0;
	/**
	 * Flag pour gérer un nouveau mode en fin de partie
	 */
	private int nouveauMode = 0;

	/**
	 * Taille de la combinaison de jeu
	 */

	private int nbDigit = 0;

	/**
	 * Nombre de tentatives
	 */
	private int nbTentatives = 0;

	/**
	 * Flag indicateur d'état du mode développeur
	 */

	private boolean dev = false;

	/**
	 * Flag indicateur status initialisation
	 */
	private boolean statusInit = false;
	/**
	 * Flag indicateur de mode actif. Utile pour conserver le mode en cas de
	 * répétition de la partie sans changement de mode
	 */
	private boolean modeActif = false;

	/**
	 * Méthode d'initialisation. Lecture du fichier properties. Chargement des
	 * paramètres par défaut
	 */

	public void init() {
		logger.debug("Méthode init() - Init partie");
		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// Chargement du fichier properties
			prop.load(input);

			// Récupération des paramètres

			// System.out.println(prop.getProperty("nbDigit"));
			// System.out.println(prop.getProperty("nbTentatives"));
			// System.out.println( prop.getProperty("modeDev"));

			String modeDev = prop.getProperty("modeDev");
			if (modeDev.equals("false")) {
				// System.out.println("Faux");
				this.dev = false;
			} else {
				// System.out.println("Vrai");
				this.dev = true;
			}

			this.nbDigit = Integer.parseInt(prop.getProperty("nbDigit"));
			this.nbTentatives = Integer.parseInt(prop.getProperty("nbTentatives"));
			this.statusInit = true;
			// System.out.println("Affichage valeur initialisation, taille, tentative, mode
			// dev");
			// System.out.println(this.nbDigit);
			// System.out.println(this.nbTentatives);
			// System.out.println(this.dev);

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Méthode principale de gestion de la partie
	 */

	public int jouer() {
		logger.debug("Méthode jouer() - exécution de la partie");
		int status = 0;
		if (!modeActif) {
			afficheur.accueil(this.dev, this.statusInit, this.nbDigit, this.nbTentatives);
			int choixMode = lecteur.lectureInt(1); // Interrogation sur le choix de mode (1 à 4)
			if (choixMode == -1 && !this.dev) { // Mode développeur activé

				this.dev = true;
				afficheur.accueil(this.dev, this.statusInit, this.nbDigit, this.nbTentatives);
				choixMode = lecteur.lectureInt(1);
			}
			if (!this.statusInit) {

				afficheur.nbdigit();
				int nbDigit = lecteur.lectureInt(-1); // Interrogation sur taille de la combinaison (1 à 9)
				// System.out.println(Partie.nbDigit);
				afficheur.tentatives();
				int nbTentatives = lecteur.lectureInt(-2);// Interrogation sur nombre de tentatives (1 à 99)
				// System.out.println(Partie.nbTentatives);
			}

			mode = modeFactory.getMode(choixMode, this.nbDigit, this.nbTentatives, this.dev);
			// setMode(mode);
			// mode.setActif(true);
			this.statusInit = true;
			this.modeActif = true;

		}

		mode.jouer(); // On joue la partie selon le mode choisi

		afficheur.resultatFinal(mode.isResultatFinal(), mode.getVainqueur(),mode.getPerdant(),mode.getCombDefenseur());
		afficheur.finDePartie(); // On interroge sur une nouvelle partie
		mode.setResultatFinal(false); // Réinitialisation résultat de partie
		nouvellePartie = lecteur.lecture(-1);

		switch (nouvellePartie) {

		case -1: // On rejoue la partie

			System.out.println("Voulez-vous changer de mode?");
			nouveauMode = lecteur.lecture(-1);

			switch (nouveauMode) {

			case -1: // On change de mode
				setModeActif(false);
				status = 1;
				break;

			case -2: // On ne change pas de mode
				status = 0;
			}

			break;

		case -2: // On ne rejoue pas la partie. Fin de l'application

			status = 2;
		}
		this.nouvellePartie = 0;
		this.nouveauMode = 0;
		return status;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public void setModeActif(boolean modeActif) {
		this.modeActif = modeActif;
	}

}