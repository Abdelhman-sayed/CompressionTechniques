import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteFilesHuffmanAlgo<T> extends ReadWriteFiles<T>{
    Huffman algo;
    public  ReadWriteFilesHuffmanAlgo(Huffman compressionAlgo, HandlingBinary HB){
        algo = compressionAlgo;
        HandleBinary  = HB;
    }
    @Override
    protected void writeToFileCompressedText(String fileName, ArrayList<byte[]> dataBinaryStream) {

    }

    @Override
    void convertTextFromDecompressToCompress(String filePathR, String filePathW) {
        String compressedData = (String)comAlgo.compress("abaacaadaa").get(0);
        ArrayList<Pair<Character, Integer>>freqArray = new ArrayList<>();
//        get map and transfer it into arrayList
        Pair<Character, Integer> p = new Pair<>();
        for (Map.Entry<Character, Integer> entry: algo.getFrequencyMap().entrySet()) {
            p.setFirst(entry.getKey());
            p.setSecond(entry.getValue());
            freqArray.add(p);
        }
        ArrayList<byte[]> bytes = new ArrayList<>();
        bytes = HandleBinary.getBinaryForCompressData(comAlgo.compress("abaacaadaa"), compressedData);

    }
    @Override
    void convertTextFromCompressToDecompress(String filePathR, String filePathW) {
    }
}
