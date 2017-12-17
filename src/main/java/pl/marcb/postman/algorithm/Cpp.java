package pl.marcb.postman.algorithm;

import com.softtechdesign.ga.GAException;
import pl.marcb.postman.config.ConfigProperties;
import pl.marcb.postman.dto.ResultDTO;
import pl.marcb.postman.exceptions.ParserException;
import pl.marcb.postman.parser.Parser;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Cpp {
    private Parser parser;
    private int edge;
    private String[] distinctElements;
    private int maxNumExecutions = 0;
    private ResultDTO resultDTO = new ResultDTO();


    public Cpp() throws IOException, GAException {
        getDataFromParser();

        CppUtils cppUtils = CppUtils.getInstance();


        maxNumExecutions = Integer.parseInt(ConfigProperties.getInstance().getProperties("max.execution"));

        boolean allEdges = false;
        int index = 0;

        do {
            CppAlgorithm cppAlgorithm = constructCppAlgorithm();
            cppAlgorithm.evolve();

            List<String> genes = new LinkedList<String>(Arrays.asList(cppAlgorithm.getFittestChromosome().toString().split("\\|")));
            genes.add(genes.get(0));

            allEdges = cppUtils.allPointsExist(genes);
            if (allEdges) {
                resultDTO.setFittestChromosome(cppAlgorithm.getFittestChromosome());
                resultDTO.setFittestChromosomesFitness(cppAlgorithm.getFittestChromosomesFitness());
                resultDTO.setFoundFittestChromoseme(true);

                System.out.print("Znaleziono prawidłową śceżkę  "+ String.join("-", genes));
                System.out.println(" o wartości " + cppAlgorithm.getFittestChromosomesFitness());
            } else {
                resultDTO.setFoundFittestChromoseme(false);

                System.out.println("Nie udało się znaleźć połączenia");
            }
            index++;
        }while (!allEdges && index<maxNumExecutions);
    }

    public ResultDTO getResultDTO() {
        return resultDTO;
    }

    private CppAlgorithm constructCppAlgorithm() throws GAException {   //build from config
        ConfigProperties configProperties = ConfigProperties.getInstance();

        int populationDim=Integer.parseInt(configProperties.getProperties("config.populationDim"));
        double crossoverProb=Double.parseDouble(configProperties.getProperties("config.crossoverProb"));
        int randomSelectionChance=Integer.parseInt(configProperties.getProperties("config.randomSelectionChance"));
        int maxGenerations=Integer.parseInt(configProperties.getProperties("config.maxGenerations"));
        int numPrelimRuns=Integer.parseInt(configProperties.getProperties("config.numPrelimRuns"));
        int maxPrelimGenerations=Integer.parseInt(configProperties.getProperties("config.maxPrelimGenerations"));
        double mutationProb=Double.parseDouble(configProperties.getProperties("config.mutationProb"));
        int chromDecPts=Integer.parseInt(configProperties.getProperties("config.chromDecPts"));
        int crossoverType=Integer.parseInt(configProperties.getProperties("config.crossoverType"));
        boolean computeStatistics= Boolean.parseBoolean(configProperties.getProperties("config.computeStatistics"));

        return new CppAlgorithm(edge,
                populationDim,
                crossoverProb,
                randomSelectionChance,
                maxGenerations,
                numPrelimRuns,
                maxPrelimGenerations,
                mutationProb,
                chromDecPts,
                distinctElements,
                crossoverType,
                computeStatistics);
    }

    private void getDataFromParser() throws IOException {
        parser = Parser.getInstance();
        try {
            parser.generateOutput();
        } catch (ParserException e) {
            e.printStackTrace();
        }
        distinctElements = parser.getDistinctPoints();
        edge = parser.getEdge();
    }
}
