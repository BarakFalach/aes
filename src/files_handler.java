import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class files_handler {

    /** This function get the path of the message to encrypt
     * split it to blocks of 128 bits and return a
     * 3 dim array [block][rows][cols]
     * */
    public static byte[][][] readFile(String PathMessage) {
        try {
            byte[] messageBytes = Files.readAllBytes(Paths.get(PathMessage));
            if(messageBytes.length%16 != 0)
                throw new IOException();
            int numOfBlocks = messageBytes.length/16;
            byte[][][] messageBlocks = new byte[numOfBlocks][4][4];
            for (int i = 0; i < numOfBlocks; i++){
                for (int j = 0; j < 4; j++){
                    for (int k = 0; k < 4; k++) {
                        messageBlocks[i][j][k] = messageBytes[16 * i + j * 4 + k];
                    }
                }
            }
            return messageBlocks;
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /** This function get the path of the keys
     * Return: Byte array of the keys
     * */
    public static byte[] readKeys(String PathKeys) {
        try {
            byte[] keysBytes = Files.readAllBytes(Paths.get(PathKeys));
            if(keysBytes.length != 32)
                throw new IOException();
            return keysBytes;
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /** This function write the byte array to a file of a given path */
    public static void writeToFile(String Path ,byte[][][] msgToWrite){
        byte[] b = new byte[msgToWrite.length*16];
        for (int i = 0; i<msgToWrite.length;i++){
            for (int j = 0; j<4;j++){
                for (int k = 0; k<4;k++){
                    b[i*16 + j * 4 + k] = msgToWrite[i][j][k];
                }
            }
        }
        try (FileOutputStream fos = new FileOutputStream(Path)) {
            fos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
