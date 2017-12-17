package pl.marcb.postman.parser;

import pl.marcb.postman.config.ConfigProperties;
import pl.marcb.postman.domain.GraphElement;
import pl.marcb.postman.exceptions.ParserException;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {
    private static Parser parserSingleton = new Parser();

    private ConfigProperties configProperties;
    private int edge;
    private List<GraphElement> graphElements;
    private String[] distinctPoints;
    private List<String> distinctPointsList;

    private Parser() {
        this.configProperties = ConfigProperties.getInstance();
        this.graphElements = new ArrayList<>();
    }

    public static Parser getInstance(){
        return parserSingleton;
    }


    public void generateOutput() throws FileNotFoundException, ParserException {
        Path fileName = Paths.get(configProperties.getProperties("file-system")+configProperties.getProperties("test-file"));
        Scanner in = new Scanner(fileName.toFile());
        boolean edgeSaved = false;

        do {
            String line = in.nextLine();
            if(!edgeSaved){
                edge = Integer.parseInt(line)-1; //in the algorithm, the first element is added at the end
                edgeSaved = true;
            }else{
                List<String> args = Arrays.asList(line.split(" "));
                if(args.size() != 4){
                    throw new ParserException("inconsistent data");
                }
                String a = args.get(0);
                String b = args.get(1);
                int aToB = Integer.parseInt(args.get(2));
                int bToA = Integer.parseInt(args.get(3));

                graphElements.add(new GraphElement(a, b, aToB, bToA));
            }
        }while(in.hasNextLine());
        createDistinctPointLists();
    }

    private void createDistinctPointLists(){
        List<String> collect = graphElements.stream()   //add all from points A
                .map(GraphElement::getPointA)
                .collect(Collectors.toList());

        collect.addAll(graphElements.stream()           //add all from points B
                .map(GraphElement::getPointB)
                .collect(Collectors.toList()));

        distinctPointsList = new ArrayList<>(new HashSet<>(collect));  //distinct points

        distinctPoints = new String[distinctPointsList.size()];     //create copy of points to array
        for (int i = 0; i < distinctPointsList.size(); i++) {
            distinctPoints[i] = distinctPointsList.get(i);
        }
    }

    public int getEdge() {
        return edge;
    }

    public List<GraphElement> getGraphElements() {
        return graphElements;
    }

    public String[] getDistinctPoints() {
        return distinctPoints;
    }

    public List<String> getDistinctPointsList() {
        return distinctPointsList;
    }
}
