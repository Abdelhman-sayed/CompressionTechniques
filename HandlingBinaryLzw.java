import java.util.ArrayList;

public class HandlingBinaryLzw extends HandlingBinary{

    @Override
    public <T> int maxElementsInIndexFromAnyTag(ArrayList<T> compressedData) {
        int mx = (Integer)compressedData.get(0);
        for (int i = 0; i < compressedData.size(); i++) {
            mx = Math.max(mx, (Integer)compressedData.get(i));
        }
        return mx;
    }
    
}
