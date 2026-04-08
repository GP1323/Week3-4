import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class P1 {

    // Bubble Sort by fee (stable)
    public static void bubbleSortFee(List<Transaction> transactions) {
        int n = transactions.size();
        int passes = 0, swaps = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break; // early termination
        }
        System.out.println("BubbleSort (fees): " + transactions + " // Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort by fee then timestamp
    public static void insertionSortFeeTimestamp(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 1; i < n; i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            while (j >= 0 && (transactions.get(j).fee > key.fee ||
                    (transactions.get(j).fee == key.fee && transactions.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                transactions.set(j + 1, transactions.get(j));
                j--;
            }
            transactions.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + transactions);
    }

    public static void highFeeOutliers(List<Transaction> transactions) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                System.out.print(t.id + " ");
                found = true;
            }
        }
        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Bubble Sort for small batches
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSortFee(bubbleList);
        highFeeOutliers(bubbleList);

        // Insertion Sort for medium batches
        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSortFeeTimestamp(insertionList);
        highFeeOutliers(insertionList);
    }
}