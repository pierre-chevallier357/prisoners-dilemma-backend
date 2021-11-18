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
import com.example.partieDeJeux.Tools;
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

  Jeu jeu = new Jeu();
  @GetMapping("/")
  String index() {
    return "index";
  }

  @GetMapping("/creation-partie/{idJoueur}&{nb_tour}")
	public Integer creationPartie(@PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "nb_tour") int nb_tour) {
		Joueur j1 = new Joueur();
    for (Joueur j : listJoueur) {
      if(j.getId()==idJoueur){
        j1 = j;
      }
    }
    jeu.setJoueur1(j1);
    jeu.setNbTour(nb_tour);

    listPartie.add(jeu);
    return jeu.getPartieId();
	}

  @GetMapping("/creation-joueur/{nom}")
	public Integer creationJoueur(@PathVariable(value = "nom") String nom) {
    Joueur joueur = new Joueur();
    Integer i = Tools.randomNum();
    for (Joueur j : listJoueur) {
      while(j.getId()==i){
        i = Tools.randomNum();
      }
    }
    
    joueur.setId(i);
    joueur.setNom(nom);
    joueur.setConnect(true);
    jeu.setJoueur1(joueur);
    listJoueur.add(joueur);

    return joueur.getId();
	}

  @GetMapping("/all-joueur")
  public String getAllJoueur(){
    String res = "";
    for (Joueur j : listJoueur) {
      res += j.getNom()+" "+j.getId()+" ";
    }
    return res;
  }

  @GetMapping("/all-partie")
  public String getAllPartie(){
    String res = "";
    for (Jeu p : listPartie) {
      res += p.getId();
    }
    return res;
  }

  @GetMapping("/rejoindrePartie/{idPartie}&{nom}")
  public Integer rejoindrePartie(@PathVariable(value = "idPartie") Integer id, @PathVariable(value = "nom") String nom ){
    Integer i = Tools.randomNum();
    Joueur joueur = new Joueur();
    joueur.setNom(nom);
    for (Joueur j : listJoueur) {
      while(j.getId()==i){
        i = Tools.randomNum();
      }
    }
    joueur.setId(i);
    jeu.connectionJoueur2(id);
    jeu.setJoueur2(joueur);
    listJoueur.add(joueur);
    return jeu.getJoueur2().getId();
  }

  @GetMapping("/partie/{idPartie}&{idJoueur}&{coup}")
  public void jouePartie(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur, @PathVariable(value = "coup") String coup  ){
    jeu.JoueUnCoup(idPartie, idJoueur, coup); 
    jeu.attenteDeCoup(idJoueur);
    jeu.jeuManche();
    jeu.resetCoupJoueur(idJoueur);

  }

  @GetMapping("/resultat/{idPartie}&{idJoueur}")
  public String resultatTour(@PathVariable(value = "idPartie") Integer idPartie, @PathVariable(value = "idJoueur") Integer idJoueur){
    String res = jeu.getRes(idJoueur);
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
