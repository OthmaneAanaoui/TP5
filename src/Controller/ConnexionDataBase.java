package Controller;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Model.Account;
import Model.Message;
import Model.Transaction;
import Model.Type;
import Model.User;

public class ConnexionDataBase {
	private Connection connexion;
	private Statement statement;
	static User userConnected;
	static Dictionary<Integer,Account> userAccounts;
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
	
	public ArrayList<Object> getVersion() {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Version\" ORDER BY date DESC");
			
			
			while(res.next()) {
				list.add(res.getString(1));
				list.add(res.getDate(2));
				list.add(res.getString(3));
			}
			res.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public User getUserById(int id) {
		User user = null;
		try {
			ResultSet res2 = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE id ="+id);
			
			while(res2.next()) {
				user = new User(res2.getInt("id"),res2.getString("firstName"), res2.getString("lastName"), res2.getString("email"), res2.getString("password"));
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
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE \"numberAccount\" IN (0,1) AND id NOT IN("+userConnected.getId()+")");
			
			while(res.next()) {
				user = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
				users.add(user);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public Dictionary<Integer,User> getAllUsers() {
		Dictionary<Integer,User> users = new Hashtable<Integer,User>();
		User user = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" ");
			
			while(res.next()) {
				user = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
				users.put(user.getId(), user);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public Dictionary<Integer,Account> getAllAccount() {
		Dictionary<Integer,Account> accounts = new Hashtable<Integer,Account>();
		Dictionary<Integer,User> allUsers = getAllUsers();
		Account account = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Account\" ");
			
			while(res.next()) {
				String users_id = res.getString("id_user");
				users_id = users_id.replace("{", "");
				users_id = users_id.replace("}", "");
				String[] array = users_id.split(",");
				User[] users = new User[array.length]; //
				for (int i = 0; i < array.length; i++) {
					User user = allUsers.get(Integer.parseInt(array[i]));
					users[i] = user;
				}
				account = new Account(res.getInt("id"), users, Type.valueOf(res.getString("type")) , res.getFloat("sold"), res.getFloat("floor"));
				accounts.put(account.getId(), account);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"User\" WHERE email = '"+ email + "' AND password = '" + password+"';");
			
			while(res.next()) {
				userConnected = new User(res.getInt("id"),res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"));
				userConnected.setNumberAccount(res.getInt("numberAccount"));
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getAccountFromUser();
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
				+ "					VALUES (nextval('\"Account_id_seq\"'::regclass),'"+ idUsers +"','"+ type +"',"+ 0 +","+ floor +")";
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
	
	public int createTransaction(String name,int idUser, float montant,int idAccount) {
		boolean isCreated = false;
		Date date = new Date();
		int id;
		if(idAccount == -1) {
			Account account = getLastAccount(idUser);
			id = account.getId();
		}
		else {
			id = idAccount;
		}
		
		try {
			int res = statement.executeUpdate("INSERT INTO \"public\".\"Transaction\" (\"id\",\"name\",\"id_account\",\"montant\",\"date\")\r\n"
					+ "					VALUES (nextval('\"Transaction_id_seq\"'::regclass),'"+ name +"',"+ id +","+ montant +",'"+date+"')");
			if(res == 1) {
				System.out.println("Transaction created");
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public Account getLastAccount(int id) {
		Account account = null;
		Dictionary<Integer,User> allUsers = getAllUsers();
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Account\" WHERE id = (SELECT max(id) FROM \"public\".\"Account\") AND id_user = '{"+id+"}' LIMIT 1");
			
			while(res.next()) {
				String users_id = res.getString("id_user");
				users_id = users_id.replace("{", "");
				users_id = users_id.replace("}", "");
				String[] array = users_id.split(",");
				
				User[] users = new User[array.length]; //
				for (int i = 0; i < array.length; i++) {
					User user = allUsers.get(Integer.parseInt(array[i]));
					users[i] = user;
				}
				Type type = Type.valueOf(res.getString("type"));
				account = new Account(res.getInt("id"), users , type , res.getFloat("floor"));
			}
			
			res.close();
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
			ResultSet res = statement.executeQuery("SELECT sold FROM \"public\".\"Account\" WHERE "+userConnected.getId()+" = ANY (id_user)");
			while(res.next()) {
				somme += res.getInt("sold");
				
			}
			reslut = somme+"";
			res.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			somme = 0;
			reslut = somme+"";
		}
		
		return reslut;
	}
	
	public ArrayList<Account> getAccountFromUser() {
		ArrayList<Account> accounts = new ArrayList<>();
		Dictionary<Integer,Account> useraccounts = new Hashtable<Integer,Account>();
		Dictionary<Integer,User> allUsers = getAllUsers();
		
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Account\" WHERE "+userConnected.getId()+" = ANY (id_user) ORDER BY id;");
			while(res.next()) {
				String users_id = res.getString("id_user");
				Type type = Type.valueOf(res.getString("type"));
				int id = res.getInt("id");
				float floor = res.getFloat("floor");
				float sold = res.getFloat("sold");
				users_id = users_id.replace("{", "");
				users_id = users_id.replace("}", "");
				String[] array = users_id.split(",");
				
				User[] users = new User[array.length];
				for (int i = 0; i < array.length; i++) {
					User user = allUsers.get(Integer.parseInt(array[i]));
					users[i] = user;
				}
				accounts.add(new Account(id, users , type , floor,sold));
				useraccounts.put(id, new Account(id, users , type , floor,sold));
			}
			res.close();
			userAccounts = useraccounts;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return accounts;
	}
	
	public Map<Integer,Transaction> getTransactionWithLimit(int id1, int id2, int limit){
		
		Map<Integer,Transaction> transactions = new Hashtable<Integer,Transaction>();
		Dictionary<Integer,Account> allAccounts = getAllAccount();
		
		try {
			ResultSet res;
			if(id2 != -1) {
				res = statement.executeQuery("SELECT * FROM \"public\".\"Transaction\" WHERE id_account IN ("+id1+","+id2+") ORDER BY id DESC LIMIT '"+limit+"';");
			}
			else {
				res = statement.executeQuery("SELECT * FROM \"public\".\"Transaction\" WHERE id_account IN ("+id1+") ORDER BY id DESC LIMIT "+limit+";");
			}
			while(res.next()) {
				int idTransaction = res.getInt("id");
				String name = res.getString("name");
				int idAccount = res.getInt("id_account");
				Account account = allAccounts.get(idAccount);
				float montant = res.getFloat("montant");
				Date date = res.getDate("date");
				transactions.put(idTransaction,new Transaction(idTransaction, name, account, montant, date));
			}
			res.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return transactions;
	}
	
	public void firstTransaction(int idAccount, float montant) {
		boolean isUpdated = false;
		try {
			int res = statement.executeUpdate("UPDATE \"public\".\"Account\" SET \"sold\" = "+montant+" WHERE id = "+idAccount);
			if(res == 1) {
				System.out.println("account updated");
				isUpdated = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getAccountFromUser();
	}
	
	public void addMoney(int idAccount, float montant) {
		float sold = userAccounts.get(idAccount).getSold() + montant;
		boolean isUpdated = false;
		try {
			int res = statement.executeUpdate("UPDATE \"public\".\"Account\" SET \"sold\" = "+sold+" WHERE id = "+idAccount);
			if(res == 1) {
				System.out.println("account updated");
				isUpdated = true;
				createTransaction("Dépot", userConnected.getId(), montant, idAccount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getAccountFromUser();
	}
	
	public boolean withdrawMoney(int idAccount, float montant) {
		float sold = userAccounts.get(idAccount).getSold() - montant;
		boolean isUpdated = false;
		if(sold >= -userAccounts.get(idAccount).getFloor()) {
			try {
				int res = statement.executeUpdate("UPDATE \"public\".\"Account\" SET \"sold\" = "+sold+" WHERE id = "+idAccount);
				if(res == 1) {
					System.out.println("Account updated");
					isUpdated = true;
					createTransaction("Retrait", userConnected.getId(), montant, idAccount);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getAccountFromUser();
		}
		return isUpdated;
	}
	
	public boolean updateFloor(int id, float floor) {
		boolean isUpdated = false;
		System.out.println(id);
		/*Enumeration<Integer> test = userAccounts.keys();
		
		Integer[] ids = new Integer[2];
		int i = 0;
		while (test.hasMoreElements()) {
			Integer integer = (Integer) test.nextElement();
			ids[i] = integer;
			i++;
		}*/
		//System.out.println(ids[0]);
		//System.out.println(ids[1]);
		//int idAccount = ids[id-1];
		int idAccount = id;
		//System.out.println(idAccount);
		if(userAccounts.get(idAccount) != null) {
			if(userAccounts.get(idAccount).floor != floor) {
				try {
					int res = statement.executeUpdate("UPDATE \"public\".\"Account\" SET \"floor\" = "+floor+" WHERE id = "+idAccount);
					if(res == 1) {
						System.out.println("Account updated");
						isUpdated = true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return isUpdated;
		
	}
	
	public ArrayList<Message> getMessages(int idUser) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM \"public\".\"Message\" WHERE idUserSender IN ("+userConnected.getId()+","+idUser+") AND idUserReceiver IN ("+userConnected.getId()+","+idUser+");");
			while(res.next()) {
				int id = res.getInt("id");
				int idSender = res.getInt("idUserSender");
				int idReceiver = res.getInt("idUserReceiver");
				String message = res.getString("message");
				Date date = res.getDate("date");
				messages.add(new Message(idSender,idReceiver,message,date));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return messages;
	}
	
	public void createMessage(int idUserReceiver, String message) {
		Date date = new Date();
		try {
			int res = statement.executeUpdate("INSERT INTO \"public\".\"Message\" (\"id\",\"idUserSender\",\"idUserReceiver\",\"message\",\"date\")\r\n"
					+ "					VALUES (nextval('\"message_id_seq\"'::regclass),"+ userConnected.getId() +","+ idUserReceiver +",'"+ message +"','"+date+"')");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
