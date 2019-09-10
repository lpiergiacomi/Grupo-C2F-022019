package unq.tpi.desapp.models;

public class Menu {

    private String name;
    private String description;
    private Category category;
    private int deliveryPrice;
    private int price;
    private int minQuantity;
    private int minQuantityPrice;
    private int minQuantity2;
    private int minQuantity2Price;
    private int maxSalesPerDay;
    private Service service;

    public Menu(String name, String description, int minQuantity, int minQuantityPrice, int maxSalesPerDay) {
        this.name = name;
        this.description = description;
        this.minQuantity = minQuantity;
        this.minQuantityPrice = minQuantityPrice;
        this.maxSalesPerDay = maxSalesPerDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getMinQuantityPrice() {
        return minQuantityPrice;
    }

    public void setMinQuantityPrice(int minQuantityPrice) {
        this.minQuantityPrice = minQuantityPrice;
    }

    public int getMinQuantity2() {
        return minQuantity2;
    }

    public void setMinQuantity2(int minQuantity2) {
        this.minQuantity2 = minQuantity2;
    }

    public int getMinQuantity2Price() {
        return minQuantity2Price;
    }

    public void setMinQuantity2Price(int minQuantity2Price) {
        this.minQuantity2Price = minQuantity2Price;
    }

    public int getMaxSalesPerDay() {
        return maxSalesPerDay;
    }

    public void setMaxSalesPerDay(int maxSalesPerDay) {
        this.maxSalesPerDay = maxSalesPerDay;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
