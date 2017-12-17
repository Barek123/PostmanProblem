package pl.marcb.postman.algorithm;

import com.softtechdesign.ga.GAException;
import com.softtechdesign.ga.GAStringsSeq;

public class CppAlgorithm extends GAStringsSeq {
    private CppUtils cppUtils = CppUtils.getInstance();

    public CppAlgorithm(int chromosomeDim,
                        int populationDim,
                        double crossoverProb,
                        int randomSelectionChance,
                        int maxGenerations,
                        int numPrelimRuns,
                        int maxPrelimGenerations,
                        double mutationProb,
                        int chromDecPts,
                        String[] possGeneValues,
                        int crossoverType,
                        boolean computeStatistics) throws GAException {

        super(chromosomeDim,
                populationDim,
                crossoverProb,
                randomSelectionChance,
                maxGenerations,
                numPrelimRuns,
                maxPrelimGenerations,
                mutationProb,
                chromDecPts,
                possGeneValues,
                crossoverType,
                computeStatistics);
    }

    protected double getFitness(int i) {
        checkIfCppUtilsExist();
        return cppUtils.calculateFitness(this.getChromosome(i));
    }

    private void checkIfCppUtilsExist(){
        if(cppUtils == null){
            cppUtils = CppUtils.getInstance();
        }
    }
}
