import java.util.Arrays;

public class break_encryption {

    public byte[] breakEncryption(String mPath, String cPath, String outPath){
        byte[][] mByteArray = files_handler.readFile(mPath)[0];
        byte[][] cByteArray = files_handler.readFile(cPath)[0];
        byte[] output = new byte[32];
        byte[] keysForSingleByte = new byte[2];

        for (int i=0; i<mByteArray.length;i++){
            for (int j =0 ; j<mByteArray[0].length;j++) {
                keysForSingleByte = findKeyForByte(mByteArray[i][j], cByteArray[i][j]);
                output[i] = keysForSingleByte[0];
                output[i + 16] = keysForSingleByte[1];
            }
        }
        return output;
    }

    public String toBits(final byte val) {
        final StringBuilder result = new StringBuilder();

        for (int i=0; i<8; i++) {
            result.append((int)(val >> (8-(i+1)) & 0x0001));
        }

        return result.toString();
    }

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
