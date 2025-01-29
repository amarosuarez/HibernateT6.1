package com.amarosuarez.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que almacena los atributos y métodos del Player,
 * además sirve para generar la tabla en la base de datos
 *
 * @author Amaro Suárez
 * @version 1.0
 */
@Entity
@Table(name = "Players")
public class Player implements Serializable {
    /**
     * Atributo que almacena el id del player
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlayer")
    private int idPlayer;

    /**
     * Atributo que almacena el nick del player
     */
    @Column(name = "Nick")
    private String nick;

    /**
     * Atributo que almacena el password del player
     */
    @Column(name = "password")
    private String password;

    /**
     * Atributo que almacena el email del player
     */
    @Column(name = "email")
    private String email;

    /**
     * Constructor con los parámetros nick, password, email
     * @param nick Nick del player
     * @param password Password del player
     * @param email Email del player
     */
    public Player(String nick, String password, String email) {
        this.nick = nick;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor sin parámetros
     */
    public Player() {}

    /**
     * Función que devuelve el id del player
     * @return Id del player
     */
    public int getIdPlayer() {
        return this.idPlayer;
    }

    /**
     * Función que devuelve el nick del player
     * @return Nick del player
     */
    public String getNick() {
        return this.nick;
    }

    /**
     * Función que devuelve el password del player
     * @return Password del player
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Función que devuelve el email del player
     * @return Email del player
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Función que almacena el nick del player
     * @param nick Nuevo nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Función que almacena el password del player
     * @param password Nueva password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Función que almacena el email del player
     * @param email Nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
