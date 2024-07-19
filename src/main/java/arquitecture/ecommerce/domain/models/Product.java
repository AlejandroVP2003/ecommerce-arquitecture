package arquitecture.ecommerce.domain.models;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Product {
    
    private Long id;
    private String sku;
    private String name;
    private String description;
    private int stock;
    private double price;
    private Date releaseDate;
    private Category category;
    private User owner;
    private String brand;
    private Double weight;
    private Double length;
    private Double width;
    private Double height;
    private String productCondition;
    private Double rating;
    private boolean isVisible;
    private List<String> imagesPath;
    
    public Product() {
        this.releaseDate = Date.from(Instant.now());
        this.isVisible = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getLength() { return length; }
    public void setLength(Double length) { this.length = length; }

    public Double getWidth() { return width; }
    public void setWidth(Double width) { this.width = width; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public String getProductCondition() { return productCondition; }
    public void setProductCondition(String productCondition) { this.productCondition = productCondition; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public boolean isVisible() { return isVisible; }
    public void setVisible(boolean isVisible) { this.isVisible = isVisible; }

    public List<String> getImagesPath() { return imagesPath; }
    public void setImagesPath(List<String> imagesPath) { this.imagesPath = imagesPath; }

}
