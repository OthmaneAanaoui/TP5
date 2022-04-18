package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Model.Account;
import Model.Type;
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
	
	public User getUserById(int id) {
		User user = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE id ="+id);
			
			while(res.next()) {
				user = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE \"numberAccount\" IN (0,1)");
			
			while(res.next()) {
				user = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
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
				System.out.println("user créé");
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isCreated;
	}
	
	public boolean createAccount(String idUsers, String type, float floor) {
		boolean isCreated = false;
		String req = "INSERT INTO \"public\".\"Account\" (\"id\",\"id_user\",\"type\",\"sold\",\"floor\")\r\n"
				+ "					VALUES (nextval('\"User_id_seq\"'::regclass),'"+ idUsers +"','"+ type +"',"+ 0 +","+ floor +")";
		System.out.println(req);
		try {
			int res = statement.executeUpdate(req);
			if(res == 1) {
				System.out.println("account créé");
				
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isCreated;
	}
	
	public boolean createTransaction(String name,int idUser, float montant) {
		boolean isCreated = false;
		Date date = new Date();
		Account account = getLastAccount(idUser);
		try {
			int res = statement.executeUpdate("INSERT INTO \"public\".\"Transaction\" (\"id\",\"name\",\"id_account\",\"montant\",\"date\")\r\n"
					+ "					VALUES (nextval('\"User_id_seq\"'::regclass),"+ name +",'"+ account.id +"',"+ montant +",'"+date+"')");
			if(res == 1) {
				System.out.println("Transaction créé");
				////account = new Account(, res, null, null, res)
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isCreated;
	}
	
	public Account getLastAccount(int id) {
		Account account = null;
		try {
			ResultSet res = statement.executeQuery("SELECT max(id) , id_user FROM \"public\".\"Account\" WHERE id_user = '{"+id+"}' GROUP BY id_user LIMIT 1");
			
			while(res.next()) {
				ArrayList<Integer> array = (ArrayList<Integer>) res.getArray("id_user").getArray();
				User[] users = null;
				for (Integer integer : array) {
					User user = getUserById(integer);
					users[integer] = user;
				}
				Type type = Type.valueOf(res.getString("type"));
				account = new Account(res.getInt("id"), users , type , res.getFloat("floor"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
}
