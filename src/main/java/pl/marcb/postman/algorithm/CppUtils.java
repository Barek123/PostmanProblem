package pl.marcb.postman.algorithm;

import com.softtechdesign.ga.ChromStrings;
import pl.marcb.postman.config.ConfigProperties;
import pl.marcb.postman.domain.GraphElement;
import pl.marcb.postman.exceptions.ParserException;
import pl.marcb.postman.parser.Parser;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CppUtils {
    private ConfigProperties configProperties = ConfigProperties.getInstance();
    private List<String> distinctPointsList;
    private List<GraphElement> graphElements;
    private int penaltyValue = 10;


    private static CppUtils singleton = new CppUtils();

    private CppUtils() {
        initData();
    }

    public static CppUtils getInstance( ) {
        return singleton;
    }

    public double calculateFitness(ChromStrings chromosome){
        double result = 0.0;
        List<String> genes = new LinkedList<String>(Arrays.asList(chromosome.getGenes()));
        genes.add(genes.get(0));        //add first gene

        for (int i = 0; i < genes.size()-1; i++) {
            result += searchInGraphElement(genes.get(i), genes.get(i+1));
        }

        if(!allPointsExist(genes)){
            result += penaltyValue;
        }

        return 1/result;
    }

    public boolean allPointsExist(List<String> genes){
        for (int i = 1; i < genes.size(); i++) {
            if(genes.get(i-1).equals(genes.get(i))){
                return false;
            }
        }
        if(distinctPointsList.stream()
                .filter(c -> !genes.contains(c)).count()==0){

            String genesToTest = String.join("", genes);

            for (GraphElement graphElement : graphElements){
                if(!(genesToTest.contains(graphElement.getPointA()+graphElement.getPointB())
                    ||genesToTest.contains(graphElement.getPointB()+graphElement.getPointA()))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private double searchInGraphElement(String pointA, String pointB){
        List<GraphElement> graphsList = graphElements.stream()
                .filter(c -> (c.getPointA().equals(pointA) && c.getPointB().equals(pointB))
                    || (c.getPointA().equals(pointB) && c.getPointB().equals(pointA)))
                .collect(Collectors.toList());

        if(graphsList.size()>0){
            GraphElement graphElement = graphsList.get(0);
            return (graphElement.getPointA().equals(pointA))? graphElement.getDistanceFromAtoB(): graphElement.getDistanceFromBtoA();
        }else{
            return 0.0;
        }
    }

    private void initData(){
        Parser parser = Parser.getInstance();
        try {
            parser.generateOutput();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
        distinctPointsList = parser.getDistinctPointsList();
        graphElements = parser.getGraphElements();
        penaltyValue = Integer.parseInt(configProperties.getProperties("penalty.value"));
    }
}
