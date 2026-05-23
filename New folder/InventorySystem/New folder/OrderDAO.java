package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderDAO {

	// CREATE
	public void insertOrder(Order o) throws Exception {

		Connection con = DBConnection.getConnection();

		String sql = "INSERT INTO orders(account_id, status_id, delivery_company_id, total_price) VALUES (?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, o.getAccountId());
		ps.setInt(2, o.getStatusId());
		ps.setInt(3, o.getDeliveryCompanyId());
		ps.setDouble(4, o.getTotalPrice());

		ps.executeUpdate();
	}

	// READ ALL
	public List<Order> getAllOrders() throws Exception {

		List<Order> list = new ArrayList<>();

		Connection con = DBConnection.getConnection();

		String sql = "SELECT * FROM orders";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Order o = new Order();

			o.setOrderId(rs.getInt("order_id"));
			o.setAccountId(rs.getInt("account_id"));
			o.setStatusId(rs.getInt("status_id"));
			o.setDeliveryCompanyId(rs.getInt("delivery_company_id"));
			o.setTotalPrice(rs.getDouble("total_price"));
			o.setCreatedAt(rs.getTimestamp("created_at"));

			list.add(o);
		}

		return list;
	}

	// READ ONE
	public Order getOrderById(int id) throws Exception {

		Connection con = DBConnection.getConnection();

		String sql = "SELECT * FROM orders WHERE order_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			Order o = new Order();

			o.setOrderId(rs.getInt("order_id"));
			o.setAccountId(rs.getInt("account_id"));
			o.setStatusId(rs.getInt("status_id"));
			o.setDeliveryCompanyId(rs.getInt("delivery_company_id"));
			o.setTotalPrice(rs.getDouble("total_price"));
			o.setCreatedAt(rs.getTimestamp("created_at"));

			return o;
		}

		return null;
	}

	// UPDATE
	public void updateOrder(Order o) throws Exception {

		Connection con = DBConnection.getConnection();

		String sql = "UPDATE orders SET account_id=?, status_id=?, delivery_company_id=?, total_price=? WHERE order_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, o.getAccountId());
		ps.setInt(2, o.getStatusId());
		ps.setInt(3, o.getDeliveryCompanyId());
		ps.setDouble(4, o.getTotalPrice());
		ps.setInt(5, o.getOrderId());

		ps.executeUpdate();
	}

	// DELETE
	public void deleteOrder(int id) throws Exception {

		Connection con = DBConnection.getConnection();

		String sql = "DELETE FROM orders WHERE order_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, id);

		ps.executeUpdate();
	}
}