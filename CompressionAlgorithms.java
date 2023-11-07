import java.util.ArrayList;
/**
 * CompressionAlgorithms
 */
public interface CompressionAlgorithms {
    void compress();
    void decompress();
    <T> ArrayList<T> runCompress(String t);
    <T> String runDeCompress(ArrayList<T>compressedData);
}
// < 12, 12121, n>