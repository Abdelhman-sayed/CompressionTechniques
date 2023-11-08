import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFilesLzwAlgo<T> extends ReadWriteFiles<T>{
    public ReadWriteFilesLzwAlgo(CompressionAlgorithms c, HandlingBinary<T> b) {
        comAlgo = c;
        HandleBinary = b;
        data = new ArrayList<>();
    }
    @Override
    protected
    void writeToFileCompressedText(String fileName, String compressedBinaryString) {
         try{
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < compressedBinaryString.length(); i+=8) {
                String part = compressedBinaryString.substring(i, i+8);
                byte b = (byte)Integer.parseInt(part, 2);
                writer.write(b);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    @Override
    void convertTextFromDecompressToCompress(String filePathR, String filePathW) {
        // step1 : read from file(string--> decompress) -->about call this method ==> readTextFromFileToCompress(filePathR)
        // step2 : store compressed(in arrayList --> data) that contains compressed data when call method runCompress
        // step3 : write compressed text to file(filePathW)
        data = comAlgo.compress(readTextFromFileToCompress(filePathR));
        // to git max bits to define overhead
        int numBits = Integer.toBinaryString(HandleBinary.maxElementsInIndexFromAnyTag(data)).length();
        // git binary text to store it in bin file to decrease storage --> HandleBinary.getBinary(data, numBits);
        // HandleBinary.convertBinaryStringToBytes(HandleBinary.getBinary(data, numBits));
        writeToFileCompressedText(filePathW, HandleBinary.getBinary(data, numBits));
    }
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
        // step1 : read from file(compressed) -->about call this method ==> readTextFromFileToDecompress(filePathR)
        // step2 : store decompressed(string) when call method runDecompress
        // step3 : write deCompressed text to file(filePathW)
        String compressed = readTextFromFileToCompress(filePathR);
        System.out.println("substr : " + compressed.substring(0, 1));
        // int getOverHead = Integer.parseInt(compressed.substring(0, 1), 2);
        // System.out.println("getOverHead : " + getOverHead);
        // System.out.println("compressed : " + compressed);
        String intCom = "";
        // int getOverHead = Integer.parseInt(compressed.substring(0, 8), 2);
        // data = HandleBinary.getOrginal(compressed, getOverHead);
        // String text = comAlgo.decompress(data);
        // writeDecompressedText(filePathW, text);
    }
    @Override
    protected String ReadFromCompressedFile(String fileName) 
    {
        try{
            // StringBuilder text = new StringBuilder();
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            String line, text = "";
            while ((line = reader.readLine()) != null) {
                text += line;
            }
            return text;
        } 
        catch (IOException e) {
            return e.getMessage();
        }
    }
}