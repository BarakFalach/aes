import java.util.Arrays;

public class Main {



    public static byte[] readFromFile(String mPath){
        int[] a;
        if (mPath.equals("a"))
            a = new int[]{13,12,40,12,1,43,2,10} ;
        else if(mPath.equals("b"))
            a = new int[]{13,12,12,1,0,43,2,53} ;
        else{
            a = new int[]{100,12,12,12,1,4,2,31} ;
        }

        byte[] res = new byte[8];
        for (int i =0; i<8; i++){
            res[i]= (byte) a[i];
        }
        return res;

    }

    /** This function get a keys byte array
     * Return: the specific key according to the user will by tje parameter: "keyNum" */
    private static byte[][] splitKeys(int keyNum, byte[] keys){
        byte[][] keyToReturn = new byte[4][4];
        for (int i = (keyNum-1)*16; i < (16*keyNum); i++){
            keyToReturn[(int)Math.floor((i%16)/4.0)][i%4] = keys[16*(keyNum-1) + (i%16)];
        }
        return keyToReturn;
    }

//    public static void asiMain(){
//        byte[][][] messageInMatrix = files_handler.readFile("C:\\message_long");
//        byte[] keysBytes = files_handler.readKeys("C:\\key_long");
//        byte[][] k1 = splitKeys(1, keysBytes);
//        byte[][] k2 = splitKeys(2, keysBytes);
//        byte[][][] c1 =  encrypt_decrypt.encrypt_decryptMessage(messageInMatrix, k1, true);
//        byte[][][] c2 =  encrypt_decrypt.encrypt_decryptMessage(c1, k2, true);
//        files_handler.writeToFile("C:\\check2", c2);
//
//    }

    public static void main(String[] args) {
        break_encryption breakEncryption = new break_encryption();
        encrypt_decrypt encrypt_decrypt = new encrypt_decrypt();
        String inputPath;
        String keyPath;
        String outPath;
        if (args[0].equals("-d") || args[0].equals("-e")){
            try{
                inputPath = args[Arrays.asList(args).indexOf("-k")+1];
                keyPath = args[Arrays.asList(args).indexOf("-i")+1];
                outPath = args[Arrays.asList(args).indexOf("-o")+1];
                if (args[0].equals("-d")) {
                    encrypt_decrypt.encrypt_decrypt_helper(inputPath,keyPath,outPath,true);
                    System.out.println("decrypt -k -i -o " + inputPath+" "+ keyPath+" "+ outPath+" ");}
                else
                    {
                        encrypt_decrypt.encrypt_decrypt_helper(inputPath,keyPath,outPath,false);
                        System.out.println("encrypt -k -i -o " + inputPath+" "+ keyPath+" "+ outPath+" ");
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(args[0].equals("-b")){
            String textPath;
            String cypherPath;
            try{
                textPath = args[Arrays.asList(args).indexOf("-m")+1];
                cypherPath = args[Arrays.asList(args).indexOf("-c")+1];
                outPath = args[Arrays.asList(args).indexOf("-o")+1];
                breakEncryption.breakEncryption(textPath,cypherPath,outPath);
                System.out.println("break encryption -m -v -o " + textPath+" "+ cypherPath+" "+ outPath+" ");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            System.out.println("The First flag must be -e or -d or -b");
//
    }
}
