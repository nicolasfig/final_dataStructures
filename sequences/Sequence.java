package sequences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Sequence {

    public String sequence;
    public String chromosome;
    public int start;
    public int end;

    public Sequence next; // pointer

    public Sequence() {
    }

    LinkedList sequences = new LinkedList();

    public Sequence(String sequence, String chromosome, int start, int end) {
        this.sequence = sequence;
        this.chromosome = chromosome;
        this.start = start;
        this.end = end;
    }

    int max_occurrences = Integer.MIN_VALUE;
    String motif_winner = "";

    public void compareMotef(String motif_candidate) {
        int counter = countMotifOccurrences(motif_candidate);
        if (counter > max_occurrences) {
            max_occurrences = counter;
            motif_winner = motif_candidate;
        }
    }

    public int countMotifOccurrences(String motif_candidate) {
        int counter = 0;
        String gen_sequence;
        Sequence temp = sequences.head;
        while (temp != null) {
            gen_sequence = temp.sequence;

            for (int i = 0; i <= (gen_sequence.length() - motif_candidate.length()); i++) {
                if (gen_sequence.substring(i, i + motif_candidate.length()).equals(motif_candidate)) {
                    counter++;
                    i += motif_candidate.length() - 1;
                }
            }
            temp = temp.next;
        }

        return counter;
    }
    
    public void generateCombinations(String subsequence, int size) {
        if (size == 1) {
            compareMotef(subsequence + "A");
            compareMotef(subsequence + "C");
            compareMotef(subsequence + "G");
            compareMotef(subsequence + "T");

        } else {
            generateCombinations(subsequence + "A", size - 1);
            generateCombinations(subsequence + "C", size - 1);
            generateCombinations(subsequence + "G", size - 1);
            generateCombinations(subsequence + "T", size - 1);
        }
    }
    
    @Override
    public String toString() {
        return "Sequence: " + this.sequence + " Chromosome: " + this.chromosome + " Start: " + this.start + " End: " + this.end + "\n";
    }

    public void countChromosomes() throws IOException{
        int[] chromosomes = new int[23];
        Sequence temp = sequences.head;
        String chromosoma;
        int index;
        while(temp != null){
            if(temp.sequence.contains(motif_winner)){
                //chromosomes[Integer.parseInt(temp.chromosome.replace("chr",""))- 1] += 1;
                chromosoma = temp.chromosome;
                chromosoma = chromosoma.replace("chr", "");
                index = Integer.parseInt(chromosoma) - 1;
                chromosomes[index] += 1;
            }
            temp = temp.next;
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for(int i = 0; i < 23; i++){
            bw.write("Chromosome " + (i + 1) + ": " + chromosomes[i] + "\n");
        }
        bw.flush();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("sequences.txt"));

        String input = br.readLine();
        Sequence run = new Sequence();
        while (input != null) {
            String[] data = input.split(",");
            run.sequences.addAtBack(new Sequence(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            input = br.readLine();
        }

        //run.sequences.printList();
        
        run.generateCombinations("", 4);
        System.out.println("winner_motif: " + run.motif_winner + "\tOccurrences: " + run.max_occurrences);
        run.countChromosomes();
    }
}
