
package com.arquitecture.ecommerce.addProductsInShopCar.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ShopCar {
    private Long id; // Identificador único del carrito de compras
    private List<ShopCarItem> items; // Lista de ítems en el carrito

    public ShopCar() {
        this.items = new ArrayList<>(); // Inicializa la lista de ítems
    }

    public void addItem(ShopCarItem item) {
        this.items.add(item); // Agrega un ítem al carrito
    }

    // Getters and Setters
    public Long getId() {
        return id; // Retorna el ID del carrito
    }

    public void setId(Long id) {
        this.id = id; // Establece el ID del carrito
    }

    public List<ShopCarItem> getItems() {
        return items; // Retorna la lista de ítems en el carrito
    }

    public void setItems(List<ShopCarItem> items) {
        this.items = items; // Establece la lista de ítems en el carrito
    }
}

