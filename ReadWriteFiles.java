import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ReadWriteFiles<T> {
    HandlingBinary HandleBinary;
    CompressionAlgorithms comAlgo;
    protected ArrayList<T> data;
    public <T> ReadWriteFiles(){
    }
    final protected String readTextFromFileToCompress(String filePath){
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);
            String line, text = "";
            while ((line = reader.readLine()) != null) {
                text += line;
            }
           return text;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    final protected String writeDecompressedText(String filePath, String Text){
        try {
              // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(Text);
            writer.close();
            return null;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    protected String ReadFromCompressedFile(String fileName) 
    {
       String binary = "";
       StringBuilder binaryStringData = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        for (byte b : bytes) {
            binary = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            binaryStringData.append(binary);
      } 
    }
        catch (IOException e) {
            return e.getMessage();
        }
        return binaryStringData.toString();
    }
    abstract protected void writeToFileCompressedText(String fileName,  ArrayList<byte[]> dataBinaryStream);
    
    // convert Text From Decompress(read --> filePathR) To Compressed file (write in it ->> filePathW)
    abstract void convertTextFromDecompressToCompress(String filePathR, String filePathW);
    // convert Text From compress(read --> filePathR) To Decompressed file (write in it ->> filePathW)
    abstract void convertTextFromCompressToDecompress(String filePathR, String filePathW);
}
