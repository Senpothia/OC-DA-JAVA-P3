package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Hasard;
import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Classe abstraite définisant un méthode abstraite jouer() variable selon le mode de jeu choisi.
 * La classe contient les méthodes communes à tous les modes héritées par les classes qui implémentent les modes
 * de jeu.
 * @author Michel Lopez
 * @version 1.0
 *
 */


public abstract class Mode {
	/**
	 * Instance de la classe Hasard définissant l'objet de traitement des saisie au clavier.
	 * @see Hasard.java
	 */
	static Logger logger = LogManager.getLogger(Mode.class);
	public Hasard alea = new Hasard();

	protected int[] combJoueur; // Combinaison secrète Joueur/Joueur
	protected int[] combMachine; // Combinaison secrète Machine/Joueur2
	protected int[] propJoueur = new int[this.nbDigit]; // Proposition Joueur/Joueur 1
	protected int[] propMachine = new int[this.nbDigit]; // Proposition Machine/Joueur 2
	protected String[] resJoueur;
	protected String[] resMachine;
	protected String combDefenseur;    // Combinaison secrète à afficher en cas de défaite de l'attaquant
	protected boolean actif = false;
	protected boolean dev = false; 
	/**
	 * Nom du vainqueur
	 */
	protected String vainqueur;
	/**
	 * Nom du perdant
	 */
	protected String perdant;
	/**
	 * Nom de l'attaquant
	 */
	protected String attaquant;
	/**
	 * Nom du défenseur
	 */
	protected String defenseur;
	/**
	 * Nonbre de chiffre de la combinaison
	 */
	protected int nbDigit;
	/**
	 * Nombre de tentatives
	 */
	protected int nbTentatives;
	/**
	 * Indicateur de résultat finale
	 */
	boolean resultatFinal;
	

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public boolean isActif() {
		return actif;
	}

	public int[] getCombJoueur() {
		return combJoueur;
	}

	public int[] getCombMachine() {
		return combMachine;
	}

	public String[] getResJoueur() {
		return resJoueur;
	}

	public String[] getResMachine() {
		return resMachine;
	}
	
	
	
	public String getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(String vainqueur) {
		this.vainqueur = vainqueur;
	}

	public String getPerdant() {
		return perdant;
	}

	public void setPerdant(String perdant) {
		this.perdant = perdant;
	}
	
	public String getCombDefenseur() {
		return combDefenseur;
	}

	public void setCombDefenseur(String combDefenseur) {
		this.combDefenseur = combDefenseur;
	}

	/**
	 * Convertit un tableau de string en String
	 *
	 * @param comb
	 * 			Tableau représentant une combinaison
	 * @return
	 * 			String représentant une combinaison
	 */

	public String convertCombString(int[] comb) {

		String combinaison = "";

		for (int i = 0; i < comb.length; i++) {

			combinaison = combinaison + comb[i];
		}

		return combinaison;

	}
	/**
	 * Méthode abstraite définissant le mode de jeu
	 * @return
	 * 			boolean indiquant le résultat de la partie
	 */

	public int getNbTentatives() {
		return nbTentatives;
	}

	public void setNbTentatives(int nbTentatives) {
		this.nbTentatives = nbTentatives;
	}

	public int getNbDigit() {
		return nbDigit;
	}

	public void setNbDigit(int nbDigit) {
		this.nbDigit = nbDigit;
	}

	

	public boolean isResultatFinal() {
		return resultatFinal;
	}

	public void setResultatFinal(boolean resultatFinal) {
		this.resultatFinal = resultatFinal;
	}
	
	public abstract boolean jouer();
	
	/**
	 * Comparaion la proposition de l'attaquant avec la combinaison secrète du défenseur et retourne une 
	 * chaine de caractère associée au résultat. 
	 * "-": indique que le chiffre de la combinaison secrète est inférieur au chiffre de la combinaison
	 * proposée.
	 * "+": indique que le chiffre de la combinaison secrète est supérieur au chiffre de la combinaison
	 * proposée.
	 * "=": indique que le chiffre de la combinaison secrète est égale au chiffre de la combinaison
	 * proposée.
	 * @param combDefenseur
	 * 						Combinaison secrète du défenseur
	 * @param combAttaquant
	 * 						Combinaison proposée par l'attaquant
	 * @param attaquant
	 * 						Identifie l'attaquant pour signaler le vainqueur
	 * @return
	 * 			une String représentant le résultat de la comparaison entre combinaison secrète et 
	 * 			combinaison proposée
	 */

	String[] evaluer(int[] combDefenseur, int[] combAttaquant, String attaquant, String defenseur) { // arguments: (combMachine, combJouer)
		logger.debug("Méthode evaluer() - Classe Mode");
		String[] resultat = new String[nbDigit]; // Tableau contenant résultat d'évaluation: "++-+-...-+"
		int nbDigitOk = 0;

		for (int i = 0; i < nbDigit; i++) {

			if (combDefenseur[i] == combAttaquant[i]) {

				resultat[i] = "=";
				nbDigitOk++;
			}

			else if (combDefenseur[i] < combAttaquant[i]) {

				resultat[i] = "-";
			}

			else {

				resultat[i] = "+";
			}

		}

		if (nbDigitOk == nbDigit) { // Toutes les chiffres de la combinaison ont été deviné

			this.setResultatFinal(true); // La partie est finie: l'attaquant a gagné
			this.setVainqueur(attaquant);
			this.setPerdant(defenseur);
			return resultat;
		}
		this.setPerdant(defenseur);
		return resultat;
	}
	
	/**
	 * Fournit une combiaison sur la base du résultat transmis en paramètre
	 * @param combinaison
	 * 					Combinaison antérieurement proposée
	 * @param resultat
	 * 					Résultat de la combinaison antérieurement proposée
	 * @return un tableau de int contenant les chiffres de la combinaison choisie sur la base du résultat
	 * 			antérieur fournit en paramètre
	 */

	int[] tirage(int[] combinaison, String[] resultat) {
		logger.debug("Méthode tirage() - Classe Mode");
		int[] nouvelleCombinaison = new int[this.nbDigit];
		for (int i = 0; i < this.nbDigit; i++) {

			if (resultat[i] == "-") {
				nouvelleCombinaison[i] = alea.moins(combinaison[i]);
			}

			if (resultat[i] == "+") {
				nouvelleCombinaison[i] = alea.plus(combinaison[i]);
			}

			if (resultat[i] == "=") {
				nouvelleCombinaison[i] = combinaison[i];
			}

		}

		String comb = "";
		for (int i = 0; i < this.nbDigit; i++) {

			comb = comb + nouvelleCombinaison[i];
		}

		System.out.printf("Proposition Machine: %s\n", comb);
		return nouvelleCombinaison;
	}

}