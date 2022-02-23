
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author ADMIN
 */
class Manager {

    void displayMenu() {
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Add storekeeper",
                "Add product",
                "Update product",
                "Search product",
                "Sort product",
                "Exit"
        ));
        System.out.println("");
        System.out.println("                Menu");
        System.out.println("=======================================");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ". " + listOptions.get(i));
        }
        System.out.println("=======================================");
    }

    void addStorekeeper(ArrayList<String> listStorekeeper) {
        while (true) {
            try {
                String newStorekeeper = Utility.inputStorekeeper("Please enter storekeeper name: ");
                for (String storekeeper : listStorekeeper) {
                    if (newStorekeeper.equals(storekeeper)) {
                        throw new Exception("Input name is already existed in the list.");
                    }
                }
                listStorekeeper.add(newStorekeeper);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    void addProduct(ArrayList<Product> listProduct, ArrayList<String> listStorekeeper) {
        String id;
        while (true) {
            try {
                id = Utility.getNonBlankStr("ID: ");
                for (Product product : listProduct) {
                    if (product.getId().equals(id)) {
                        throw new Exception("ID is duplicated");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        String name = Utility.getNonBlankStr("Name: ");
        String location = Utility.getNonBlankStr("Location: ");
        BigDecimal price;
        while (true) {
            try {
                price = Utility.inputPrice("Price: ");
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        LocalDate expiryDate = Utility.inputDate("Expiry Date [dd/mm/yyyy]: ");
        LocalDate manufacturedDate = Utility.inputDate("Manufactured Date [dd/mm/yyyy]: ");
        String category = Utility.getNonBlankStr("Category: ");
        String storekeeper;
        while (true) {
            try {
                storekeeper = Utility.inputStorekeeper("Storekeeper: ");
                boolean storekeeperExisted = false;
                for (String storekeeperInList : listStorekeeper) {
                    if (storekeeperInList.equals(storekeeper)) {
                        storekeeperExisted = true;
                        break;
                    }
                }
                if (storekeeperExisted == false) {
                    throw new Exception("Storekeeper's name is not in the list");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        LocalDate receiptDate = Utility.inputDate("Receipt Date [dd/mm/yyyy]: ");
        listProduct.add(new Product(id, name, location, price, expiryDate,
                manufacturedDate, category, storekeeper, receiptDate));
    }

    void updateProduct(ArrayList<Product> listProduct, ArrayList<String> listStorekeeper) throws Exception {
        String searchID = Utility.getNonBlankStr("Enter product's ID to update: ");
        Product updateProduct = null;
        boolean foundID = false;
        for(Product p : listProduct) {
            if(searchID == null ? p.getId() == null : searchID.equals(p.getId())) {
                updateProduct = p;
                foundID = true;
                break;
            }
        }
        if(!foundID) {
            throw new Exception("Not found");
        }
        String id;
        while (true) {
            try {
                id = Utility.getNonBlankStr("ID: ");
                for (Product product : listProduct) {
                    if (product.getId().equals(id)) {
                        throw new Exception("ID is duplicated");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        String name = Utility.getNonBlankStr("Name: ");
        String location = Utility.getNonBlankStr("Location: ");
        BigDecimal price;
        while (true) {
            try {
                price = Utility.inputPrice("Price: ");
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        LocalDate expiryDate = Utility.inputDate("Expiry Date [dd/mm/yyyy]: ");
        LocalDate manufacturedDate = Utility.inputDate("Manufactured Date [dd/mm/yyyy]: ");
        String category = Utility.getNonBlankStr("Category: ");
        String storekeeper;
        while (true) {
            try {
                storekeeper = Utility.inputStorekeeper("Storekeeper: ");
                boolean storekeeperExisted = false;
                for (String storekeeperInList : listStorekeeper) {
                    if (storekeeperInList.equals(storekeeper)) {
                        storekeeperExisted = true;
                        break;
                    }
                }
                if (storekeeperExisted == false) {
                    throw new Exception("Storekeeper's name is not in the list");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        LocalDate receiptDate = Utility.inputDate("Receipt Date [dd/mm/yyyy]: ");
        updateProduct.setId(id);
        updateProduct.setName(name);
        updateProduct.setLocation(location);
        updateProduct.setCategory(category);
        updateProduct.setExpiryDate(expiryDate);
        updateProduct.setManufacturedDate(manufacturedDate);
        updateProduct.setReceiptDate(receiptDate);
        updateProduct.setPrice(price);
        updateProduct.setPrice(price);
    }

    void searchProduct(ArrayList<Product> listProduct) {
        System.out.println("----Search by----");
        System.out.println("1. Name");
        System.out.println("2. Category");
        System.out.println("3. Storekeeper");
        System.out.println("4. ReceiptDate");
        int choice = this.selectOption("Select option: ", 1, 4);
        switch (choice) {
            case 1:
                this.searchByName();
                break;
            case 2:
                this.searchByCategory();
                break;
            case 3:
                this.searchByStorekeeper();
                break;
            case 4:
                this.searchByReceiptDate();
                break;
        }
    }

    void sortProduct(ArrayList<Product> listProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Ask user to select an option from min to max.
     *
     * @param message message to user
     * @param min minimum value
     * @param max maximum value
     * @return the input value
     */
    public int selectOption(String message, int min, int max) {
        String input;
        input = Utility.getNonBlankStr(message);
        try {
            //throw exception if input is empty
            if (input.isEmpty()) {
                throw new Exception("Empty input");
            }
            int num;
            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Not a valid number");
            }
            //Throw exception if the input is out of range
            if (num < min || num > max) {
                throw new Exception("Input out of range [1-5]");
            }
            return num;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectOption(message, min, max);
        }
    }

    private void searchByName() {
        Utilitysd
    }

    private void searchByCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void searchByStorekeeper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void searchByReceiptDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
