package com.amarosuarez.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Función que almacena los atributos y métodos,
 * además sirve para generar la tavla en la base de datos
 *
 * @author Amaro Suárez
 * @version 1.0
 */
@Entity
@Table(name = "Compras")
public class Compras implements Serializable {
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
    @Column(name = "idGames")
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
}
