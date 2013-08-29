package controllers;
import models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.Properties;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

	public Client getClientFromPortfolio(int portfolioID){
		Client client = new Client();
		
		try{
			String s = "SELECT * FROM clients WHERE id_portfolio = ?";
		
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setInt(1, portfolioID);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()){
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setIdTrader(rs.getInt("id_trader"));
				client.setIdPortfolio(rs.getInt("id_portfolio"));
				client.setBalance(rs.getDouble("balance"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}

	public boolean isBondExistInPortfolio(Portfolio portfolio, Bond bond){
		int portfolioID = portfolio.getId();
		String cusip = bond.getCusip();
		
		String s = "SELECT id FROM portfolios WHERE id = ? AND cusip_bond = ?";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setInt(1, portfolioID);
			pstmt.setString(2, cusip);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	public List<Client> getClientsFromTrader(Trader trader){
		
		List<Client> clients = new ArrayList<Client>();
		int idTrader = trader.getId();
		
		String s = "SELECT * FROM clients WHERE id_trader = ? ORDER BY name";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setInt(1, idTrader);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()){
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setIdTrader(rs.getInt("id_trader"));
				client.setIdPortfolio(rs.getInt("id_portfolio"));
				client.setBalance(rs.getDouble("balance"));
				
				clients.add(client);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return clients;
	}
	

	public Portfolio getPortfolioFromClient(Client client){
		Portfolio portfolio = new Portfolio();
		int portfolioID = client.getIdPortfolio();
		List<Bond> bonds = new ArrayList<Bond>();
		List<Integer> quantities = new ArrayList<Integer>();
		
		try{
			String s = "SELECT cusip_bond, quantity FROM portfolios WHERE id = ?" +
					"ORDER BY cusip_bond";
			
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setInt(1, portfolioID);
			
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
		
		portfolio.setId(portfolioID);
		portfolio.setBonds(bonds);
		portfolio.setQuantities(quantities);
		
		return portfolio;
		
	}
	

public Map<Integer, String> getSpRating(){
		Map<Integer, String> spRating = new HashMap<Integer, String>();
		String s = "select * from idsprating";
		ResultSet rs = executeQuery(s);
		try{
			while (rs.next()){
				spRating.put(rs.getInt("id"), rs.getString("rating"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return spRating;
	}
	
	public Map<Integer, String> getMoodysRating(){
		Map<Integer, String> moodysRating = new HashMap<Integer, String>();
		String s = "select * from idmoodysrating";
		ResultSet rs = executeQuery(s);
		try {
			while (rs.next())
				moodysRating.put(rs.getInt("id"), rs.getString("rating"));
		} catch(Exception e){
			e.printStackTrace();
		}
		return moodysRating;
	}
	
	public int getMoodysIDFromString(String rating){
		int id = 0;
		String s = "SELECT id FROM idmoodysrating WHERE rating = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(s);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt("id");
		} catch (Exception e){
			e.printStackTrace();
		}		
		return id;
	}
	
	public int getSpIDFromString(String rating){
		int id = 0;
		String s = "SELECT id FROM idsprating WHERE rating = ?";
		try{
			PreparedStatement pstmt = conn.prepareStatement(s);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				id = rs.getInt("id");
			}
		} catch (Exception e){
			e.printStackTrace();
		}		
		return id;
	}
	

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
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				bond.setCusip(cusip);
				bond.setPrice(rs.getDouble("price"));
				bond.setName(rs.getString("name"));
				bond.setParValue(rs.getDouble("parvalue"));
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

	public boolean sellBond(Trader trader, Portfolio portfolio, Bond bond, int amount){
		Client client = getClientFromPortfolio(portfolio.getId());
		
		int index = portfolio.getBonds().indexOf(bond);
		int quantity = portfolio.getQuantities().get(index);
		
		
		double moneyAmount = amount * bond.getPrice();

		try {
			conn.setAutoCommit(false);
			if (quantity == amount){
				String deleteBond = "DELETE FROM portfolios WHERE cusip_bond = ?;";
				
				PreparedStatement pstmt = conn.prepareStatement(deleteBond);
				
				pstmt.setString(1, bond.getCusip());
				
				pstmt.executeUpdate();
			}
			else{
				String updatePortfolio = "UPDATE portfolios " + 
						"SET quantity = quantity - ? WHERE cusip_bond = ?;";
				
				PreparedStatement pstmt = conn.prepareStatement(updatePortfolio);
				
				pstmt.setInt(1, amount);
				pstmt.setString(2, bond.getCusip());

				pstmt.executeUpdate();
			}
			
			String updateClient = "UPDATE clients SET balance = balance + ? " +
					"WHERE id = ?;";
			
			PreparedStatement pstmt = conn.prepareStatement(updateClient);
			
			pstmt.setDouble(1, moneyAmount);
			pstmt.setInt(2, client.getId());
			
			pstmt.executeUpdate();
			
			LogRecord log = new LogRecord(portfolio, trader, bond, amount, "sell");
			
			writeLog(log);

			conn.commit();
			conn.setAutoCommit(true);
			
			return true;
			
			
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<Bond> searchBonds(List<String> lBound, List<String> hBound){
		List<Bond> bonds = new ArrayList<Bond>();
		String s = "SELECT * FROM bonds WHERE 1=1";
			
			for (int i = 0; i < lBound.size(); ++i){
				
				switch (i) {
					case 0:	if (!lBound.get(i).isEmpty())
								s += " AND id_moodysrating <= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND id_moodysrating >= ?";
					
							break;
					case 1: if (!lBound.get(i).isEmpty())
								s += " AND 	coupon >= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND 	coupon <= ?";
					
							break;
					case 2: if (!lBound.get(i).isEmpty())
								s += " AND currentyield >= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND currentyield <= ?";
							
							break;
					case 3: if (!lBound.get(i).isEmpty())
								s += " AND maturityyield >= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND maturityyield <= ?";
							
							break;
					case 4: if (!lBound.get(i).isEmpty())
								s += " AND maturitydate >= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND maturitydate <= ?";
					
							break;
					case 5: if (!lBound.get(i).isEmpty())
								s += " AND parvalue >= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND parvalue <= ?";
							
							break;
					case 6: if (!lBound.get(i).isEmpty())
								s += " AND price >= ?";
							if (!hBound.get(i).isEmpty())
								s += " AND price >= ?";
					
							break;
				}
			}
			try{
				System.out.println(s);
				PreparedStatement pstmt = conn.prepareStatement(s);
				
				int count = 1;
				
				for (int k = 0; k < lBound.size(); ++k){
					switch (k) {
						case 0:	if (!lBound.get(k).isEmpty()){
									int lMoodysID = getMoodysIDFromString(lBound.get(k));
									pstmt.setInt(count, lMoodysID);
									++count;
								}
									
								if (!hBound.get(k).isEmpty()){
									int hMoodysID = getMoodysIDFromString(hBound.get(k));
									pstmt.setInt(count, hMoodysID);
									++count;
								}
						
								break;
						case 1: if (!lBound.get(k).isEmpty()){
									int lCoupon = Integer.parseInt(lBound.get(k));
									pstmt.setInt(count, lCoupon);
									++count;
								}
								if (!hBound.get(k).isEmpty()){
									int hCoupon = Integer.parseInt(hBound.get(k));
									pstmt.setInt(count, hCoupon);
									++count;
								}
						
								break;
						case 2: if (!lBound.get(k).isEmpty()){
									double lCurrentYield = Double.parseDouble(lBound.get(k));
									pstmt.setDouble(count, lCurrentYield);
									++count;
								}
								if (!hBound.get(k).isEmpty()){
									double hCurrentYield = Double.parseDouble(hBound.get(k));
									pstmt.setDouble(count, hCurrentYield);
									++count;
								}
								
								break;
						case 3: if (!lBound.get(k).isEmpty()){
									double lMaturityYield = Double.parseDouble(lBound.get(k));
									pstmt.setDouble(count, lMaturityYield);
									++count;
								}
								if (!hBound.get(k).isEmpty()){
									double hMaturityYield = Double.parseDouble(hBound.get(k));
									pstmt.setDouble(count, hMaturityYield);
									++count;
								}
								
								break;
						case 4: 
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								if (!lBound.get(k).isEmpty()){
									Date lMaturityDate = dateFormat.parse(lBound.get(k));
									java.sql.Date lsDate = new java.sql.Date(lMaturityDate.getTime());
									pstmt.setDate(count, lsDate);
									++count;
									
								}
								if (!hBound.get(k).isEmpty()){
									Date hMaturityDate = dateFormat.parse(hBound.get(k));
									java.sql.Date hsDate = new java.sql.Date(hMaturityDate.getTime());
									pstmt.setDate(count, hsDate);
									++count;
								}
						
								break;
						case 5: 
							if (!lBound.get(k).isEmpty()){
								double lParValue = Double.parseDouble(lBound.get(k));
								pstmt.setDouble(count, lParValue);
								++count;
								
							}
							if (!hBound.get(k).isEmpty()){
								double hParValue = Double.parseDouble(hBound.get(k));
								pstmt.setDouble(count, hParValue);
								++count;
								
							}
					
							break;		
								
						case 6: if (!lBound.get(k).isEmpty()){
									double lPrice = Double.parseDouble(lBound.get(k));
									pstmt.setDouble(count, lPrice);
									++count;
								}
								if (!hBound.get(k).isEmpty()){
									double hPrice = Double.parseDouble(hBound.get(k));
									pstmt.setDouble(count, hPrice);
									++count;
								}
						
								break;
					}
				}
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()){
					Bond bond = new Bond();
					bond.setCusip(rs.getString("cusip"));
					bond.setParValue(rs.getDouble("parvalue"));
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
					bonds.add(bond);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			return bonds;
	}
		
public boolean writeLog(LogRecord logrecord){
		String s = "INSERT INTO log(id_portfolio, id_trader, cusip, price, quantity, datetime, type) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(s);
			
			pstmt.setInt(1, logrecord.getPortfolioId());
			pstmt.setInt(2, logrecord.getTraderID());
			pstmt.setString(3, logrecord.getCusip());
			pstmt.setDouble(4, logrecord.getPrice());
			pstmt.setInt(5, logrecord.getQuantity());
			pstmt.setTimestamp(6, logrecord.getTMSP());
			pstmt.setString(7, logrecord.getType());

			pstmt.executeUpdate();

			return true;
			
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean buyBond(Trader trader, Portfolio portfolio, Bond bond, int amount){
			
		int portfolioID = portfolio.getId();
		String cusip = bond.getCusip();
		double price = bond.getPrice() * amount;
		int clientID = getClientFromPortfolio(portfolioID).getId();
		
		try{
			conn.setAutoCommit(false);
			
			if (isBondExistInPortfolio(portfolio, bond))
			{	
				String updatePortfolios = "UPDATE portfolios SET quantity = quantity + ?" +
						"WHERE id = ? AND cusip_bond = ?;";
				
				PreparedStatement portfolioPstmt = conn.prepareStatement(updatePortfolios);
					
				portfolioPstmt.setInt(1, amount);
				portfolioPstmt.setInt(2, portfolioID);
				portfolioPstmt.setString(3, cusip);
				
				portfolioPstmt.executeUpdate();
				
				String updateBonds = "UPDATE bonds SET quantity = quantity - ? WHERE cusip = ?";
				
				PreparedStatement bondPstmt = conn.prepareStatement(updateBonds);
				
				bondPstmt.setInt(1, amount);
				bondPstmt.setString(2, cusip);
				
				bondPstmt.executeUpdate();
				
				String updateClients = "UPDATE clients SET balance = balance - ? WHERE id = ?";
				
				PreparedStatement clientPstmt = conn.prepareStatement(updateClients);
				
				clientPstmt.setDouble(1, price);
				clientPstmt.setInt(2, clientID);
				
				clientPstmt.executeUpdate();
				
				LogRecord log = new LogRecord(portfolio, trader, bond, amount, "buy");
				
				writeLog(log);
				
				conn.commit();
				
				conn.setAutoCommit(true);
					
				return true;
			}
			else{
				String insertPortfolios = "INSERT INTO portfolios VALUES(?, ?, ?);";
				
				PreparedStatement portfolioPstmt = conn.prepareStatement(insertPortfolios);
					
				portfolioPstmt.setInt(1, portfolioID);
				portfolioPstmt.setString(2, cusip);
				portfolioPstmt.setInt(3, amount);
				
				portfolioPstmt.executeUpdate();
				
				String updateBonds = "UPDATE bonds SET quantity = quantity - ? WHERE cusip = ?";
				
				PreparedStatement bondPstmt = conn.prepareStatement(updateBonds);
				
				bondPstmt.setInt(1, amount);
				bondPstmt.setString(2, cusip);
				
				bondPstmt.executeUpdate();
				
				String updateClients = "UPDATE clients SET balance = balance - ? WHERE id = ?";
				
				PreparedStatement clientPstmt = conn.prepareStatement(updateClients);
				
				clientPstmt.setDouble(1, price);
				clientPstmt.setInt(2, clientID);
				
				clientPstmt.executeUpdate();
				
				LogRecord log = new LogRecord(portfolio, trader, bond, amount, "buy");
				
				writeLog(log);
				
				conn.commit();
				
				conn.setAutoCommit(true);
				
				return true;
			} 
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}		
	}

	public Trader getTrader(int traderID){
		Trader trader = new Trader();
		trader.setId(traderID);
		String s = "SELECT fullname FROM traders WHERE id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(s);
			pstmt.setInt(1, traderID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				trader.setName(rs.getString("fullname"));
			}
			trader.setClients(getClientsFromTrader(trader));
		} catch (Exception e){
			e.printStackTrace();
		}
		return trader;	
	}

	public Client getClientFromID(int id){
		Client client = new Client();
		try{
			String s = "SELECT * FROM clients WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(s);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setIdTrader(rs.getInt("id_trader"));
				client.setIdPortfolio(rs.getInt("id_portfolio"));
				client.setBalance(rs.getDouble("balance"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}

}
