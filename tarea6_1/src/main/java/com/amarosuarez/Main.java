package com.amarosuarez;

import java.time.Year;
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

            switch (opcion) {
                case 1 -> {
                    // LISTAR
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
                    } while (opcionListar != 7);
                }
                case 2 -> {
                    // AÑADIR
                    int opcionAnyadir;

                    do {
                        // Mostramos el menú de añadir
                        menuAnyadir();

                        // Recogemos la opción elegida
                        opcionAnyadir = scanner.nextInt();

                        // Limpiamos el scanner
                        scanner.nextLine();

                        // Ejecutamos la opción seleccioanda
                        mostrarAnyadirOpcion(opcionAnyadir);
                    } while (opcionAnyadir != 4);
                }
                case 4 -> {
                    // ELIMINAR
                    int opcionEliminar;

                    do {
                        // Mostramos el menú de eliminar
                        menuEliminar();

                        // Recogemos la opción elegida
                        opcionEliminar = scanner.nextInt();

                        // Limpiamos el Scanner
                        scanner.nextLine();

                        // Ejecutamos la opción seleccioanda
                        mostrarEliminarOpcion(opcionEliminar);
                    } while (opcionEliminar != 4);
                }
                case 5 ->
                    System.out.println("Hasta la próxima!");
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
        System.out.println();
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
                    int playerOption = 0;

                    do {
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

                                List<Player> players = getAllPlayersByNick(nickPlayer);

                                if (players != null && !players.isEmpty()) {
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
                                    }

                                    if (playerOption != -1) {
                                        // Obtenemos el player seleccionado
                                        Player player = players.get(playerOption);

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
                                    } else {
                                        System.out.println("Volviendo...");
                                    }
                                } else {
                                    System.out.println("No se ha encontrado ningún player con ese nick");
                                }

                                break;

                            case 3:
                                // Busca la compra por el nombre del juego
                                String nameGame;

                                // Preguntamos y leemos el nombre del juego
                                System.out.println();
                                System.out.println("¿Cuál es el nombre del juego?");
                                nameGame = scanner.nextLine();

                                try {
                                    int opcionJuego = 0;
                                    int idGame;

                                    // Buscamos todos los games con ese nombre
                                    List<Game> games = instancia.buscarPorParametro("getGamesByName", "nombre", "%" + nameGame + "%");

                                    if (!games.isEmpty()) {

                                        if (games.size() > 1) {
                                            for (int i = 0; i < games.size(); i++) {
                                                Game game = games.get(i);
                                                System.out.println("-----------------");
                                                System.out.println("Opción " + i);
                                                System.out.println("ID -> " + game.getIdGame());
                                                System.out.println("Nombre -> " + game.getNombre());
                                                System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
                                            }
                                            System.out.println("-----------------");

                                            do {
                                                System.out.println();
                                                System.out.println(
                                                        "Escribe el número de opción del juego que deseas seleccionar (-1 para volver)");
                                                opcionJuego = scanner.nextInt();
                                            } while (opcionJuego > games.size());

                                        } else {
                                            idGame = games.get(0).getIdGame();
                                        }

                                        if (opcionJuego != -1) {
                                            // Buscamos las compras con ese juego
                                            List<Compra> compras = instancia.listarConParametros("getComprasByGame", "idGame", games.get(opcionJuego).getIdGame());

                                            if (!compras.isEmpty()) {
                                                // Pintamos las compras
                                                for (Compra compra : compras) {
                                                    // Buscamos el player
                                                    Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

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
                                                throw new Exception();
                                            }
                                        } else {
                                            System.out.println("Volviendo...");
                                        }

                                    } else {
                                        System.out.println("No se ha encontrado ningún juego con ese nombre");
                                    }

                                } catch (Exception e) {
                                    System.err.println("No se ha encontrado ninguna compra con ese juego");
                                }
                                break;
                            case 4:
                                // Busca la compra por el precio
                                double precioInicio;
                                double precioFin;

                                // Preguntamos y leemos los precios
                                System.out.println("Va a buscar una compra por franja de precio");
                                System.out.println("¿Cuál es el precio inicial? (-1 para salir)");
                                precioInicio = scanner.nextDouble();

                                scanner.nextLine();

                                if (precioInicio != -1) {
                                    System.out.println();
                                    System.out.println("¿Cuál es el precio final? (-1 para salir)");
                                    precioFin = scanner.nextDouble();

                                    if (precioFin != -1) {
                                        // Buscamos todas las compras por ese precio
                                        List<Compra> compras = instancia.listarPorPrecio(precioInicio, precioFin);

                                        if (!compras.isEmpty()) {
                                            // Pintamos las compras
                                            for (Compra compra : compras) {
                                                // Buscamos el player
                                                Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

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
                                            System.out.println("No se ha encontrado ninguna compra con ese rango de precios");
                                        }
                                    } else {
                                        System.out.println("Volviendo...");
                                    }
                                } else {
                                    System.out.println("Volviendo...");
                                }
                                break;
                            case 5:
                                // Busca la compra por la fecha de compra
                                String fecha;
                                int dia;
                                int mes;
                                int anyo;

                                // Preguntamos y leemos los datos
                                System.out.println();
                                System.out.println("Va a buscar una compra por su fecha (dd/MM/aaaa)");
                                System.out.println("¿Cuál es el día? (-1 para salir)");
                                dia = scanner.nextInt();
                                scanner.nextLine();

                                if (dia != -1) {
                                    System.out.println();
                                    System.out.println("¿Cuál es el mes? (-1 para salir)");
                                    mes = scanner.nextInt();
                                    scanner.nextLine();

                                    if (mes != -1) {
                                        System.out.println();
                                        System.out.println("¿Cuál es el año? (-1 para salir)");
                                        anyo = scanner.nextInt();
                                        scanner.nextLine();

                                        if (anyo != -1) {
                                            fecha = anyo + "/" + (mes < 10 ? "0" + mes : mes) + "/" + (dia < 10 ? "0" + dia : dia);

                                            // Buscamos las compras
                                            List<Compra> compras = instancia.listarConParametros("getComprasByDate", "fechaCompra", fecha);

                                            if (!compras.isEmpty()) {
                                                // Pintamos las compras
                                                for (Compra compra : compras) {
                                                    // Buscamos el player
                                                    Player player = (Player) instancia.buscarPorId("getPlayerById", compra.getIdPlayer());

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
                                                System.out.println("No se ha encontrado ninguna compra para esa fecha");
                                            }

                                        } else {
                                            System.out.println("Volviendo...");
                                        }
                                    } else {
                                        System.out.println("Volviendo...");
                                    }
                                } else {
                                    System.out.println("Volviendo...");
                                }

                                break;
                            case 6:
                                System.out.println("Volviendo...");
                                break;
                        }
                    } while (opcionCompra != 6);
                }
                case 7 ->
                    System.out.println("Volviendo...");
            }
        } catch (Exception e) {
            System.out.println("Error al abrir la instancia:" + e.getMessage());
        } finally {
            instancia.cerrar();
        }
    }

    // Función que pinta el menú de añadir
    private static void menuAnyadir() {
        System.out.println();
        System.out.println("¿Qué deseas añadir?");
        System.out.println("1. Player");
        System.out.println("2. Game");
        System.out.println("3. Compra");
        System.out.println("4. Volver al menú principal");
    }

    // Función que muestra los resultados de la opción elegida de añadir
    private static void mostrarAnyadirOpcion(int opcionAnyadir) {
        try {
            instancia.abrir();

            switch (opcionAnyadir) {
                case 1 -> {
                    // Añade un player
                    String nick;
                    String email;
                    String password;

                    System.out.println();
                    System.out.println("Va a añadir un nuevo Player");

                    // Preguntamos y leemos los datos
                    System.out.println("¿Cuál es su nombre? (-1 para salir)");
                    nick = scanner.nextLine();

                    if (!nick.equals("-1")) {
                        System.out.println();
                        System.out.println("¿Cuál es su email? (-1 para salir)");
                        email = scanner.nextLine();

                        if (!email.equals("-1")) {
                            System.out.println();
                            System.out.println("¿Cuál es su contraseña? (-1 para salir)");
                            password = scanner.nextLine();

                            if (!password.equals("-1")) {
                                Player player = new Player(nick, password, email);

                                Object ob = instancia.guardar(player);

                                if (ob != null) {
                                    System.out.println("Player guardado con éxito");
                                } else {
                                    System.out.println("No se ha podido guardar al jugador");
                                }
                            } else {
                                System.out.println("Saliendo...");
                            }
                        } else {
                            System.out.println("Saliendo...");
                        }
                    } else {
                        System.out.println("Saliendo...");
                    }
                }
                case 2 -> {
                    // Añade un Game
                    String nombre;
                    int horas;
                    int minutos;
                    int segundos;

                    System.out.println();
                    System.out.println("Va a añadir un nuevo Game");

                    // Preguntamos y leemos los datos
                    System.out.println("¿Cuál es su nombre? (-1 para salir)");
                    nombre = scanner.nextLine();

                    if (!nombre.equals("-1")) {
                        System.out.println();
                        System.out.println("¿Cuales son sus horas jugadas? (-1 para salir)");
                        horas = scanner.nextInt();
                        scanner.nextLine();

                        if (horas != -1) {
                            do {
                                System.out.println();
                                System.out.println("¿Cuales son sus minutos jugados? (-1 para salir)");
                                minutos = scanner.nextInt();
                                scanner.nextLine();
                            } while (minutos > 59);

                            if (minutos != -1) {
                                do {
                                    System.out.println();
                                    System.out.println("¿Cuales son sus segundos jugados? (-1 para salir)");
                                    segundos = scanner.nextInt();
                                    scanner.nextLine();
                                } while (segundos > 59);

                                if (segundos != -1) {
                                    String tiempoJugado = (horas < 10 ? "0" + horas : horas) + ":" + (minutos < 10 ? "0" + minutos : minutos) + ":" + (segundos < 10 ? "0" + segundos : segundos);
                                    Game game = new Game(nombre, tiempoJugado);

                                    Object ob = instancia.guardar(game);

                                    if (ob != null) {
                                        System.out.println("Game guardado con éxito");
                                    } else {
                                        System.out.println("No se ha podido guardar al jugador");
                                    }
                                } else {
                                    System.out.println("Saliendo...");
                                }

                            } else {
                                System.out.println("Saliendo...");
                            }
                        } else {
                            System.out.println("Saliendo...");
                        }
                    } else {
                        System.out.println("Saliendo...");
                    }
                }
                case 3 -> {
                    // Añade una Compra
                    int playerOption = 0;
                    String nickPlayer;

                    // Preguntamos el nick del player y la leemos
                    System.out.println();
                    System.out.println("¿Cuál es el nick del player?");

                    nickPlayer = scanner.nextLine();

                    List<Player> players = getAllPlayersByNick(nickPlayer);

                    if (players != null && !players.isEmpty()) {
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

                        }

                        if (playerOption != -1) {
                            Player player = players.get(playerOption);

                            // Agregamos un juego
                            String gameName;
                            int gameOption = 0;

                            System.out.println();
                            System.out.println("¿Cuál es el nombre del juego?");
                            gameName = scanner.nextLine();

                            List<Game> games = getAllGames(gameName);

                            if (games != null && !games.isEmpty()) {
                                if (games.size() > 1) {
                                    for (int i = 0; i < games.size(); i++) {
                                        Game game = games.get(i);
                                        System.out.println("-----------------");
                                        System.out.println("Opción " + i);
                                        System.out.println("ID -> " + game.getIdGame());
                                        System.out.println("Nombre -> " + game.getNombre());
                                        System.out.println("Tiempo jugado -> " + game.getTiempoJugado());
                                    }
                                    System.out.println("-----------------");

                                    do {
                                        System.out.println();
                                        System.out.println(
                                                "Escribe el número de opción del juego que deseas seleccionar (-1 para volver)");
                                        gameOption = scanner.nextInt();
                                    } while (gameOption > games.size());
                                }

                                if (gameOption >= 0) {
                                    Game game = games.get(gameOption);

                                    // Pedimos un precio
                                    double precio = 0;

                                    do {
                                        System.out.println();
                                        System.out.println("¿Cuál es el precio?");
                                        precio = scanner.nextDouble();
                                    } while (precio < 0);

                                    scanner.nextLine();

                                    // Pedimos la fecha de la compra
                                    int dia;
                                    int mes;
                                    int anyo;

                                    do {
                                        System.out.println();
                                        System.out.println("¿Cuál es el año de la compra? (-1 para salir)");
                                        anyo = scanner.nextInt();
                                    } while ((anyo > 0 && anyo < 1900) || anyo < -1);

                                    if (anyo > 0) {
                                        do {
                                            System.out.println();
                                            System.out.println("¿Cuál es el mes de la compra? (-1 para salir)");
                                            mes = scanner.nextInt();
                                        } while (mes > 12 || mes < -1 || mes == 0);

                                        if (mes > 0) {
                                            do {
                                                System.out.println();
                                                System.out.println("¿Cuál es el día de la compra? (-1 para salir)");
                                                dia = scanner.nextInt();
                                            } while (dia > 31
                                                    || dia < -1
                                                    || dia == 0
                                                    || ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
                                                    || (Year.isLeap(anyo) && mes == 2 && dia > 29)
                                                    || (!Year.isLeap(anyo) && mes == 2 && dia > 28));

                                            if (dia > 0) {
                                                String fecha = anyo + "-" + (mes > 9 ? mes : "0" + mes) + "-" + (dia > 9 ? dia : "0" + dia);

                                                Compra compra = new Compra(player.getIdPlayer(), game.getIdGame(), game.getNombre(), precio, fecha);
                                                instancia.guardar(compra);
                                                System.out.println("Compra guardada con éxito");
                                            } else {
                                                System.out.println("Volviendo...");
                                            }
                                        } else {
                                            System.out.println("Volviendo...");
                                        }
                                    } else {
                                        System.out.println("Volviendo...");
                                    }
                                } else {
                                    System.out.println("Volviendo...");
                                }
                            } else {
                                System.out.println("No se ha encontrado ningún juego con ese nombre");
                            }

                        } else {
                            System.out.println("Volviendo...");
                        }
                    } else {
                        System.out.println("No se ha encontrado ningún player con esen nick");
                    }
                }
                case 4 -> {
                    System.out.println("Volviendo...");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al abrir la instancia:" + e.getMessage());
        } finally {
            instancia.cerrar();
        }
    }

    // Función que pinta el menú de eliminar
    private static void menuEliminar() {
        System.out.println();
        System.out.println("¿Qué desea eliminar?");
        System.out.println("1. Un registro");
        System.out.println("2. Una tabla");
        System.out.println("3. Todas las tablas");
        System.out.println("4. Volver al menú principal");
    }

    // Función que muestra los resultados de la opción elegida de eliminar
    private static void mostrarEliminarOpcion(int opcionEliminar) {
        try {
            instancia.abrir();

            switch (opcionEliminar) {
                case 1 -> {
                    // Elimina un registro
                    int tabla;

                    do {
                        System.out.println();
                        System.out.println("¿De qué tabla deseas eliminar?");
                        System.out.println("1. Player");
                        System.out.println("2. Game");
                        System.out.println("3. Compra");
                        System.out.println("4. Volver");

                        // Leemos la tabla elegida y limpiamos el scanner
                        tabla = scanner.nextInt();
                        scanner.nextLine();

                        switch (tabla) {
                            case 1 -> {
                                eliminarPlayer();
                            }
                        }
                    } while (tabla != 4);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al abrir la instancia:" + e.getMessage());
        } finally {
            instancia.cerrar();
        }
    }

    // Función que elimina un player
    private static void eliminarPlayer() {
        int opcion;

        do {
            System.out.println();
            System.out.println("¿Por qué campo desea eliminar?");
            System.out.println("1. ID");
            System.out.println("2. Nick");
            System.out.println("3. Email");
            System.out.println("4. Volver");

            // Leemos la opción y limpiamos el scanner
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    // Por ID
                    int id;

                    System.out.println();
                    System.out.println("¿Cuál es el ID (-1 para salir)");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    if (id > -1) {
                        try {
                            Player player = (Player) instancia.buscarPorId("getPlayerById", id);

                            System.out.println();
                            System.out.println("El player elegido es:");
                            System.out.println("ID -> " + player.getIdPlayer());
                            System.out.println("Nick -> " + player.getNick());
                            System.out.println("Email -> " + player.getEmail());

                            String confirm;
                            do {
                                System.out.println();
                                System.out.println("¿Está seguro de que desea eliminarlo? (y/n)");
                                confirm = scanner.nextLine();

                                if (confirm.equalsIgnoreCase("y")) {
                                    instancia.borrar(player);
                                    System.out.println("Player eliminado");
                                } else if (confirm.equalsIgnoreCase("n")) {
                                    System.out.println("Operación cancelada");
                                }
                            } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
                        } catch (Exception e) {
                            System.out.println("No se ha encontrado ningún player con ese ID");
                        }
                    } else {
                        System.out.println("Volviendo...");
                    }
                }
                case 2 -> {
                    // Por nick
                    String nick;

                    System.out.println();
                    System.out.println("¿Cuál es el nick (-1 para salir)");
                    nick = scanner.nextLine();

                    if (!nick.equals("-1")) {
                        int playerOption = 0;
                        Player player;
                        List<Player> players = getAllPlayersByNick(nick);

                        if (players != null && !players.isEmpty()) {
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
                            }

                            if (playerOption != -1) {
                                player = players.get(playerOption);
                                System.out.println();
                                System.out.println("El player elegido es:");
                                System.out.println("ID -> " + player.getIdPlayer());
                                System.out.println("Nick -> " + player.getNick());
                                System.out.println("Email -> " + player.getEmail());

                                String confirm;
                                do {
                                    System.out.println();
                                    System.out.println("¿Está seguro de que desea eliminarlo? (y/n)");
                                    confirm = scanner.nextLine();

                                    if (confirm.equalsIgnoreCase("y")) {
                                        instancia.borrar(player);
                                        System.out.println("Player eliminado");
                                    } else if (confirm.equalsIgnoreCase("n")) {
                                        System.out.println("Operación cancelada");
                                    }
                                } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
                            } else {
                                System.out.println("Volviendo...");
                            }
                        } else {
                            System.out.println("No se ha encontrado ningún player con ese nick");
                        }
                    } else {
                        System.out.println("Volviendo...");
                    }
                }
                case 3 -> {
                    // Por email
                    String email;

                    System.out.println();
                    System.out.println("¿Cuál es el email (-1 para salir)");
                    email = scanner.nextLine();

                    if (!email.equals("-1")) {
                        int playerOption = 0;
                        Player player;
                        List<Player> players = getAllPlayersByEmail(email);

                        if (players != null && !players.isEmpty()) {
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
                            }

                            if (playerOption != -1) {
                                player = players.get(playerOption);
                                System.out.println();
                                System.out.println("El player elegido es:");
                                System.out.println("ID -> " + player.getIdPlayer());
                                System.out.println("Nick -> " + player.getNick());
                                System.out.println("Email -> " + player.getEmail());

                                String confirm;
                                do {
                                    System.out.println();
                                    System.out.println("¿Está seguro de que desea eliminarlo? (y/n)");
                                    confirm = scanner.nextLine();

                                    if (confirm.equalsIgnoreCase("y")) {
                                        instancia.borrar(player);
                                        System.out.println("Player eliminado");
                                    } else if (confirm.equalsIgnoreCase("n")) {
                                        System.out.println("Operación cancelada");
                                    }
                                } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
                            } else {
                                System.out.println("Volviendo...");
                            }
                        } else {
                            System.out.println("No se ha encontrado ningún player con ese email");
                        }
                    } else {
                        System.out.println("Volviendo...");
                    }
                }
            }
        } while (opcion != 4);
    }

    // Función que obtiene todos los players con un nick
    private static List<Player> getAllPlayersByNick(String nick) {
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
    private static List<Player> getAllPlayersByEmail(String email) {
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
    private static List<Game> getAllGames(String name) {
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
}
