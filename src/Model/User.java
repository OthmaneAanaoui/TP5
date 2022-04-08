package Model;

public class User {
	 public int  id, numberAccount;
     public String FirstName,LastName,
        email,password;
     
     public  float Sold;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param passeword
	 * @param sold
	 */
	public User(int id, String firstName, String lastName, String email, String passeword) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		this.email = email;
		this.password = passeword;
		//Sold = sold;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passeword
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param passeword the passeword to set
	 */
	public void setPassword(String passeword) {
		this.password = passeword;
	}

	/**
	 * @return the sold
	 */
	public float getSold() {
		return Sold;
	}

	/**
	 * @param sold the sold to set
	 */
	public void setSold(float sold) {
		Sold = sold;
	}
	
	public Account addAccount(Type type, float plafond, User[] users){
		
		return new Account(1 ,users, type, plafond);
		
	}
     
     
}
