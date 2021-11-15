package partieDeJeux;

import java.util.ArrayList;

import joueur.Coup;
import joueur.Joueur;

public class HistoriqueJoueur {
	private ArrayList<Coup> coupList = new ArrayList<>();
	private ArrayList<Resultat> resultatList = new ArrayList<>();
	
	public void addCoupRes(Joueur joueur) {
		coupList.add(joueur.getCoup());
		resultatList.add(joueur.getResultat());	
	}
	
	public Resultat getResultat (int index) {
		return resultatList.get(index);
		
	}
	public Coup getCoup (int index) {
		return coupList.get(index);
		
	}
	
	public ArrayList<Coup> getListCoup(){
		return this.coupList;
	}
	
	public ArrayList<Resultat> getListResultat(){
		return this.resultatList;
	}
	
	public int calculPoint() {

		int point = 0;
		for(Resultat res : resultatList) {
			point += res.getPoint();			
		}
		
		return point;
		
	}
}
