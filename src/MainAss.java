//public class MainAss {
//
//    public static void main(String[] args) {
//        byte[][][] messageInMatrix = files_handler.readFile("C:\\message_long");
//        byte[] keysBytes = files_handler.readKeys("C:\\key_long");
//        byte[][] k1 = splitKeys(1, keysBytes);
//        byte[][] k2 = splitKeys(2, keysBytes);
//        byte[][][] c1 =  encrypt_decrypt.encrypt_decryptMessage(messageInMatrix, k1, true);
//        byte[][][] c2 =  encrypt_decrypt.encrypt_decryptMessage(c1, k2, true);
//        files_handler.writeToFile("C:\\check2", c2);
//
//        //byte[] check1 = check("C:\\check2");
//        //byte[] check2 = check("C:\\cipher_long");
//    }
//
////    public static byte[] check(String Path) {
////        try {
////            byte[] toCheck = Files.readAllBytes(Paths.get(Path));
////            return toCheck;
////        } catch (IOException e){
////            e.printStackTrace();
////            System.exit(-1);
////        }
////        return null;
////    }
//
//    /** This function get a keys byte array
//     * Return: the specific key according to the user will by tje parameter: "keyNum" */
//    private static byte[][] splitKeys(int keyNum, byte[] keys){
//        byte[][] keyToReturn = new byte[4][4];
//        for (int i = (keyNum-1)*16; i < (16*keyNum); i++){
//            keyToReturn[(int)Math.floor((i%16)/4.0)][i%4] = keys[16*(keyNum-1) + (i%16)];
//        }
//        return keyToReturn;
//    }
//}
