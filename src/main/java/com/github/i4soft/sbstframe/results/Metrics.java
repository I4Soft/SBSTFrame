/**
 *
 * Copyright (c) 2014-2015,
 *	Beatriz Proto          <beatrizproto@gmail.com>
 *	Bruno Machado          <brunonmachado@outlook.com>	
 *	André Lôbo             <andre.assis.lobo@gmail.com> 
 *	Celso Camilo           <celso@inf.ufg.br>
 *  Auri Vincenzi          <auri@inf.ufg.br>                                             
 *	Cassio Rodrigues       <cassio@inf.ufg.br>
 *	Plinio Júnior          <plinio@inf.ufg.br
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
package com.github.i4soft.sbstframe.results;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Metric reader class for experiments
 * 
 * @author Eduardo Horst <Eddyosos at eduardoquijano2@gmail.com>
 */
public class Metrics {
    /**
     * Output diretory path
     */
    private String outDir;
    
    /**
     * Average score of the experiment
     */
    private double averageScore;
    
    /**
     * Max score of the experiment
     */
    private double maxScore;
    
    /**
     * Average time of the experiment
     */
    private double averageTime;
    
    /**
     * Standard deviation of the experiment
     */
    private double stdDeviation;
    
    /**
     * Quantity of calls for the evaluation function
     */
    private int evaluatorCallsTotal;
    
    /**
     * chart data
     */
    private XYSeries graphData;
    
    /**
     * chart instance
     */
    private JFreeChart chart;
    
    /**
     * output file
     */
    private FileWriter outFile;
    
    /**
     * Info about the algorithm
     */
    private String algInfo;
    
    /**
     * Best test subset
     */
    private int[] bestSubset;
 //   private String operator;

    /**
     * Default constructor
     * @param outDir Diretory for outputing the result
     */
    public Metrics(String outDir){
        this.outDir = outDir;
        this.maxScore = 0;
        this.bestSubset = null;
        this.averageScore = 0;
        this.averageTime = 0;
        this.stdDeviation = 0;
        this.outFile = null;
        
     //   this.operator = null;
    }
    
    /**
     * Exposes graphData for overwrite
     * @param graphData to be used by this class
     */
    public void setGraphData(XYSeries graphData){
        this.graphData = graphData;
    }
    
    /**
     * Exposes graphData for reading
     * @return 
     */
    public XYSeries getGraphData() {
        return graphData;
    }
    
    /**
     * Draws a new chart, using graphData, and stores it in chart also outputs 
     * it to file ./results/chart/jpg
     */
    public void drawGraphic(){
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graphData);
        chart = ChartFactory.createXYLineChart(
            "Chart", 
            "Execution/Experiment", 
            "Fitness", 
            dataset, 
            PlotOrientation.VERTICAL,
            false, 
            true, 
            false 
        );

