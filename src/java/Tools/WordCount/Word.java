package Tools.WordCount;

public class Word {

    public String word;
    public int count = 0;

    public Word(String word) {
        this.word = word;
        increaseCount();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    

    public void reset() {
        count = 0;
    }

    public void increaseCount() {
        count++;
    }

    public void decreaseCount() {
        count--;
    }
}
