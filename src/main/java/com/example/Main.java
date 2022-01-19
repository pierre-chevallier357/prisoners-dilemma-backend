
package com.example;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import javax.sql.DataSource;

import com.example.partiedejeux.Jeu;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Main {
  
  @Value("${spring.datasource.url}")
  private String dbUrl;

  private GameInterface game = new GameRequest();
  

  @GetMapping("/")
  public String indexUser() {
    return "Ceci est le serveur, pour jouer, veuillez aller sur l'application : client-pc.web.app";
  }

  @GetMapping("/creation-joueur/{nom}")
	public Integer creationJoueurUser(@PathVariable(value = "nom") String nom) {
    return game.creationJoueur(nom);
	}

  @GetMapping("/creation-partie/{idJoueur}&{nb_tour}")
	public Integer creationPartieUser(@PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "nb_tour") int nbTour) {
    return game.creationPartie(idJoueur, nbTour);
	}

  @GetMapping("/rejoindrePartie/{idPartie}&{idJoueur}")
  public boolean rejoindrePartieUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.rejoindrePartie(idPartie, idJoueur);
  }

  @GetMapping("/attente-connection/{idPartie}&{idJoueur}")
  public boolean attenteDuJoueur2User(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.attenteDuJoueur2(idPartie, idJoueur);
  }

  @GetMapping("/nb-coups/{idPartie}")
  public String nbCoupsJouesUser(@PathVariable(value = "idPartie") Integer idPartie){
    return game.nbCoupsJoues(idPartie);
  } 

  @GetMapping("/coup/{idPartie}&{idJoueur}&{coup}")
  public boolean coupJoueurUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "coup") String coup  ){
    return game.coupJoueur(idPartie, idJoueur, coup);
  }

  @GetMapping("/strategie/{idPartie}&{idJoueur}&{strategie}")
  public boolean strategieJoueurUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "strategie") int strategie ){
    return game.strategieJoueur(idPartie, idJoueur, strategie);
  }


  @GetMapping("/disconnect/{idPartie}&{idJoueur}")
  public boolean disconnectJoueurUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.disconnectJoueur(idPartie, idJoueur);
  }


  @GetMapping("/partie/{idPartie}&{idJoueur}")
  public boolean jouePartieUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.jouePartie(idPartie, idJoueur);
  }

  @GetMapping("/resultat-tour/{idPartie}&{idJoueur}")
  public String resultatTourUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.resultatTour(idPartie, idJoueur);
  }

  @GetMapping("/dernier-coup-adv/{idPartie}&{idJoueur}")
  public String dernierCoupAdvUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.dernierCoupAdv(idPartie, idJoueur);
  }

  @GetMapping("/resultat-final/{idPartie}&{idJoueur}")
  public String resFinalUser(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    return game.resFinal(idPartie, idJoueur);
  }

  @GetMapping("/supprimer-partie/{idPartie}")
  public boolean supprimerPartieUser(@PathVariable(value = "idPartie") Integer idPartie){
    return game.supprimerPartie(idPartie);
  }

  @GetMapping("/all-joueur")
  public String getAllJoueurUser(){
    return game.getAllJoueur();
  }

  @GetMapping("/all-joueur-by-id")
  public String getAllIdsJoueurUser(){
    return game.getAllIdsJoueur();
  }

  @GetMapping("/all-joueur-by-nom")
  public String getAllNomsJoueurUser(){
    return game.getAllNomsJoueur();
  }

  @GetMapping("/all-partie")
  public String getAllPartieUser(){
    return game.getAllPartie();
  }

  public Jeu getJeuUser(){
    return game.getJeu();
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
