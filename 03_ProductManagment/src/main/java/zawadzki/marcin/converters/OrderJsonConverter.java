package zawadzki.marcin.converters;


import zawadzki.marcin.model.Product;

public class OrderJsonConverter extends JsonConverter<Product> {
    public OrderJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
