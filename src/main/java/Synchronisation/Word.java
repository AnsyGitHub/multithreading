package Synchronisation;

public class Word extends Thread{
    StringBuffer word;
//    private static final Object lock = new Object();

    public Word(StringBuffer word){
        this.word = word;
    }

    @Override
    public void run(){
//        synchronized (word){
        for(int i = 1; i<=50; ++i) {
            System.out.print(word);
        }
        System.out.println();
        char temp = word.charAt(0);
        ++temp;         // Increment the letter in StringBuffer:
        word.setCharAt(0, temp);
//        }
    }

    public static void main(String [] args) {
        StringBuffer sb = new StringBuffer("A");
        new Word(sb).start();
        new Word(sb).start();
        new Word(sb).start();
    }
}

