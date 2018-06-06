package sequences;

public class Sequence {
    public String sequence;
    public String chromosome;
    public int start;
    public int end;
    
    public Sequence next; // pointer
    
    public Sequence(){ };
    public Sequence(String sequence, String chromosome, int start, int end){
        this.sequence = sequence;
        this.chromosome = chromosome;
        this.start = start;
        this.end = end;
    }
    
    public String toString(){
        return "Sequence: " + this.sequence + " Chromosome: " + this.chromosome +"\n";
    }
}
