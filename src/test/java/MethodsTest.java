import org.junit.Assert;
import org.junit.Test;
import pl.marcb.postman.domain.GraphElement;
import pl.marcb.postman.parser.Parser;

import java.util.Arrays;
import java.util.List;

public class MethodsTest {

    @Test
    public void allEdgesValidatorTest(){
        String temp = "1,5,4,3,2,4,2,1";
        List<String> list = Arrays.asList(temp.split(","));
        System.out.println(list.size()-1);

        Assert.assertTrue(allPointsExist(list));
    }

    private List<String> distinctPointsList = Arrays.asList("1", "2", "3", "4", "5");
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
            return true;
        }
        return false;
    }
}
