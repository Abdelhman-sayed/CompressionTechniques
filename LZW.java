import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LZW implements CompressionAlgorithms{
    private Map<String, Integer> DictionaryForCompress;
    private Map<Integer, String> DictionaryForDeCompress;
    private ArrayList<Integer> compressData;
    private String text;
    private String decompressData;
    public LZW(){
    }
    // put capital character in the dictionary for compress
    public void setCapitalCharForCompress(){
         for (int i = 65; i <= 90; i++) {
            String key = "";
            key += (char)i;
            DictionaryForCompress.put(key, i);
        }
    }
     // put small character in the dictionary for compress
    public void setSmallCharForCompress(){
         for (int i = 97; i <= 122; i++) {
            String key = "";
            key += (char)i;
            DictionaryForCompress.put(key, i);
        }
    }
    
     // put capital character in the dictionary for compress
    public void setCapitalCharForDeCompress(){
         for (int i = 65; i <= 90; i++) {
            String key = "";
            key += (char)i;
            DictionaryForDeCompress.put(i, key);
        }
    }
     // put small character in the dictionary for compress
     public void setSmallCharForDeCompress()
     {
         for (int i = 97; i <= 122; i++) {
            String key = "";
            key += (char)i;
            DictionaryForDeCompress.put(i, key);
        }
    }
    @Override
    public void compress() 
    {
        int j = 0, valueForKey = 128;
        String str = "";
        str += text.charAt(j);
        j++;
        while( j < text.length()){
            while (DictionaryForCompress.get(str) != null && j < text.length()) {
                str += text.charAt(j);
                j++;
            }
            if(j >= text.length()){
                break;
            }
            DictionaryForCompress.put(str, valueForKey);
            compressData.add(DictionaryForCompress.get(str.substring(0, str.length()-1)));
            str = "";
            str +=  text.charAt(j-1);
            valueForKey++;
        }
        if(DictionaryForCompress.get(str) != null){
             compressData.add(DictionaryForCompress.get(str.substring(0, str.length())));
        }else{
             DictionaryForCompress.put(str, valueForKey);
            compressData.add(DictionaryForCompress.get(str.substring(0, str.length()-1)));
            compressData.add(DictionaryForCompress.get(str.substring(str.length()-1)));
        }
        if(compressData.get(compressData.size()-1) == null)
            compressData.remove(compressData.size()-1);
        
    }
    @Override
    public void decompress() {
         int j = 0, valueForKey = 128;
        
        decompressData += DictionaryForDeCompress.get(compressData.get(j));
        for (j = 1; j < compressData.size(); j++) {
            String key = "", curCompressInt = "";
            key = DictionaryForDeCompress.get(compressData.get(j-1));
            if(DictionaryForDeCompress.get(compressData.get(j)) != null){
                curCompressInt = DictionaryForDeCompress.get(compressData.get(j));
                curCompressInt = curCompressInt.substring(0, 1);
            }else{
                curCompressInt = key.substring(0, 1);
            }
            key+=curCompressInt;
            DictionaryForDeCompress.put(valueForKey, key);
            String value = "";
            value = DictionaryForDeCompress.get(compressData.get(j));
            decompressData += value;
            valueForKey++;
        }
    }   
    // public void printDictionary(){
    //     for (Map.Entry<Integer, String> entry : DictionaryForDeCompress.entrySet()) {
    //         // Iterate over the map using entrySet
    //         int key = entry.getKey();
    //         String value = entry.getValue();
    //         System.out.println("Key: " + key + ", Value: " + value);
    //     }
    // }
    // public void printCompressedData(){
    //     for (int index = 0; index < compressData.size(); index++) {
    //         System.out.print(compressData.get(index) + " ");
    //     }
    //     System.out.println();
    // }
    @Override
    public <T> ArrayList<T> runCompress(String t) {
        text = t;
        DictionaryForCompress = new HashMap<String, Integer>();
        compressData = new ArrayList<Integer>();
        setCapitalCharForCompress();
        setSmallCharForCompress();
        compress();
        return (ArrayList<T>)compressData;
    }
    @Override
    public <T> String runDeCompress(ArrayList<T> compressedData) {
        DictionaryForDeCompress = new HashMap<Integer, String>();
        compressData = new ArrayList<Integer>();
        compressData = (ArrayList<Integer>) compressedData;
        setCapitalCharForDeCompress();
        setSmallCharForDeCompress();
        decompressData = "";
        decompress();
        return decompressData;
    }
}
