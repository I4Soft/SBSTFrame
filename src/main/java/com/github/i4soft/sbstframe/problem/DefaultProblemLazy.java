/**
 *
 * Copyright (c) 2014-2015, 
 * Beatriz Proto          <beatrizproto@gmail.com>
 * Bruno Machado          <brunonmachado@outlook.com>
 * André Lôbo             <andre.assis.lobo@gmail.com>
 * Celso Camilo           <celso@inf.ufg.br>
 * Auri Vincenzi          <auri@inf.ufg.br>
 * Cassio Rodrigues       <cassio@inf.ufg.br>
 * Plinio Júnior          <plinio@inf.ufg.br>
 * Eduardo Horst          <eduardoquijano2@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. The names of the contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package com.github.i4soft.sbstframe.problem;

import java.io.FileReader;
import java.io.IOException;

/**
 * Reads the benchmark file using the proposed layout
 * @see IProblem
 * @author Andre/Beatriz/Bruno/Eduardo
 */
public class DefaultProblemLazy implements IProblem {
    public final static char CASE_SEPARATOR = ';'; //Separator of testCases in benchmark
    public final static char REQ_SEPARATOR = '\n'; //Separator of testRequirements in benchmark 
    public final static char TEST_PASSED = '1'; //Mark test passed
    public final static char TEST_FAILED = '0'; //Mark test failed
    
    private final String benchmarkPath; //Path to benchmark file
    private int tcTotal; //Number of test cases
    private int reqTotal; //Number of requirements
    private int worthlessReqTotal; //Number of worthless requirements
    private double scoreMax; //Maximum score obtainable from this subset
    
    /**
     * Default constructor for {@code ProblemInterface}
     * @param path path to benchmark, ex: benchmarks/bubcorrecto.csv"
     * @param scoreMax Max score to be considerated
     * @param worthlessReqTotal  quantity of useless test requirements (whose 
     * will allways return the same test result as some other test requirement)
     * @throws java.io.IOException if a problem is found on reading the file
     */
    public DefaultProblemLazy(String path, double scoreMax, int worthlessReqTotal) throws IOException {
        this.benchmarkPath = path;
        this.worthlessReqTotal = worthlessReqTotal;
        this.scoreMax = scoreMax;
        sizeFile();
    }

    /**
     * Default constructor for ProblemInterface using a default benchmark
     * @param benchmark non null benchmark
     * @throws NullPointerException if benchmark is null
     */
    public DefaultProblemLazy(Benchmarks benchmark) {
        this.benchmarkPath = benchmark.getPath();
        this.tcTotal = benchmark.getTotalTestCase();
        this.reqTotal = benchmark.getTotalTestReq();
    }

    /**
     * Reads the benchmarkPath file in order to size it.
     * @throws NoSuchElementException if benchmark path file is empty
     * @throws IOException if benchmarkPath file doesn't exists
     */
    private void sizeFile() throws IOException {
        try (FileReader reader = new FileReader(benchmarkPath)) {
            reqTotal = tcTotal = 0;
            boolean lineOver = false;

            while(reader.ready() && !lineOver) switch (reader.read()) {
                case CASE_SEPARATOR: tcTotal++; break;
                case REQ_SEPARATOR: lineOver = true; break;
                case TEST_FAILED: 
                case TEST_PASSED: break;
                default: throw new RuntimeException();
            }
            while(reader.ready()) if(reader.read() == REQ_SEPARATOR) reqTotal++;
        }
    }
    
    /**
     * Path to benchmark path
     * @return path
     */
    public final String getBenchmarkPath() {
        return this.benchmarkPath;
    }

    /**
     * Quantity of test cases
     * @return count
     */
    @Override
    public final int getTestCaseTotal() {
        return this.tcTotal;
    }

    /**
     * Quantity of test requirements
     * @return count
     */
    @Override
    public final int getRequirementTotal() {
        return this.reqTotal;
    }

    /**
     * Default implementation of the {@see ProblemInterface.getTest}
     *
     * @see ProblemInterface.getTest
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        try(FileReader reader = new FileReader(benchmarkPath)) {
            reader.skip((tcTotal*2 + 1) * testReq + 2 * testCase);
            switch(reader.read()) {
                case TEST_PASSED: return true;
                case TEST_FAILED: return false;
                default: throw new RuntimeException();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Quantity of repeated test cases
     * @return 
     */
    public int getWorthlessReqTotal() {
        return this.worthlessReqTotal;
    }

    /**
     * Max score to be considerated
     * @return 
     */
    public double getScoreMax() {
        return this.scoreMax;
    }
}
