import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteFilesLzwAlgo<T> extends ReadWriteFiles<T>{
    private StringBuilder append;

    public ReadWriteFilesLzwAlgo(CompressionAlgorithms c, HandlingBinary<T> b) {
        comAlgo = c;
        HandleBinary = b;
        data = new ArrayList<>();
    }
    @Override
    protected
    void writeToFileCompressedText(String fileName, ArrayList<byte[]> dataBinaryStream) {
    try(FileOutputStream fileWriter = new FileOutputStream(fileName)){
        for (byte[] item : dataBinaryStream) {
        fileWriter.write(item);
      }
    } 
    catch (IOException e) 
    {
      System.out.println("error occurred in file writing" + e.getMessage());
    }
    }
   
    @Override
    void convertTextFromDecompressToCompress(String filePathR, String filePathW) {
        // step1 : read from file(string--> decompress) -->about call this method ==> readTextFromFileToCompress(filePathR)
        // step2 : store compressed(in arrayList --> data) that contains compressed data when call method runCompress
        // step3 : write compressed text to file(filePathW)
        data = comAlgo.compress(readTextFromFileToCompress(filePathR));
        int numBits = Integer.toBinaryString(HandleBinary.maxElementsInIndexFromAnyTag(data)).length();
        // git binary text to store it in bin file to decrease storage --> HandleBinary.getBinary(data, numBits);
        // HandleBinary.convertBinaryStringToBytes(HandleBinary.getBinary(data, numBits));
        // HandleBinary.getBinary(data, numBits) this method return array of bytes to store it in file
        writeToFileCompressedText(filePathW, HandleBinary.getBinary(data, numBits));
    }
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
        // step1 : read from file(compressed) -->about call this method ==> readTextFromFileToDecompress(filePathR)
        // step2 : store decompressed(string) when call method runDecompress
        // step3 : write deCompressed text to file(filePathW)
        String compressed = ReadFromCompressedFile(filePathR);
        int overhead = Integer.parseInt(compressed.substring(0, 8), 2);
        data = HandleBinary.getOrginal(compressed, overhead);
        String decompress = comAlgo.decompress(data);
        writeDecompressedText(filePathW, decompress);

    }
}
