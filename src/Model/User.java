package Model;

public class User {
	 public int  id;
     public String FirstName,LastName,
        email,passeword;
     
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
	public User(int id, String firstName, String lastName, String email, String passeword, float sold) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		this.email = email;
		this.passeword = passeword;
		Sold = sold;
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
	public String getPasseword() {
		return passeword;
	}

	/**
	 * @param passeword the passeword to set
	 */
	public void setPasseword(String passeword) {
		this.passeword = passeword;
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
