import java.util.ArrayList;
import java.util.List;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + "(" + riskScore + ")";
    }
}

public class P2 {

    // Bubble Sort ascending by riskScore
    public static void bubbleSortAscending(List<Client> clients) {
        int n = clients.size();
        int totalSwaps = 0;

        System.out.println("Bubble Sort Steps:");
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients.get(j).riskScore > clients.get(j + 1).riskScore) {
                    Client temp = clients.get(j);
                    clients.set(j, clients.get(j + 1));
                    clients.set(j + 1, temp);
                    swapped = true;
                    totalSwaps++;
                    System.out.println("Swap: " + clients);
                }
            }
            if (!swapped) break; // early termination
        }
        System.out.println("Bubble sorted (asc): " + clients + " // Total swaps: " + totalSwaps);
    }

    // Insertion Sort descending by riskScore, tie-break by accountBalance ascending
    public static void insertionSortDescRisk(List<Client> clients) {
        int n = clients.size();

        for (int i = 1; i < n; i++) {
            Client key = clients.get(i);
            int j = i - 1;
            while (j >= 0 && (clients.get(j).riskScore < key.riskScore ||
                    (clients.get(j).riskScore == key.riskScore &&
                            clients.get(j).accountBalance > key.accountBalance))) {
                clients.set(j + 1, clients.get(j));
                j--;
            }
            clients.set(j + 1, key);
        }
        System.out.println("Insertion Sort (desc risk + asc balance): " + clients);
    }

    // Get top N high-risk clients
    public static void topHighRiskClients(List<Client> clients, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < Math.min(topN, clients.size()); i++) {
            Client c = clients.get(i);
            System.out.print(c.name + "(" + c.riskScore + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("clientC", 80, 5000));
        clients.add(new Client("clientA", 20, 10000));
        clients.add(new Client("clientB", 50, 7500));

        // Bubble Sort ascending
        List<Client> bubbleList = new ArrayList<>(clients);
        bubbleSortAscending(bubbleList);

        // Insertion Sort descending
        List<Client> insertionList = new ArrayList<>(clients);
        insertionSortDescRisk(insertionList);

        // Top 3 high-risk clients
        topHighRiskClients(insertionList, 3);
    }
}