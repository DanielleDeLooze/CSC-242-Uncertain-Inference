/*
Author: Danielle DeLooze
Date: 3/29/2018
Purpose: topigraphically sort node -> create empty distribution -> for each value xi of X ->
         Q(xi) = enumerate all(bn.vars, e_xi) where e_xi is extended with X=xi
 */
package bn.inference;
import bn.core.*;

import java.util.List;

public class EnumerationAsk {

    public Distribution asker(BayesianNetwork bn, RandomVariable X, Assignment e){
        Distribution dist = new Distribution(X);

        for(int i = 0; i < X.getDomain().size(); i++){
            Object elem = X.getDomain().get(i);
            List<RandomVariable> vars_sorted = bn.getVariableListTopologicallySorted();
            Assignment copy = e.copy();
            e.set(X, elem);
            dist.put(elem, Enumerate_all(vars_sorted, copy));
        }

        dist.normalize();
        return dist;
    }

    public double Enumerate_all(List<RandomVariable> vars, Assignment e){
        if( vars.isEmpty()){
            return 1.0;
        }
        RandomVariable Y = vars.get(0);
        if( vars.contains(Y)){

        }
    }

}
