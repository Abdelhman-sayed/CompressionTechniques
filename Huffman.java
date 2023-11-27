import java.util.*;

public class Huffman implements CompressionAlgorithms{
    private  Map<Character, String>huffmanCodes;
    private  Map<Character, Integer>frequencyMap;
    public Huffman()
    {
        frequencyMap = new HashMap<>();
        huffmanCodes = new HashMap<>();
    }
    //  to get freq of each character in text
    private Map<Character, Integer> buildFrequencyMap(String input) {
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
//        for (Map.Entry<Character, Integer> entry: frequencyMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
        return frequencyMap;
    }
//    to get frequency map
    public Map<Character, Integer> getFrequencyMap(){
        return frequencyMap;
    }
//    to build huffman tree
    private  HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        for (HuffmanNode element : priorityQueue) {
            System.out.println(element.frequency);
        }
//        5- > left 9 -> right
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode internalNode = new HuffmanNode('\0', left.frequency + right.frequency);
            internalNode.left = left;
            internalNode.right = right;
            priorityQueue.add(internalNode);
        }
//       this return root of huffman tree
        return priorityQueue.poll();
    }
    private void buildHuffmanCodes(HuffmanNode root, String code, Map<Character, String>huffmanCodes){

        if(root == null)return;

        if(root.data != '\0'){
            huffmanCodes.put(root.data, code);
        }
//        one call for left
        buildHuffmanCodes(root.left, code+"0", huffmanCodes);
//        one call for right
        buildHuffmanCodes(root.right, code+"1", huffmanCodes);
    }
    @Override
    public <T> ArrayList<T> compress(String t) {
        HuffmanNode root = buildHuffmanTree(buildFrequencyMap(t));
        buildHuffmanCodes(root, "", huffmanCodes);
        String compressData = "";
        for (int i = 0; i < t.length(); i++) {
            compressData+=huffmanCodes.get(t.charAt(i));
        }
        ArrayList<String>compressDataFI = new ArrayList<>();
        compressDataFI.add(0, compressData);
        for (Map.Entry<Character, String> entry:huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return (ArrayList<T>) getFrequencyMap();
    }
    @Override
    public <T> String decompress(ArrayList<T> compressedData) {
        return null;
    }
}

