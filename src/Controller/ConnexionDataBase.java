package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.User;

public class ConnexionDataBase {
	Connection connexion;
	Statement statement;
	public ConnexionDataBase() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
			connexion = DriverManager.getConnection("jdbc:postgresql://postgresql-ynov.alwaysdata.net:5432/ynov_database","ynov","ynov!3543");
			System.out.println(connexion);
			statement = connexion.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getVersion() {
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Version\"");
			
			while(res.next()) {
				System.out.println(res.getString(1)+" "+res.getDate(2)+" "+res.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE email = '"+ email + "' AND password = '" + password+"';");
			
			while(res.next()) {
				//System.out.println("");
				//System.out.println(res.getString("Nom"));
				user = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
				//System.out.println(user);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean createUser(String email, String password, String firstName, String lastName) {
		boolean isCreated = false;
		try {
			int res = statement.executeUpdate("INSERT INTO \"public\".\"User\" (\"id\",\"firstName\",\"lastName\",\"email\",\"password\",\"numberAccount\")\r\n"
					+ "					VALUES (nextval('\"User_id_seq\"'::regclass),'"+ firstName +"','"+ lastName +"','"+ email +"','"+ password +"',"+ 0 +")");
			if(res == 1) {
				System.out.println("user cr��");
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isCreated;
	}
	
	public boolean createAccount(int idUser, String type, float floor) {
		boolean isCreated = false;
		try {
			int res = statement.executeUpdate("INSERT INTO \"public\".\"Account\" (\"id\",\"id_user\",\"type\",\"sold\",\"floor\")\r\n"
					+ "					VALUES (nextval('\"User_id_seq\"'::regclass),"+ idUser +",'"+ type +"',"+ 0 +","+ floor +")");
			if(res == 1) {
				System.out.println("account cr��");
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isCreated;
	}
	
	
}
