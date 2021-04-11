import java.util.Arrays;

public class Main {

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
