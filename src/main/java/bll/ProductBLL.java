package bll;

import bll.validators.ProductValidations;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private final ProductDAO productDAO;

    public ProductBLL(){
        productDAO=new ProductDAO();

    }

    /**
     * Metoda de validare a datelor unui produs
     * @param product - produsul care trebuie validat
     * @return - un intreg reprezentand un cod de eroare/succes
     */
    private int validateProductData(Product product){
        ProductValidations productValidations=new ProductValidations();
        if(!productValidations.isValidName(product.getName())){
            return 1;
        }
        if(!productValidations.isValidPrice(product.getPrice())){
            return 2;
        }

        if(!productValidations.isValidStockQuantity(product.getStock_quantity())){
            return 3;
        }

        if(productDAO.findProductByName(product.getName())!=null){ //if product already exists
            return -1;
        }
        return 0;
    }

    /**
     * Metoda de adaugare a unui produs nou
     * @param product - produsul care urmeaza sa fie inserat
     * @return - cod intreg de eroare/succes
     */
    public int addProduct(Product product){

        int valid = validateProductData(product);
        if(valid == 0) {
            productDAO.insert(product);
        }
        else{
            return valid; //error
        }

        return 0; //succes

    }

    /**
     * Metoda de actualizare a datelor unui produs
     * @param product - produsul care este editat
     * @return - cod intreg de eroare/succes
     */
    public int editProduct(Product product){
        int valid = validateProductData(product);
        if(valid == 0) {
            productDAO.update(product);
        }
        else{
            return valid;
        }
        return 0;
    }

    /**
     * Metoda de stergere a unui produs
     * @param id - id-ul produsului care se doreste a fi sters
     */
    public void deleteProduct(int id){
        productDAO.delete(id);
    }

    public List<Product> searchProduct(String field){
        List<Product> foundProductsList=new ArrayList<>();
        int id=-1;
        try{
            id=Integer.parseInt(field);
            Product product = (Product) productDAO.findById(id);
            if(product!=null){
                foundProductsList.add(product);
                return foundProductsList;
            } else {
                return null;
            }
        }catch(NumberFormatException e){
            return productDAO.findProductsByPattern(field);
        }
    }

    /**
     * Metoda de vizualizare a tuturor produselor din baza de date
     * @return - o lista de produse
     * @throws RuntimeException
     */
    public List<Product> viewAll() throws RuntimeException{
        List<Product> productsList= productDAO.findAll();
        if(productsList==null){
            throw new RuntimeException();
        }
        return productsList;
    }


}
