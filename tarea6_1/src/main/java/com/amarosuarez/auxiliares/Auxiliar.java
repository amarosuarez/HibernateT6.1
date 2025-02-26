package com.amarosuarez.auxiliares;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.amarosuarez.dal.AccesoBD;
import com.amarosuarez.entities.Compra;
import com.amarosuarez.entities.Game;
import com.amarosuarez.entities.Player;

public class Auxiliar {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Función que lista una serie de players
     *
     * @param players Lista de Players
     */
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

    /**
     * Función que pinta una serie de players con opción
     *
     * @param players Lista de Players
     */
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

    /**
     * Función que pinta un player por pantalla
     *
     * @param player Player a pintar
     */
    public static void pintaUnPlayer(Player player) {
        System.out.println("-----------------");
        System.out.println("ID -> " + player.getIdPlayer());
        System.out.println("Nick -> " + player.getNick());
        System.out.println("Email -> " + player.getEmail());
        System.out.println("-----------------");
    }

    /**
     * Función que muestra el player elegido a eliminar
     *
     * @param player Player elegido
     */
    public static void muestraPlayerElegido(Player player) {
        System.out.println();
        System.out.println("El player elegido es:");
        System.out.println("ID -> " + player.getIdPlayer());
        System.out.println("Nick -> " + player.getNick());
        System.out.println("Email -> " + player.getEmail());
    }

    /**
     * Función que lista una serie de games
     *
     * @param games
     */
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

    /**
     * Función que pinta una lista una serie de games con opciones
     *
     * @param games Lista de games
     */
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

    /**
     * Función que pinta un game por pantalla
     *
     * @param game Game a pintar
     */
    public static void pintaUnGame(Game game) {
        System.out.println("-----------------");
        System.out.println("ID -> " + game.getIdGame());
        System.out.println("Nombre -> " + game.getNombre());
        System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
        System.out.println("-----------------");
    }

    /**
     * Función que muestra el game elegido a eliminar
     *
     * @param game Game elegido
     */
    public static void muestraGameElegido(Game game) {
        System.out.println();
        System.out.println("El game elegido es:");
        System.out.println("ID -> " + game.getIdGame());
        System.out.println("Nombre -> " + game.getNombre());
        System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
    }

    /**
     * Función que lista una serie de compras
     *
     * @param compras Lista de compras
     * @param instancia Instancia a la base de datos
     */
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

    /**
     * Función que lista una serie de compras con opciones
     *
     * @param compras Lista de compras
     * @param instancia Instancia a la base de datos
     */
    public static void pintaCompras(List<Compra> compras, AccesoBD instancia) {
        for (int i = 0; i < compras.size(); i++) {
            Compra compra = compras.get(i);

            // Buscamos al jugador
            Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

            System.out.println("-----------------");
            System.out.println("Opción -> " + i);
            System.out.println("ID -> " + compra.getIdCompra());
            System.out.println("Player -> " + player.getNick());
            System.out.println("Game -> " + compra.getCosa());
            System.out.println("Precio -> " + compra.getPrecio() + " €");
            System.out.println("Fecha de compra -> " + compra.getFechaCompra());
        }
        System.out.println("-----------------");
    }

    /**
     * Función que pinta una compra en pantalla
     *
     * @param compra Compra
     * @param player Player
     */
    public static void pintaUnaCompra(Compra compra, Player player) {
        System.out.println("-----------------");
        System.out.println("ID -> " + compra.getIdCompra());
        System.out.println("Player -> " + player.getNick());
        System.out.println("Game -> " + compra.getCosa());
        System.out.println("Precio -> " + compra.getPrecio() + " €");
        System.out.println("Fecha de compra -> " + compra.getFechaCompra());
        System.out.println("-----------------");
    }

    /**
     * Función que muestra la compra elegida a eliminar
     *
     * @param compra Compra elegida
     */
    public static void muestraCompraElegida(Compra compra, AccesoBD instancia) {
        Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

        System.out.println();
        System.out.println("La compra elegida es:");
        System.out.println("ID -> " + compra.getIdCompra());
        System.out.println("Player -> " + player.getNick());
        System.out.println("Game -> " + compra.getCosa());
        System.out.println("Precio -> " + compra.getPrecio());
        System.out.println("Fecha de compra -> " + compra.getFechaCompra());
    }

    /**
     * Función que obtiene todos los players que coincidan con un nick
     *
     * @param nick Nick del player a buscar
     * @param instancia Instancia a la base de datos
     * @return Lista de players
     */
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

    /**
     * Función que obtiene todos los players que coincidan con un email
     *
     * @param email Email del player a buscar
     * @param instancia Instancia a la base de datos
     * @return Lista de Players
     */
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

