import java.util.ArrayList;
/**
 * CompressionAlgorithms
 */
public interface CompressionAlgorithms {
    <T> ArrayList<T> compress(String t);
    <T> String decompress(ArrayList<T>compressedData);

}
