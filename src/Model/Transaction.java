package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	int  id;
	String name = "";
	User user;
	float montant;
	Date date;
	
	
	public Transaction(int id, String name, User user, float montant, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.montant = montant;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Transaction addMoney(User user,Account account,Float amount,String Descript)
	{
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date));
		return  new Transaction(1,Descript,user,amount,date);
	}
	
	public boolean Retrait(User user,Account account,Float amount,String Descript)
	{
		
		if(VerifyFloor(account, amount) == true)
		{
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());
			System.out.println(formatter.format(date));
			float CurrentSold = account.getSold();
			float NewSold = account.getSold() - amount;
			//new Transaction(1,Descript,user,NewSold,date);
			
			new Transaction(1,Descript,user,amount,date);
			  
			return true;
		}
		else {
			return false; 
		}
			
	}
	
	public boolean VerifyFloor(Account account,Float amount) {
		
	
		if(account.getSold() >  amount)
	
		{
			return true;
		}
		if(account.getSold() <=  amount)
		{
			return false;
		}
	
	
		return false;
	}
	
	
	
	
	
}
