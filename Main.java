import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        String s3 = "", s2;
        ReadWriteFiles f = new ReadWriteFilesLzwAlgo(new LZW(), new HandlingBinaryLzw());
        // f.convertTextFromDecompressToCompress("..\\ff f.txt", "..\\lzw.bin");
        f.convertTextFromCompressToDecompress("..\\lzw.bin", "..\\ffff.txt");
    }
}
//  ArrayList<Integer> a = new ArrayList<Integer>();
// a.add(65);
// a.add(66);
// a.add(65);
// a.add(128);
// a.add(128);
// a.add(129);
// a.add(131);
// a.add(134);
// a.add(130);
// a.add(129);
// a.add(66);
// a.add(138);
// a.add(139);
// a.add(138);
 // String s = "FUUUCKKKABBBDDOOOOOO";
// s  -> 70 85 129 67 75 132 65 66 135 68 68 79 139 140
//  s3 = "ABAABABBAABAABAAAABABBBBBBBB";
// s2 -> 65 66 65 128 128 129 131 134 130 129 66 138 139 138 

// String binaryString = "10"; // 
//         byte decimalInteger = (byte)Integer.parseInt(binaryString, 10);

// abdefldfldkfnclseirjpehrfdjf

// System.out.println("dfdfdf" +  "1    fddfdf");
// System.out.println("s2 : " + s2);
// s2 ->65 66 65 128 128 129 131 134 130 129 66 138 139 138
// System.out.println(Math.log(139));