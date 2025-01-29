package com.amarosuarez.entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase Game que almacena sus atributos y métodos,
 * además sirve para generar la tabla en la base de datos
 *
 * @author Amaro Suárez
 * @version 1.0
 */
@Entity
@Table(name = "Games")
public class Game implements Serializable {
    /**
     * Atributo que almacena el id del juego
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGame")
    private int idGame;

    /**
     * Atributo que almacena el nombre del juego
     */
    @Column(name = "Nombre")
    private String nombre;

    /**
     * Atributo que almacena el tiempo jugado
     */
    @Column(name = "tiempoJugado")
    private Time tiempoJugado;

    /**
     * Constructor con los parámetros nombre y tiempoJugado
     * @param nombre Nombre del juego
     * @param tiempoJugado Tiempo jugado al juego
     */
    public Game(String nombre, Time tiempoJugado) {
        this.nombre = nombre;
        this.tiempoJugado = tiempoJugado;
    }

    /**
     * Constructor sin parámetros
     */
    public Game() {}

    /**
     * Función que devuelve el id del juego
     * @return Id del juego
     */
    public int getIdGame() {
        return this.idGame;
    }

    /**
     * Función que devuelve el nombre del juego
     * @return Nombre del juego
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Función que devuelve el tiempo jugado
     * @return Tiempo jugado
     */
    public Time getTiempoJugado() {
        return this.tiempoJugado;
    }

    /**
     * Función que almacena el nombre del juego
     * @param nombre Nuevo Nombre del juego
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Función que almacena el tiempo jugado
     * @param tiempoJugado Nuevo Tiempo jugado
     */
    public void setTiempoJugado(Time tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }
}
