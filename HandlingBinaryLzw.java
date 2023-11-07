import java.util.ArrayList;

public class HandlingBinaryLzw<T> extends HandlingBinary<T>{

    @Override
    public <T> int maxElementsInIndexFromAnyTag(ArrayList<T> compressedData) {
        int mx = (Integer)compressedData.get(0);
        for (int i = 0; i < compressedData.size(); i++) {
            mx = Math.max(mx, (Integer)compressedData.get(i));
        }
        return mx;
    }
    @Override
    <T> String getBinary(ArrayList<T>compressedData, int overhead){
        String compressedText = "";
        compressedText += ToCompleteBinaryWithZeros(Integer.toBinaryString(overhead), 8);
        for (T t : compressedData) {
            compressedText += ToCompleteBinaryWithZeros(Integer.toBinaryString((int)t), overhead);
        }
        return compressedText;
    }
    
    @Override
    <T> ArrayList<T> getOrginal(String compressedDataBinary, int overhead) {
        ArrayList<Integer> compressedDataIntegers = new ArrayList<>();
        for (int i = overhead; i < compressedDataBinary.length(); i+=overhead) {
            compressedDataIntegers.add(Integer.parseInt(compressedDataBinary.substring(i, i+overhead), 2));
            System.out.println(Integer.parseInt(compressedDataBinary.substring(i, i+overhead), 2));
        }
        return (ArrayList<T>)compressedDataIntegers;
    }
    @Override
    void convertBinaryStringToBytes(String binaryString) {
        // byte b;
        // for (int i = 0; i < binaryString.length(); i++) {
        //     b = (byte)Integer.parseInt(binaryString.substring(0, i+8));
        //     System.out.println("b : " + b);
        // }
    }
}
// byte[] byteArray = new byte[size];