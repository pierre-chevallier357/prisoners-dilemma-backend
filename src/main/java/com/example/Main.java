/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	public Integer creationPartie(@PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "nb_tour") int nb_tour) {
		Joueur joueur = Tools.joueurDansList(listJoueur, idJoueur);
    Jeu jeu = new Jeu();

    jeu.setJoueur1(joueur);
    jeu.setNbTour(nb_tour);
    listPartie.add(jeu);
    return jeu.getPartieId();
	}

  @GetMapping("/rejoindrePartie/{idPartie}&{idJoueur}")
  public boolean rejoindrePartie(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    Joueur joueur = Tools.joueurDansList(listJoueur, idJoueur);
    System.out.println(joueur.getNom()+"  ---  "+idPartie);
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);    
    System.out.println("ID PArtie :"+jeu.getPartieId());
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

  @GetMapping("/partie/{idPartie}&{idJoueur}")
  public boolean jouePartie(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    boolean res = false;
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
    if(jeu.getPartieId().equals(idPartie) && jeu.getJoueur1().getId().equals(idJoueur)){
      jeu.jeuManche();
      jeu.resetCoupJoueur();
      res = true;
    }
    return res;
  }

  @GetMapping("/resultat/{idPartie}&{idJoueur}")
  public String resultatTour(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
    String res = jeu.getRes(idJoueur);
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
