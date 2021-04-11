import java.util.Arrays;

public class break_encryption {

    /**
     * accept m and c, extract the desired keys.
     * @param mPath message path
     * @param cPath cypher path
     * @param outPath output path
     */
    public void breakEncryption(String mPath, String cPath, String outPath){
        byte[][] mByteArray = files_handler.readFile(mPath)[0];
        byte[][] cByteArray = files_handler.readFile(cPath)[0];
        byte[][] kByteArray = new byte[4][4];
        byte[] keysForSingleByte = new byte[2];

        for (int i=0; i<mByteArray.length;i++){
            for (int j =0 ; j<mByteArray[0].length;j++) {
                keysForSingleByte = findKeyForByte(mByteArray[i][j], cByteArray[i][j]);
                kByteArray[j][i] = keysForSingleByte[0];
            }
        }
        byte[] output = new byte[32];
        for (int i=0; i<kByteArray.length;i++){
            System.arraycopy(kByteArray[i], 0, output, i * 4, kByteArray[0].length);
        }


        files_handler.writeKey(outPath,output);

    }

    /**
     *
     * @param val - base 10 byte.
     * @return string that represent val as binary sequence.
     */
    public String toBits(final byte val) {
        final StringBuilder result = new StringBuilder();

        for (int i=0; i<8; i++) {
            result.append((int)(val >> (8-(i+1)) & 0x0001));
        }

        return result.toString();
    }

    /**
     * extact key from single byte of msg and single byte from cypher
     * @param mByte - msg byte
     * @param cByte - cypher byte
     * @return return the byte for each key.
     */
    public byte[] findKeyForByte(byte mByte, byte cByte){
        byte[] keys = new byte[2];
        char[] mAsBits = toBits(mByte).toCharArray();
        char[] cAsBits = toBits(cByte).toCharArray();
        char[] key1 = new char[8];
        char[] key2 = new char[8];
        for (int i =0; i<8;i++){
            if (mAsBits[i] == cAsBits[i]) {
                key1[i] = '0';
                key2[i] = '0';
            }
            else {
                key1[i] = '1';
                key2[i] = '0';
            }
        }

        keys[0] = (byte)Integer.parseInt(new String(key1),2);
        keys[1] = (byte)Integer.parseInt(new String(key2),2);
        return keys;

    }


}
