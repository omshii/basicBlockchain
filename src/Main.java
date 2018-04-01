import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Main
{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty=0;

    public static void main(String[] args)
    {
        blockchain.add(new Block("First block", "0"));
        System.out.println("Trying to mine first block...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine second block...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine third block...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("Blockchain validity: "+isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("The blockchain ");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid()
    {
        Block currentBlock;
        Block previousBlock;
        String hashTarget=new String(new char[difficulty]).replace('\0','0');
        for (int i=1; i<blockchain.size(); i++)
        {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!(currentBlock.hash.equals(currentBlock.calculateHash())))
            {
                System.out.println("Current hashes not equal");
                return false;
            }

            if(!(previousBlock.hash.equals(currentBlock.previousHash)))
            {
                System.out.println("Previous hashes not equal");
                return false;
            }

            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget))
            {
                System.out.println("This block has not been mined");
                return false;
            }
        }
        System.out.println("Success");
        return true;
    }
}
