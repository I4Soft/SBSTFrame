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

import java.security.InvalidParameterException;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * Implements an ProblemInterface in order to use SBSTFrame with {@see Junit}
 * @author Eduardo Horst
 */
public class JunitProblem implements IProblem{   
    /**
     * Holds all the test suites, were each one contains all test cases for one testRequisite
     */
    private final TestSuite[] testCasesOnTestRequirements; //Stores the testSuites (one for each mutant
    
    /**
     * Holds the quantity of equal test cases
     */
    private final int WorthLessReq;
    
    /**
     * Holds the best score of a test
     */
    private final double scoreMax;
    
    /**
     * Default Constructor of the {@see JunitReport}
     * @param testCasesOnTestRequirements all test cases against all mutants, each {@see TestSuite}
     * @param WorthLessReq the quantity of equal test cases
     * @param scoreMax the max score of any given test case
     * @throws NullPointerException if testCasesOnMutants is null
     * @throws InvalidParameterException if the quantity of tests in any TestSuite of
     * testCaseOnMutants differs
     */
    public JunitProblem(TestSuite[] testCasesOnTestRequirements, int WorthLessReq, double scoreMax) throws NullPointerException, InvalidParameterException {
        //Integrity test, all mutants must have the same quantity of Tests since it is spected for them to run the same tests
        int qntTestCase = testCasesOnTestRequirements[0].testCount();
        for(int mutantIndex = 0; mutantIndex < testCasesOnTestRequirements.length; mutantIndex++) {
            if(testCasesOnTestRequirements[mutantIndex].testCount() != qntTestCase) {
                throw new InvalidParameterException("The quantity of test cases"
                        + "difer from test requirement 0 and " + mutantIndex);
            }
        }
        
        this.testCasesOnTestRequirements = testCasesOnTestRequirements;
        this.WorthLessReq = WorthLessReq;
        this.scoreMax = scoreMax;
        
    }

    /**
     * Gets test result of test case in the test requisite.
     * @param testCase test case index
     * @param testReq test requisite index
     * @return {@code true} if the test was covered
     *         {@code false} if the test wasn't covered
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        TestResult result;
        result = new TestResult();
        
        TestSuite testReqContainer;
        testReqContainer = testCasesOnTestRequirements[testReq];

        Test myTest;
        myTest = testReqContainer.testAt(testCase);
        myTest.run(result);
        
        return result.wasSuccessful();
    }

    /**
     * Counts the quantity of test cases
     * @return the count
     */
    @Override
    public int getTestCaseTotal() {
        return testCasesOnTestRequirements[0].testCount();
    }

    /**
     * Counts the quantity of test requirements
     * @return the count
     */
    @Override
    public int getRequirementTotal() {
        return testCasesOnTestRequirements.length;
    }

}
