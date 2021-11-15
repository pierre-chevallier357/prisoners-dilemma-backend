package partieDeJeux;
import strategie.*;

import java.util.ArrayList;

import joueur.*;

public class Jeu {
	
	Joueur joueur1 = new Joueur();
	Joueur joueur2 = new Joueur();
	int nb_tour;
	
	HistoriqueJoueur historiqueJ1 = new HistoriqueJoueur();
	HistoriqueJoueur historiqueJ2 = new HistoriqueJoueur();
	
    ArrayList<String> RetourDesResultats = new ArrayList<String>();
	public Jeu() {
		
		this.nb_tour= 5;
		this.joueur1.setNom("Pierre");
		this.joueur2.setNom("Loris");
		joueur1.setConnect(false);

		joueur2.setConnect(false);
		for(int i=0; i<nb_tour; i++) {
			if(joueur1.isConnect() == false) {
				Strategie stategieJ1;
				stategieJ1= new VraiPacificateur();
				Strategie strategieJ2;
				strategieJ2= new Aleatoire();
				
				joueur1.setCoup(stategieJ1.ProchainCoup(joueur1, historiqueJ1.getListCoup(), historiqueJ2.getListCoup()));
				joueur2.setCoup(strategieJ2.ProchainCoup(joueur2, historiqueJ2.getListCoup(), historiqueJ1.getListCoup()));
			}else {
				this.joueur1.setCoup(Coup.COOPERER);
				this.joueur2.setCoup(Coup.TRAHIR);
			}
			
			partieJouee(this.joueur1, this.joueur2);
			
			RetourDesResultats.add(joueur1.getNom()+"   "+joueur1.getResultat()+"  Point: "+ historiqueJ1.calculPoint());

			RetourDesResultats.add(joueur2.getNom()+"   "+joueur2.getResultat()+"  Point: "+historiqueJ2.calculPoint());
		}
		
	}
	public ArrayList<String> renvoiString(){
		return this.RetourDesResultats;
	}
	
	public void partieJouee(Joueur joueur1, Joueur joueur2){
		if (joueur1.getCoup() == joueur2.getCoup()) {
			switch (joueur1.getCoup()) {
			case TRAHIR :
				joueur1.setResultat(Resultat.P);
				joueur2.setResultat(Resultat.P);
				break;
			case COOPERER:
				joueur1.setResultat(Resultat.C);
				joueur2.setResultat(Resultat.C);
				break;
				
			}
		}
		else if ((joueur1.getCoup() == Coup.TRAHIR) && (joueur2.getCoup() == Coup.COOPERER)){
			joueur1.setResultat(Resultat.T);
			joueur2.setResultat(Resultat.D);
		}
		else {
			joueur1.setResultat(Resultat.D);
			joueur2.setResultat(Resultat.T);
		}
		
		historiqueJ1.addCoupRes(joueur1);
		historiqueJ2.addCoupRes(joueur2);
	}

}


