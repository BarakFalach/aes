public class encrypt_decrypt {

    /** This function encrypt/decrypt the message with the key parameter
     * Return: 3 dim array of the message after change (encrypt/decrypt)*/
    public  byte[][][] encrypt_decryptMessage(byte[][][] messageToEncrypt, byte[][]key, boolean encrypt){
        byte[][][] changedMessage = new byte[messageToEncrypt.length][4][4];
        for (int i=0; i<messageToEncrypt.length; i++){
            byte[][] transMessage = new byte[4][4];
            transpose(messageToEncrypt[i], transMessage);
            changedMessage[i] = addRoundKey(transMessage, key);
        }
        return changedMessage;
    }

    /** This function add round key to a message (Bit Xor)
     * Return: the "new" message after the action with round key */
    public   byte[][] addRoundKey(byte[][] msg, byte[][]key){
        byte[][] roundKeyToReturn = new byte[4][4];
        for(int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                roundKeyToReturn[j][k] = (byte) (msg[j][k] ^ key[j][k]);
            }
        }
        return roundKeyToReturn;
    }

    /** This function get a matrix
     * Return: the transpose matrix*/
    public   void transpose(byte [][]A, byte [][]B)
    {
        int i, j;
        for (i = 0; i < 4; i++){
            for (j = 0; j < 4; j++)
                B[i][j] = A[j][i];
        }
    }

    /** This function get a keys byte array
     * Return: the specific key according to the user will by tje parameter: "keyNum" */
    public byte[][] splitKeys(int keyNum, byte[] keys){
        byte[][] keyToReturn = new byte[4][4];
        for (int i = (keyNum-1)*16; i < (16*keyNum); i++){
            keyToReturn[(int)Math.floor((i%16)/4.0)][i%4] = keys[16*(keyNum-1) + (i%16)];
        }
        return keyToReturn;
    }

    /**
     * rap function for the encrypt / decrypt.
     * @param msgPath - path for the message to encrypt/decrypt
     * @param keyPath - path for the keys
     * @param outPath - path for the output
     * @param encrypt - boolean ? encrypt : decrypt
     */
    public void encrypt_decrypt_helper(String msgPath,String keyPath, String outPath, boolean encrypt){
        byte[][][] messageInMatrix = files_handler.readFile(msgPath);
        byte[] keysBytes = files_handler.readKeys(keyPath);
        byte[][] k1 = splitKeys(1, keysBytes);
        byte[][] k2 = splitKeys(2, keysBytes);
        byte[][][] c1 =  encrypt_decryptMessage(messageInMatrix, k1, encrypt);
        byte[][][] c2 =  encrypt_decryptMessage(c1, k2, true);
        files_handler.writeToFile(outPath, c2);
    }


}
