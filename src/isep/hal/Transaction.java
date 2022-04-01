package isep.hal;

public class Transaction {

    public Transaction(
            Wallet originWalet,
            Wallet destinationWalet,
            int isepCoins,
            boolean payed
    ) {
        this.originWalet = originWalet;
        this.destinationWalet = destinationWalet;
        this.isepCoins = isepCoins;
        this.payed = payed;
    }

    private Wallet originWalet;
    public Wallet getOriginWalet() {
        return originWalet;
    }

    private Wallet destinationWalet;
    public Wallet getDestinationWalet() {
        return destinationWalet;
    }

    private int isepCoins;
    public int getIsepCoins() {
        return isepCoins;
    }

    private boolean payed;
    public boolean isPayed() {
        return payed;
    }

    // Exo 3
    public void pay() {
        if ((originWalet != destinationWalet)
                && (originWalet.getIsepCoins() - isepCoins >= 0)) {
            originWalet.setIsepCoins
                    (originWalet.getIsepCoins() - isepCoins);
            destinationWalet.setIsepCoins
                    (destinationWalet.getIsepCoins() + isepCoins);
            this.payed = true;
        }
    }

    @Override
    public String toString() {
        return "\n" +
                "" + originWalet.getOwner() + "(" + originWalet.getIsepCoins() + ")" +
                "=>" +
                "" + destinationWalet.getOwner() + "(" + destinationWalet.getIsepCoins() + ")" +
                ", isepCoins=" + isepCoins +
                ", payed=" + payed +
                "";
    }
}
