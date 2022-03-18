package Model;

public class Account {
	public int id;
	public User[] user;
	public Type type;
	public float sold;
	public float floor;
	/**
	 * @param id
	 * @param user
	 * @param type
	 * @param sold
	 */
	public Account(int id, User[] user, Type type, float floor) {
		super();
		this.id = id;
		this.user = user;
		this.type = type;
		this.sold = 0;
		this.floor = floor;
	}
	
	public float getFloor() {
		return floor;
	}

	public void setFloor(float floor) {
		this.floor = floor;
	}

	/**
	 * 
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the user
	 */
	public User[] getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User[] user) {
		this.user = user;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * @return the sold
	 */
	public float getSold() {
		return sold;
	}
	/**
	 * @param sold the sold to set
	 */
	public void setSold(float sold) {
		this.sold = sold;
	}
	
}
