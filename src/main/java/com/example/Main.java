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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.rmi.ServerException;
import java.sql.Connection;
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
  Jeu jeu = new Jeu();
  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  @GetMapping("/")
  String index() {
    return "index";
  }

  @GetMapping("/creation")
  String creation(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
    return "creation";
    }
    catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }


  @GetMapping("/creation-partie/{nb_tour}")
	public Integer addNbTour(@PathVariable(value = "nb_tour") int nb_tour) {
		jeu.setNbTour(nb_tour);
    
    return jeu.getId();
	}

  @GetMapping("/creation-joueur/{nom}")
	public String addNom(@PathVariable(value = "nom") String nom) {
    Joueur joueur = new Joueur();
    if(nom != null){
      joueur.setNom(nom);
    }
    else {
      
      joueur.setNom("mauvais nom");
    }
    joueur.setConnect(true);
    jeu.setJoueur1(joueur);
    listJoueur.add(joueur);
    return nom;
	}

  @GetMapping("/all-joueur")
  public String getAllJoueur(){
    String res = "";
    for (Joueur j : listJoueur) {
      res += j.getNom()+" ";
      
    }
    return res;
  }

  @GetMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      int nb_tour= 5;
      ArrayList<String> output = new ArrayList<String>();
      output = jeu.renvoiString();
      model.put("records", output); 
    return "db";
    }
    catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
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
