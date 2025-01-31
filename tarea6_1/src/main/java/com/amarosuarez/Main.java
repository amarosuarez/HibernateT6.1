package com.amarosuarez;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import com.amarosuarez.dal.AccesoBD;
import com.amarosuarez.entities.Player;

public class Main {

    private static AccesoBD instancia;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        int opcion;
        instancia = new AccesoBD();

        do {
            // Mostramos el menú
            menu();

            // Recogemos la opción seleccionada
            opcion = scanner.nextInt();

            // Limpiamos el scanner
            scanner.nextLine();

            // Salto de línea
            System.out.println();

            switch (opcion) {
                case 1 -> {
                    int opcionListar;

                    do {
                        // Mostramos el menú de listar
                        menuListar();

                        // Recogemos la opción elegida
                        opcionListar = scanner.nextInt();

                        // Limpiamos el scanner
                        scanner.nextLine();

                        // Ejecutamos la opción seleccionada
                        mostrarListarOpcion(opcionListar);

                        // Salto de línea
                        System.out.println();
                    } while (opcionListar != 7);
                }
                case 5 -> System.out.println("Hasta la próxima!");
                default -> throw new AssertionError();
            }

            // Salto de línea
            System.out.println();
        } while (opcion != 5);

    }

    // Función que pinta el menú general
    private static void menu() {
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Listar");
        System.out.println("2. Añadir");
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.println("5. Salir");
    }

    // Función que pinta el menú de listar
    private static void menuListar() {
        System.out.println("¿Qué desea listar?");
        System.out.println("1. Todos los jugadores");
        System.out.println("2. Un jugador");
        System.out.println("3. Todos los juegos");
        System.out.println("4. Un juego");
        System.out.println("5. Todas las compras");
        System.out.println("6. Una compra");
        System.out.println("7. Volver al menú principal");
    }

    // Función que muestra los resultados de la opción elegida de listar
    private static void mostrarListarOpcion(int opcionListar) {
        try {
            instancia.abrir();

            switch (opcionListar) {
                case 1 -> {
                    // Muestra todos los player
                    try {
                        List<Player> listaPlayers = instancia.listar("getAllPlayers");

                        if (!listaPlayers.isEmpty()) {
                            for (Player player : listaPlayers) {
                                System.out.println("-----------------");
                                System.out.println("ID -> " + player.getIdPlayer());
                                System.out.println("Nick -> " + player.getNick());
                                System.out.println("Email -> " + player.getEmail());
                            }

                            System.out.println("-----------------");
                        } else {
                            System.out.println("No se ha encontrado ningún player");
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha encontrado ningún player");
                    }
                }

                case 2 -> {
                    // Muestra solo un player
                    int opcionBuscarPlayer;

                    do {
                        Player player = null;

                        System.out.println();
                        System.out.println("¿Por qué campo deseas buscar al Player?");
                        System.out.println("1. ID");
                        System.out.println("2. Nick");
                        System.out.println("3. Email");
                        System.out.println("4. Volver");

                        // Leemos la opcion
                        opcionBuscarPlayer = scanner.nextInt();

                        // Limpiamos el Scanner
                        scanner.nextLine();

                        switch (opcionBuscarPlayer) {
                            case 1 -> {
                                // Busca por ID
                                int idPlayer;

                                System.out.println();
                                System.out.println("¿Cuál es el ID?");
                                idPlayer = scanner.nextInt();

                                try {
                                    player = (Player) instancia.buscarPorId("getPlayerById", idPlayer);

                                    System.out.println("-----------------");
                                    System.out.println("ID -> " + player.getIdPlayer());
                                    System.out.println("Nick -> " + player.getNick());
                                    System.out.println("Email -> " + player.getEmail());
                                    System.out.println("-----------------");
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún player");
                                }
                            }

                            case 2 -> {
                                // Busca por nick
                                String nick;

                                System.out.println();
                                System.out.println("¿Cuál es el nick?");
                                nick = scanner.nextLine();

                                try {
                                    List<Player> players = instancia.buscarPorParametro("getPlayersByNick", "nick", "%" + nick + "%");

                                    if (!players.isEmpty()) {
                                        for (Player playerO : players) {
                                            System.out.println("-----------------");
                                            System.out.println("ID -> " + playerO.getIdPlayer());
                                            System.out.println("Nick -> " + playerO.getNick());
                                            System.out.println("Email -> " + playerO.getEmail());
                                        }
                                    } else {
                                        System.out.println("No se ha encontrado ningún player");
                                    }
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún player");
                                }
                            }
                            case 3 -> {
                                // Busca por email
                                String email;

                                System.out.println();
                                System.out.println("¿Cuál es el email?");
                                email = scanner.nextLine();

                                try {
                                    List<Player> players = instancia.buscarPorParametro("getPlayersByEmail", "email", "%" + email + "%");

                                    if (!players.isEmpty()) {
                                        for (Player playerO : players) {
                                            System.out.println("-----------------");
                                            System.out.println("ID -> " + playerO.getIdPlayer());
                                            System.out.println("Nick -> " + playerO.getNick());
                                            System.out.println("Email -> " + playerO.getEmail());
                                        }
                                    } else {
                                        System.out.println("No se ha encontrado ningún player");
                                    }
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún player");
                                }
                            }
                        }

                    } while (opcionBuscarPlayer != 4);
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                }
                case 6 -> {
                }
                case 7 -> System.out.println("Volviendo...");
            }
            // Muestra todos los game
            // Muestra solo un game
            // Muestra todas las compras
            // Muestra solo una compra
            
        } catch (Exception e) {
            System.out.println("Error al abrir la instancia:" + e.getMessage());
        } finally {
            instancia.cerrar();
        }
    }
}
