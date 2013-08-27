package controllers;
import models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class DataBaseHandler {

	Connection conn;
	public DataBaseHandler(){
		String user = "cymuzpcvvjplbh";
        String password = "yZYqsfq3BNCoQJL382QQC_rchL";

        try{
        	String url = "jdbc:postgresql://ec2-54-221-229-7.compute-1.amazonaws.com:5432/d98ldghnhunfae";
        	Properties props = new Properties();
        	props.setProperty("user", 		user);
        	props.setProperty("password", 	password);
        	props.setProperty("ssl", 		"true");
        	props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        	conn = DriverManager.getConnection(url, props);
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean closeConnection(){
		try{
			conn.close();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public int checkLogin(String login, String pwd){
		try{
			Statement stmt = conn.createStatement();
			
			String s = "SELECT id FROM traders WHERE login = '" + login +
					"' and pwd ='" + pwd + "'";
			ResultSet rs = stmt.executeQuery(s);
			
			if (rs.next())
				return rs.getInt(1);
			else
				return -1;
		} catch (Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public int getPortfolioId(int clientId){
		try{
			Statement stmt = conn.createStatement();
			
			String s = "SELECT id_portfolio FROM clients WHERE id = '" + clientId + "'";
			ResultSet rs = stmt.executeQuery(s);
			if (rs.next())
				return rs.getInt(1);
			else
				return -1;
		} catch (Exception e){
			e.printStackTrace();
			return -1;
		}
	}


	// public ArrayList<Bond> getBonds(){
	// 	ArrayList<Bond> bonds = new ArrayList<Bond>();
	// 	try{
	// 		Statement stmt = conn.createStatement();
	// 		String s = "SELECT * FROM bonds WHERE login = '" + login +
	// 				"' and pwd ='" + pwd + "'";
	// 		String s = "SELECT * FROM bonds";
	// 		ResultSet rs = executeQuery(s);
			
	// 		while (rs.next()){
	// 			Bond bond = new Bond();
	// 			bond.setCusip(rs.getString(1));
	// 			bond.setPrice(rs.getDouble(2));
	// 			bond.setName(rs.getString(3));
	// 			bond.setCoupon(rs.getDouble(4));
	// 			bond.setCurrentYield(rs.getDouble(5));
	// 			bond.setMaturityYield(rs.getDouble(6));
	// 			bond.setQuantity(rs.getInt(7));
	// 			bond.setIdSpRating(rs.getInt(8));
	// 			bond.setIdMoodysRating(rs.getInt(9));
	// 			bond.setMaturityDate(rs.getDate(10));
	// 			bond.setIssuer(rs.getString(11));
	// 			bonds.add(bond);
	// 		}
	// 	} catch (Exception e){
	// 		e.printStackTrace();
	// 	}

		
	// 	return bonds;
	// }

	public ArrayList<Client> getClients(int traderId){
		ArrayList<Client> clients = new ArrayList<Client>();
		String s = "SELECT * FROM clients WHERE id_trader = ?";
		try{
			PreparedStatement pstmt = conn.prepareStatement(s);
			pstmt.setInt(1, traderId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2));
				client.setIdTrader(rs.getInt(3));
				client.setIdPortfolio(rs.getInt(4));
				client.setBalance(rs.getDouble(5));
				clients.add(client);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return clients;
	}
	
	// public Map<Integer, String> getSpRating(){
	// 	Map<Integer, String> spRating = new HashMap<Integer, String>();
	// 	String s = "select * from idsprating";
	// 	ResultSet rs = executeQuery(s);
	// 	try{
	// 		while (rs.next()){
	// 			spRating.put(rs.getInt(1), rs.getString(2));
	// 		}
	// 	} catch(Exception e){
	// 		e.printStackTrace();
	// 	}
		
	// 	return spRating;
	// }
	
	// public Map<Integer, String> getMoodysRating(){
	// 	Map<Integer, String> moodysRating = new HashMap<Integer, String>();
	// 	String s = "select * from idmoodysrating";
		
	// 	ResultSet rs = executeQuery(s);
		
	// 	try{
	// 		while (rs.next()){
	// 			moodysRating.put(rs.getInt(1), rs.getString(2));
	// 		}
	// 	} catch(Exception e){
	// 		e.printStackTrace();
	// 	}
		
	// 	return moodysRating;
	// }
	
	// public boolean buyBond(Portfolio portfolio, Bond bond){
	// 	int portfolioID = portfolio.getId();		
	// 	String s = "UPDATE 	";
		
	// 	try{
	// 		return true;
	// 	} catch (Exception e){
	// 		e.printStackTrace();
	// 		return false;
	// 	} 
	// }

	public Portfolio getPortfolio(int portfolioId){
		Portfolio portfolio = new Portfolio();
		List<Bond> bonds = new ArrayList<Bond>();
		List<Integer> quantities = new ArrayList<Integer>();
		
		try{
			String s = "SELECT cusip_bond, quantity FROM portfolios WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setInt(1, portfolioId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()){
				String cusip = rs.getString("cusip_bond");
				int quantity = rs.getInt("quantity");
				
				Bond bond = getBondFromCUSIP(cusip);
				bonds.add(bond);
				quantities.add(quantity);
			}			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		portfolio.setId(portfolioId);
		portfolio.setBonds(bonds);
		portfolio.setQuantities(quantities);
		
		return portfolio;
	}

	public Bond getBondFromCUSIP(String cusip){
		Bond bond = new Bond();
		
		try{
			String s = "SELECT * FROM bonds WHERE cusip = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setString(1, cusip);
			
			ResultSet rs =  pstmt.executeQuery();
			if (rs.next()){
				bond.setCusip(cusip);
				bond.setPrice(rs.getDouble("price"));
				bond.setName(rs.getString("name"));
				bond.setCoupon(rs.getDouble("coupon"));
				bond.setCurrentYield(rs.getDouble("currentyield"));
				bond.setMaturityYield(rs.getDouble("maturityyield"));
				bond.setQuantity(rs.getInt("quantity"));
				bond.setIdSpRating(rs.getInt("id_sprating"));
				bond.setIdMoodysRating(rs.getInt("id_moodysrating"));
				bond.setMaturityDate(rs.getDate("maturitydate"));
				bond.setIssuer(rs.getString("issuer"));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return bond;
	}
	
	public ResultSet executeQuery(String sqlStatement){
		try{
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sqlStatement);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
