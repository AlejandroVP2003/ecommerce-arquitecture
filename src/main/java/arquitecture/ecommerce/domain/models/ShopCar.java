package arquitecture.ecommerce.domain.models;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShopCar {
    
    private Long id;
    private User client;
    private Map<Long, Integer> products = new HashMap<>();
    private Date creationDate;
    private boolean isActive;
    private boolean isCompleted;
    private double total;

    public ShopCar() {
        this.creationDate = Date.from(Instant.now());
        this.isActive = true;
        this.isCompleted = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }

    public Map<Long, Integer> getProducts() { return products; }
    public void setProducts(Map<Long, Integer> products) { this.products = products; }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

}
