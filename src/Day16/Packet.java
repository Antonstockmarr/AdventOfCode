package Day16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Packet {

    public int version;
    public int type;
    private long payload = 0;
    public List<Packet> subpackets = new LinkedList<>();
    public int packetLength = 0;

    public Packet(String encoding, boolean inHex) {
        String bitString = inHex ? hex2bin(encoding) : encoding;
        System.out.println("Packet with string: "+bitString);
        version = Integer.parseInt(bitString.substring(0,3),2);
        type = Integer.parseInt(bitString.substring(3,6),2);
        packetLength += 6;
        System.out.println("version "+version);
        System.out.println("type "+type);
        if (type == 4) {
            payload = bin2Long(decodePacket(bitString.substring(6)));
        }
        else {
            subpackets = decodeSubPackets(bitString.substring(6));
//            packetLength = packetLength + subpackets.stream().map(p -> p.packetLength).mapToInt(Integer::intValue).sum();
        }
        System.out.println("Packet decoded. Actual packet: "+bitString.substring(0,packetLength));
    }

    private String decodePacket(String string) {
        System.out.println("decoding literal packet: "+string);
        int index = 0;
        List<String> payload = new LinkedList<>();
        boolean last = false;
        while (!last) {
            String bitGroup = string.substring(index, index + 5);
            index += 5;
            if (bitGroup.charAt(0) == '0') {
                last = true;
            }
            payload.add(bitGroup.substring(1));
        }
        this.packetLength += index;
        String packetString = String.join("", payload);
        return packetString;
    }

    private List<Packet> decodeSubPackets(String string) {
        List<Packet> packets = new LinkedList<>();
        int sizeBits = string.charAt(0) == '1' ? 11 : 15;
        long packetsLength = bin2Long(string.substring(1,sizeBits+1));
        int index = 0;
        int i=0;
        packetLength += 1 + sizeBits;
        while ((i < packetsLength && sizeBits == 11) || (index < packetsLength && sizeBits == 15) ) {
            if (sizeBits == 11) {
                System.out.println("Parsing package "+(i+1)+" out of "+packetsLength);
            }
            else {
                System.out.println("Parsing bits from "+index+" up to "+packetsLength);
            }
            String packetString = string.substring(index + sizeBits + 1);
            Packet nextPacket = new Packet(packetString, false);
            packets.add(nextPacket);
            packetLength += nextPacket.packetLength;
            index += nextPacket.packetLength;
            i++;
        }
        return packets;
    }

    public int versionSum() {
        return version + subpackets.stream().map(Packet::versionSum).mapToInt(Integer::intValue).sum();
    }

    private String hex2bin(String encoding) {
        List<String> binaryStrings = new LinkedList<>();
        for (char c : encoding.toCharArray()) {
            binaryStrings.add(char2bits(c));
        }
        return String.join("",binaryStrings);
    }

    private String char2bits(char c) {
        return switch (c) {
            case '0' -> "0000";
            case '1' -> "0001";
            case '2' -> "0010";
            case '3' -> "0011";
            case '4' -> "0100";
            case '5' -> "0101";
            case '6' -> "0110";
            case '7' -> "0111";
            case '8' -> "1000";
            case '9' -> "1001";
            case 'A' -> "1010";
            case 'B' -> "1011";
            case 'C' -> "1100";
            case 'D' -> "1101";
            case 'E' -> "1110";
            case 'F' -> "1111";
            default -> "";
        };
    }

    public static long bin2Long(String binary) {
        long dec_value = 0L;

        // Initializing base
        // value to 1, i.e 2^0
        long base = 1L;


        for (int i=binary.length() - 1; i >= 0; i--) {
            dec_value += binary.charAt(i) == '1' ? base : 0;
            base *= 2;
        }
        return dec_value;
    }

    public long value() throws Exception {
        if (type == 4) {
            return payload;
        }
        List<Long> subPacketValues = new ArrayList<>();
        for (Packet subpacket : subpackets) {
            Long value = subpacket.value();
            subPacketValues.add(value);
        }
        switch (type) {
            case 0: return subPacketValues.stream().reduce(0L, (Long::sum));
            case 1: return subPacketValues.stream().reduce(1L, (l1, l2) -> l1*l2);
            case 2: return subPacketValues.stream().reduce(Long.MAX_VALUE, Long::min);
            case 3: return subPacketValues.stream().reduce(0L, Long::max);
            case 5:
                if (subPacketValues.size() != 2)
                    throw new Exception();
                else return subPacketValues.get(0) > subPacketValues.get(1) ? 1L : 0L;
            case 6:
                if (subPacketValues.size() != 2)
                    throw new Exception();
                else return subPacketValues.get(0) < subPacketValues.get(1) ? 1L : 0L;
            case 7:
                if (subPacketValues.size() != 2)
                    throw new Exception();
                else return subPacketValues.get(0).equals(subPacketValues.get(1)) ? 1L : 0L;
            default: throw new Exception("packet had type "+type);
        }
    }
}
