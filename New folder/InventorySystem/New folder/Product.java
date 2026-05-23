package model;

public class Product {

	private int productId;
	private int categoryId;
	private String barcode;
	private String productName;
	private String description;
	private boolean trend;
	private String photo;

	public Product() {
	}

	public Product(int productId, int categoryId, String barcode, String productName, String description, boolean trend,
			String photo) {

		this.productId = productId;
		this.categoryId = categoryId;
		this.barcode = barcode;
		this.productName = productName;
		this.description = description;
		this.trend = trend;
		this.photo = photo;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTrend() {
		return trend;
	}

	public void setTrend(boolean trend) {
		this.trend = trend;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return productName + " (" + barcode + ")";
	}
}