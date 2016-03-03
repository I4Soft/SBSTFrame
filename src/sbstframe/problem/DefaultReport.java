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
package sbstframe.problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Reads the benchmark file using the proposed layout
 * DO NOT USE IT WITH CACHED REPORT
 * This class already has a storage for test results
 * @see ProblemInterface
 * @author Andre/Beatriz/Bruno/Eduardo
 */
public class DefaultReport implements ProblemInterface {

    private String benchmarkPath;
    private int tcTotal; //Number of test cases
    private int reqTotal; //Number of requirements
    private int worthlessReqTotal; //Number of worthless requirements
    private HashMap<Integer, HashMap<Integer, Integer>> tests; //tests<reqNum, Hash testCase <tcNum, reqStatus>>
    private double scoreMax;

    /**
     * Default constructor for {@code ProblemInterface}
     * @param path path to benchmark, ex: benchmarks/bubcorrecto.csv"
     * @param scoreMax Max score to be considerated
     * @param worthlessReqTotal  quantity of useless test requirements (whose 
     * will allways return the same test result as some other test requirement)
     */
    public DefaultReport(String path, double scoreMax, int worthlessReqTotal) {
        this.benchmarkPath = path;
        this.tcTotal = -1;
        this.reqTotal = -1;
        this.worthlessReqTotal = worthlessReqTotal;
        this.tests = new HashMap<>();
        this.scoreMax = scoreMax;
        readBenchmarkFile();
    }

    public DefaultReport(Benchmarks benchmark) {
        this.benchmarkPath = "benchmarks/";
        this.tcTotal = -1;
        this.reqTotal = -1;
        this.scoreMax = 1.0;
        switch (benchmark) {
            case bubcorrecto:
                benchmarkPath += "bubcorrecto.csv";
                worthlessReqTotal = 12;
                break;
            case fourballs:
                benchmarkPath += "fourballs.csv";
                worthlessReqTotal = 44;
                break;
            case mid:
                benchmarkPath += "mid.csv";
                worthlessReqTotal = 43;
                break;
            case trityp:
                benchmarkPath += "trityp.csv";
                worthlessReqTotal = 70;
                break;
            case cal:
                benchmarkPath += "cal.csv";
                worthlessReqTotal = 344;
                this.scoreMax = 0.99742;
                break;
            case comm:
                benchmarkPath += "comm.csv";
                worthlessReqTotal = 222;
                break;
            case look:
                benchmarkPath += "look.csv";
                worthlessReqTotal = 233;
                break;
            case uniq:
                benchmarkPath += "uniq.csv";
                worthlessReqTotal = 224;
                break;

            case spaceResultByMethod:
                benchmarkPath += "spaceResultByMethod.csv";
                worthlessReqTotal = 0;
                break;

            case spaceResultByLine:
                benchmarkPath += "spaceResultByLine.csv";
                worthlessReqTotal = 0;
                break;

        }
        tests = new HashMap<>();
        readBenchmarkFile();
    }

    /**
     * reads and loads all tests in benchmark file, stores in {@code tests}
     */
    private void readBenchmarkFile() {
        try {
            BufferedReader buff = new BufferedReader(new FileReader(benchmarkPath));
            String temp, tokens[];

            if ((temp = buff.readLine()) == null) {
                System.err.println("Benchmark at " + benchmarkPath + " is empty");
            }
            this.tcTotal = temp.length() / 2;
            System.out.println("Total de Caso de teste: " + tcTotal);
            int reqCount = 0;
            do {
                HashMap<Integer, Integer> tcResult = new HashMap<>(); //<tcNum, reqStatus>

                tokens = temp.split(";");

                for (int i = 0; i < tokens.length; i++) {
                    tcResult.put((Integer) i, Integer.valueOf(tokens[i]));
                }

                this.tests.put(reqCount, tcResult); //link requirements with its list
                reqCount++;
            } while ((temp = buff.readLine()) != null);

            this.reqTotal = reqCount;
            System.out.println("Total de Metodos: " + reqTotal);
            buff.close();

        } catch (Exception e) {
            System.out.println("\nError reading file: " + benchmarkPath);
        }
    }

    /**
     * Path to benchmark path
     * @return path
     */
    @Override
    public String getBenchmarkPath() {
        return this.benchmarkPath;
    }

    /**
     * Quantity of test cases
     * @return count
     */
    @Override
    public int getTestCaseTotal() {
        return this.tcTotal;
    }

    /**
     * Quantity of test requirements
     * @return count
     */
    @Override
    public int getRequirementTotal() {
        return this.reqTotal;
    }

    /**
     * Default implementation of the {@see ProblemInterface.getTest}
     *
     * @see ProblemInterface.getTest
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        return this.tests.get(testReq).get(testCase) == 1;
    }

    /**
     * Quantity of repeated test cases
     * @return 
     */
    @Override
    public int getWorthlessReqTotal() {
        return this.worthlessReqTotal;
    }

    /**
     * Max score to be considerated
     * @return 
     */
    @Override
    public double getScoreMax() {
        return this.scoreMax;
    }

}
