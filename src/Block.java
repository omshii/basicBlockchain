import java.util.Date;

public class Block
{
    public String hash, previousHash;
    private String data;
    private long timeStamp;

    public Block(String data, String previousHash)
    {
        this.data=data;
        this.previousHash=previousHash;
        this.timeStamp=new Date().getTime();
        this.hash=calculateHash();
    }

    public String calculateHash()
    {
        String calculatedHash = StringUtil.encrypt(previousHash + Long.toString(timeStamp) + data);
        return calculatedHash;
    }

    public void mineBlock(int difficulty)
    {
        int nonce=0;
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target));
        {
            nonce++;
            hash=calculateHash();
        }

        System.out.println("Block mined, hash: "+hash);
    }
}
