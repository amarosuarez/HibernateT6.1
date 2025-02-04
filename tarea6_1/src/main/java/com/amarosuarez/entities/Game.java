package com.amarosuarez.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Game que almacena sus atributos y métodos,
 * además sirve para generar la tabla en la base de datos
 *
 * @author Amaro Suárez
 * @version 1.0
 */
@NamedQueries({
    @NamedQuery(
        name="getAllGames",
        query="from Game"
    ),
    @NamedQuery(
        name="getGameById",
        query="from Game where idGame = :id"
    ),
    @NamedQuery(
        name="getGamesByName",
        query="from Game where nombre LIKE :nombre"
    ),
})
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
     * Atributo que almacena las compras en las que aparece el juego
     */
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Compra> compras;

    /**
     * Constructor con los parámetros nombre y tiempoJugado
     * @param nombre Nombre del juego
     * @param tiempoJugado Tiempo jugado al juego
     * @param compras Lista de compras donde aparece el juego
     */
    public Game(String nombre, Time tiempoJugado, List<Compra> compras) {
        if (nombre != null && !nombre.isEmpty())
            this.nombre = nombre;
        
        if (tiempoJugado != null)
            this.tiempoJugado = tiempoJugado;

        if (compras != null)
            this.compras = compras;
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
        if (nombre != null && !nombre.isEmpty())
            this.nombre = nombre;
    }

    /**
     * Función que almacena el tiempo jugado
     * @param tiempoJugado Nuevo Tiempo jugado
     */
    public void setTiempoJugado(Time tiempoJugado) {
        if (tiempoJugado != null)
            this.tiempoJugado = tiempoJugado;
    }
}
