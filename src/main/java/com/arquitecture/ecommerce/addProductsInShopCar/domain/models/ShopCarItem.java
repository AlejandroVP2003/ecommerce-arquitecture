package com.arquitecture.ecommerce.addProductsInShopCar.domain.models;

public class ShopCarItem {
    private Long productId; // Identificador del producto
    private int quantity; // Cantidad del producto

    // Getters and Setters
    public Long getProductId() {
        return productId; // Retorna el ID del producto
    }

    public void setProductId(Long productId) {
        this.productId = productId; // Establece el ID del producto
    }

    public int getQuantity() {
        return quantity; // Retorna la cantidad del producto
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity; // Establece la cantidad del producto
    }
}

