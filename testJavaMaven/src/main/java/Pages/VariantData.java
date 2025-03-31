package Pages;

public class VariantData {
    private String orgPrice;       // Giá niêm yết
    private String discountPrice;  // Giá bán
    private String costPrice;      // Giá gốc
    private String stock;          // Số lượng

    public VariantData(String orgPrice, String discountPrice, String costPrice, String stock) {
        this.orgPrice = orgPrice;
        this.discountPrice = discountPrice;
        this.costPrice = costPrice;
        this.stock = stock;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public String getStock() {
        return stock;
    }

// POJO (Plain Old Java Object)
}
