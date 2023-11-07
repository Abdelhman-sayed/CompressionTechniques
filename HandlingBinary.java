import java.util.ArrayList;

public abstract class HandlingBinary {
    // <12, 34, A>
    // <4, 45, A>
    // <2, 7, A>
    // <1, 67, A>
    final String ToCompleteBinary(int mxSize, String binary){
        String Zeros = "0000000000000000000000000000";
        String complete = "";
        complete+=Zeros.substring(0, mxSize - binary.length());
        return complete;
    }
    abstract <T> int maxElementsInIndexFromAnyTag(ArrayList<T>compressedData);  
    
    // ex: max between 12, 4, 2 , 1;
    
}