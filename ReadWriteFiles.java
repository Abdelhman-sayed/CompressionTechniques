import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ReadWriteFiles<T> {
    HandlingBinary HandleBinary;
    CompressionAlgorithms comAlgo;
    protected ArrayList<T> data;
    final protected String readTextFromFileToCompress(String filePath){
        try {
            // StringBuilder text = new StringBuilder();
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
    abstract protected void writeToFileCompressedText(String fileName);
    // convert Text From Decompress(read --> filePathR) To Compressed file (write in it ->> filePathW)
    abstract void convertTextFromDecompressToCompress(String filePathR, String filePathW);
    // convert Text From compress(read --> filePathR) To Decompressed file (write in it ->> filePathW)
    abstract void convertTextFromCompressToDecompress(String filePathR, String filePathW);
}
