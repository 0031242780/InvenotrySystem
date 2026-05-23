package ui;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ProductDAO dao = new ProductDAO();

            // 1. INSERT — only if barcode doesn't already exist
            Product p = new Product();
            p.setCategoryId(1);
            p.setBarcode("TEST-001");
            p.setProductName("Test Product");
            p.setDescription("Inserted from Main");
            p.setTrend(true);
            p.setPhoto("test.jpg");

            if (!dao.exists(p.getBarcode())) {
                dao.insertProduct(p);
                System.out.println("Product inserted successfully!");
            } else {
                System.out.println("Skipped insert — barcode already exists.");
            }

            // 2. GET ALL
            List<Product> products = dao.getAllProducts();
            System.out.println("\n--- ALL PRODUCTS ---");
            for (Product pr : products) {
                System.out.println(pr);
            }

            // 3. GET BY ID
            Product single = dao.getProductById(1);
            if (single != null) {
                System.out.println("\n--- PRODUCT ID 1 ---");
                System.out.println(single);

                // 4. UPDATE
                single.setProductName("Updated Name");
                dao.updateProduct(single);
                System.out.println("Product updated!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}