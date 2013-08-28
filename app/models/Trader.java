package models;

import java.util.ArrayList;
import java.util.List;


import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

import java.util.Date;

public class Trader extends Model {
	private int id;
	private String name;
	private List<Client> clients;

	
	public Trader(int id, String name, List<Client> clients) {
		this.id = id;
		this.name = name;
		this.clients = clients;
	}
	
	public Trader() {
		this.id = 0;
		this.name = "";
		this.clients = new ArrayList<Client>();
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

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Trader [id=" + id + ", name=" + name + ", clients=" + clients
				+ "]";
	}
	
	
	
	
	

}
