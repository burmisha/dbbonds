package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

import java.util.Date;
import java.util.ArrayList;

import java.util.List;

public class Portfolio extends Model{
	
	private int id;
	private List<Bond> bonds;
	private List<Integer> quantities;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Bond> getBonds() {
		return bonds;
	}

	public void setBonds(List<Bond> bonds) {
		this.bonds = bonds;
	}

	public List<Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<Integer> quantities) {
		this.quantities = quantities;
	}

	public Portfolio(List<Bond> bonds, List<Integer> quantities,
			double balance) {
		this.bonds = bonds;
		this.quantities = quantities;
	}
	
	public Portfolio(){
		this.bonds = new ArrayList<Bond>();
		this.quantities = new ArrayList<Integer>();
		
	}

	public void buyBond(Bond bond, int quantity){
		if (!canBeBought(bond, quantity))
			return;
		
		bonds.add(bond);
		quantities.add(quantity);
		bond.setQuantity(bond.getQuantity() - quantity);
		// LogRecord.createNewLogRecord(this, bond, quantity, "Buy");
	}

	public boolean canBeBought(Bond bond, int quantity){
		boolean flag = false;
		
		if (bond.getQuantity() > quantity)
			flag = true;
		
		return flag;
	}
	
	public void sellBond(Bond bond, int quantity){
		
		if (!canBeSold(bond, quantity))
			return;
		
		int ind = bonds.indexOf(bond);
		int newQuantity = quantities.get(ind) - quantity;
		if (newQuantity != 0){
			quantities.set(ind, newQuantity);
		}
		else{
			quantities.remove(ind);
			bonds.remove(ind);
		}
	}
	public boolean canBeSold(Bond bond, int quantity){
		boolean flag = false;
		if (quantity <= quantities.get(bonds.indexOf(bond)))
				flag = true;
		
		return flag;
	}

	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", bonds=" + bonds + ", quantities="
				+ quantities + "]";
	}

	

}
