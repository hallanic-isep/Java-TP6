package isep.hal;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Exo 2
        String[] eleves = {"Maelys","Ryann","Ethan","Côme","Quentin"};
        Map<Integer,Wallet> dico = creeDico(eleves);
        System.out.println("=== Liste des portefeuilles ===");
        System.out.println(dico);
        // Exo 3
        simulation(dico);
        System.out.println("=== Contenu de la blockchain ===");
        System.out.println(Block.blockchain);
        // Exo 4
        System.out.println("=== Transactions des 2 derniers blocs ===");
        afficheDerniersBlocs(2);
        System.out.println("=== Nombre de transactions par portefeuilles ===");
        afficheActivite(dico);
        System.out.println("=== Contenu de la blockchain ===");
        afficheAdjacence(dico);
    }

    // Exo 2
    private static Map<Integer, Wallet> creeDico(String[] eleves) {
        Map<Integer,Wallet> dicoLocal = new HashMap<>();
        for (String eleve: eleves) {
            int token = (int)(Math.random() * 1000000);
            dicoLocal.put(token, new Wallet(eleve, token));
            dicoLocal.get(token).setIsepCoins(10);
        }
        return dicoLocal;
    }
    // Exo 3
    private static void simulation(Map<Integer,Wallet> wallets) {
        Block block = new Block();
        Block.blockchain.add(block);
        for (int i = 0; i < 55; i++) {
            int size = wallets.keySet().size();
            int source = (int) wallets.keySet()
                    .toArray()[ (int)(Math.random() * size) ];
            int target = (int) wallets.keySet()
                    .toArray()[ (int)(Math.random() * size) ];
            int coins = (int)(Math.random() * 10);
            Transaction transaction= new Transaction
                    (wallets.get(source), wallets.get(target), coins, false);
            block = block.add(transaction);
        }
    }

    // Exo 4

    private static void afficheDerniersBlocs(int nbBlocks) {
        ListIterator<Block> blockIter
                = Block.blockchain.listIterator(Block.blockchain.size());
        for (int i = 0; i < nbBlocks && blockIter.hasPrevious(); i++)
        {
            Iterator<Transaction> trIter
                = blockIter.previous().getTransactions().descendingIterator();
            while (trIter.hasNext()) {
                System.out.print(trIter.next());
            }
            System.out.print("\n##################");
        }
        System.out.println();
    }

    private static void afficheActivite(Map<Integer,Wallet> wallets) {
        Map<Integer,Integer> activite = new HashMap<>();
        for (Block block: Block.blockchain) {
            for (Transaction transaction: block.getTransactions()) {
                int[] tokens = {transaction.getOriginWalet().getToken()
                        , transaction.getDestinationWalet().getToken()};
                for (int token: tokens) {
                    if (activite.containsKey(token)) {
                        activite.put(token, activite.get(token) + 1);
                    } else {
                        activite.put(token, 1);
                    }
                }
            }
        }
        for (int token: activite.keySet()) {
            System.out.println(
                wallets.get(token).getOwner()
                + "(" + wallets.get(token).getIsepCoins() + ")"
                + "=>" + activite.get(token)
            );
        }
    }

    private static void afficheAdjacence(Map<Integer, Wallet> wallets) {
        int size = wallets.size();
        int[][] adja = new int[size][size];
        Map<Integer, Integer> indexMap = new HashMap<>();
        int index = 0;
        for (int token : wallets.keySet()) {
            indexMap.put(token, index++);
        }
        for (Block block : Block.blockchain) {
            for (Transaction transaction : block.getTransactions()) {
                int li = indexMap.get(transaction.getOriginWalet().getToken());
                int co = indexMap.get(transaction.getDestinationWalet().getToken());
                adja[li][co]++;
            }
        }
        Object[] tokenIndex = wallets.keySet().toArray();
        for (int li = 0; li < size; li++) {
            for (int co = 0; co < size; co++) {
                System.out.printf("%2d ", adja[li][co]);
            }
            System.out.println(wallets.get(tokenIndex[li]).getOwner());
        }
    }
}
