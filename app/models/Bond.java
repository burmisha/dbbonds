package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

import java.util.Date;
import java.util.ArrayList;

@Entity 
public class Bond extends Model{
	
	private String cusip;
	private double parValue;
	private double price;
	private String name;
	private double coupon;
	private double currentYield;
	private double maturityYield;
	private int quantity;
	private int idSpRating;
	private int idMoodysRating;
	private Date maturityDate;
	private Date settlementDate;	
	private ArrayList<Date> couponPaymentDates;
	private String issuer;
	
	public Bond(String cusip, double parValue, double price, String name, 
			double coupon, double currentYield, double maturityYield,
			int quantity, int idSpRating, int idMoodysRating, Date maturityDate,
			Date settlementDate, ArrayList<Date> couponPaymentDates) {
		this.cusip = cusip;
		this.parValue = parValue;
		this.price = price;
		this.name = name;
		this.coupon = coupon;
		this.currentYield = currentYield;
		this.maturityYield = maturityYield;
		this.quantity = quantity;
		this.idSpRating = idSpRating;
		this.idMoodysRating = idMoodysRating;
		this.maturityDate = maturityDate;
		this.settlementDate = settlementDate;
		this.couponPaymentDates = couponPaymentDates;
	}
	
	public Bond() {
		this.cusip = "";
		this.parValue = 0;
		this.price = 0;
		this.name = "";
		this.coupon = 0;
		this.currentYield = 0;
		this.maturityYield = 0;
		this.quantity = 0;
		this.idSpRating = 0;
		this.idMoodysRating = 0;
		this.maturityDate = new Date();
		this.settlementDate = new Date();
		this.couponPaymentDates = new ArrayList<Date>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coupon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((couponPaymentDates == null) ? 0 : couponPaymentDates
						.hashCode());
		temp = Double.doubleToLongBits(currentYield);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cusip == null) ? 0 : cusip.hashCode());
		result = prime * result + idMoodysRating;
		result = prime * result + idSpRating;
		result = prime * result + ((issuer == null) ? 0 : issuer.hashCode());
		result = prime * result
				+ ((maturityDate == null) ? 0 : maturityDate.hashCode());
		temp = Double.doubleToLongBits(maturityYield);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result
				+ ((settlementDate == null) ? 0 : settlementDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bond other = (Bond) obj;

		if (cusip == null) {
			if (other.cusip != null)
				return false;
		} else if (!cusip.equals(other.cusip))
			return false;
		
		return true;
	}
	
	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
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
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCoupon() {
		return coupon;
	}
	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}
	public double getCurrentYield() {
		return currentYield;
	}
	public void setCurrentYield(double currentYield) {
		this.currentYield = currentYield;
	}
	public double getMaturityYield() {
		return maturityYield;
	}
	public void setMaturityYield(double maturityYield) {
		this.maturityYield = maturityYield;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getidSpRating() {
		return idSpRating;
	}
	public void setIdSpRating(int idSpRating) {
		this.idSpRating = idSpRating;
	}
	public int getIdMoodysRating() {
		return idMoodysRating;
	}
	public void setIdMoodysRating(int idMoodysRating) {
		this.idMoodysRating = idMoodysRating;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public ArrayList<Date> getCouponPaymentDates() {
		return couponPaymentDates;
	}
	public void setCouponPaymentDates(ArrayList<Date> couponPaymentDates) {
		this.couponPaymentDates = couponPaymentDates;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Override
	public String toString() {
		return "Bond [cusip=" + cusip + ", parValue=" + parValue + ", price="
				+ price + ", name=" + name + ", coupon=" + coupon
				+ ", currentYield=" + currentYield + ", maturityYield="
				+ maturityYield + ", quantity=" + quantity + ", idSpRating="
				+ idSpRating + ", idMoodysRating=" + idMoodysRating
				+ ", maturityDate=" + maturityDate + ", settlementDate="
				+ settlementDate + ", couponPaymentDates=" + couponPaymentDates
				+ ", issuer=" + issuer + "]";
	}
}

