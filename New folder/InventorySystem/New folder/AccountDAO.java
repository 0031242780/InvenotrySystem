//package dao;
//
//import model.Account;
//import java.sql.*;
//
//public class AccountDAO {
//
//	public void insert(Account a) throws Exception {
//
//		String sql = "INSERT INTO accounts(role_id,email,password_,first_name,last_name,phone_number,city,street) VALUES (?,?,?,?,?,?,?,?)";
//
//		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//
//			ps.setInt(1, a.getRoleId());
//			ps.setString(2, a.getEmail());
//			ps.setString(3, a.getPassword());
//			ps.setString(4, a.getFirstName());
//			ps.setString(5, a.getLastName());
//			ps.setString(6, a.getPhoneNumber());
//			ps.setString(7, a.getCity());
//			ps.setString(8, a.getStreet());
//
//			ps.executeUpdate();
//		}
//	}
//
//	public Account login(String email, String password) throws Exception {
//
//		String sql = "SELECT * FROM accounts WHERE email=? AND password_=?";
//
//		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//
//			ps.setString(1, email);
//			ps.setString(2, password);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				Account a = new Account();
//				a.setAccountId(rs.getInt("account_id"));
//				a.setRoleId(rs.getInt("role_id"));
//				a.setEmail(rs.getString("email"));
//				a.setFirstName(rs.getString("first_name"));
//				a.setLastName(rs.getString("last_name"));
//				return a;
//			}
//		}
//
//		return null;
//	}
//}