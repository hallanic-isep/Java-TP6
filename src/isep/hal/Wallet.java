package isep.hal;

public class Wallet {

    public Wallet(String owner, int token) {
        this.owner = owner;
        this.token = token;
        this.isepCoins = 0;
    }

    private String owner;
    public String getOwner() {
        return this.owner;
    }

    private int token;
    public int getToken() {
        return token;
    }

    private int isepCoins;
    public int getIsepCoins() {
        return isepCoins;
    }
    public void setIsepCoins(int isepCoins) {
        this.isepCoins = isepCoins;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "owner='" + owner + '\'' +
                ", token=" + token +
                ", isepCoins=" + isepCoins +
                "}\n";
    }
}
