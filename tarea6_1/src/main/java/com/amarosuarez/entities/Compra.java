package com.amarosuarez.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Función que almacena los atributos y métodos,
 * además sirve para generar la tavla en la base de datos
 *
 * @author Amaro Suárez
 * @version 1.0
 */
@NamedQueries({
    @NamedQuery(
        name="getAllCompras",
        query="from Compra"
    ),
    @NamedQuery(
        name="getCompraById",
        query="from Compra where idCompra = :id"
    ),
    @NamedQuery(
        name="getComprasByPlayer",
        query="from Compra where nombre LIKE :nombre"
    ),
})
@Entity
@Table(name = "Compras")
public class Compra implements Serializable {
    /**
     * Atributo que almacena el id de la compra
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompra")
    private int idCompra;

    /**
     * Atributo que almacena el id del player
     */
    @Column(name = "idPlayer")
    private int idPlayer;

    /**
     * Atributo que almacena el id del juego
     */
    @Column(name = "idGame")
    private int idGame;

    /**
     * Atributo que almacena el nombre del juego
     */
    @Column(name = "Cosa")
    private String cosa;

    /**
     * Atributo que almacena el precio del juego
     */
    @Column(name = "Precio")
    private double precio;

    /**
     * Atributo que almacena la fecha de la compra
     */
    @Column(name = "FechaCompra")
    private Date fechaCompra;

    @ManyToOne
    @JoinColumn(name = "idPlayer", nullable = false, insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "idGame", nullable = false, insertable = false, updatable = false)
    private Game game;

    /**
     * Constructor con todos los parámetros
     * @param idPlayer Id del jugador
     * @param idGame Id del juego
     * @param cosa Nombre del juego
     * @param precio Precio del juego
     * @param fechaCompra Fecha de la compra
     */
    public Compra(int idPlayer, int idGame, String cosa, double precio, Date fechaCompra) {
        if (idPlayer >= 0) {
            this.idPlayer = idPlayer;
        }

        if (idGame >= 0) {
            this.idGame = idGame;
        }

        if (cosa != null && !cosa.isEmpty()) {
            this.cosa = cosa;
        }

        if (precio >= 0) {
            this.precio = precio;
        }

        if (fechaCompra != null) {
            this.fechaCompra = fechaCompra;
        }
    }

    /**
     * Constructor sin parámetros
     */
    public Compra() {}

    /**
     * Función que devuelve el id de la compra
     * @return ID de la compra
     */
    public int getIdCompra() {
        return this.idCompra;
    }

    /**
     * Función que devuelve el id del jugador
     * @return ID del jugador
     */
    public int getIdPlayer() {
        return this.idPlayer;
    }

    /**
     * Función que devuelve el id del juego
     * @return ID del juego
     */
    public int getIdGame() {
        return this.idGame;
    }

    /**
     * Función que devuelve el nombre del juego
     * @return Nombre del juego
     */
    public String getCosa() {
        return this.cosa;
    }

    /**
     * Función que devuelve el precio de la compra
     * @return Precio de la compra
     */
    public double getPrecio() {
        return this.precio;
    }
    
    /**
     * Función que devuelve la fecha de la compra
     * @return Fecha de la compra
     */
    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    /**
     * Función que coloca el id del jugador
     * @param idPlayer Id del jugador
     */
    public void setIdPlayer(int idPlayer) {
        if (idPlayer >= 0) {
            this.idPlayer = idPlayer;
        }
    }

    /**
     * Función que coloca el id de la compra
     * @param idGame ID de la compra
     */
    public void setIdGame(int idGame) {
        if (idGame >= 0) {
            this.idGame = idGame;
        }
    }

    /**
     * Función que coloca el nombre del juego
     * @param cosa Nombre del juego
     */
    public void setCosa(String cosa) {
        if (cosa != null && !cosa.isEmpty())
            this.cosa = cosa;
    }

    /**
     * Función que coloca el precio de la compra
     * @param precio Precio de la compra
     */
    public void setPrecio(double precio) {
        if (precio >= 0)
            this.precio = precio;
    }

    /**
     * Función que coloca la fecha de la compra
     * @param fechaCompra Fecha de la compra
     */
    public void setFechaCompra(Date fechaCompra) {
        if (fechaCompra != null)
            this.fechaCompra = fechaCompra;
    }
}

