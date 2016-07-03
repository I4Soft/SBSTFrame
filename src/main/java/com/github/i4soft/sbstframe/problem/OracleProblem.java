/*
 * Copyright 2015 Eduardo Horst.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.i4soft.sbstframe.problem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Implements an generic use of ProblemInterface for mutation test using an Oracle
 * @author Eduardo Horst
 * @see IProblem
 */
public class OracleProblem implements IProblem {
    /**
     * Holds the Oracle also known as the original program results
     */
    private final Object oracle;
    
    /**
     * Holds the mutants
     */
    private final Object[] mutants;

    /**
     * Holds the parameters, as parameters[i][j]
     * where i is the test case
     * where j is the parameter number
     * EX: parameters[5][10] means the 5th test case 10th parameter.
     */
    private final Object[][] parameters;
    
    /**
     * Holds the method whose is used in every test case of every mutant;
     */
    private final Method testingMethod;

    
    /**
     * Default Implementation
     * @param oracle an Object that represents the original program
     * @param mutants an array of Objects that represents each mutant of the original program
     * @param parameters an matrix  of parameters
     * @param testingMethod 
     */
    public OracleProblem(Object oracle, Object[] mutants, Object[][] parameters, Method testingMethod) {
        this.oracle = oracle;
        this.mutants = mutants;
        this.parameters = parameters;
        this.testingMethod = testingMethod;
    }
    
    /**
     * Get the test result, as if the mutant is alive or not
     * @param testCase index of the test case
     * @param testReq index of the test requisite
     * @return true if the mutant is dead
     *         false if the mutant is alive
     * @throws ArrayIndexOutOfBoundsException if testCase or testReq are out of bounds
     * @throws NullPointerException TEMPORARY
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        Object expected;
        
        try {
            expected = testingMethod.invoke(oracle, parameters[testCase]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            //TODO
            return false;
        }
        
        Object result;
        try {
            result = testingMethod.invoke(mutants[testReq], parameters[testCase]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            //TODO
            return false;
        }
        
        return !expected.equals(result);
    }

    /**
     * Quantity of test cases
     * @return the quantity 
     */
    @Override
    public int getTestCaseTotal() {
        return parameters.length;
    }

    /**
     * Quantity of requirements in the mutants
     * @return the quantity
     */
    @Override
    public int getRequirementTotal() {
        return mutants.length;
    }
}
