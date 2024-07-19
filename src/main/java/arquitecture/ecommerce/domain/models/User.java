package arquitecture.ecommerce.domain.models;

import java.util.Set;

public class User {
    
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isSeller;
    private boolean isAdmin;
    private Set<Product> products;
    
    public User() {
        this.isSeller = false;
        this.isAdmin = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isSeller() { return isSeller; }
    public void setSeller(boolean isSeller) { this.isSeller = isSeller; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }

}
