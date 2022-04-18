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
	private Connection connexion;
	private Statement statement;
	static User userConnected;
	
	public ConnexionDataBase() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
			connexion = DriverManager.getConnection("jdbc:postgresql://postgresql-ynov.alwaysdata.net:5432/ynov_database","ynov","ynov!3543");
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
		//User user = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE email = '"+ email + "' AND password = '" + password+"';");
			
			while(res.next()) {
				//System.out.println("");
				//System.out.println(res.getString("Nom"));
				userConnected = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
				userConnected.setNumberAccount(res.getInt("numberAccount"));
				//System.out.println(user);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//userConnected = user;
		System.out.println(userConnected.getId());
		return userConnected;
	}
	
	public boolean createUser(String email, String password, String firstName, String lastName) {
		boolean isCreated = false;
		try {
			int res = statement.executeUpdate("INSERT INTO \"public\".\"User\" (\"id\",\"firstName\",\"lastName\",\"email\",\"password\",\"numberAccount\")\r\n"
					+ "					VALUES (nextval('\"User_id_seq\"'::regclass),'"+ firstName +"','"+ lastName +"','"+ email +"','"+ password +"',"+ 0 +")");
			if(res == 1) {
				System.out.println("User created");
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
				System.out.println("Account created");
				
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
				System.out.println("Transaction created");
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
	
	public boolean updateNumberAccount(int id,int numberAccount) {
		boolean isUpdated = false;
		try {
			int res = statement.executeUpdate("UPDATE \"public\".\"User\" SET \"numberAccount\" = "+numberAccount+" WHERE id = "+id);
			if(res == 1) {
				System.out.println("user updated");
				isUpdated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	public String getSoldTotal() {
		int somme = 0;
		String reslut = "0";
		try {
			System.out.println(userConnected.getId());
			ResultSet res = statement.executeQuery("SELECT sold FROM \"public\".\"Account\" WHERE "+userConnected.getId()+" = ANY (id_user)");
			while(res.next()) { //SELECT "id_user" FROM "public"."Account" WHERE "id_user" LIKE '{%1%}'::integer[]
				somme += res.getInt("sold");
				
			}
			reslut = somme+"";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			somme = 0;
			reslut = somme+"";
		}
		
		return reslut;
	}
	
	public String getAccountFromUser(int id) {
		int somme = 0;
		String reslut = "0";
		try {
			System.out.println(userConnected.getId());
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Account\" WHERE id_user LIKE '{"+userConnected.getId()+"%}' GROUP BY id_user");
			while(res.next()) {
				somme = res.getInt("somme");
				reslut = somme+"";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			somme = 0;
			reslut = somme+"";
		}
		
		return reslut;
	}
	
	
}
