/*
 * Copyright 2016 Eduardo Horst <Eddyosos at eduardoquijano2@gmail.com>.
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

/**
 * Class to abstract the concept of Decorator to IProblem
 * @author Eduardo Horst
 */
public abstract class ProblemDecorator implements IProblem {
    /**
     * problem to be decorated
     */
    protected IProblem problem;

    /**
     * Constructor for Problem Decorator
     * @param problem 
     */
    public ProblemDecorator(IProblem problem) {
        this.problem = problem;
    }

    /**
     * Delegates the call to problem (past as paramter in constructor)
     * @param testCase
     * @param testReq
     * @return 
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        return problem.getTest(testCase, testReq);
    }

    /**
     * Delegates the call to problem (past as paramter in constructor)
     * @return 
     */
    @Override
    public int getTestCaseTotal() {
        return problem.getTestCaseTotal();
    }
    
    /**
     * Delegates the call to problem (past as paramter in constructor)
     * @return 
     */
    @Override
    public int getRequirementTotal() {
        return problem.getRequirementTotal();
    }
    
}
