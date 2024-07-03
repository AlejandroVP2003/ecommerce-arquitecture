/*package com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.entities;public class ShopCarEntity {
}*/
package com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShopCarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShopCarItemEntity> items;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ShopCarItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ShopCarItemEntity> items) {
        this.items = items;
    }
}

