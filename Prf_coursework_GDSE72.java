import java.util.Scanner;


public class Prf_coursework_GDSE72 {

    static Scanner input = new Scanner(System.in);
    static String user_name = "R";
    static String password = "1234";
    static String[][] suppliers = new String[0][2]; //0->supplierId, 1->supplier name
    static String[] category = new String[0];  //0->category name
    static String[][] items = new String[0][6];
	/*0->supplierId , 1->itemCode ,2->description ,3->unit price ,
	4->Quantity on hand,5->category name*/


    /**
     * purpose->use this method to exit the system.
     */
    public static void ExitTheSystem() {
        clearConsole();
        System.exit(0);
    }


    /**
     * purpose->use this method to log out of a page.
     */
    public static void logOutPage() {
        clearConsole();
        createLoginPage();
    }


    /**
     * purpose->In this option all the Items will be sorted on their unit price , They are made in order from least to most.
     */
    public static void sortingUnitPrice() {

        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - 1; j++) {
                if (Double.parseDouble(items[j][3]) > Double.parseDouble(items[j + 1][3])) {
                    //put j th elemenths to tempory string array
                    //int temp = items[j][2];
                    String[] temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }


    /**
     * purpose->the details of the sorted prices are printed in a table
     */
    public static void RankItemsPerUnitPrice() {

        clearConsole();

        //It checks whether the system already has items or not.
        if (items.length == 0) {
            System.out.println("Oops! It seems that you don't have any items in the system.");
            System.out.print("Do you want to add a new items?(Y/N):");
            String temp = input.next();
            String yesNo = temp.toLowerCase();
            //If you want to add a new item, call the add item method.If not needed, the stock manage method is called.
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addItem();
                }
                stockManage();
            }
            stockManage();
        }

        //If all the details of the items are there, call the sorting unit price  method.
        sortingUnitPrice();

        System.out.printf("+---------------------------------------+%n");
        System.out.printf("|%-10s %-17s %-10s|%n", " ", "RANKED UNIT PRICE", " ");
        System.out.printf("+---------------------------------------+%n");
        System.out.printf("+------+-------+-------------+-------+-----+----------+%n");
        System.out.printf("| %-4s | %-5s | %-11s | %-5s | %-3s | %-8s |%n", "SID", "CODE", "DESC", "PRICE", "QTY", "CAT");
        System.out.printf("+------+-------+-------------+-------+-----+----------+%n");

        //Information related to item codes is printed, from lowest to highest unit price.
        for (int i = 0; i < items.length; i++) {
            System.out.printf("| %-4s | %-5s | %-11s | %-5s | %-3s | %-8s |%n", items[i][0], items[i][1], items[i][2], items[i][3], items[i][4], items[i][5]);
        }

        System.out.printf("+------+-------+-------------+-------+-----+----------+%n");