    /**
     * Función que obtiene todos los juegos que coinciden con un nombre
     *
     * @param name Nombre del juego a buscar
     * @param instancia Instancia a la base de datos
     * @return Lista de Game
     */
    public static List<Game> getAllGamesByName(String name, AccesoBD instancia) {
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

    /**
     * Función que recibe un objeto, un tag y una instancia a la base de datos y
     * pide la confirmación de la eliminación
     *
     * @param object Objeto a eliminar
     * @param tag Tag del objeto
     * @param instancia Instancia a la base de datos
     */
    public static void confirmDelete(Object object, String tag, AccesoBD instancia) {
        String confirm;
        do {
            System.out.println();
            System.out.println("¿Está seguro de que desea eliminarlo? (y/n)");
            confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                instancia.borrar(object);
                System.out.println(tag + " eliminad@");
                
                instancia.rollbackTransaction(Auxiliar.confirmTransaction());
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("Operación cancelada");
            }
        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
    }

    /**
     * Función que un tag y una instancia a la base de datos y
     * pide la confirmación de la eliminación
     *
     * @param tag Tag del objeto
     * @param instancia Instancia a la base de datos
     */
    public static void confirmDeleteAll(String tag, AccesoBD instancia) {
        String confirm;
        do {
            System.out.println();
            System.out.println("¿Está seguro de que desea eliminar todos los registros de " + tag + "s? (y/n)");
            confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                instancia.borrarTodosLosRegistros(tag);
                System.out.println(tag + "s eliminad@s");
                
                instancia.rollbackTransaction(Auxiliar.confirmTransaction());
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("Operación cancelada");
            }
        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
    }

    /**
     * Función que pide al usuario si está seguro de eliminar
     *
     * @return Respuesta del usuario (y o n)
     */
    public static String confirmaBorrado() {
        String confirm = "";

        do {
            System.out.println();
            System.out.println("¿Está seguro de que desea eliminarlo? (y/n)");
            confirm = scanner.nextLine().toLowerCase();
        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));

        return confirm;
    }

    /**
     * Función que pregunta al usuario si desea revertir la acción
     *
     * @return Desea revertir la acción
     */
    public static boolean confirmTransaction() {
        String res = "";

        do {
            System.out.println();
            System.out.println("¿Desea revertir la acción? (y/n)");
            res = scanner.nextLine();
        } while (!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n"));

        return res.equalsIgnoreCase("y");
    }

    /**
     * Función que muestra una serie de opciones y pide que se elija uno
     *
     * @param listaSize Tamaño de la lista
     * @param tag Tag del objeto
     * @return Opción elegida
     */
    public static int pideElegirOpcionObjeto(int listaSize, String tag) {
        int opcion;
        do {
            System.out.println();
            System.out.println(
                    "Escribe el número de opción de " + tag + " que deseas seleccionar (-1 para volver)");
            opcion = scanner.nextInt();

            // Limpiamos el Scanner
            scanner.nextLine();
        } while (opcion >= listaSize || opcion < -1);

        return opcion;
    }

    /**
     * Función que muestra un mensaje, pide un entero y lo devuelve
     *
     * @param message Mensaje a mostrar
     * @return Entero
     */
    public static int leeEntero(String message) {
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

    /**
     * Función que muestra un mensaje, pide un double y lo devuelve
     *
     * @param message Mensaje a mostrar
     * @return Double
     */
    public static double leeDouble(String message) {
        double res = 0;
        boolean pasado = false;

        do {
            try {
                // Mostramos el mensaje
                System.out.println();
                System.out.println(message);

                // Leemos la respuesta
                res = scanner.nextDouble();

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

    /**
     * Función que muestra un mensaje, pide un String y lo devuelve
     *
     * @param message
     * @return String
     */
    public static String leeString(String message) {
        String res = "";

        System.out.println();
        System.out.println(message);
        res = scanner.nextLine();

        return res;
    }

    /**
     * Función que pide al usuario que ingrese una fecha
     *
     * @return Fecha completa
     */
    public static String pideFecha() {
        // Pedimos la fecha de la compra
        int dia;
        int mes;
        int anyo;
        String fecha = "-1";

        do {
            // Pedimos el año
            anyo = Auxiliar.leeEntero("Ingrese el año de la compra (-1 para salir)");
        } while ((anyo > 0 && anyo < 1900) || anyo < -1);

        if (anyo > 0) {
            do {
                // Pedimos el mes
                mes = Auxiliar.leeEntero("Ingrese el mes de la compra (-1 para salir)");
            } while (mes > 12 || mes < -1 || mes == 0);

            if (mes > 0) {
                do {
                    // Pedimos el día
                    dia = Auxiliar.leeEntero("Ingrese el día de la compra (-1 para salir)");
                } while (dia > 31
                        || dia < -1
                        || dia == 0
                        || ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
                        || (Year.isLeap(anyo) && mes == 2 && dia > 29)
                        || (!Year.isLeap(anyo) && mes == 2 && dia > 28));

                if (dia > 0) {
                    fecha = anyo + "-" + (mes > 9 ? mes : "0" + mes) + "-"
                            + (dia > 9 ? dia : "0" + dia);
                }
            }
        }

        return fecha;
    }
}
