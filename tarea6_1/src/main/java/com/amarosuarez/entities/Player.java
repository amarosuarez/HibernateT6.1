package com.amarosuarez.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
     * Atributo que almacena la lista de compras del player
     */
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Compra> compras;

    /**
     * Constructor con los parámetros nick, password, email, compras
     * @param nick Nick del player
     * @param password Password del player
     * @param email Email del player
     * @param compras Lista de compras del player
     */
    public Player(String nick, String password, String email, List<Compra> compras) {
        if (nick != null && !nick.isEmpty())
            this.nick = nick;

        if (password != null && !password.isEmpty())
            this.password = password;

        if (email != null && !email.isEmpty())
            this.email = email;

        if (compras != null) {
            this.compras = compras;
        }
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
     * Función que devuelve la lista de compras del player
     * @return Lista ed compras del player
     */
    public List<Compra> getCompras() {
        return this.compras;
    }

    /**
     * Función que almacena el nick del player
     * @param nick Nuevo nick
     */
    public void setNick(String nick) {
        if (nick != null && !nick.isEmpty())
            this.nick = nick;
    }

    /**
     * Función que almacena el password del player
     * @param password Nueva password
     */
    public void setPassword(String password) {
        if (password != null && !password.isEmpty())
            this.password = password;
    }

    /**
     * Función que almacena el email del player
     * @param email Nuevo email
     */
    public void setEmail(String email) {
        if (email != null && !email.isEmpty())
            this.email = email;
    }

    /**
     * Función que almacena las compras del player
     * @param compras Nueva lista de compra
     */
    public void setCompras(List<Compra> compras) {
        if (compras != null) {
            this.compras = compras;
        }
    }
}
