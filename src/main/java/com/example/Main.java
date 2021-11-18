
package com.example;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.rmi.ServerException;
import javax.sql.DataSource;

import com.example.joueur.Joueur;
import com.example.partieDeJeux.Jeu;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Main {
  ArrayList<Joueur> listJoueur = new ArrayList<>();
  ArrayList<Jeu> listPartie = new ArrayList<>();
  @Value("${spring.datasource.url}")
  private String dbUrl;

  @GetMapping("/")
  String index() {
    return "index";
  }

  @GetMapping("/creation-joueur/{nom}")
	public Integer creationJoueur(@PathVariable(value = "nom") String nom) {
    Joueur joueur = new Joueur();
    Integer i = Tools.randomNum();
    for (Joueur j : listJoueur) {
      while(j.getId().equals(i)){
        i = Tools.randomNum();
      }
    }
    joueur.setId(i);
    joueur.setNom(nom);
    joueur.setConnect(true);
    listJoueur.add(joueur);
    return joueur.getId();
	}

  

  @GetMapping("/creation-partie/{idJoueur}&{nb_tour}")
	public Integer creationPartie(@PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "nb_tour") int nbTour) {
		Joueur joueur = Tools.joueurDansList(listJoueur, idJoueur);
    Jeu jeu = new Jeu();

    jeu.setJoueur1(joueur);
    jeu.setNbTour(nbTour);
    listPartie.add(jeu);
    return jeu.getPartieId();
	}

  @GetMapping("/rejoindrePartie/{idPartie}&{idJoueur}")
  public boolean rejoindrePartie(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    Joueur joueur = Tools.joueurDansList(listJoueur, idJoueur);
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);  
    jeu.setJoueur2(joueur);
    jeu.attenteJoueur2(idJoueur);
    return true;
  }

  @GetMapping("/attente-connection/{idPartie}&{idJoueur}")
  public boolean attenteDuJoueur2(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);    
    jeu.attenteJoueur2(idJoueur);
    return true;
  }
  

  @GetMapping("/coup/{idPartie}&{idJoueur}&{coup}")
  public boolean coupJoueur(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "coup") String coup  ){
    boolean res = false;
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
    try{
      jeu.JoueUnCoup(idPartie, idJoueur, coup); 
      jeu.attenteDeCoup(idJoueur);
      res = true;
    }catch (Exception e) {
      res = false;
    }
    return res;
  }

  @GetMapping("/strategie/{idPartie}&{idJoueur}&{strategie}")
  public boolean strategieJoueur(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "strategie") int strategie ){
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
    jeu.setStrategie(idJoueur, strategie);
    return true;
  }

  @GetMapping("/partie/{idPartie}&{idJoueur}")
  public boolean jouePartie(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    boolean res = false;
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
    if(jeu.getJoueur1().isConnect()){
      if(jeu.getPartieId().equals(idPartie) && jeu.getJoueur1().getId().equals(idJoueur)){
        jeu.jeuManche();
        jeu.resetCoupJoueur();
        res = true;
      }
    }
    else{
      jeu.jeuManche();
      jeu.resetCoupJoueur();
      res = true;
    }
    jeu.setNbTourJouee(jeu.getNbTourJouee()+1);
    return res;
  }

  @GetMapping("/resultat/{idPartie}&{idJoueur}")
  public String resultatTour(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
    String res = "";
    if(jeu.getNbTourJouee()<jeu.getNbTour()){
      res = "Vos point :"+jeu.getRes(idJoueur)+" "+jeu.getDerniersCoup(idJoueur);
    }
    else {
      res = "Fin voici votre resultat "+jeu.getRes(idJoueur) ;
    }
    return res;
  }

  @GetMapping("/all-joueur")
  public String getAllJoueur(){
    String res = "";
    for (Joueur j : listJoueur) {
      res += j.getNom()+"&"+j.getId()+"&";
    }
    return res;
  }

  @GetMapping("/all-partie")
  public String getAllPartie(){
    String res = "";
    for (Jeu p : listPartie) {
      res += p.getPartieId()+"&";
    }
    return res;
  }

  @Bean
  public DataSource dataSource() throws ServerException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

}
