
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

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

    void addStorekeeper(ArrayList<Storekeeper> listStorekeeper) {
        while (true) {
            try {
                String name = Utility.inputStorekeeper("Please enter "
                        + "storekeeper name: ");
                for (Storekeeper storekeeper : listStorekeeper) {
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
    }

    void addProduct(ArrayList<Product> listProduct, ArrayList<Storekeeper> listStorekeeper) throws Exception {
        if (listStorekeeper.isEmpty()) {
            throw new Exception("list of storekeeper is empty");
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
                for (Storekeeper s : listStorekeeper) {
                    if (s.getName().equals(storekeeper)) {
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
        listProduct.add(new Product(Integer.toString(1 + listProduct.size()), name, location, price, expiryDate,
                manufacturedDate, category, storekeeper, receiptDate));
    }

    void updateProduct(ArrayList<Product> listProduct, ArrayList<Storekeeper> listStorekeeper) throws Exception {
        if(listStorekeeper.isEmpty()) {
            throw new Exception("List of Storekeeper is empty");
        } else if(listProduct.isEmpty()) {
            throw new Exception("List of Product is empty");
        }
        String searchID = Utility.getNonBlankStr("Enter product's ID to update: ");
        Product updateTarget = null; //target product that user want to update
        boolean foundID = false;
        for (Product p : listProduct) {
            if (searchID == null ? p.getId() == null : searchID.equals(p.getId())) {
                updateTarget = p;
                foundID = true;
                break;
            }
        }
        if (!foundID) {
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
                for (Storekeeper s : listStorekeeper) {
                    if (s.getName().equals(storekeeper)) {
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
        if(listProduct.isEmpty()) {
            throw new Exception("List of Product is empty");
        }
        System.out.println("----Search by----");
        System.out.println("1. Name");
        System.out.println("2. Category");
        System.out.println("3. Storekeeper");
        System.out.println("4. ReceiptDate");
        int choice = this.selectOption("Select option: ", 1, 4);
        switch (choice) {
            case 1:
                this.searchByName(listProduct);
                break;
            case 2:
                this.searchByCategory(listProduct);
                break;
            case 3:
                this.searchByStorekeeper(listProduct);
                break;
            case 4:
                this.searchByReceiptDate(listProduct);
                break;
        }
    }

    void sortProduct(ArrayList<Product> listProduct) {
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
                throw new Exception("Input out of range [1-5]");
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
        for (Product p : listProduct) {
            if (p.getName().contains(nameToSearch)) {
                searchResult.add(p);
            }
        }
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        if (!listProduct.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void searchByCategory(ArrayList<Product> listProduct) throws Exception {
        String categoryToSearch = Utility.getNonBlankStr("Search category: ");
        ArrayList<Product> searchResult = new ArrayList<>();
        for (Product p : listProduct) {
            if (p.getCategory().contains(categoryToSearch)) {
                searchResult.add(p);
            }
        }
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        if (!listProduct.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void searchByStorekeeper(ArrayList<Product> listProduct) throws Exception {
        String storekeeper = Utility.getNonBlankStr("Search storekeeper: ");
        ArrayList<Product> searchResult = new ArrayList<>();
        for (Product p : listProduct) {
            if (p.getCategory().contains(storekeeper)) {
                searchResult.add(p);
            }
        }
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        if (!listProduct.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void searchByReceiptDate(ArrayList<Product> listProduct) throws Exception {
        LocalDate date = Utility.inputDate("Search receipt date: ");
        ArrayList<Product> searchResult = new ArrayList<>();
        for (Product p : listProduct) {
            if (p.getReceiptDate().isEqual(date)) {
                searchResult.add(p);
            }
        }
        if (searchResult.isEmpty()) {
            throw new Exception("Not found");
        } else if (searchResult.size() == 1) {
            System.out.println("Found 1 product");
        } else {
            System.out.println("Found " + searchResult.size() + " products");
        }
        if (!listProduct.isEmpty()) {
            displayListProduct(searchResult);
        } else {
            System.out.println("Not found");
        }
    }

    private void displayListProduct(ArrayList<Product> listProduct) {
        System.out.printf("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s\n",
                "ID", "NAME", "LOCATION", "PRICE", "EXP DATE", "MF DATE", "CATEGORY",
                "STOREKEEPER", "RECEIPTDATE");
        for(Product p : listProduct) {            
             System.out.printf("%-16.16s%-16.16s%-16.16s%-16.16s%-16.16s%-16.16s"
                     + "%-16.16s%-16.16s%-16.16s\n",
                p.getId(), p.getName(), p.getLocation(),
                NumberFormat.getCurrencyInstance(Locale.US).format(p.getPrice()),
                p.getExpiryDate(), p.getManufacturedDate(), p.getCategory(),
                p.getStorekeeper(), p.getReceiptDate());
        }
    }
    
    private String displayString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter
    }

}
