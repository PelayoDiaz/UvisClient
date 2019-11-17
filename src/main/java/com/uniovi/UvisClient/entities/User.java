package com.uniovi.UvisClient.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * The users that interact with the application.
 * 
 * @author Pelayo Díaz Soto
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	/** The username. */
	@Column(unique = true)
	private String username;
	
	/** The password. */
	private String password;
	
	/** The personal name. */
	private String name;
	
	/** The first surname. */
	private String surname1;
	
	/** The second surname. */
	private String surname2;
	
	/** Only used to verify that passwords are equals into the sign up page. */
	@Transient 
	private String passwordConfirm;
	
	/** The wallets that belong to the user. */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Wallet> wallets;
	
	public User() {}

	/**
	 * Constructor.
	 *  
	 * @param username
	 * 			The username.
	 * @param name
	 * 			The name.
	 * @param surname1
	 * 			The first surname.
	 * @param surname2
	 * 			The second surname.
	 */
	public User(String username, String name, String surname1, String surname2) {
		super();
		this.username = username;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the surname1
	 */
	public String getSurname1() {
		return surname1;
	}


	/**
	 * @param surname1 the surname1 to set
	 */
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}


	/**
	 * @return the surname2
	 */
	public String getSurname2() {
		return surname2;
	}


	/**
	 * @param surname2 the surname2 to set
	 */
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	/**
	 * @return the wallets
	 */
	public Set<Wallet> getWallets() {
		return wallets;
	}


	/**
	 * @param wallets the wallets to set
	 */
	public void setWallets(Set<Wallet> wallets) {
		this.wallets = wallets;
	}

	

}
