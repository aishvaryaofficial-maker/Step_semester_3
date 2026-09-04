class FoodOrder {
    String studentName;
    String dishName;
    boolean delivered = false;

    FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() ||
                dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order");
        }

        this.studentName = studentName;
        this.dishName = dishName;
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered");
        } else {
            System.out.println("Order already delivered");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}