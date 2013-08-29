package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.Date;

public class LogRecord extends Model{
	
	int portfolioID;
	int traderID;
	java.sql.Timestamp tmsp;
	String cusip;
	double price;
	String type;
	int quantity;
	
	public LogRecord(){
		
		portfolioID = 0;
		traderID = 0;
		tmsp = new java.sql.Timestamp(new Date().getTime());
		cusip = "";
		price = 0;
		this.type = "";
		this.quantity = 0;
		
	}
	
	public LogRecord(Portfolio portfolio, Trader trader, Bond bond, int quantity, String type){
	
		portfolioID = portfolio.getId();
		traderID = trader.getId();
		tmsp = new java.sql.Timestamp(new Date().getTime());
		cusip=bond.getCusip();
		price=bond.getPrice();
		this.type=type;
		this.quantity = quantity;
		
	}

	
	
	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getTraderID() {
		return traderID;
	}



	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}



	public int getPortfolioId() {
		return portfolioID;
	}

	public void setPortofilioId(int portfolioId) {
		this.portfolioID = portfolioId;
	}

	public java.sql.Timestamp getTMSP() {
		return tmsp;
	}

	public void setTMSP(java.sql.Timestamp tmsp) {
		this.tmsp = tmsp;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	@Override
	public String toString() {
		return "LogRecord [portfolioId=" + portfolioID + ", traderId=" + traderID +
				", tmsp=" + tmsp + ", cusip=" + cusip
				+ ", price=" + price + ", type=" + type + "]";
	}
	
	
	
}