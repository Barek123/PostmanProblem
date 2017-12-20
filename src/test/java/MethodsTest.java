import org.junit.Assert;
import org.junit.Test;
import pl.marcb.postman.domain.GraphElement;
import pl.marcb.postman.exceptions.ParserException;
import pl.marcb.postman.parser.Parser;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodsTest {

    @Test
    public void allEdgesValidatorTest() throws FileNotFoundException, ParserException {
        String temp = "8-3-4-5-8-2--3-2-1-8-1-7-6-5-8";
        List<String> list = Arrays.asList(temp.split("-"));
        System.out.println(list.size()-1);

        Parser instance = Parser.getInstance();
        instance.generateOutput();
        graphElements = instance.getGraphElements();
        distinctPointsList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
        Assert.assertTrue(allPointsExist(list));
    }

    private List<String> distinctPointsList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
    private List<GraphElement> graphElements = Parser.getInstance().getGraphElements();

    private boolean allPointsExist(List<String> genes){
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
            for (int i = 1; i < genesToTest.length(); i++) {
                int pointAIndex = i-1;  //variable in stream must be effectively final
                int pointBIndex = i;
                if(graphElements.stream()
                        .filter(c -> (c.getPointA().equals(genesToTest.charAt(pointAIndex)+"") && c.getPointB().equals(genesToTest.charAt(pointBIndex)+""))
                                || (c.getPointA().equals(genesToTest.charAt(pointBIndex)+"") && c.getPointB().equals(genesToTest.charAt(pointAIndex)+"")))
                        .collect(Collectors.toList()).size() == 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
