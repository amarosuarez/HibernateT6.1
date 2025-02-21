package com.amarosuarez;

import java.util.List;
import java.util.logging.Level;

import com.amarosuarez.auxiliares.Auxiliar;
import com.amarosuarez.dal.AccesoBD;
import com.amarosuarez.entities.Compra;
import com.amarosuarez.entities.Game;
import com.amarosuarez.entities.Player;

public class Main {

    private static AccesoBD instancia;

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        int opcion;
        instancia = new AccesoBD();

        do {
            // Mostramos el menú y recogemos la opción seleccionada
            opcion = Auxiliar.leeEntero(
                    menu());

            switch (opcion) {
                case 1 -> {
                    // LISTAR
                    int opcionListar;

                    do {
                        // Mostramos el menú de listar
                        // Recogemos la opción elegida
                        opcionListar = Auxiliar.leeEntero(menuListar());

                        // Ejecutamos la opción seleccionada
                        mostrarListarOpcion(opcionListar);
                    } while (opcionListar != 7);
                }
                case 2 -> {
                    // AÑADIR
                    int opcionAnyadir;

                    do {
                        // Mostramos el menú de añadir y recogemos la opción elegida
                        opcionAnyadir = Auxiliar.leeEntero(menuAnyadir());

                        // Ejecutamos la opción seleccioanda
                        mostrarAnyadirOpcion(opcionAnyadir);
                    } while (opcionAnyadir != 4);
                }
                case 4 -> {
                    // ELIMINAR
                    int opcionEliminar;

                    do {
                        // Mostramos el menú de eliminar y recogemos la opción elegida
                        opcionEliminar = Auxiliar.leeEntero(menuEliminar());

                        // Ejecutamos la opción seleccioanda
                        mostrarEliminarOpcion(opcionEliminar);
                    } while (opcionEliminar != 5);
                    System.out.println("Volviendo...");
                }
                case 5 ->
                    System.out.println("Hasta la próxima!");
            }

            // Salto de línea
            System.out.println();
        } while (opcion != 5);

    }

    // Función que pinta el menú general
    private static String menu() {
        String menu = "¿Qué desea hacer?\n";
        menu += "1. Listar\n";
        menu += "2. Añadir\n";
        menu += "3. Editar\n";
        menu += "4. Eliminar\n";
        menu += "5. Salir";

        return menu;
    }

    // Función que pinta el menú de listar
    private static String menuListar() {
        String menu = "";
        menu += "¿Qué desea listar?\n";
        menu += "1. Todos los jugadores\n";
        menu += "2. Un jugador\n";
        menu += "3. Todos los juegos\n";
        menu += "4. Un juego\n";
        menu += "5. Todas las compras\n";
        menu += "6. Una compra\n";
        menu += "7. Volver al menú principal";
        return menu;
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
                            if (listaPlayers.size() > 1) {
                                // Pintamos los players
                                Auxiliar.listaPlayers(listaPlayers);
                            }
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

                        // Leemos la opción
                        opcionBuscarPlayer = Auxiliar.leeEntero(
                                "¿Por qué campo deseas buscar al Player?\n"
                                + "1. ID\n"
                                + "2. Nick\n"
                                + "3. Email\n"
                                + "4. Volver");

                        switch (opcionBuscarPlayer) {
                            case 1 -> {
                                // Busca por ID
                                int idPlayer;

                                // Leemos el ID
                                idPlayer = Auxiliar.leeEntero("¿Cuál es el ID?");

                                try {
                                    player = (Player) instancia.buscarPorId("getPlayerById", idPlayer);

                                    Auxiliar.pintaUnPlayer(player);
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún player");
                                }
                            }

                            case 2 -> {
                                // Busca por nick
                                String nick;

                                nick = Auxiliar.leeString("¿Cuál es el nick?");

                                try {
                                    List<Player> players = instancia.buscarPorParametro("getPlayersByNick", "nick",
                                            "%" + nick + "%");

                                    if (!players.isEmpty()) {
                                        // Pintamos los players
                                        Auxiliar.listaPlayers(players);
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

                                email = Auxiliar.leeString("¿Cuál es el email?");

                                try {
                                    List<Player> players = instancia.buscarPorParametro("getPlayersByEmail", "email",
                                            "%" + email + "%");

                                    if (!players.isEmpty()) {
                                        // Pintamos los players
                                        Auxiliar.listaPlayers(players);
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
                                // Pintamos los games
                                Auxiliar.listaGames(listaGames);
                        } else {
                            System.out.println("No se ha encontrado ningún game");
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha encontrado ningún game");
                    }
                }
                case 4 -> {
                    // Listar un solo juego
                    int opcionGame = 0;

                    do {
                        // Obtenemos la opción
                        opcionGame = Auxiliar.leeEntero(
                                "¿Por qué campo desea buscar el Game?\n"
                                + "1. ID\n"
                                + "2. Nombre\n"
                                + "3. Volver");

                        switch (opcionGame) {
                            case 1:
                                int idGame;

                                // Leemos el ID
                                idGame = Auxiliar.leeEntero("¿Cuál es el ID?");

                                try {
                                    // Obtenemos el juego y lo mostramos por pantalla
                                    Game game = (Game) instancia.buscarPorId("getGameById", idGame);

                                    Auxiliar.pintaUnGame(game);
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ningún Game con ese ID");
                                }

                                break;

                            case 2:
                                String nombreJuego;

                                // Preguntamos y leemos el nombre del juego
                                nombreJuego = Auxiliar.leeString("¿Cuál es el nombre del Game?");

                                try {
                                    // Obtenemos todos los juegos con ese nombre o que lo contengan
                                    List<Game> games = instancia.buscarPorParametro("getGamesByName", "nombre",
                                            "%" + nombreJuego + "%");

                                    // Comprobamos que no esté vacío
                                    if (!games.isEmpty()) {
                                        // Mostramos los juegos
                                        Auxiliar.listaGames(games);
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
                            Auxiliar.listaCompras(compras, instancia);
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
                        // Leemos la opción
                        opcionCompra = Auxiliar.leeEntero(
                                "¿Por qué campo deseas buscar la compra?\n"
                                + "1. ID\n"
                                + "2. Player\n"
                                + "3. Game\n"
                                + "4. Precio\n"
                                + "5. Fecha de compra\n"
                                + "6. Volver");

                        switch (opcionCompra) {
                            case 1:
                                // Busca la compra por su ID
                                int idCompra;

                                // Preguntamos y leemos el id
                                idCompra = Auxiliar.leeEntero("¿Cuál es el ID?");

                                try {
                                    Compra compra = (Compra) instancia.buscarPorId("getCompraById", idCompra);

                                    // Buscamos al jugador
                                    Player player = (Player) instancia.buscarPorId("getPlayerById",
                                            compra.getIdPlayer());

                                    Auxiliar.pintaUnaCompra(compra, player);
                                } catch (Exception e) {
                                    System.out.println("No se ha encontrado ninguna compra con ese id");
                                }
                                break;

                            case 2:
                                // Busca la compra por el nick del player
                                String nickPlayer;

                                nickPlayer = Auxiliar.leeString("¿Cuál es el nick del player?");

                                List<Player> players = Auxiliar.getAllPlayersByNick(nickPlayer, instancia);

                                if (players != null && !players.isEmpty()) {
                                    if (players.size() > 1) {
                                        // Pintamos los players
                                        Auxiliar.pintaPlayers(players);

                                        playerOption = Auxiliar.pideElegirOpcionObjeto(players.size(), "player");
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
                                            Auxiliar.listaCompras(compras, instancia);
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
                                nameGame = Auxiliar.leeString("¿Cuál es el nombre del juego?");

                                try {
                                    int opcionJuego = 0;

                                    // Buscamos todos los games con ese nombre
                                    List<Game> games = instancia.buscarPorParametro("getGamesByName", "nombre",
                                            "%" + nameGame + "%");

                                    if (!games.isEmpty()) {

                                        if (games.size() > 1) {
                                            Auxiliar.pintaGames(games);

                                            opcionJuego = Auxiliar.pideElegirOpcionObjeto(games.size(), "game");
                                        }

                                        if (opcionJuego != -1) {
                                            // Buscamos las compras con ese juego
                                            List<Compra> compras = instancia.listarConParametros("getComprasByGame",
                                                    "idGame", games.get(opcionJuego).getIdGame());

                                            if (!compras.isEmpty()) {
                                                Auxiliar.listaCompras(compras, instancia);
                                            } else {
                                                System.out.println("No se ha encontrado ninguna compra con ese game");
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
                                precioInicio = Auxiliar.leeDouble(
                                        "Va a buscar una compra por franja de precio\n"
                                        + "¿Cuál es el precio inicial? (-1 para salir)");

                                if (precioInicio != -1) {
                                    precioFin = Auxiliar.leeDouble("¿Cuál es el precio final? (-1 para salir)");

                                    if (precioFin != -1) {
                                        // Buscamos todas las compras por ese precio
                                        List<Compra> compras = instancia.listarPorPrecio(precioInicio, precioFin);

                                        if (!compras.isEmpty()) {
                                            // Pintamos las compras
                                            Auxiliar.listaCompras(compras, instancia);
                                        } else {
                                            System.out.println(
                                                    "No se ha encontrado ninguna compra con ese rango de precios");
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
                                dia = Auxiliar.leeEntero(
                                        "Va a buscar una compra por su fecha (dd/MM/aaaa)\n"
                                        + "¿Cuál es el día? (-1 para salir)"
                                );

                                if (dia != -1) {
                                    mes = Auxiliar.leeEntero("¿Cuál es el mes? (-1 para salir)");

                                    if (mes != -1) {
                                        anyo = Auxiliar.leeEntero("¿Cuál es el año? (-1 para salir)");

                                        if (anyo != -1) {
                                            fecha = anyo + "-" + (mes < 10 ? "0" + mes : mes) + "-"
                                                    + (dia < 10 ? "0" + dia : dia);

                                            // Buscamos las compras
                                            List<Compra> compras = instancia.listarConParametros("getComprasByDate",
                                                    "fechaCompra", fecha);

                                            if (!compras.isEmpty()) {
                                                // Pintamos las compras
                                                Auxiliar.listaCompras(compras, instancia);
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
    private static String menuAnyadir() {
        String menu = "";
        menu += "¿Qué deseas añadir?\n";
        menu += "1. Player\n";
        menu += "2. Game\n";
        menu += "3. Compra\n";
        menu += "4. Volver al menú principal";

        return menu;
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
                    nick = Auxiliar.leeString("Ingrese el nombre (-1 para salir)");

                    if (!nick.equals("-1")) {
                        email = Auxiliar.leeString("Ingrese el email (-1 para salir)");

                        if (!email.equals("-1")) {
                            password = Auxiliar.leeString("Ingrese la contraseña (-1 para salir)");

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
                    nombre = Auxiliar.leeString("Ingrese el nombre (-1 para salir)");

                    if (!nombre.equals("-1")) {
                        // Pedimos las horas
                        horas = Auxiliar.leeEntero("Ingrese las horas jugadas (-1 para salir)");

                        if (horas != -1) {
                            do {
                                // Pedimos los minutos
                                minutos = Auxiliar.leeEntero("Ingrese los minutos jugados (-1 para salir)");
                            } while (minutos > 59);

                            if (minutos != -1) {
                                do {
                                    // Pedimos los segundos
                                    segundos = Auxiliar.leeEntero("Ingrese los segundos jugados (-1 para salir)");
                                } while (segundos > 59);

                                if (segundos != -1) {
                                    String tiempoJugado = (horas < 10 ? "0" + horas : horas) + ":"
                                            + (minutos < 10 ? "0" + minutos : minutos) + ":"
                                            + (segundos < 10 ? "0" + segundos : segundos);
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
                    nickPlayer = Auxiliar.leeString("Ingrese el nick del player");

                    List<Player> players = Auxiliar.getAllPlayersByNick(nickPlayer, instancia);

                    if (players != null && !players.isEmpty()) {
                        if (players.size() > 1) {
                            // Pintamos los players
                            Auxiliar.pintaPlayers(players);

                            playerOption = Auxiliar.pideElegirOpcionObjeto(players.size(), "player");
                        }

                        if (playerOption != -1) {
                            Player player = players.get(playerOption);
                            System.out.println("Player elegido: " + player.getNick() + " - " + player.getEmail());

                            // Agregamos un juego
                            String gameName;
                            int gameOption = 0;

                            gameName = Auxiliar.leeString("Ingrese el nombre del juego");

                            List<Game> games = Auxiliar.getAllGamesByName(gameName, instancia);

                            if (games != null && !games.isEmpty()) {
                                if (games.size() > 1) {
                                    Auxiliar.pintaGames(games);

                                    gameOption = Auxiliar.pideElegirOpcionObjeto(games.size(), "game");
                                }

                                if (gameOption >= 0) {
                                    Game game = games.get(gameOption);
                                    System.out.println("Game elegido: " + game.getNombre());

                                    // Pedimos un precio
                                    double precio = 0;

                                    do {
                                        precio = Auxiliar.leeDouble("Ingrese el precio");
                                    } while (precio < 0);

                                    // Pedimos la fecha de la compra
                                    String fecha = Auxiliar.pideFecha();

                                    if (!fecha.equals("-1")) {
                                        Compra compra = new Compra(player.getIdPlayer(), game.getIdGame(),
                                                game.getNombre(), precio, fecha);
                                        instancia.guardar(compra);
                                        System.out.println("Compra guardada con éxito");
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
    private static String menuEliminar() {
        String menu = "";
        menu += "¿Qué desea eliminar?\n";
        menu += "1. Un registro\n";
        menu += "2. Todos los registros de una tabla\n";
        menu += "3. Una tabla\n";
        menu += "4. Todas las tablas\n";
        menu += "5. Volver al menú principal";
        return menu;
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
                        String opc = "";
                        opc += "¿De qué tabla deseas eliminar?\n";
                        opc += "1. Player\n";
                        opc += "2. Game\n";
                        opc += "3. Compra\n";
                        opc += "4. Volver";

                        // Leemos la tabla elegida y limpiamos el scanner
                        tabla = Auxiliar.leeEntero(opc);

                        switch (tabla) {
                            case 1 -> {
                                eliminarPlayer();
                            }
                            case 2 -> {
                                eliminarGame();
                            }
                            case 3 -> {
                                eliminarCompra();
                            }
                            case 4 -> {
                                System.out.println("Volviendo...");
                            }
                        }
                    } while (tabla != 4);
                }
                case 2 -> {
                    int tablaElegida = 0;
                    String confirm = "";

                    do {
                        // Leemos la tabla elegida
                        tablaElegida = Auxiliar.leeEntero(
                                "¿De qué tabla deseas eliminar los registros?\n"
                                + "1. Player\n"
                                + "2. Game\n"
                                + "3. Compra\n"
                                + "4. Volver"
                        );

                        if (tablaElegida != 4) {
                            confirm = Auxiliar.confirmaBorrado();

                            if (confirm.equals("y")) {
                                switch (tablaElegida) {
                                    case 1 -> {
                                        try {
                                            instancia.borrarTodosLosRegistros("Player");
                                            System.out.println("Players borrados");
                                        } catch (Exception e) {
                                            System.out.println("Ha ocurrido un error");
                                        }
                                    }
                                    case 2 -> {
                                        try {
                                            instancia.borrarTodosLosRegistros("Game");
                                            System.out.println("Games borrados");
                                        } catch (Exception e) {
                                            System.out.println("Ha ocurrido un error");
                                        }
                                    }
                                    case 3 -> {
                                        try {
                                            instancia.borrarTodosLosRegistros("Compra");
                                            System.out.println("Compras borradas");
                                        } catch (Exception e) {
                                            System.out.println("Ha ocurrido un error");
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Operación cancelada");
                            }
                        } else {
                            System.out.println("Volviendo...");
                        }

                    } while (tablaElegida != 4);

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
            // Leemos la opción
            opcion = Auxiliar.leeEntero(
                    "¿Por qué campo desea eliminar?\n"
                    + "1. ID\n"
                    + "2. Nick\n"
                    + "3. Email\n"
                    + "4. Volver");

            switch (opcion) {
                case 1 -> {
                    // Por ID
                    int id;

                    // Pedimos el ID
                    id = Auxiliar.leeEntero("¿Cuál es el ID? (-1 para salir)");

                    if (id > -1) {
                        try {
                            Player player = (Player) instancia.buscarPorId("getPlayerById", id);

                            Auxiliar.muestraPlayerElegido(player);

                            Auxiliar.confirmDelete(player, "Player", instancia);
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

                    nick = Auxiliar.leeString("¿Cuál es el nick (-1 para salir)");

                    if (!nick.equals("-1")) {
                        int playerOption = 0;
                        Player player;
                        List<Player> players = Auxiliar.getAllPlayersByNick(nick, instancia);

                        if (players != null && !players.isEmpty()) {
                            if (players.size() > 1) {
                                Auxiliar.pintaPlayers(players);

                                do {
                                    // Leemos la opción
                                    playerOption = Auxiliar.leeEntero(
                                            "Escribe el número de opción del player que deseas seleccionar (-1 para volver)");
                                } while (playerOption > players.size());
                            }

                            if (playerOption != -1) {
                                player = players.get(playerOption);
                                Auxiliar.muestraPlayerElegido(player);

                                Auxiliar.confirmDelete(player, "Player", instancia);
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

                    email = Auxiliar.leeString("¿Cuál es el email (-1 para salir)");

                    if (!email.equals("-1")) {
                        int playerOption = 0;
                        Player player;
                        List<Player> players = Auxiliar.getAllPlayersByEmail(email, instancia);

                        if (players != null && !players.isEmpty()) {
                            if (players.size() > 1) {
                                Auxiliar.pintaPlayers(players);

                                playerOption = Auxiliar.pideElegirOpcionObjeto(players.size(), "player");
                            }

                            if (playerOption != -1) {
                                player = players.get(playerOption);
                                Auxiliar.muestraPlayerElegido(player);

                                Auxiliar.confirmDelete(player, "Player", instancia);
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
                case 4 -> {
                    System.out.println("Volviendo...");
                }
            }
        } while (opcion != 4);
    }

    // Función que elimina un game
    private static void eliminarGame() {
        int opcion;

        do {
            // Leemos la opción
            opcion = Auxiliar.leeEntero(
                    "¿Por qué campo desea eliminar?\n"
                    + "1. ID\n"
                    + "2. Nombre\n"
                    + "3. Volver");

            switch (opcion) {
                case 1 -> {
                    // POR ID
                    int id;

                    // Leemos el ID
                    id = Auxiliar.leeEntero("¿Cuál es el ID? (-1 para salir)");

                    if (id > -1) {
                        try {
                            Game game = (Game) instancia.buscarPorId("getGameById", id);

                            Auxiliar.muestraGameElegido(game);

                            Auxiliar.confirmDelete(game, "Game", instancia);
                        } catch (Exception e) {
                            System.out.println("No se ha encontrado ningún game con ese ID");
                        }
                    } else {
                        System.out.println("Volviendo...");
                    }
                }
                case 2 -> {
                    // Por nombre
                    String name;

                    name = Auxiliar.leeString("¿Cuál es el nombre (-1 para salir)");

                    if (!name.equals("-1")) {
                        int gameOption = 0;
                        Game game;
                        List<Game> games = Auxiliar.getAllGamesByName(name, instancia);

                        if (games != null && !games.isEmpty()) {
                            if (games.size() > 1) {
                                Auxiliar.pintaGames(games);

                                gameOption = Auxiliar.pideElegirOpcionObjeto(games.size(), "game");
                            }

                            if (gameOption != -1) {
                                game = games.get(gameOption);
                                Auxiliar.muestraGameElegido(game);

                                Auxiliar.confirmDelete(game, "Game", instancia);
                            } else {
                                System.out.println("Volviendo...");
                            }
                        } else {
                            System.out.println("No se ha encontrado ningún game con ese nombre");
                        }
                    }
                }
            }
        } while (opcion != 3);
    }

    // Función que elimina una compra
    private static void eliminarCompra() {
        int opcion;

        do {
            // Leemos la opción
            opcion = Auxiliar.leeEntero(
                    "¿Por qué campo desea eliminar?\n"
                    + "1. ID\n"
                    + "2. Player\n"
                    + "3. Game\n"
                    + "4. Precio\n"
                    + "5. Fecha de compra\n"
                    + "6. Volver");

            switch (opcion) {
                case 1 -> {
                    // Elimina por ID
                    int id;

                    id = Auxiliar.leeEntero("¿Cuál es el ID? (-1 para salir)");

                    if (id != -1) {
                        try {
                            Compra compra = (Compra) instancia.buscarPorId("getCompraById", id);

                            Auxiliar.muestraCompraElegida(compra, instancia);
                        } catch (Exception e) {
                            System.out.println("No se ha encontrado ninguna compra con ese ID");
                        }
                    } else {
                        System.out.println("Volviendo...");
                    }
                }
                case 2 -> {
                    // Elimina por Player
                    String nick = "";
                    int playerOption = 0;

                    nick = Auxiliar.leeString("¿Cuál es el nick del player?");

                    List<Player> players = Auxiliar.getAllPlayersByNick(nick, instancia);

                    if (players != null && !players.isEmpty()) {
                        if (players.size() > 1) {
                            // Pintamos los players
                            Auxiliar.pintaPlayers(players);

                            playerOption = Auxiliar.pideElegirOpcionObjeto(players.size(), "player");
                        } else {
                            Auxiliar.muestraPlayerElegido(players.get(playerOption));
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
                                int compraOpcion = 0;

                                if (compras.size() > 1) {
                                    Auxiliar.pintaCompras(compras, instancia);

                                    compraOpcion = Auxiliar.pideElegirOpcionObjeto(compras.size(), "Compra");
                                } else {
                                    Auxiliar.muestraCompraElegida(compras.get(compraOpcion), instancia);
                                }

                                if (compraOpcion != -1) {
                                    Auxiliar.confirmDelete(compras.get(compraOpcion), "compra", instancia);
                                } else {
                                    System.out.println("Volviendo...");
                                }
                            } else {
                                System.out.println("No se ha encontrado ninguna compra para ese player");
                            }
                        } else {
                            System.out.println("Volviendo...");
                        }
                    } else {
                        System.out.println("No se ha encontrado ningún player con ese nick");
                    }
                }
                case 3 -> {
                    // Por Game
                    String nombreGame = "";
                    int gameOpcion = 0;

                    nombreGame = Auxiliar.leeString("¿Cuál es el nombre del Game?");

                    List<Game> games = instancia.buscarPorParametro("getGamesByName", "nombre", "%" + nombreGame + "%");

                    if (!games.isEmpty()) {
                        if (games.size() > 1) {
                            Auxiliar.pintaGames(games);
                            gameOpcion = Auxiliar.pideElegirOpcionObjeto(games.size(), nombreGame);
                        } else {
                            Auxiliar.muestraGameElegido(games.get(gameOpcion));
                            System.out.println();
                        }

                        if (gameOpcion != -1) {
                            int compraOpcion = 0;

                            // Obtenemos el Game
                            Game game = games.get(gameOpcion);

                            // Obtenemos las compras con ese Game
                            List<Compra> compras = instancia.listarConParametros("getComprasByGame", "idGame", game.getIdGame());

                            if (!compras.isEmpty()) {
                                if (compras.size() > 1) {
                                    Auxiliar.pintaCompras(compras, instancia);
                                    compraOpcion = Auxiliar.pideElegirOpcionObjeto(compras.size(), "compra");
                                } else {
                                    Auxiliar.muestraCompraElegida(compras.get(compraOpcion), instancia);
                                }

                                if (compraOpcion != -1) {
                                    Auxiliar.confirmDelete(compras.get(compraOpcion), "compra", instancia);
                                } else {
                                    System.out.println("Volviendo...");
                                }
                            } else {
                                System.out.println("No se ha encontrado ninguna compra con ese Game");
                            }
                        } else {
                            System.out.println("Volviendo...");
                        }
                    } else {
                        System.out.println("No se ha encontrado ningún Game con ese nombre");
                    }
                }
                case 4 -> {
                    // Por precio
                    double precio = 0;
                    int compraOpcion = 0;

                    precio = Auxiliar.leeDouble("¿Cuál es el precio de la compra?");

                    // Buscamos las compras
                    List<Compra> compras = instancia.listarPorPrecioUnico(precio);

                    if (!compras.isEmpty()) {
                        if (compras.size() > 1) {
                            Auxiliar.pintaCompras(compras, instancia);
                            compraOpcion = Auxiliar.pideElegirOpcionObjeto(compras.size(), "Compra");
                        } else {
                            Auxiliar.muestraCompraElegida(compras.get(compraOpcion), instancia);
                        }

                        if (compraOpcion != -1) {
                            Auxiliar.confirmDelete(compras.get(compraOpcion), "compra", instancia);
                        } else {
                            System.out.println("Volviendo...");
                        }
                    } else {
                        System.out.println("No se ha encontrado ninguna compra con ese precio");
                    }
                }
                case 5 -> {

                    // Por fecha
                    String fecha = "";
                    int compraOpcion = 0;

                    // Pedimos la fecha
                    fecha = Auxiliar.pideFecha();

                    if (!fecha.equals("-1")) {
                        List<Compra> compras = instancia.buscarPorParametro("getComprasByDate", "fechaCompra", fecha);

                        if (!compras.isEmpty()) {
                            if (compras.size() > 1) {
                                Auxiliar.pintaCompras(compras, instancia);
                                compraOpcion = Auxiliar.pideElegirOpcionObjeto(compras.size(), "compra");
                            } else {
                                Auxiliar.muestraCompraElegida(compras.get(compraOpcion), instancia);
                            }

                            if (compraOpcion != -1) {
                                Auxiliar.confirmDelete(compras.get(compraOpcion), "Compra", instancia);
                            } else {
                                System.out.println("Volviendo...");
                            }
                        } else {
                            System.out.println("No se ha encontrado ninguna compra con esa fecha");
                        }
                    } else {
                        System.out.println("Volviendo...");
                    }
                }
                case 6 -> {
                    System.out.println("Volviendo...");
                }
            }
        } while (opcion != 6);
    }
}
