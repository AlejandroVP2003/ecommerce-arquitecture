package arquitecture.ecommerce.infrastructure.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_car")
public class ShopCarEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @ElementCollection
    @CollectionTable(name = "shop_car_products", joinColumns = @JoinColumn(name = "shop_car_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> products = new HashMap<>();

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(nullable = false)
    private double total;
    
    public ShopCarEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserEntity getClient() { return client; }
    public void setClient(UserEntity client) { this.client = client; }

    public Map<Long, Integer> getProducts() { return products; }
    public void setProducts(Map<Long, Integer> products) { this.products = products; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
    
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public void addProduct(Long productId, int quantity) {
        this.products.put(productId, quantity);
    }

}
