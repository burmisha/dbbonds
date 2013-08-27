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
	
	// public enum moodysRatingE{
	// 	AAA, 
	// 	Aa1, 	Aa2,	Aa3,
	// 	A1,		A2,		A3,
	// 	Baa1,	Baa2,	Baa3,
	// 	Ba1,	Ba2,	Ba3,
	// 	B1,		B2,		B3,
	// 	Caa1,	Caa2,	Ca,
	// 	C,	NONE		
	// }
	
	// public enum spRatingE{
	// 	AAA,
	// 	AAp,	AA,		AAm,
	// 	Ap,		A,		Am,
	// 	BBBp,	BBB,	BBBm,
	// }
	
	@Id
	private String cusip;
	@Constraints.Required
	private double price;
	// @Constraints.Required
	private String name;
	// @Constraints.Required
	private double coupon;
	// @Constraints.Required
	private double currentYield;
	// @Constraints.Required
	private double maturityYield;
	// @Constraints.Required
	private int quantity;
	// @Constraints.Required
	private int idSpRating;
	// @Constraints.Required
	private int idMoodysRating;
	// @Constraints.Required
	private Date maturityDate;
	// @Constraints.Required
	private Date settlementDate;	
	// @Constraints.Required
	private ArrayList<Date> couponPaymentDates;
	// @Constraints.Required
	private String issuer;
		
	public Bond(String cusip, double price, String name, double coupon,
			double currentYield, double maturityYield, int quantity,
			int idSpRating, int idMoodysRating, Date maturityDate,
			Date settlementDate, ArrayList<Date> couponPaymentDates) {
		this.cusip = cusip;
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
		return "Bond [cusip=" + cusip + ", price=" + price + ", name=" + name
				+ ", coupon=" + coupon + ", currentYield=" + currentYield
				+ ", maturityYield=" + maturityYield + ", quantity=" + quantity
				+ ", idSpRating=" + idSpRating + ", moodysRating=" + idMoodysRating
				+ ", maturityDate=" + maturityDate + ", settlementDate="
				+ settlementDate + ", couponPaymentDates=" + couponPaymentDates
				+ ", issuer=" + issuer + "]";
	}
	public static Finder<String,Bond> find = new Finder<String,Bond>(String.class, Bond.class); 
	
	public static Page<Bond> page(int page, int pageSize, 
									  String sortBy, String order, String filter) {
		return 
			find.where()
				.ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize)
				.getPage(page);
	}
}

