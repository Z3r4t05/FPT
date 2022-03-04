
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author ADMIN
 */
class ManageProductAndStorekeeper {

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

    void addStorekeeper(ArrayList<Storekeeper> listStorekeeper) {
        //Keep asking if there is an Exception
        while (true) {
            try {
                String name = Utility.inputStorekeeper("Please enter "
                        + "storekeeper name: ");
                //Loop the list to find duplicated name
                for (Storekeeper storekeeper : listStorekeeper) {
                    //If the name is already exists in the list then throw Exception
                    if (name.equals(storekeeper.getName())) {
                        throw new Exception("Input name is already existed "
                                + "in the list.");
                    }
                }
                listStorekeeper.add(new Storekeeper(listStorekeeper.size() + 1, name));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.displayListStorekeeper(listStorekeeper);
        
    }

    void addProduct(ArrayList<Product> listProduct, ArrayList<Storekeeper> listStorekeeper) throws Exception {
        //throw exception if there is no storekeeper
        if (listStorekeeper.isEmpty()) {
            throw new Exception("list of storekeeper is empty");
        }
        String id;
        //keep asking id if there's an exception
        while (true) {
            try {
                id = Utility.getNonBlankStr("ID: ");
                //check all the product to find duplicated id
                for (Product product : listProduct) {
                    //throw exception if the code is duplicated
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
        //keep asking price when there's an exception
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
        //Keep asking storekeeper's name if there's an exception
        while (true) {
            try {
                storekeeper = Utility.inputStorekeeper("Storekeeper: ");
                boolean storekeeperExisted = false;
                //Loop the list to find duplicated storekeeper
                for (Storekeeper s : listStorekeeper) {
                    //If the name is in the list then we mark it as existed
                    if (s.getName().equals(storekeeper)) {
                        storekeeperExisted = true;
                        break;
                    }
                }
                //If the name is not in the list then it cannot be added
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

    void updateProduct(ArrayList<Product> listProduct, ArrayList<Storekeeper> listStorekeeper) throws Exception {
        //throw exception if there's no storekeeper
        if (listStorekeeper.isEmpty()) {
            throw new Exception("List of Storekeeper is empty");
        } 
        //throw exception if there's no product
        else if (listProduct.isEmpty()) {
            throw new Exception("List of Product is empty");
        }
        String searchID = Utility.getNonBlankStr("Enter product's ID to update: ");
        Product updateTarget = null; //target product that user want to update
        boolean foundID = false;
        //search through the list to find if the product that need update is existed
        for (Product p : listProduct) {
            //If found the product, then target that product and mark foundID as true
            if (searchID == null ? p.getId() == null : searchID.equals(p.getId())) {
                updateTarget = p;
                foundID = true;
                break;
            }
        }
        //if not found anything then throw new exception
        if (!foundID) {
            throw new Exception("Not found");
        }
        String id;
        //Keep asking ID until there's no exception
        while (true) {
            try {
                id = Utility.getNonBlankStr("ID: ");
                //if new id equals old id then break
                if(updateTarget.getId().equals(id)) {
                    break;
                }
                //Loop through the list to find duplicated id
                for (Product product : listProduct) {
                    //throw exception if the id is duplicated
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
        //keep asking price if there's an exception
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
        //keep asking storekeeper if there's an exception
        while (true) {
            try {
                storekeeper = Utility.inputStorekeeper("Storekeeper: ");
                boolean storekeeperExisted = false;
                //loop through the list to find duplicated storekeeper
                for (Storekeeper s : listStorekeeper) {
                    //if it found a storekeeper that duplicated then mark it
                    if (s.getName().equals(storekeeper)) {
                        storekeeperExisted = true;
                        break;
                    }
                }
                //throw exception if the storekeeper is not existed
                if (storekeeperExisted == false) {
                    throw new Exception("Storekeeper's name is not in the list");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        LocalDate receiptDate = Utility.inputDate("Receipt Date [dd/mm/yyyy]: ");
        updateTarget.setId(id);
        updateTarget.setName(name);
        updateTarget.setLocation(location);
        updateTarget.setCategory(category);
        updateTarget.setExpiryDate(expiryDate);
        updateTarget.setManufacturedDate(manufacturedDate);
        updateTarget.setReceiptDate(receiptDate);
        updateTarget.setPrice(price);
        updateTarget.setPrice(price);
        updateTarget.setStorekeeper(storekeeper);
    }

    void searchProduct(ArrayList<Product> listProduct) throws Exception {
        //throw exception if the list is empty
        if (listProduct.isEmpty()) {
            throw new Exception("List of Product is empty");
        }
        System.out.println("----Search by----");
        System.out.println("1. Name");
        System.out.println("2. Category");
        System.out.println("3. Storekeeper");
        System.out.println("4. ReceiptDate");
        int choice = this.selectOption("Select option: ", 1, 4);
        //perform search option based on user choice
        switch (choice) {
            //search by name
            case 1:
                this.searchByName(listProduct);
                break;
            //search by category    
            case 2:
                this.searchByCategory(listProduct);
                break;
            //search by storekeeper
            case 3:
                this.searchByStorekeeper(listProduct);
                break;
            //search by receipt date
            case 4:
                this.searchByReceiptDate(listProduct);
                break;
        }
    }

    /**
     * Sort product based on ExpiryDate and ManufacturedDate
     *
     * @param listProduct list of product
     */
    public void sortProduct(ArrayList<Product> listProduct) throws Exception {
        //throw new exception if the list is empty
        if (listProduct.isEmpty()) {
            throw new Exception("List of product is empty");
        }
        listProduct.sort(Comparator.comparing(Product::getExpiryDate)
                .thenComparing(Product::getManufacturedDate));
        this.displayListProduct(listProduct);
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
                throw new Exception("Input out of range [1-6]");
            }
            return num;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectOption(message, min, max);
        }
    }

    private void searchByName(ArrayList<Product> listProduct) throws Exception {
        String nameToSearch = Utility.getNonBlankStr("Search name: ");
        ArrayList<Product> searchResult = new ArrayList<>();
        //Loop all the list to find a product that contains the name to search
        for (Product p : listProduct) {
            //if it found the product then add it the result list
            if (p.getName().contains(nameToSearch)) {
                searchResult.add(p);
            }
        }
        //if the search result is empty then throw exception
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } 
        //if there's only 1 product found then tell it to the user 
        else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } 
        //else tell user the total number of product 
        else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        //if the search result is not empty then display
        if (!searchResult.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void searchByCategory(ArrayList<Product> listProduct) throws Exception {
        String categoryToSearch = Utility.getNonBlankStr("Search category: ");
        ArrayList<Product> searchResult = new ArrayList<>();
        //loop through the list to find the category
        for (Product p : listProduct) {
            //if it found a product then add that product to the list
            if (p.getCategory().contains(categoryToSearch)) {
                searchResult.add(p);
            }
        }
        //throw exception if the search result is empty
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } 
        //tell user if there's only one product in the search result
        else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } 
        //else display total number of search result
        else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        //display the result if the search result is empty
        if (!searchResult.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void searchByStorekeeper(ArrayList<Product> listProduct) throws Exception {
        String storekeeper = Utility.getNonBlankStr("Search storekeeper: ");
        System.out.println(storekeeper);
        ArrayList<Product> searchResult = new ArrayList<>();
        //search through the product to find storekeeper
        for (Product p : listProduct) {
            //add it to the result list if it's found
            if (p.getStorekeeper().contains(storekeeper)) {
                searchResult.add(p);
            }
        }
        //throw exception when the search result is empty
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } 
        //tell user if it found only 1 product
        else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } 
        //tell user if it founds more than one
        else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        //if the list product is not empty then display it 
        if (!listProduct.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void searchByReceiptDate(ArrayList<Product> listProduct) throws Exception {
        LocalDate date = Utility.inputDate("Search receipt date: ");
        ArrayList<Product> searchResult = new ArrayList<>();
        //loop through all product in the list of product to search the date
        for (Product p : listProduct) {
            //add the product the the search result if the dates are equal 
            if (p.getReceiptDate().isEqual(date)) {
                searchResult.add(p);
            }
        }
        //throw exception if the search result is empty
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        }
        //tell user if it founds only 1 product
        else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        }
        //otherwise tell user the number of products in the search result
        else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        //display the result if the search result is not empty
        if (!listProduct.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void displayListProduct(ArrayList<Product> listProduct) {
        System.out.println("+--------+----------------+----------------+"
                + "----------------+----------------+----------------+"
                + "----------------+----------------+----------------+");
        System.out.printf("|%-8s|%-16s|%-16s|%-16s|%-16s|%-16s|%-16s|%-16s|%-16s|\n",
                "ID", "NAME", "LOCATION", "PRICE", "EXP DATE", "MF DATE", "CATEGORY",
                "STOREKEEPER", "RECEIPTDATE");
        System.out.println("+--------+----------------+----------------+"
                + "----------------+----------------+----------------+"
                + "----------------+----------------+----------------+");
        //loop the list of product to display each product
        for (Product p : listProduct) {
            System.out.printf("|%-8.8s|%-16.16s|%-16.16s|%-16.16s|%-16.16s|%-16.16s|"
                    + "%-16.16s|%-16.16s|%-16.16s|\n",
                    p.getId(), p.getName(), p.getLocation(),
                    NumberFormat.getCurrencyInstance(Locale.US).format(p.getPrice()),
                    this.displayDate(p.getExpiryDate()),
                    this.displayDate(p.getManufacturedDate()),
                    p.getCategory(),
                    p.getStorekeeper(),
                    this.displayDate(p.getReceiptDate()));
            System.out.println("+--------+----------------+----------------+"
                    + "----------------+----------------+----------------+"
                    + "----------------+----------------+----------------+");
        }
    }
    
    private void displayListStorekeeper(ArrayList<Storekeeper> listStorekeeper) {
        System.out.println("+--------+----------------+");
        System.out.printf("|%-8.8s|%-16.16s|\n", "ID", "NAME");
        System.out.println("+--------+----------------+");
        //loop through the list to display each storekeeper
        for(Storekeeper s : listStorekeeper) {
            System.out.printf("|%-8s|%-16s|\n", s.getId(), s.getName());
            System.out.println("+--------+----------------+");
        }
    }
    
    private String displayDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
}
