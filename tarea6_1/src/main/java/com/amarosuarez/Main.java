package com.amarosuarez;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import com.amarosuarez.dal.AccesoBD;
import com.amarosuarez.entities.Compra;
import com.amarosuarez.entities.Game;
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
                                    List<Player> players = instancia.buscarPorParametro("getPlayersByNick", "nick",
                                            "%" + nick + "%");

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
                                    List<Player> players = instancia.buscarPorParametro("getPlayersByEmail", "email",
                                            "%" + email + "%");

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
                    // Muestra todos los games
                    try {
                        List<Game> listaGames = instancia.listar("getAllGames");

                        if (!listaGames.isEmpty()) {
                            for (Game game : listaGames) {
                                System.out.println("-----------------");
                                System.out.println("ID -> " + game.getIdGame());
                                System.out.println("Nombre -> " + game.getNombre());
                                System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
                            }

                            System.out.println("-----------------");
                        } else {
                            System.out.println("No se ha encontrado ningún player");
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha encontrado ningún player");
                    }
                }
                case 4 -> {
                    // Listar un solo juego
                    int opcionGame = 0;

                    do {
                        System.out.println();
                        System.out.println("¿Por qué campo desea buscar el Game?");
                        System.out.println("1. ID");
                        System.out.println("2. Nombre");
                        System.out.println("3. Volver");

                        // Obtenemos la opción
                        opcionGame = scanner.nextInt();

                        // Limpiamos el scanner
                        scanner.nextLine();

                        switch (opcionGame) {
                            case 1:
                                int idGame;

                                // Preguntamos y leemos el id
                                System.out.println();
                                System.out.println("¿Cuál es el ID?");

                                idGame = scanner.nextInt();

                                // Limpiamos el scanner
                                scanner.nextLine();

                                try {
                                    // Obtenemos el juego y lo mostramos por pantalla
                                    Game game = (Game) instancia.buscarPorId("getGameById", idGame);

                                    System.out.println("-----------------");
                                    System.out.println("ID -> " + game.getIdGame());
                                    System.out.println("Nombre -> " + game.getNombre());
                                    System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
                                    System.out.println("-----------------");
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún Game con ese ID");
                                }

                                break;

                            case 2:
                                String nombreJuego;

                                // Preguntamos y leemos el nombre del juego
                                System.out.println();
                                System.out.println("¿Cuál es el nombre del Game?");
                                nombreJuego = scanner.nextLine();

                                try {
                                    // Obtenemos todos los juegos con ese nombre o que lo contengan
                                    List<Game> games = instancia.buscarPorParametro("getGamesByName", "nombre",
                                            "%" + nombreJuego + "%");

                                    // Comprobamos que no esté vacío
                                    if (!games.isEmpty()) {
                                        // Mostramos los juegos
                                        for (Game gameObject : games) {
                                            System.out.println("-----------------");
                                            System.out.println("ID -> " + gameObject.getIdGame());
                                            System.out.println("Nombre -> " + gameObject.getNombre());
                                            System.out.println("Tiempo jugado -> " + gameObject.getTiempoJugado());
                                        }

                                        System.out.println("-----------------");
                                    } else {
                                        System.out.println("No se ha encontrado ningún Game con ese nombre");
                                    }
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún Game con ese nombre");
                                }
                                break;

                            case 3:
                                System.out.println("Volviendo...");
                                break;
                        }
                    } while (opcionGame != 3);
                }
                case 5 -> {
                    // Muestra todas las compras
                    try {
                        List<Compra> compras = instancia.listar("getAllCompras");

                        if (!compras.isEmpty()) {
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
                        } else {
                            System.out.println("No se ha encontrado ninguna compra");
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha encontrado ninguna compra");
                    }
                }
                case 6 -> {
                    // Muestra una compra
                    int opcionCompra;
                    int playerOption;

                    System.out.println();
                    System.out.println("¿Por qué campo deseas buscar la compra?");
                    System.out.println("1. ID");
                    System.out.println("2. Player");
                    System.out.println("3. Game");
                    System.out.println("4. Precio");
                    System.out.println("5. Fecha de compra");
                    System.out.println("6. Salir");

                    // Leemos la opción
                    opcionCompra = scanner.nextInt();

                    // Limpiamos el Scanner
                    scanner.nextLine();

                    switch (opcionCompra) {
                        case 1:
                            // Busca la compra por su ID
                            int idCompra;

                            // Preguntamos y leemos el id
                            System.out.println();
                            System.out.println("¿Cuál es el ID de la compra?");

                            idCompra = scanner.nextInt();

                            // Limpiamos el Scanner
                            scanner.nextLine();

                            try {
                                Compra compra = (Compra) instancia.buscarPorId("getCompraById", idCompra);

                                // Buscamos al jugador
                                Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

                                System.out.println("-----------------");
                                System.out.println("ID -> " + compra.getIdCompra());
                                System.out.println("Player -> " + player.getNick());
                                System.out.println("Game -> " + compra.getCosa());
                                System.out.println("Precio -> " + compra.getPrecio() + " €");
                                System.out.println("Fecha de compra -> " + compra.getFechaCompra());
                                System.out.println("-----------------");
                            } catch (Exception e) {
                                System.out.println("No se ha encontrado ninguna compra con ese id");
                            }
                            break;

                        case 2:
                            // Busca la compra por el nick del player
                            String nickPlayer;

                            // Preguntamos el nick del player y la leemos
                            System.out.println();
                            System.out.println("¿Cuál es el nick del player?");

                            nickPlayer = scanner.nextLine();

                            try {
                                // Buscamos al player
                                List<Player> players = instancia.buscarPorParametro("getPlayersByNick", "nick",
                                        "%" + nickPlayer + "%");

                                if (!players.isEmpty()) {
                                    if (players.size() > 1) {

                                        for (int i = 0; i < players.size(); i++) {
                                            Player playerO = players.get(i);
                                            System.out.println("-----------------");
                                            System.out.println("Opción " + i);
                                            System.out.println("ID -> " + playerO.getIdPlayer());
                                            System.out.println("Nick -> " + playerO.getNick());
                                            System.out.println("Email -> " + playerO.getEmail());
                                        }
                                        System.out.println("-----------------");

                                        do {
                                            System.out.println();
                                            System.out.println(
                                                    "Escribe el número de opción del player que deseas seleccionar (-1 para volver)");
                                            playerOption = scanner.nextInt();
                                        } while (playerOption > players.size());

                                        // Limpiamos el Scanner
                                        scanner.nextLine();

                                        if (playerOption != -1) {
                                            // Obtenemos el player seleccionado
                                            Player player = players.get(playerOption);

                                            try {
                                                // Buscamos las compras
                                                List<Compra> compras = instancia.listarConParametros(
                                                        "getComprasByPlayer",
                                                        "idPlayer",
                                                        player.getIdPlayer());

                                                if (!compras.isEmpty()) {
                                                    // Pintamos las compras
                                                    for (Compra compra : compras) {
                                                        System.out.println("-----------------");
                                                        System.out.println("ID -> " + compra.getIdCompra());
                                                        System.out.println("Player -> " + player.getNick());
                                                        System.out.println("Game -> " + compra.getCosa());
                                                        System.out.println("Precio -> " + compra.getPrecio() + " €");
                                                        System.out.println(
                                                                "Fecha de compra -> " + compra.getFechaCompra());
                                                    }
                                                    System.out.println("-----------------");
                                                } else {
                                                    System.out.println("No se ha encontrado ninguna compra para ese player");
                                                }
                                            } catch (Exception e) {
                                                System.out
                                                        .println("No se ha encontrado ninguna compra para ese player");
                                            }
                                        } else {
                                            System.out.println("Volviendo...");
                                        }
                                    } else {
                                        playerOption = players.get(0).getIdPlayer();
                                    }
                                } else {
                                    System.out.println("No se ha encontrado ningún player con ese nick");
                                }
                            } catch (Exception e) {
                                System.out.println("No se ha encontrado ninguna compra para ese player");
                            }

                            break;

                        case 3:
                            // Busca la compra por el nombre del juego
                            break;
                        case 4:
                            // Busca la compra por el precio
                            break;
                        case 5:
                            // Busca la compra por la fecha de compra
                            break;
                        case 6:
                            System.out.println("Volviendo...");
                            break;
                    }
                }
                case 7 -> System.out.println("Volviendo...");
            }
        } catch (Exception e) {
            System.out.println("Error al abrir la instancia:" + e.getMessage());
        } finally {
            instancia.cerrar();
        }
    }
}
