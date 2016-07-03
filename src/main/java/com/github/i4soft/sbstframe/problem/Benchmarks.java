/**
 *
 * Copyright (c) 2014-2015,
 *	Beatriz Proto          <beatrizproto@gmail.com>
 *	Bruno Machado          <brunonmachado@outlook.com>	
 *	André Lôbo             <andre.assis.lobo@gmail.com> 
 *	Celso Camilo           <celso@inf.ufg.br>
 *      Auri Vincenzi          <auri@inf.ufg.br>                                             
 *	Cassio Rodrigues       <cassio@inf.ufg.br>
 *	Plinio Júnior          <plinio@inf.ufg.br>
 *      Eduardo Horst          <eduardoquijano2@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. The names of the contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package com.github.i4soft.sbstframe.problem;

/**
 * Enum containing the default benchmarks supported by SBSTFrame
 */
public enum Benchmarks {
    bubcorrecto(255,79,"bubcorrecto.csv"), //worthlessReqTotal = 12; 
    fourballs(96,211,"fourballs.csv"), //worthlessReqTotal = 44;
    mid(125,180,"mid.csv"), //worthlessReqTotal = 43;
    trityp(216,308,"trityp.csv"), //worthlessReqTotal = 70;
    cal(2000,4621,"cal.csv"), //worthlessReqTotal = 344; scoreMax = 0.99742;
    comm(801,1868,"comm.csv"), //worthlessReqTotal = 222;
    look(255,1979,"look.csv"), //worthlessReqTotal = 233;
    uniq(490,1617,"uniq.csv"), //worthlessReqTotal = 224;
    spaceResultByLine(13497,3656,"spaceResultByMethod.csv"), 
    spaceResultByMethod(13497,135,"spaceResultByLine.csv");
    
    /**
     * Root folder where default benchmarks are hold
     */
    private static final String BENCH_FOLDER_PATH = "benchmarks/";
    
    /**
     * Quantity of test cases
     */
    private final int totalTestCase;
    
    /**
     * Quantity of test requirements
     */
    private final int totalTestReq;
    
    /**
     * Path to benchmark file
     */
    private final String path;

    /**
     * Default construtctor for the Benchmarks
     * Holds the necessary information for reading and using an default benchmark
     * @param totalTestCase Quantity of test Cases
     * @param totalTestReq Quantity of test Requirements
     * @param fileName Name of the benchmark file
     */
    private Benchmarks(int totalTestCase, int totalTestReq, String fileName) {
        this.totalTestCase = totalTestCase;
        this.totalTestReq = totalTestReq;
        this.path = BENCH_FOLDER_PATH + fileName;
    }

    /**
     * Getter to property BENCH_FOLDER
     * @return BENCH_FOLDER
     */
    public static String getBENCH_FOLDER() {
        return BENCH_FOLDER_PATH;
    }

    /**
     * Getter to property totalTestCase
     * @return totalTestCase
     */
    public int getTotalTestCase() {
        return totalTestCase;
    }

    /**
     * Getter to property totalTestReq
     * @return totalTestReq
     */
    public int getTotalTestReq() {
        return totalTestReq;
    }

    /**
     * Getter to property path
     * @return path
     */
    public String getPath() {
        return path;
    }
}