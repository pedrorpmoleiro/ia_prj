package ga;

public abstract class IntVectorIndividual<P extends Problem, I extends IntVectorIndividual> extends Individual<P, I> {
    protected int[] genome;

    public IntVectorIndividual(P problem, int size) {
        super(problem);
        genome = new int[size];

        int i = 0;

        do {
            int value = GeneticAlgorithm.random.nextInt(size) + 1;

            boolean flag = true;
            for (int i1 : genome) {
                //se for repetido o valor, voltar para cima
                if (value == i1) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            //popula genoma com valores random
            genome[i] = value;
            i++;

        } while (i < size);
    }

    public IntVectorIndividual(IntVectorIndividual<P, I> original) {
        super(original);

        this.genome = new int[original.genome.length];

        System.arraycopy(original.genome, 0, genome, 0, genome.length);
    }

    @Override
    public int getNumGenes() {
        return genome.length;
    }

    public int getIndexof(int value) {
        for (int i = 0; i < genome.length; i++) {
            if (genome[i] == value)
                return i;
        }

        return -1;
    }

    public int getGene(int index) {
        return genome[index];
    }

    public void setGene(int index, int newValue) {
        genome[index] = newValue;
    }

    @Override
    public void swapGenes(IntVectorIndividual other, int index) {
        int aux = genome[index];
        genome[index] = other.genome[index];
        other.genome[index] = aux;
    }
}
