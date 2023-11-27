import java.util.ArrayList;
import java.util.Map;

public class HandlingBinaryHuffman<T> extends HandlingBinary<T>{
    @Override
    ArrayList<T> getOrginal(String compressedDataBinary, int overhead) {
        return null;
    }

    @Override
    <T1> int maxElementsInIndexFromAnyTag(ArrayList<T1> compressedData) {
        return 0;
    }

    <T> ArrayList<byte[]> getBinaryForCompressData(ArrayList<T> compressedData, String str) {
//      compressedData = (Map<Character, Integer>)compressedData;
        for (Map.Entry<Character, Integer> entry : (Map<Character, Integer>)compressedData){

        }
        return null;
    }
}
