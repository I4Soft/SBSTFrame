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
 * DO NOT USE WITH CACHED REPORT, this implementation has it's own way of storing
 * data and thus doesn't need to be cached
 * @see ProblemInterface
 * @author Andre/Beatriz/Bruno/Eduardo
 */
public class DefaultProblem extends DefaultProblemLazy {

    private final boolean[][] tests; //test[req][tc]
    
    /**
     * Constructor for Default Report
     * @param path
     * @param scoreMax
     * @param worthlessReqTotal
     * @throws IOException 
     */
    public DefaultProblem(String path, double scoreMax, int worthlessReqTotal) throws IOException {
        super(path, scoreMax, worthlessReqTotal);
        tests = new boolean[getRequirementTotal()][getTestCaseTotal()];
        readBenchmarkFile();
    }

    /**
     * Constructor for Default Report
     * @param benchmark
     * @throws IOException 
     */
    public DefaultProblem(Benchmarks benchmark) throws IOException {
        super(benchmark);
        tests = new boolean[getRequirementTotal()][getTestCaseTotal()];
        readBenchmarkFile();
    }

    /**
     * Reads the benchark file and fills tests array
     * @throws RuntimeException if file is an invalid file
     * @throws IOException 
     */
    private void readBenchmarkFile() throws IOException {
        FileReader reader = new FileReader(getBenchmarkPath());
        int tc, req;
        tc = req = 0;
        final int reqTotal = getRequirementTotal();
        while(req < reqTotal) {
            switch (reader.read()) {
                case TEST_FAILED: tests[req][tc] = false; break;
                case TEST_PASSED: tests[req][tc] = true;  break;
                case CASE_SEPARATOR: tc++; break;
                case REQ_SEPARATOR: req++; tc = 0; break;
                default: throw new RuntimeException("BAD FILE");
            }
        }
    }

    /**
     * Returns test result
     * @param testCase
     * @param testReq
     * @return 
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        return tests[testReq][testCase];
    }
}