        ChartPanel chPanel = new ChartPanel(chart);
        JPanel panel = new JPanel(new BorderLayout());
        JFrame frame = new JFrame("Graphic");
        frame.setSize(new Dimension(1030, 630));
        frame.setLocationRelativeTo(null);
        panel.setPreferredSize(new Dimension(1000, 600));
        panel.add(chPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            ChartUtilities.saveChartAsJPEG(new File("results/chart.jpg"), chart, 500, 300);
        } catch (IOException e) {
            System.err.println("Error while creating chart.");
        }
    }
    
    /**
     * Saves the parameters in outFile as each column "\column\"
     * @param columns Strings to be saved in outfile
     * @throws IOException if an I/O error occurs
     */
    public void saveOutput(String[] columns) throws IOException{
        for (String str : columns){
            outFile.append("\""+str+"\"");
            outFile.append(";");
        }
        outFile.append("\n");
    }
    
    /**
     * Saves parametes to file to outDir/experiment.csv as
     * \index\ \fitness\            - Header
     * \index\ \fitness\            - Data passed in parameters
     * @param index data to be saved as index
     * @param fitness data to be saved as fitness
     */
    public void saveExecutionOutput(int index, double fitness){        
         try {
            
            String attr[] = {"index", "fitness"};
            createOutputFile(outDir+"experiment.csv");
            saveOutput(attr);
            
            String data[] = {String.valueOf(index), String.format("%.7f",fitness)};
            saveOutput(data);
        } catch (IOException e) {
             System.out.println(e);
        }
    }
    
    /**
     * Saves output with a call of saveOutput(data)
     * where data is [maxScore, averageScore, stdDeviation, averageTime, 
     * evaluatorCallsTotal]
     * @throws IOException if an I/O error occurs 
     */
    public void saveExperimentOutput() throws IOException{
        String attr[] = {"maxFitness", "averageScore", "stdDeviation",
            "time", "fitnessCalls", "algorithmInfo"};
        createOutputFile(outDir+"experiment.csv");
        saveOutput(attr);
        
        String data[] = {
            String.format("%.7f", maxScore),
            String.format("%.7f", averageScore),
            String.format("%.7f", stdDeviation),
            String.format("%.4f", averageTime),
            String.format("%d", evaluatorCallsTotal),
            algInfo
        };
        
        saveOutput(data);
    }

    /**
     * Exposes maxScore to overwrite
     * @param maxScore
     */
    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * Exposes stdDeviation to overwrite
     * @param stdDeviation 
     */
    public void setStdDeviation(double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }
    
    /**
     * Exposes stdDeviation to overwrite
     * @param evaluatorCallsTotal 
     */
    public void setEvaluatorCallsTotal(int evaluatorCallsTotal) {
        this.evaluatorCallsTotal = evaluatorCallsTotal;
    }
    
    /**
     * Exposes outFile to read
     * @return outFile
     */
    public FileWriter getOutFile() {
        return outFile;
    }

    /**
     * Garants that outFile is a valid Writer to path passed as parameter
     * If path is a file, path is open to append
     * If path doesn't exists a new file is created with such path
     * @param path to outputfile
     * @throws IOException if I/O exeption occurs
     */
    public void createOutputFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            if(file.createNewFile()){
                System.out.println("Creating output file at "+path);
                this.outFile = new FileWriter(path);
                
            }

        }
        else{ //append
            outFile = new FileWriter(path, true);
        }

    }

    /**
     * Exposes algInfo to read
     * @return algInfo
     */
    public String getAlgInfo() {
        return algInfo;
    }

    /**
     * Exposes algInfo to overwrite
     * @param algInfo
     */
    public void setAlgInfo(String algInfo) {
        this.algInfo = algInfo;
    }
    
    /**
     * Exposes averageScore to read
     * @return averageScore
     */
    public double getAverageScore() {
        return averageScore;
    }
    
    /**
     * Exposes averageScore to overwrite
     * @param averageScore 
     */
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Exposes averageTime to read
     * @return averageTime
     */
    public double getAverageTime() {
        return averageTime;
    }

    /**
     * Exposes averageTime to overwrite
     * @param averageTime 
     */
    public void setAverageTime(double averageTime) {
        this.averageTime = averageTime;
    }
    
    /**
     * Constructs a string representation of this
     * @return Score: averageScore; Time: averageTime; BestSubset:bestSubset[0] 
     * bestSubset[1] bestSubset[2]...
     */
    @Override
    public String toString(){
        String ret = "\n\nScore: "+String.format("%.6f", this.averageScore)+"; Time: "+
                String.format("%.4f", this.averageTime)+" Best Subset:";
        for(int tc : bestSubset)
            ret += " "+ tc;
        return ret;
        
    }

    /**
     * Exposes bestSubset to overwrite
     * @param bestSubset
     */
    public void setBestSubset(int[] bestSubset) {
        this.bestSubset = bestSubset;
    }
    
}
