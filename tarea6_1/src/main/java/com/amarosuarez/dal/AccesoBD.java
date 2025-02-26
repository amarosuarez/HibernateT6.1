package com.amarosuarez.dal;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.amarosuarez.entities.Compra;
import com.amarosuarez.entities.Game;
import com.amarosuarez.entities.Player;

/**
 * Clase que almacena los métodos relacionados a la Base de Datos
 *
 * @author Amaro Suárez
 * @version 1.0
 */
public class AccesoBD {

    /**
     * Atributo que almacena el sessionfactory
     */
    private SessionFactory sf;

    /**
     * Atributo que almacena la sesion
     */
    private Session session;

    /**
     * Atributo que almacena el transaction
     */
    private Transaction transaction;

    /**
     * Función que configura Hibernate
     *
     * @throws Exception Exception
     */
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new Exception("Error al configurar Hibernate: " + e.getMessage(), e); // Lanza una excepción clara
        }
    }

    /**
     * Función que abre la conexión con la base de datos e inicia una
     * transacción
     *
     * @throws Exception Excepción
     */
    public void abrir() throws Exception {
        setUp();
        session = sf.openSession();
        // transaction = session.beginTransaction();
    }

    /**
     * Función que cierra la transacción y la base de datos
     */
    public void cerrar() {
        if (transaction != null && transaction.isActive()) {
            try {
                transaction.commit(); // Confirmar la transacción si está activa
            } catch (Exception e) {
                try {
                    transaction.rollback(); // Revertir la transacción en caso de error
                } catch (Exception ex) {
                    ex.printStackTrace(); // Manejar errores durante el rollback
                }
                e.printStackTrace(); // Manejar errores durante el commit
            }
        }

        if (sf != null) {
            sf.close(); // Cerrar la SessionFactory
        }
    }

    /**
     * Función que guarda un objeto
     *
     * @param cosa Objeto a guardar
     * @return Objeto
     */
    public Object guardar(Object cosa) {
        return session.save(cosa);
    }

    /**
     * Función que elimina un objeto
     *
     * @param cosa Objeto a eliminar
     */
    public void borrar(Object cosa) {
        // session.delete(cosa);
        String hql;
        String nombreEntidad = "";
        String parametro = "";
        int valorParametro = 0;

        try {
            // Verificar si la sesión está abierta
            if (session.isOpen()) {
                // Iniciar una transacción
                transaction = session.beginTransaction();
                
                // Comprobamos de que clase es el objeto
                if (cosa instanceof Player player) {
                    nombreEntidad = "Player";
                    parametro = "idPlayer";
                    valorParametro = player.getIdPlayer();
                } else if (cosa instanceof Game game) {
                    nombreEntidad = "Game";
                    parametro = "idGame";
                    valorParametro = game.getIdGame();
                } else if (cosa instanceof Compra compra) {
                    nombreEntidad = "Compra";
                    parametro = "idCompra";
                    valorParametro = compra.getIdCompra();
                }

                hql = "DELETE FROM " + nombreEntidad + " WHERE " + parametro + " = :" + parametro;

                // Ejecutar la consulta
                session.createQuery(hql)
                        .setParameter(parametro, valorParametro)
                        .executeUpdate();
            }
        } catch (Exception e) {
            // Revertir la transacción en caso de error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Función que elimina todos los registros de una tabla
     *
     * @param nombreEntidad Nombre de la entidad
     */
    public void borrarTodosLosRegistros(String nombreEntidad) {
        transaction = session.beginTransaction();
        try {
            String hql = "DELETE FROM " + nombreEntidad;
            session.createQuery(hql).executeUpdate();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Función que recibe un booleano y hace commit o rollback
     *
     * @param rollback Booleano que indica si se hace commit o rollback
     */
    public void rollbackTransaction(boolean rollback) {
        try {
            if (!rollback) {
                transaction.commit();
                System.out.println("Cambios guardados!✅");
            } else {
                transaction.rollback();
                System.out.println("Cambios descartados❌");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Función que devuelve una lista
     *
     * @param namedQuery Nombre de la query
     * @return Lista
     */
    public List listar(String namedQuery) {
        TypedQuery query = session.getNamedQuery(namedQuery);

        return query.getResultList();
    }

    /**
     * Función que devuelve una lista buscada por un parámetro determinado
     *
     * @param namedQuery Nombre de la query
     * @param parametro Nombre del parámetro
     * @param valor Valor del parámetro
     * @return Lista
     */
    public List listarConParametros(String namedQuery, String parametro, String valor) {
        TypedQuery query = session.getNamedQuery(namedQuery).setParameter(parametro, valor);

        return query.getResultList();
    }

    /**
     * Función que devuelve una lista buscada por un parámetro determinado y su
     * valor es int
     *
     * @param namedQuery Nombre de la query
     * @param parametro Nombre del parámetro
     * @param valor Valor del parámetro (int)
     * @return Lista
     */
    public List listarConParametros(String namedQuery, String parametro, int valor) {
        TypedQuery query = session.getNamedQuery(namedQuery).setParameter(parametro, valor);

        return query.getResultList();
    }

    /**
     * Función que devuelve una lista de compras filtradas por un rango de
     * precios
     *
     * @param valor1 Valor del precio inicial
     * @param valor2 Valor del precio final
     * @return Lista
     */
    public List<Compra> listarPorPrecio(double valor1, double valor2) {
        TypedQuery query = session.getNamedQuery("getComprasByPrice")
                .setParameter("precioInicio", valor1)
                .setParameter("precioFin", valor2);

        return query.getResultList();
    }

    /**
     * Función que devuelve una lista de compras filtradas por precio
     *
     * @param precio Precio de la compra
     * @return Lista de compras
     */
    public List<Compra> listarPorPrecioUnico(double precio) {
        TypedQuery query = session.getNamedQuery("getComprasByOnePrice")
                .setParameter("precio", precio);

        return query.getResultList();
    }

    /**
     * Función que devuelve un objeto buscado por id
     *
     * @param namedQuery Nombre de la query
     * @param id Id a buscar
     * @return Objeto
     */
    public Object buscarPorId(String namedQuery, int id) {
        TypedQuery query = session.getNamedQuery(namedQuery).setParameter("id", id);

        return query.getSingleResult();
    }

    /**
     * Función que busca por parámetro especificado
     *
     * @param namedQuery Nombre de la query
     * @param parametro Nombre del parámetro
     * @param valor Valor del parámetro (String)
     * @return Lista de los objetos encontrados
     */
    public List buscarPorParametro(String namedQuery, String parametro, String valor) {
        TypedQuery query = session.getNamedQuery(namedQuery).setParameter(parametro, valor);

        return query.getResultList();
    }
}
