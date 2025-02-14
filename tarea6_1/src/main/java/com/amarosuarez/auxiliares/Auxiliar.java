package com.amarosuarez.auxiliares;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.amarosuarez.dal.AccesoBD;
import com.amarosuarez.entities.Compra;
import com.amarosuarez.entities.Game;
import com.amarosuarez.entities.Player;

public class Auxiliar {
    // Función que pinta todos los players
    public static void listaPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player playerO = players.get(i);
            System.out.println("-----------------");
            System.out.println("ID -> " + playerO.getIdPlayer());
            System.out.println("Nick -> " + playerO.getNick());
            System.out.println("Email -> " + playerO.getEmail());
        }
        System.out.println("-----------------");
    }

    // Función que pinta todas las opciones de players
    public static void pintaPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player playerO = players.get(i);
            System.out.println("-----------------");
            System.out.println("Opción " + i);
            System.out.println("ID -> " + playerO.getIdPlayer());
            System.out.println("Nick -> " + playerO.getNick());
            System.out.println("Email -> " + playerO.getEmail());
        }
        System.out.println("-----------------");
    }

    // Función que pinta un player
    public static void pintaUnPlayer(Player player) {
        System.out.println("-----------------");
        System.out.println("ID -> " + player.getIdPlayer());
        System.out.println("Nick -> " + player.getNick());
        System.out.println("Email -> " + player.getEmail());
        System.out.println("-----------------");
    }

    // Función que muestra el player elegido para eliminar
    public static void muestraPlayerElegido(Player player) {
        System.out.println();
        System.out.println("El player elegido es:");
        System.out.println("ID -> " + player.getIdPlayer());
        System.out.println("Nick -> " + player.getNick());
        System.out.println("Email -> " + player.getEmail());
    }

    // Función que lista los games
    public static void listaGames(List<Game> games) {
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            System.out.println("-----------------");
            System.out.println("ID -> " + game.getIdGame());
            System.out.println("Nombre -> " + game.getNombre());
            System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
        }
        System.out.println("-----------------");
    }

    // Función que pinta las opciones de games
    public static void pintaGames(List<Game> games) {
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            System.out.println("-----------------");
            System.out.println("Opción " + i);
            System.out.println("ID -> " + game.getIdGame());
            System.out.println("Nombre -> " + game.getNombre());
            System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
        }
        System.out.println("-----------------");
    }

    // Función que pinta un game
    public static void pintaUnGame(Game game) {
        System.out.println("-----------------");
        System.out.println("ID -> " + game.getIdGame());
        System.out.println("Nombre -> " + game.getNombre());
        System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
        System.out.println("-----------------");
    }

    // Función que muestra el game elegido para eliminar
    public static void muestraGameElegido(Game game) {
        System.out.println();
        System.out.println("El game elegido es:");
        System.out.println("ID -> " + game.getIdGame());
        System.out.println("Nombre -> " + game.getNombre());
        System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
    }

    // Función que lista compras
    public static void listaCompras(List<Compra> compras, AccesoBD instancia) {
        for (Compra compra : compras) {
            // Buscamos al jugador
            Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

            System.out.println("-----------------");
            System.out.println("ID -> " + compra.getIdCompra());
            System.out.println("Player -> " + player.getNick());
            System.out.println("Game -> " + compra.getCosa());
            System.out.println("Precio -> " + compra.getPrecio() + " €");
            System.out.println("Fecha de compra -> " + compra.getFechaCompra());
        }
        System.out.println("-----------------");
    }

    // Función que pinta una compra
    public static void pintaUnaCompra(Compra compra, Player player) {
        System.out.println("-----------------");
        System.out.println("ID -> " + compra.getIdCompra());
        System.out.println("Player -> " + player.getNick());
        System.out.println("Game -> " + compra.getCosa());
        System.out.println("Precio -> " + compra.getPrecio() + " €");
        System.out.println("Fecha de compra -> " + compra.getFechaCompra());
        System.out.println("-----------------");
    }

    // Función que obtiene todos los players con un nick
    public static List<Player> getAllPlayersByNick(String nick, AccesoBD instancia) {
        List<Player> players = null;

        try {
            // Buscamos al player
            players = instancia.buscarPorParametro("getPlayersByNick", "nick",
                    "%" + nick + "%");
        } catch (Exception e) {
            // No se ha encontrado nada
        }

        return players;
    }

    // Función que obtiene todos los players con un email
    public static List<Player> getAllPlayersByEmail(String email, AccesoBD instancia) {
        List<Player> players = null;

        try {
            // Buscamos al player
            players = instancia.buscarPorParametro("getPlayersByEmail", "email",
                    "%" + email + "%");
        } catch (Exception e) {
            // No se ha encontrado nada
        }

        return players;
    }

    // Función que obtiene todos los games con su name
    public static List<Game> getAllGames(String name, AccesoBD instancia) {
        List<Game> games = null;

        try {
            // Buscamos los games
            games = instancia.buscarPorParametro("getGamesByName", "nombre",
                    "%" + name + "%");
        } catch (Exception e) {
            // No se ha encontrado nada
        }

        return games;
    }

    // Función que confirma el borrado
    public static void confirmDelete(Object object, String tag, AccesoBD instancia, Scanner scanner) {
        String confirm;
        do {
            System.out.println();
            System.out.println("¿Está seguro de que desea eliminarlo? (y/n)");
            confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                instancia.borrar(object);
                System.out.println(tag + " eliminado");
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("Operación cancelada");
            }
        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
    }

    // Función que devuelve la opción del objeto elegido
    public static int pideElegirOpcionObjeto(List lista, String tag, Scanner scanner) {
        int opcion;
        do {
            System.out.println();
            System.out.println(
                    "Escribe el número de opción del " + tag + " que deseas seleccionar (-1 para volver)");
            opcion = scanner.nextInt();

            // Limpiamos el Scanner
            scanner.nextLine();
        } while (opcion > lista.size());

        return opcion;
    }

    /**
     * Función que muestra un mensaje, pide un entero y lo devuelve
     * @param message Mensaje a mostrar
     * @param scanner Scanner para leer
     * @return Entero
     */
    public static int leeEntero(String message, Scanner scanner) {
        int res = 0;
        boolean pasado = false;
        
        do {
            try {
                // Mostramos el mensaje
                System.out.println();
                System.out.println(message);

                // Leemos la respuesta
                res = scanner.nextInt();

                // Cambiamos el boolean
                pasado = true;
            } catch (InputMismatchException e) {
                System.out.println("Debe escribir un número entero");
            } finally {
                // Limpiamos el Scanner
                scanner.nextLine();
            }
        } while (!pasado);

        return res;
    }
}
