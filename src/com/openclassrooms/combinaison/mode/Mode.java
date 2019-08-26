package com.openclassrooms.combinaison.mode;

import com.openclassrooms.combinaison.Hasard;
import com.openclassrooms.combinaison.Partie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Classe abstraite d�finisant un m�thode abstraite jouer() variable selon le mode de jeu choisi.
 * La classe contient les m�thodes communes � tous les modes h�rit�es par les classes qui impl�mentent les modes
 * de jeu.
 * @author Michel Lopez
 * @version 1.0
 *
 */


public abstract class Mode {
	/**
	 * Instance de la classe Hasard d�finissant l'objet de traitement des saisie au clavier.
	 * @see Hasard.java
	 */
	static Logger logger = LogManager.getLogger(Mode.class);
	public Hasard alea = new Hasard();

	protected int[] combJoueur; // Combinaison secr�te Joueur/Joueur
	protected int[] combMachine; // Combinaison secr�te Machine/Joueur2
	protected int[] propJoueur = new int[this.nbDigit]; // Proposition Joueur/Joueur 1
	protected int[] propMachine = new int[this.nbDigit]; // Proposition Machine/Joueur 2
	protected String[] resJoueur;
	protected String[] resMachine;
	protected String combDefenseur;    // Combinaison secr�te � afficher en cas de d�faite de l'attaquant
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
	 * Nom du d�fenseur
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
	 * Indicateur de r�sultat finale
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
	 * 			Tableau repr�sentant une combinaison
	 * @return
	 * 			String repr�sentant une combinaison
	 */

	public String convertCombString(int[] comb) {

		String combinaison = "";

		for (int i = 0; i < comb.length; i++) {

			combinaison = combinaison + comb[i];
		}

		return combinaison;

	}
	/**
	 * M�thode abstraite d�finissant le mode de jeu
	 * @return
	 * 			boolean indiquant le r�sultat de la partie
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
	 * Comparaion la proposition de l'attaquant avec la combinaison secr�te du d�fenseur et retourne une 
	 * chaine de caract�re associ�e au r�sultat. 
	 * "-": indique que le chiffre de la combinaison secr�te est inf�rieur au chiffre de la combinaison
	 * propos�e.
	 * "+": indique que le chiffre de la combinaison secr�te est sup�rieur au chiffre de la combinaison
	 * propos�e.
	 * "=": indique que le chiffre de la combinaison secr�te est �gale au chiffre de la combinaison
	 * propos�e.
	 * @param combDefenseur
	 * 						Combinaison secr�te du d�fenseur
	 * @param combAttaquant
	 * 						Combinaison propos�e par l'attaquant
	 * @param attaquant
	 * 						Identifie l'attaquant pour signaler le vainqueur
	 * @return
	 * 			une String repr�sentant le r�sultat de la comparaison entre combinaison secr�te et 
	 * 			combinaison propos�e
	 */

	String[] evaluer(int[] combDefenseur, int[] combAttaquant, String attaquant, String defenseur) { // arguments: (combMachine, combJouer)
		logger.debug("M�thode evaluer() - Classe Mode");
		String[] resultat = new String[nbDigit]; // Tableau contenant r�sultat d'�valuation: "++-+-...-+"
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

		if (nbDigitOk == nbDigit) { // Toutes les chiffres de la combinaison ont �t� devin�

			this.setResultatFinal(true); // La partie est finie: l'attaquant a gagn�
			this.setVainqueur(attaquant);
			this.setPerdant(defenseur);
			return resultat;
		}
		this.setPerdant(defenseur);
		return resultat;
	}
	
	/**
	 * Fournit une combiaison sur la base du r�sultat transmis en param�tre
	 * @param combinaison
	 * 					Combinaison ant�rieurement propos�e
	 * @param resultat
	 * 					R�sultat de la combinaison ant�rieurement propos�e
	 * @return un tableau de int contenant les chiffres de la combinaison choisie sur la base du r�sultat
	 * 			ant�rieur fournit en param�tre
	 */

	int[] tirage(int[] combinaison, String[] resultat) {
		logger.debug("M�thode tirage() - Classe Mode");
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