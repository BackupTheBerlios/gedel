/*
 * Created on 5 sept. 2004
 *
 */
package ecole.gui.utils;

import ecole.gui.EcoleApp;

/**
 * Cette classe implémente un pattern Pont, et permet de relier
 * 2 classes entre elles, ici un traitement et un affichage (barre de progession). 
 * @author jemore
 *
 */
public class Callbacker
{
	private EcoleApp ihm;
	
	/**
	 * Constructeur
	 * @param ihm Fenêtre principale
	 */
	public Callbacker(EcoleApp ihm)
	{
		this.ihm = ihm;
	}
	
	/**
	 * Ecriture d'une log d'info
	 * @param str log a ecrire
	 * @author jemore
	 */
	public void logInfo(String str)
	{
		ihm.setStatus(str);
	}
	
	/**
	 * Initialisation de la barre de progression
	 * @param maxValue valeur maximal que peut atteindre la barre. <0 si pas de valeur max
	 * @author jemore
	 */
	public void progressBarInit(int maxValue)
	{
		ihm.progressBarInit(maxValue);		
	}

	/**
	 * Positionne une valeur sur la barre deprogression
	 * @param v valeur a positionner
	 * @author jemore
	 */	
	public void progressBarSetValue(int v)
	{
		ihm.progressBarSetValue(v);
	}
}
