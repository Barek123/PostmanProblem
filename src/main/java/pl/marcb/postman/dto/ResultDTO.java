package pl.marcb.postman.dto;

import com.softtechdesign.ga.Chromosome;

public class ResultDTO {
    Chromosome fittestChromosome;
    double fittestChromosomesFitness = 0.0;

    boolean foundFittestChromoseme = false;

    public ResultDTO() {
    }

    public ResultDTO(Chromosome fittestChromosome, double fittestChromosomesFitness, boolean foundFittestChromoseme) {
        this.fittestChromosome = fittestChromosome;
        this.fittestChromosomesFitness = fittestChromosomesFitness;
        this.foundFittestChromoseme = foundFittestChromoseme;
    }

    public Chromosome getFittestChromosome() {
        return fittestChromosome;
    }

    public void setFittestChromosome(Chromosome fittestChromosome) {
        this.fittestChromosome = fittestChromosome;
    }

    public double getFittestChromosomesFitness() {
        return fittestChromosomesFitness;
    }

    public void setFittestChromosomesFitness(double fittestChromosomesFitness) {
        this.fittestChromosomesFitness = fittestChromosomesFitness;
    }

    public boolean isFoundFittestChromoseme() {
        return foundFittestChromoseme;
    }

    public void setFoundFittestChromoseme(boolean foundFittestChromoseme) {
        this.foundFittestChromoseme = foundFittestChromoseme;
    }
}
