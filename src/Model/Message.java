package Model;

import java.util.Date;

public class Message {
	
	
	public int idUserSender;
	public int idUserReceiver;
	
	public String message;
	public Date date;
	public Message() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param idUserSender
	 * @param idUserReceiver
	 * @param message
	 * @param date
	 */
	public Message(int idUserSender, int idUserReceiver, String message, Date date) {
		super();
		this.idUserSender = idUserSender;
		this.idUserReceiver = idUserReceiver;
		this.message = message;
		this.date = date;
	}
	
	
	/**
	 * @return the idUserSender
	 */
	public int getIdUserSender() {
		return idUserSender;
	}
	/**
	 * @param idUserSender the idUserSender to set
	 */
	public void setIdUserSender(int idUserSender) {
		this.idUserSender = idUserSender;
	}
	/**
	 * @return the idUserReceiver
	 */
	public int getIdUserReceiver() {
		return idUserReceiver;
	}
	/**
	 * @param idUserReceiver the idUserReceiver to set
	 */
	public void setIdUserReceiver(int idUserReceiver) {
		this.idUserReceiver = idUserReceiver;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