        //Then if you want to go to the stock manage page, call the stock manage method.
        //If not needed, the create home page method is called.
        System.out.print("Do you want to go stock manage page?(Y/N) ");
        String temp = input.next();
        String yesNo = temp.toLowerCase();
        if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
            if (yesNo.charAt(0) == 'y') {
                clearConsole();
                stockManage();
            }
            createHomePage();
        }
        createHomePage();
    }


    /**
     * purpose->In this option the user can view items grouped by their item category.
     * output-> All data should be printed in tabular format.
     */
    public static void viewItems() {

        clearConsole();

        System.out.printf("+-------------------------------------+%n");
        System.out.printf("|%-10s %-15s %-10s|%n", " ", "VIEW ITEMS", " ");
        System.out.printf("+-------------------------------------+%n");

        //It checks whether the system already has items or not.

        if (items.length == 0) {
            System.out.println("Oops! It seems that you don't have any items in the system.");
            System.out.print("Do you want to add a new items?(Y/N):");
            String temp = input.next();
            String yesNo = temp.toLowerCase();

            //If you want to add a new item, call the add item method.If not needed, the stock manage method is called.
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addItem();
                }
                stockManage();
            }
            stockManage();
        }

        for (String s : category) {

            //Every index in the category array is checked and the categories in each index are printed separately.
            System.out.println(s + ":");
            System.out.printf("+------------+------------+-----------+-----------+------------+%n");
            System.out.printf("|%-12s|%-12s|%-11s|%-11s|%-12s|%n", "SID", "CODE", "DESC", "PRICE", "QTY");
            System.out.printf("+------------+------------+-----------+-----------+------------+%n");

            //It checks whether there are items from the relevant category in the indexes of the item array,
            //and prints the details of those items in a table.
            for (int j = 0; j < items.length; j++) {
                if (s.equals(items[j][5])) {
                    System.out.printf("|%-12s|%-12s|%-11s|%-11s|%-12s|%n", items[j][0], items[j][1], items[j][2], items[j][3], items[j][4]);
                }
            }
            System.out.printf("+------------+------------+-----------+-----------+------------+%n");
        }

        //After printing the tables, you can go to the required method.
        System.out.print("\nDo you want to go stock manage page?(Y/N) ");
        String temp = input.next();
        String yesNo = temp.toLowerCase();
        if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
            if (yesNo.charAt(0) == 'y') {
                clearConsole();
                stockManage();
            }
            createHomePage();
        }
        createHomePage();
    }


    /**
     * purpose->In the next option the user can see items by supplier wise. You need to enter the valid supplier id.
     * The system will show the supplier-wise result.
     */
    public static void searchItemSupplierWise() {

        clearConsole();
        System.out.printf("+-------------------------------------+%n");
        System.out.printf("|%-10s %-14s %-10s|%n", " ", "SEARCH SUPPLIER", " ");
        System.out.printf("+-------------------------------------+%n");

        boolean iterate = true;


        L7:
        while (iterate) {

            System.out.print("Enter Supplier Id: ");
            String id = input.next();

            for (int i = 0; i < suppliers.length; i++) {
                if (id.equals(suppliers[i][0])) {

                    System.out.print("Supplier Name: " + suppliers[i][1] + "\n\n");
                    System.out.printf("+------------+------------+-----------+-----------+------------+%n");
                    System.out.printf("|%-12s|%-12s|%-11s|%-11s|%-12s|%n", "ITEM CODE", "DESCRIPTION", "UNIT PRICE", "QTY ON HAND", "CATEGORY");
                    System.out.printf("+------------+------------+-----------+-----------+------------+%n");
                    for (int j = 0; j < items.length; j++) {
                        if (id.equals(items[j][0])) {
                            System.out.printf("|%-12s|%-12s|%-11s|%-11s|%-12s|%n", items[j][1], items[j][2], items[j][3], items[j][4], items[j][5]);
                        }
                    }

                    System.out.printf("+------------+------------+-----------+-----------+------------+%n");
                    System.out.print("Search successfully! Do you want to another search?(Y/N) ");
                    String temp = input.next();
                    String yesNo = temp.toLowerCase();
                    if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                        if (yesNo.charAt(0) == 'y') {
                            clearConsole();
                            searchItemSupplierWise();
                        }
                        stockManage();
                    }
                    stockManage();
                }
            }
            System.out.println("Can't find supplier id.try again!");
            continue L7;
        }
    }


    /**
     * purpose->The items array is grown and new items are added to it.
     * parameter inputs->the details of the newly added item are passed to the grow items method inside the add item method.
     * output->return new array
     **/
    public static String[][] growItems(String iCode, int supplierNum, int categoryNum, String description, double unitPrice, int qtyOnHand) {

        //when a new item is added,a new array is created by adding 1 to the length of the old items array.
        String[][] newArray = new String[items.length + 1][6];

        //the values in the old array are added to the new array
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                newArray[i][j] = items[i][j];
            }
        }

        //the details of the newly added item are added to the new array.
        newArray[newArray.length - 1][0] = suppliers[supplierNum - 1][0];
        newArray[newArray.length - 1][1] = iCode;
        newArray[newArray.length - 1][2] = description;

        //double convert to string.
        String price = String.valueOf(unitPrice);
        newArray[newArray.length - 1][3] = price;

        //int convert to string.
        String number = String.valueOf(qtyOnHand);
        newArray[newArray.length - 1][4] = number;
        newArray[newArray.length - 1][5] = category[categoryNum - 1];

        return newArray;
    }


    /**
     * purpose->adding new item into the system
     */
    public static void addItem() {

        clearConsole();

        System.out.printf("+--------------------------------+%n");
        System.out.printf("|%-10s %-10s %-10s|%n", " ", "ADD ITEM", " ");
        System.out.printf("+--------------------------------+%n");

        //It checks whether the system already has categories or not.
        if (category.length == 0) {
            System.out.println("Oops! It seems that you don't have any item categories in the system.");
            System.out.print("Do you want to add a new item category?(Y/N):");
            String temp = input.next();
            String yesNo = temp.toLowerCase();

            //If you want to add a new categorys, call the add item category method.If not needed, the stock manage method is called.
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addItemCategory();
                }
                stockManage();
            }
            stockManage();
        }

        //It checks whether the system already has suppliers or not.
        if (suppliers.length == 0) {
            System.out.println("Oops! It seems that you don't have any suppliers in the system.");
            System.out.print("Do you want to add a new supplier?(Y/N):");
            String temp = input.next();
            String yesNo = temp.toLowerCase();

            //If you want to add a new suppliers, call the add supplier method.If not needed, the stock manage method is called.
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addSupplier();
                }
                stockManage();
            }
            stockManage();
        }

        //A new item code is added to the system.
        boolean iterate = true;

        L6:
        while (iterate) {
            System.out.print("Item Code     :");
            String iCode = input.next();
            //Check if the item code is already exists.If it is already in the system, the loop continues to request a new item code.
            for (int i = 0; i < items.length; i++) {
                if (iCode.equals(items[i][1])) {
                    System.out.println("Item code is already exists.please try again.");
                    continue L6;
                }
            }

            //The details of the suppliers are shown in a table.
            System.out.println("\nSuppliers List:");
            System.out.printf("+--------------------------------+%n");
            System.out.printf("| %-3s| %-6s| %-10s|%n", "# ", "SUPPLIER ID", "SUPPLIER NAME");
            System.out.printf("+--------------------------------+%n");

            for (int i = 0; i < suppliers.length; i++) {
                System.out.printf("| %-3s| %-10s | %-12s |%n", (i + 1), suppliers[i][0], suppliers[i][1]);
            }
            System.out.printf("+--------------------------------+%n");

            //It should ask you for a number for a respective supplier who belongs to that Item.
            System.out.print("Enter the supplier number > ");
            int supplierNum = input.nextInt();

            //A table with the details of the categories will be printed
            System.out.println("\nItem Categories:");
            System.out.printf("+-------------------+%n");
            System.out.printf("| %-3s| %-10s|%n", "#", "CATEGORY NAME");
            System.out.printf("+-------------------+%n");

            for (int i = 0; i < category.length; i++) {
                System.out.printf("| %-3s| %-12s |%n", (i + 1), category[i]);
            }

            System.out.printf("+-------------------+%n");

            //the system asks to enter the Item category name as previously.
            System.out.print("Enter the category number > ");
            int categoryNum = input.nextInt();
            input.nextLine();

            //After that you can simply add the item details.
            // But the system should know which items belong to which category and the supplier.
            System.out.print("Description :");
            String description = input.nextLine();

            System.out.print("Unit price :");
            double unitPrice = input.nextDouble();
            input.nextLine();

            System.out.print("Qty on hand :");
            int qtyOnHand = input.nextInt();
            input.nextLine();

            //The details of the newly added item are passed to the GrowItems method.
            items = growItems(iCode, supplierNum, categoryNum, description, unitPrice, qtyOnHand);

            //If you want to add another new item, call the add item method again.If not needed, stock management will be called.
            System.out.print("added successfully! Do you want to add another Item(Y/N)?");
            String temp = input.next();
            String yesNo = temp.toLowerCase();

            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addItem();
                }
                stockManage();
            }
            stockManage();
        }
    }


    /**
     * purpose->Updating the categories included in the system
     */
    public static void updateItemCategory() {

        clearConsole();

        System.out.printf("+-------------------------------------------------------------+%n");
        System.out.printf("|%-19s %-20s %-20s|%n", " ", "UPDATE ITEM CATEGORY", " ");
        System.out.printf("+-------------------------------------------------------------+%n");

        boolean iterate = true;

        L5:
        while (iterate) {
            System.out.print("Enter category name:");
            String categoryName = input.next();

            for (int i = 0; i < category.length; i++) {

                //It checks if there is already a category similar to the entered category in the system.a new category name will be updated.
                if (categoryName.equals(category[i])) {
                    System.out.print("Enter the new category name:");
                    categoryName = input.next();
                    System.out.print("updated successfully! Do you want to update another category name(Y/N)?");

                    //If you want to update another category name,call the UpdateCategory method again.
                    //If not needed,the manage category method is called again.
                    String temp = input.next();
                    String yesNo = temp.toLowerCase();
                    if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                        if (yesNo.charAt(0) == 'y') {
                            clearConsole();
                            updateItemCategory();
                        }
                        manageItemCategory();
                    }
                    stockManage();
                }
            }

            //If the entered category name is not mentioned in the system, you will be asked to input another category name again.
            System.out.println("Can't find category name. try again!\n");
            continue L5;
        }
    }


    /**
     * purpose->Creating a new array one less than the length of the old array, adding the other categories
     * of the old array ,except the category name that needs to be deleted to the new array.
     * parameter inputs->The category name to be deleted is passed to this method
     * outputs->new array is return.
     */
    public static String[] reduceCategory(String categoryName) {

        //One is subtracted from the length of the old array and added to the new array
        String[] newArray = new String[category.length - 1];

        //The category that needs to be deleted will be skipped and the other category names will be added to a new array.
        for (int i = 0, k = 0; i < category.length; i++) {
            if (category[i].equals(categoryName)) {
                continue;
            }
            newArray[k++] = category[i];
        }
        return newArray;
    }


    /**
     * purpose->delete categories
     */
    public static void deleteItemCategory() {

        clearConsole();

        System.out.printf("+-------------------------------------------------------------+%n");
        System.out.printf("|%-19s %-20s %-20s|%n", " ", "DELETE ITEM CATEGORY", " ");
        System.out.printf("+-------------------------------------------------------------+%n");

        boolean iterate = true;

        L4:
        while (iterate) {

            System.out.print("Enter category name:");
            String categoryName = input.next();

            for (int i = 0; i < category.length; i++) {

                //It checks whether there are categories in the system similar to the inputted name.
                //If there is a similar one, it will be deleted
                if (categoryName.equals(category[i])) {

                    //The inputted category name is passed to the Reduce category method.
                    category = reduceCategory(categoryName);
                    System.out.print("Deleted successfully! Do you want to delete another?(Y/N)");
                    String temp = input.next();
                    String yesNo = temp.toLowerCase();

                    //If you want to delete another category, call the DeleteCategory method again.
                    //If not needed, the manage item category method is called.
                    if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                        if (yesNo.charAt(0) == 'y') {
                            clearConsole();
                            deleteItemCategory();
                        }
                        manageItemCategory();
                    }
                    manageItemCategory();
                }
            }
            //If there is no match to the inputted category name, another input is requested. The loop continues.
            System.out.println("Can't find category name. try again!\n");
            continue L4;
        }
    }


    /**
     * purpose->When a new category is added, the length of the array is increased by one and added to a new array.
     * The values ​​of the old array are added to the new array, and return new array.
     * output->return new array.
     */
    public static String[] growCategory() {

        //A new array is made by adding one to the length of the old array
        String[] newArray = new String[category.length + 1];

        //The categories of the old array are added to the new array
        for (int i = 0; i < category.length; i++) {
            newArray[i] = category[i];
        }
        return newArray;
    }


    /**
     * purpose->add new item categories.
     */
    public static void addItemCategory() {

        clearConsole();

        System.out.printf("+-------------------------------------------------------------+%n");
        System.out.printf("|%-19s %-20s %-20s|%n", " ", "ADD ITEM CATEGORY", " ");
        System.out.printf("+-------------------------------------------------------------+%n");
        boolean iterate = true;

        L6:
        while (iterate) {
            System.out.print("Enter the new item category: ");
            String addCategory = input.next();
            input.nextLine();
            //It will check if the added item category already exists.If it already exists, it will tell you to enter another category.
            for (int i = 0; i < category.length; i++) {
                if (addCategory.equals(category[i])) {
                    System.out.println("Already exists. try another category!\n");
                    continue L6;
                }
            }
            //After inputting a new category, the GrowCategory method is called.
            category = growCategory();

            //The newly input category is added to the last index of the grown array.
            category[category.length - 1] = addCategory;

            //If you want to add a new category, call the add item category method.
            //If not needed, the manage item category method is called.
            System.out.print("added successfully! Do you want to add another category(Y/N)?");
            String temp = input.next();
            String yesNo = temp.toLowerCase();
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addItemCategory();
                }
                manageItemCategory();
            }
            manageItemCategory();
        }
    }


    /**
     * purpose->the user needs to Manage Item Categories before the manage items.
     * output->it will be taken to the required method.
     */
    public static void manageItemCategory() {

        clearConsole();

        System.out.printf("+-------------------------------------------------------------+%n");
        System.out.printf("|%-19s %-20s %-20s|%n", " ", "MANAGE ITEM CATEGORY", " ");
        System.out.printf("+-------------------------------------------------------------+%n");

        System.out.print("[1] Add New Item Category\t\t");
        System.out.println("[2] Delete Item Category");
        System.out.print("[3] Update Item Category\t\t");
        System.out.println("[4] Stock Management\n");
        System.out.print("Enter an option to continue > ");

        String option = input.next();

        switch (option) {
            case "1" -> addItemCategory();
            case "2" -> deleteItemCategory();
            case "3" -> updateItemCategory();
            case "4" -> stockManage();
            default -> {
                clearConsole();
                manageItemCategory();
            }
        }
    }


    /**
     * purpose->the main business process is stock management. So if the user wants to manage the
     * stock who needs to navigate this menu.
     */
    public static void stockManage() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-15s|%n", " ", "STOCK MANAGEMENT", " ");
        System.out.printf("+------------------------------------------------+\n%n");
        System.out.print("[1] Manage Item Categories\t\t");
        System.out.println("[2] Add Item");
        System.out.print("[3] Get Items Supplier Wise\t\t");
        System.out.println("[4] View Items");
        System.out.print("[5] Rank Items Per Unit Price\t\t");
        System.out.println("[6] Home Page\n");
        System.out.print("Enter an option to continue > ");
        String option = input.next();

        switch (option) {
            case "1" -> manageItemCategory();
            case "2" -> addItem();
            case "3" -> searchItemSupplierWise();
            case "4" -> viewItems();
            case "5" -> RankItemsPerUnitPrice();
            default -> {
                clearConsole();
                createHomePage();
            }
        }
    }

    /**
     * purpose->Method to grow the array by adding an extra row for a new supplier
     */
    public static String[][] growArray() {

        // Create a new array with one more row than the current suppliers array
        String[][] newArray = new String[suppliers.length + 1][2];

        // Copy the existing supplier information to the new array
        for (int i = 0; i < suppliers.length; i++) {
            for (int j = 0; j < 2; j++) {
                newArray[i][j] = suppliers[i][j];
            }
        }

        // Return the newly created array with additional space
        return newArray;
    }


    /**
     * purpose->Method to add a new supplier to the system.
     * The user is prompted to enter a supplier ID and supplier name.
     * If a new supplier is added successfully, a prompt asks whether to add another supplier or go back to the supplier management page.
     */
    public static void addSupplier() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-16s %-10s %-18s|%n", " ", "ADD SUPPLIER", " ");
        System.out.printf("+------------------------------------------------+\n%n");

        boolean iterate = true;

        L1:
        while (iterate) {

            // Prompt the user for supplier ID
            System.out.print("Supplier Id:");
            String id = input.next();
            input.nextLine();

            // Check if the supplier ID already exists
            for (int i = 0; i < suppliers.length; i++) {
                if (suppliers[i][0].equals(id)) {
                    System.out.println("already exists. try another supplier id!\n");
                    continue L1;
                }
            }

            // Prompt the user for supplier name
            System.out.print("Supplier Name:");
            String name = input.nextLine();

            // Grow the suppliers array to add the new supplier
            suppliers = growArray();

            // Add the new supplier details to the last row of the array
            suppliers[suppliers.length - 1][0] = id;
            suppliers[suppliers.length - 1][1] = name;

            // Notify the user of successful addition
            System.out.print("added successfully! Do you want to add another supplier(Y/N)? ");
            String temp = input.next();
            String yesNo = temp.toLowerCase();

            // Check the user's response
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {

                    // If the user wants to add another supplier, clear the console and call addSupplier again
                    clearConsole();
                    addSupplier();
                }
                // If the user wants to go back to the supplier management page
                supplierManage();
            }
            // If none of the above conditions are met, go back to the homepage
            createHomePage();
        }
    }


    public static void updateSupplier() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-16s|%n", " ", "UPDATE SUPPLIER", " ");
        System.out.printf("+------------------------------------------------+\n%n");

        boolean iterate = true;

        L1:
        while (iterate) {

            // Prompt the user for supplier ID
            System.out.print("Supplier Id:");
            String id = input.next();

            // Iterate through the suppliers array to find the supplier with the given ID
            for (int i = 0; i < suppliers.length; i++) {
                if (suppliers[i][0].equals(id)) {

                    // If the supplier is found, display the current supplier name
                    System.out.println("Supplier name:" + suppliers[i][1]);

                    // Prompt the user to enter the new supplier name
                    System.out.print("Enter the new supplier name:");
                    String name = input.next();

                    // Update the supplier name in the array
                    suppliers[i][1] = name;

                    // Notify the user of the successful update
                    System.out.print("updated successfully! Do you want to update another supplier? (Y/N):");
                    String temp = input.next();
                    String yesNo = temp.toLowerCase();

                    if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                        if (yesNo.charAt(0) == 'y') {

                            // If the user wants to update another supplier, clear the console and call updateSupplier again
                            clearConsole();
                            updateSupplier();
                        }
                        // If the user wants to go back to the supplier management page
                        supplierManage();
                    }
                    createHomePage();
                }
            }
            // If the supplier ID is not found, inform the user and prompt to try again
            System.out.println("Can't find supplier id. try again!\n");

            // Continue with the outer loop, skipping the rest of this iteration
            continue L1;
        }
    }


    /**
     * purpose->Reduces the array by removing the element at the specified index.
     * parameters->oldArray-The original array of suppliers,index-The index of the supplier to be removed.
     * output->A new array with the specified supplier removed.
     */
    public static String[][] reduceArray(String[][] oldArray, int index) {
        String[][] newArray = new String[oldArray.length - 1][2];
        for (int i = 0, k = 0; i < oldArray.length; i++) {
            if (i == index) {
                continue; // Skip the index to be removed.
            }
            newArray[k++] = oldArray[i]; // Copy elements to new array.
        }
        return newArray;
    }


    /**
     * purpose->Manages the deletion of suppliers from the suppliers array.
     * Prompts the user to input the supplier ID and confirms deletion.
     */
    public static void deleteSupplier() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-16s|%n", " ", "DELETE SUPPLIER", " ");
        System.out.printf("+------------------------------------------------+\n%n");
        boolean iterate = true;

        L1:
        while (iterate) {
            System.out.print("Supplier Id:");
            String id = input.next();
            for (int i = 0; i < suppliers.length; i++) {
                if (suppliers[i][0].equals(id)) {
                    suppliers = reduceArray(suppliers, i);
                    System.out.print("deleted successfully! Do you want to delete another?(Y/N): ");
                    String temp = input.next();
                    String yesNo = temp.toLowerCase();
                    if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                        if (yesNo.charAt(0) == 'y') {
                            clearConsole();
                            deleteSupplier();
                        }
                        supplierManage();
                    }
                    createHomePage();
                }
            }
            System.out.println("can't find supplier id. try again!\n");
            continue L1;
        }
    }


    public static void viewSuppliers() {

        clearConsole();

        //It checks whether the system already has suppliers or not
        if (suppliers.length == 0) {
            System.out.println("Oops! It seems that you don't have any suppliers in the system.");
            System.out.print("Do you want to add a new supplier?(Y/N):");
            String temp = input.next();
            String yesNo = temp.toLowerCase();
            if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                if (yesNo.charAt(0) == 'y') {
                    clearConsole();
                    addSupplier();
                }
                supplierManage();
            }
            supplierManage();
        }

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-17s|%n", " ", "VIEW SUPPLIERS", " ");
        System.out.printf("+------------------------------------------------+\n%n");
        System.out.printf("-----------------------------%n");
        System.out.printf("| %-6s| %-10s|%n", "SUPPLIER ID", "SUPPLIER NAME");
        System.out.printf("-----------------------------%n");

        for (int i = 0; i < suppliers.length; i++) {
            System.out.printf("| %-10s | %-12s |%n", suppliers[i][0], suppliers[i][1]);
        }

        System.out.printf("-----------------------------%n");

        System.out.print("Do you want to go supplier manage page(Y/N)");
        String temp = input.next();
        String yesNo = temp.toLowerCase();

        if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
            if (yesNo.charAt(0) == 'y') {
                supplierManage();
            }
            createHomePage();
        }
    }


    public static void searchSupplier() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-16s|%n", " ", "SEARCH SUPPLIER", " ");
        System.out.printf("+------------------------------------------------+\n%n");
        boolean iterate = true;

        L2:
        while (iterate) {
            System.out.print("Supplier Id   :");
            String id = input.next();

            for (int i = 0; i < suppliers.length; i++) {
                if (id.equals(suppliers[i][0])) {
                    System.out.println("Supplier Name :" + suppliers[i][1]);
                    System.out.print("added successfully! Do you want to add another find(Y/N)?");

                    String temp = input.next();
                    String yesNo = temp.toLowerCase();
                    if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
                        if (yesNo.charAt(0) == 'y') {
                            clearConsole();
                            searchSupplier();
                        }
                        supplierManage();
                    }
                    supplierManage();
                }
            }
            System.out.println("Can't find supplier id. try again!\n");
            continue L2;
        }
    }


    public static void supplierManage() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-16s|%n", " ", "SUPPLIER MANAGE", " ");
        System.out.printf("+------------------------------------------------+\n%n");
        System.out.print("[1] Add supplier\t\t");
        System.out.println("[2] Update supplier");
        System.out.print("[3] Delete supplier\t\t");
        System.out.println("[4] View suppliers");
        System.out.print("[5] Search supplier\t\t");
        System.out.println("[6] Home page\n");
        System.out.print("Enter an option to continue >");
        String option = input.next();

        switch (option) {
            case "1" -> addSupplier();
            case "2" -> updateSupplier();
            case "3" -> deleteSupplier();
            case "4" -> viewSuppliers();
            case "5" -> searchSupplier();
            case "6" -> createHomePage();
            default -> {
                clearConsole();
                supplierManage();
            }
        }
    }


    public static void credentialManage() {

        clearConsole();

        System.out.printf("+------------------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-14s|%n", " ", "CREDENTIAL MANAGE", " ");
        System.out.printf("+------------------------------------------------+\n%n");
        System.out.print("Please enter the user name to verify it's you:");
        String checkUserName = input.next();

        while (!checkUserName.equals(user_name)) {
            System.out.println("invalid user name.Try again!\n");
            System.out.print("Please enter the user name to verify it's you:");
            checkUserName = input.next();
        }
        System.out.println("Hey" + " " + user_name);
        System.out.print("Enter your current password:");
        String checkPassword = input.next();

        while (!checkPassword.equals(password)) {
            System.out.println("incorrect password. try again!\n");
            System.out.print("Enter your current password:");
            checkPassword = input.next();
        }
        System.out.print("Enter your new password:");
        password = input.next();
        System.out.print("\nPassword change successfully! Do you want to go home page (Y/N):");
        String temp = input.next();
        String yesNo = temp.toLowerCase();

        if (yesNo.charAt(0) == 'n' || yesNo.charAt(0) == 'y') {
            if (yesNo.charAt(0) == 'y') {
                clearConsole();
                createHomePage();
            }
            clearConsole();
            credentialManage();
        }
        createHomePage();
    }


    /**
     * purpose -> create Home Page of the Stock Management System
     * output->it will be taken to the required method.
     */
    public static void createHomePage() {

        clearConsole();

        System.out.printf("+------------------------------------------------------------------------+%n");
        System.out.printf("|%-15s %-39s %-16s|%n", " ", "WELCOME TO IJSE STOCK MANAGEMENT SYSTEM", " ");
        System.out.printf("+------------------------------------------------------------------------+\n%n");
        System.out.print("[1].Change the credentials\t");
        System.out.println("[2].Supplier manage");
        System.out.print("[3].Stock manage\t\t");
        System.out.println("[4].Log out");
        System.out.println("[5].Exit the system\n");
        System.out.print("Enter an option to continue >");
        String option = input.next();
        System.out.println();

        switch (option) {
            case "1" -> credentialManage();
            case "2" -> supplierManage();
            case "3" -> stockManage();
            case "4" -> logOutPage();
            case "5" -> ExitTheSystem();
            default -> {
                clearConsole();
                createHomePage();
            }
        }
    }


    /**
     * purpose -> input user name & password,check user name & password
     */
    public static void createLoginPage() {

        clearConsole();

        System.out.printf("+----------------------------------------+%n");
        System.out.printf("|%-15s %-10s %-13s|%n", " ", "LOGIN PAGE", " ");
        System.out.printf("+----------------------------------------+\n%n");
        System.out.print("User name:");
        String user_name1 = input.next();

        //Check if the username is valid.
        //It will repeatedly ask you to input the user name until a valid user name is given.
        while (!user_name.equals(user_name1)) {
            System.out.println("User name is invalid.please try again.");
            System.out.print("\nUser name:");
            user_name1 = input.next();
            System.out.println();
        }
        System.out.print("\nPassword:");
        String password1 = input.next();

        //Check if the password is valid.
        //It will repeatedly ask you to input the password until a valid password is given.
        while (!password.equals(password1)) {
            System.out.println("Password is incorrect.Please try again.\n");
            System.out.print("Password:");
            password1 = input.next();
        }

        //After giving a valid username and password,the create home page method is called.
        clearConsole();
        createHomePage();
    }


    /**
     * purpose -> clear console
     */
    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            //handle the exception
            System.err.println(e.getMessage());
        }
    }


    public static void main(String args[]) {
        createLoginPage(); // create login page
    }
}




