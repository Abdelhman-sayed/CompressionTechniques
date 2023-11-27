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
    <T> ArrayList<T> getOrginal(String compressedDataBinary, int overhead) {
        ArrayList<Integer> compressedDataIntegers = new ArrayList<>();
        for (int i = 8; i < compressedDataBinary.length(); i+=overhead) {
            // System.out.println(compressedDataBinary.substring(i, i+overhead));
            compressedDataIntegers.add(Integer.parseInt(compressedDataBinary.substring(i, i+overhead), 2));
            // System.out.println(Integer.parseInt(compressedDataBinary.substring(i, i+overhead), 2));
        }
        return (ArrayList<T>)compressedDataIntegers;
    }
}
// byte[] byteArray = new byte[size];