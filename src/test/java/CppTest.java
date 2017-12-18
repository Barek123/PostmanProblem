import com.softtechdesign.ga.GAException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.marcb.postman.algorithm.Cpp;
import pl.marcb.postman.config.ConfigProperties;
import pl.marcb.postman.dto.ResultDTO;

import java.io.IOException;
import java.util.Map;

public class CppTest {
    @Before
    public void setUp(){
        initProperties();
        System.setProperty("dev", "true");  //if is dev property ConfigProperties read System property
                                            // instead application.properties
    }

    @Test
    public void test1() throws IOException, GAException {
        //given
        setProperty("max.execution", "10");
        setProperty("config.maxGenerations", "200");
        setProperty("config.populationDim", "1000");

        //when
        Cpp cpp = new Cpp();
        ResultDTO resultDTO = cpp.getResultDTO();

        //then
        Assert.assertTrue(resultDTO.isFoundFittestChromoseme());
    }


    //overrides default property
    private void setProperty(String key, String value){
        System.setProperty(key, value);
    }

    //init default property
    private void initProperties(){
        Map<String, String> allProperties = ConfigProperties.getInstance().getAllProperties();
        for(String key: allProperties.keySet()){
            System.setProperty(key, allProperties.get(key));
        }
    }
}
