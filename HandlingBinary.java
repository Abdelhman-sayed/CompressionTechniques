import java.util.ArrayList;

public abstract class HandlingBinary<T> {
    // <12, 34, A>
    // <4, 45, A>
    // <2, 7, A>
    // <1, 67, A>
    public ArrayList<byte[]> getBinary(ArrayList<T>compressedData,int overhead) {
        ArrayList<byte[]> dataBinaryStream = new ArrayList<>();
        // add overhead in file
        dataBinaryStream.add(toBytes(formatIntToBinaryString(overhead, 8)));
        
        StringBuilder data = new StringBuilder();
        for (T index : compressedData) {
        data.append(formatIntToBinaryString(overhead, (int)index));
        }
        dataBinaryStream.add(toBytes(String.valueOf(data)));
        return dataBinaryStream;
    }
    // this method take the number and format it in specific number of bits
    private String formatIntToBinaryString(int numBits, int number) {
        String chunk = Integer.toBinaryString(number);
        chunk = String.format("%" + numBits + "s", chunk).replace(' ', '0');
        return chunk;
    }
    // to convert binary string to array of bytes.
    private byte[] toBytes(String tag) {
        int max  = tag.length();
        max += (8 - (max % 8 == 0 ? 8 : max % 8));// make it multiple of 8;

        StringBuilder tagBuilder = new StringBuilder(tag);
        tagBuilder.append("0".repeat(Math.max(0, max - tagBuilder.length())));
        tag = tagBuilder.toString();

        int numBytes = max / 8;
        byte[] bytes = new byte[numBytes];
        for (int i = 0; i < numBytes; i++) {
        String byteString = tag.substring(i * 8, (i + 1) * 8);
        bytes[i] = (byte) Integer.parseInt(byteString, 2);
        }
        return bytes;
  }
    abstract <T> int maxElementsInIndexFromAnyTag(ArrayList<T>compressedData);
    // convert any compressed normal text to binary text
    abstract <T> ArrayList<T> getOrginal(String compressedDataBinary, int overhead);
    
    // ex: max between 12, 4, 2 , 1;
    
}
// data ---> max for all 
// 1-overhead --> 1 byte
// convert all to binary ---> method ====> handlingBinary
