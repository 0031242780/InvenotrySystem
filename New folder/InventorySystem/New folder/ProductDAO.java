package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Product;

public class ProductDAO {

	public void insertProduct(Product p) throws Exception {
		String sql = "INSERT INTO products(category_id, barcode, product_name, description, trend, photo) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, p.getCategoryId());
			ps.setString(2, p.getBarcode());
			ps.setString(3, p.getProductName());
			ps.setString(4, p.getDescription());
			ps.setBoolean(5, p.isTrend());
			ps.setString(6, p.getPhoto());

			ps.executeUpdate();
		}
	}

	public ArrayList<Product> getAllProducts() throws Exception {
		ArrayList<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM products";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(mapRow(rs));
			}
		}

		return list;
	}

	public Product getProductById(int id) throws Exception {
		String sql = "SELECT * FROM products WHERE product_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRow(rs);
				}
			}
		}

		return null;
	}

	public void updateProduct(Product p) throws Exception {
		String sql = "UPDATE products SET category_id=?, barcode=?, product_name=?, description=?, trend=?, photo=? WHERE product_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, p.getCategoryId());
			ps.setString(2, p.getBarcode());
			ps.setString(3, p.getProductName());
			ps.setString(4, p.getDescription());
			ps.setBoolean(5, p.isTrend());
			ps.setString(6, p.getPhoto());
			ps.setInt(7, p.getProductId());

			ps.executeUpdate();
		}
	}

	public void deleteProduct(int id) throws Exception {
		String sql = "DELETE FROM products WHERE product_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	public boolean exists(String barcode) throws Exception {
		String sql = "SELECT 1 FROM products WHERE barcode = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, barcode);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	// Private helper — avoids repeating mapping in every method
	private Product mapRow(ResultSet rs) throws Exception {
		Product p = new Product();
		p.setProductId(rs.getInt("product_id"));
		p.setCategoryId(rs.getInt("category_id"));
		p.setBarcode(rs.getString("barcode"));
		p.setProductName(rs.getString("product_name"));
		p.setDescription(rs.getString("description"));
		p.setTrend(rs.getBoolean("trend"));
		p.setPhoto(rs.getString("photo"));
		return p;
	}
}