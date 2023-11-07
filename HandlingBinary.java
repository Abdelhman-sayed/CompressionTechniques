import java.util.ArrayList;

public abstract class HandlingBinary<T> {
    // <12, 34, A>
    // <4, 45, A>
    // <2, 7, A>
    // <1, 67, A>
    final String ToCompleteBinaryWithZeros(String binary, int mxSize){
        String Zeros = "0000000000000000000000000000";
        String complete = "";
        complete = Zeros.substring(0, mxSize - binary.length()) + binary;
        return complete;
    }
    abstract <T> int maxElementsInIndexFromAnyTag(ArrayList<T>compressedData);
    // convert any compressed normal text to binary text
    abstract <T> String getBinary(ArrayList<T>compressedData, int overhead);
    abstract <T> ArrayList<T> getOrginal(String compressedDataBinary, int overhead);
    abstract void convertBinaryStringToBytes(String binaryString);
    
    // ex: max between 12, 4, 2 , 1;
    
}
// data ---> max for all 
// 1-overhead --> 1 byte
// convert all to binary ---> method ====> handlingBinary
