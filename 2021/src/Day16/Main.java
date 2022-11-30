package Day16;

import Util.DataReader;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
        DataReader dataReader = new DataReader("./Data/Day16.txt");
        String hex = dataReader.readUntilEOF().get(0);
        Packet packet = new Packet(hex, true);
//        Packet packet = new Packet("9C0141080250320F1802104A08", true);
        System.out.println(packet.versionSum());
        System.out.println(packet.value());
    }
}
