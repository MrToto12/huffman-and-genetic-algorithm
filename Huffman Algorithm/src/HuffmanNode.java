public class HuffmanNode implements Comparable<HuffmanNode>{
    char character;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode rhs) {
        return this.frequency - rhs.frequency;
    }
}
