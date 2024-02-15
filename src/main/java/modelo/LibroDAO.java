package modelo;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;*/
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;


public class LibroDAO {
	
	/*private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String DB_URL = "jdbc:mysql://localhost/biblioteca";
	private String DB_USER = "bibliotecario";
	private String DB_PASS = "bibliotecario";
	private Connection conn = null;
	private Statement stm = null;
	PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public LibroDAO() throws RuntimeException {
		super();
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("ERROR: failed to load MySQL JDBC driver", e);
		}catch(SQLException e) {
			throw new RuntimeException("ERROR: failed to access database", e);
		}
	}
	
	@Override
	protected void finalize() throws Throwable{
		if(conn != null) {
			try {
				conn.close();
			}catch (SQLException e) {
				throw new RuntimeException("Error cerrando la conexion", e);
			}
		}
		if(rs != null) {
			try {
				rs.close();
			}catch (SQLException e) {
				throw new RuntimeException("Error cerrando el resultset", e);
			}
		}
	}*/
	public List<Libro> getLibros() throws RuntimeException{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		TypedQuery<Libro> q = em.createQuery("select l from Libro l", Libro.class);
		List<Libro> listaLibros = null;
		try {
			listaLibros = q.getResultList();
		}finally {
				em.close();
		}
		return listaLibros;
			
	}
	
	public void insertar(Libro libro){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(libro);
			tx.commit();
		}catch (PersistenceException e) {
			if(tx.isActive()) {
				tx.rollback();
			}
			throw new RuntimeException("Error insertando libro", e);
		}
		finally {
			em.close();
		}
	}
	
	
	
	
}
