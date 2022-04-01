package isep.hal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private ArrayDeque<Transaction> transactions = new ArrayDeque<>();
    public ArrayDeque<Transaction> getTransactions() {
        return transactions;
    }
    // Exo 3
    static List<Block> blockchain = new ArrayList<>();
    public Block add(Transaction newTransaction) {
        Block block = this;
        transactions.addLast(newTransaction);
        if (transactions.size() == 10) {
            for (Transaction transaction: transactions) {
                transaction.pay();
            }
            block = new Block();
            blockchain.add(block);
        }
        return block;
    }

    @Override
    public String toString() {
        return "Block{" +
                "transactions=" + transactions +
                "}\n";
    }
}
