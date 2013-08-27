package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

import java.util.Date;
import java.util.ArrayList;

public class Client extends Model{
	private int id;
	private String name;
	private int idTrader;
	private int idPortfolio;
	private double balance;
	
	public Client(int id, String name, int idTrader, int idPortfolio,
			double balance) {
		this.id = id;
		this.name = name;
		this.idTrader = idTrader;
		this.idPortfolio = idPortfolio;
		this.balance = balance;
	}
	
	public Client() {
		this.id = 0;
		this.name = "";
		this.idTrader = 0;
		this.idPortfolio = 0;
		this.balance = 0;
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
	public int getIdTrader() {
		return idTrader;
	}
	public void setIdTrader(int idTrader) {
		this.idTrader = idTrader;
	}
	public int getIdPortfolio() {
		return idPortfolio;
	}
	public void setIdPortfolio(int idPortfolio) {
		this.idPortfolio = idPortfolio;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", idTrader="
				+ idTrader + ", idPortfolio=" + idPortfolio + ", balance="
				+ balance + "]";
	}
	
	

}
