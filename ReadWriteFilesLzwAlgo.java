import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFilesLzwAlgo extends ReadWriteFiles{
    public ReadWriteFilesLzwAlgo(CompressionAlgorithms c, HandlingBinary b) {
        comAlgo = c;
        HandleBinary = b;
        data = new ArrayList<>();
    }
    @Override
    protected
    void writeToFileCompressedText(String fileName) {
         try {
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(fileName);
            for (Integer number : (ArrayList<Integer>)data) {
                writer.write(number + " ");
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
        data = comAlgo.runCompress(readTextFromFileToCompress(filePathR));

        // int numBits = Integer.toBinaryString(HandleBinary.maxElementsInIndexFromAnyTag(data)).length();
        // HandleBinary.ToCompleteBinary(numBits, filePathW);
        writeToFileCompressedText(filePathW);
    }
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
        // step1 : read from file(compressed) -->about call this method ==> readTextFromFileToDecompress(filePathR)
        // step2 : store decompressed(string) when call method runDecompress
        // step3 : write deCompressed text to file(filePathW)
        String compressed = readTextFromFileToCompress(filePathR);
        String intCom = "";
        for (int i = 0; i < compressed.length(); i++) {
            if(compressed.charAt(i) != ' ')
                intCom += compressed.charAt(i);
            else{
                if(intCom != "" && intCom != " ")data.add(Integer.parseInt(intCom));
                intCom = "";
            }
        }
        if(intCom != "" && intCom != " ")data.add(Integer.parseInt(intCom));
        String text = comAlgo.runDeCompress(data);
        // System.out.println("ddecompressed : " + s);
        // data = (ArrayList<Integer>)data;
        writeDecompressedText(filePathW, text);
    }
}
