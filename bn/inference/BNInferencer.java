package bn.inference;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bn.core.*;
import bn.parser.*;

public class BNInferencer {
    public static void main(String[] argv){
        EnumerationAsk asker = new EnumerationAsk();
        RejectionSampling rej = new RejectionSampling();
        LikelihoodWeighting like = new LikelihoodWeighting();

        if(argv[0].contains(".xml")){
            XMLBIFParser x = new XMLBIFParser();
            try {
                BayesianNetwork bn = x.readNetworkFromFile(argv[0]);
                Assignment e = new Assignment();
                for(int i = 2; i < argv.length; i+=2){
                    e.put(bn.getVariableByName(argv[i]), argv[i+1]);
                }
                //Distribution dist = asker.ask(bn, bn.getVariableByName(argv[1]), e);
                //Distribution dist = rej.rejectionSampling(bn.getVariableByName(argv[1]),e, bn,100000 );
                Distribution dist = like.likelihoodWeighting(bn.getVariableByName(argv[1]), e, bn, 100000);
                System.out.println(dist);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        } else {
            BIFParser x;
            try {
                x = new BIFParser(new FileInputStream(argv[0]));

                BayesianNetwork bn = x.parseNetwork();
                Assignment e = new Assignment();
                for(int i = 2; i < argv.length; i+=2){
                    e.put(bn.getVariableByName(argv[i]), argv[i+1]);
                }
                Distribution dist = rej.rejectionSampling(bn.getVariableByName(argv[1]),e, bn,100000 );

                System.out.println(dist);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}