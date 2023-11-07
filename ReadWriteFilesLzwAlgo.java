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
    void writeToFileCompressedText(String fileName, String compressedBinaString) {
         try {
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(fileName);
           writer.write(compressedBinaString);
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
        // to git max bits to define overhead
        int numBits = Integer.toBinaryString(HandleBinary.maxElementsInIndexFromAnyTag(data)).length();
        // git binary text to store it in bin file to decrease storage --> HandleBinary.getBinary(data, numBits);
        writeToFileCompressedText(filePathW, HandleBinary.getBinary(data, numBits));
    }
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
        // step1 : read from file(compressed) -->about call this method ==> readTextFromFileToDecompress(filePathR)
        // step2 : store decompressed(string) when call method runDecompress
        // step3 : write deCompressed text to file(filePathW)
        String compressed = readTextFromFileToCompress(filePathR);
        String intCom = "";
        int getOverHead = Integer.parseInt(compressed.substring(0, 8), 2);
        data = HandleBinary.getOrginal(compressed, getOverHead);
        String text = comAlgo.runDeCompress(data);
        writeDecompressedText(filePathW, text);
    }
}

//  for (int i = 0; i < compressed.length(); i++) {
//             if(compressed.charAt(i) != ' ')
//                 intCom += compressed.charAt(i);
//             else{
//                 if(intCom != "" && intCom != " ")data.add(Integer.parseInt(intCom));
//                 intCom = "";
//             }
//         }
// if(intCom != "" && intCom != " ")data.add(Integer.parseInt(intCom));
