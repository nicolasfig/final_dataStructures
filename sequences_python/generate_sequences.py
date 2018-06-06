import random

class GenerateSequences:

    @staticmethod
    def nucleotide(self):
        rd = random.randint(0,3)
        if rd == 0:
            return 'A'
        elif rd == 1:
            return 'C'
        elif rd == 2:
            return 'G'
        elif rd == 3:
            return 'T'
        else:
            return ''
    
    @staticmethod
    def sequence(self,length):
        nucleotide = self.nucleotide()
        sequence = self.sequence()
        if length == 1:
            return nucleotide
        else:
            return nucleotide + sequence(self,length-1)
    
    @staticmethod
    def chromosome(self):
        rd = random.randint(1, 23)
        return "chr" + rd

    @staticmethod
    def save_to_file(self):
        data_size = 1000
        experimental_read = ""
        sequence = self.sequence()
        chromosome = self.chromosome()
        with open("sequences.txt", "w") as file:
            for i in range(0, data_size):
                length = random.randint(1,51)
                start = random.randint(1,1000)
                experimental_read = "%s,%s,%d,%d\n" % (sequence(length),chromosome(), start, (start + length-1))
                print(experimental_read)
                file.print(experimental_read)
    
    @staticmethod
    def print_something(self):
        return "Hello world"

g = GenerateSequences()
g.save_to_file
g.print_something()