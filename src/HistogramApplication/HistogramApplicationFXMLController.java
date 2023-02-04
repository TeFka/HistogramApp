/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package HistogramApplication;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.scene.layout.StackPane;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

import javafx.scene.control.MenuItem;

import javafx.stage.FileChooser;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.chart.NumberAxis;

import javafx.event.ActionEvent;

import javafx.embed.swing.SwingFXUtils;

//Class HistogramApplicationFXMLController
//A controller class for operation of the GUI
public class HistogramApplicationFXMLController {
    
    //the used model instance
    private HistogramApplicationGUIModel modelInstance;
    
    //data series to be added to charts
    private XYChart.Series<String, Number> theBarDataSeries;
    private XYChart.Series<String, Number> theLineDataSeries;
    
    CategoryAxis xBarAxis;
    NumberAxis yBarAxis;
    
    CategoryAxis xLineAxis;
    NumberAxis yLineAxis;
    
    private BarChart<String, Number> mainPlotBarArea;

    private LineChart<String, Number> mainPlotLineArea;
    
    //alert used for notification about issues
    private Alert notificationAlert;
    
    //label for alert
    private Label notificationAlertLabel;
    
    //Defaut text to add to diagram title
    private String defaultTitle;
    
    //FXML defined variables
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    
    @FXML
    private StackPane chartStack;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="barChartCategoryChartAxis"
    private CategoryAxis barChartCategoryChartAxis; // Value injected by FXMLLoader

    @FXML // fx:id="barChartCheck"
    private CheckBox barChartCheck; // Value injected by FXMLLoader

    @FXML // fx:id="barChartNumberAxis"
    private NumberAxis barChartNumberAxis; // Value injected by FXMLLoader

    @FXML // fx:id="binFormula"
    private ToggleGroup binFormula; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="dataBasicInfo"
    private TextArea dataBasicInfo; // Value injected by FXMLLoader

    @FXML // fx:id="dataBarFitCheck"
    private CheckBox dataBarFitCheck; // Value injected by FXMLLoader
    
    @FXML // fx:id="dataLineFitCheck"
    private CheckBox dataLineFitCheck; // Value injected by FXMLLoader

    @FXML // fx:id="inputDataDetails"
    private TextArea inputDataDetails; // Value injected by FXMLLoader

    @FXML // fx:id="lineChartCheck"
    private CheckBox lineChartCheck; // Value injected by FXMLLoader

    @FXML // fx:id="normalizationCheck"
    private CheckBox normalizationCheck; // Value injected by FXMLLoader

    @FXML // fx:id="openFIleMenuButton"
    private MenuItem openFIleMenuButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="quitMenuButton"
    private MenuItem quitMenuButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="clearMenuButton"
    private MenuItem clearMenuButton; // Value injected by FXMLLoader
  
    @FXML // fx:id="openFileButton"
    private Button openFileButton; // Value injected by FXMLLoader

    @FXML // fx:id="riceRuleOption"
    private RadioButton riceRuleOption; // Value injected by FXMLLoader

    @FXML // fx:id="saveFileButton"
    private Button saveFileButton; // Value injected by FXMLLoader

    @FXML // fx:id="saveFileMenuButton"
    private MenuItem saveFileMenuButton; // Value injected by FXMLLoader

    @FXML // fx:id="squareRootOption"
    private RadioButton squareRootOption; // Value injected by FXMLLoader

    @FXML // fx:id="sturgesFormulaOption"
    private RadioButton sturgesFormulaOption; // Value injected by FXMLLoader
    
    @FXML // fx:id="fitParametersInfo"
    private TextArea fitParametersInfo; // Value injected by FXMLLoader

    //FXML Function clearAction()
    //Clears all relevant data and nodes
    //Argument: FXML event
    //Return: None
    @FXML
    void clearAction(ActionEvent event) {
        
        clearData();
        
    }
    
    //FXML Function normalizeAction()
    //Performs data normalisation function
    //Argument: FXML event
    //Return: None
    @FXML
    void normalizeAction(ActionEvent event) {
        
        //perform normalisation operation
        modificationUpdate();
        
        //update charts
        handleCharts();
        
    }
    
    //FXML Function dataFitAction()
    //Performs data fitting function
    //Argument: FXML event
    //Return: None
    @FXML
    void dataFitAction(ActionEvent event){
        
        //perform data fitting operation
        modificationUpdate();
        
        //update charts
        handleCharts();    
        
    }
    
    //FXML Function openFileAction()
    //Allows to choose and open a data file
    // to load data to process
    //Argument: FXML event
    //Return: None
    @FXML
    void openFileAction(ActionEvent event) {
        
        //use File Chooser to open a file explorer
        //User chooses a fie
        //Operation is done in controller, because file chooser is a part of javafx stage
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setTitle("Save File");
        File file = fileChooser.showOpenDialog(saveFileButton.getScene().getWindow());
        
        //confirm that file is viable
        if (file != null) {
            
            //read the data file
            if(modelInstance.readDataFile(file)){
                
                //perform the data handling process
                handleDataProcess();
                
                //open popup notifying the user that file was loaded properly
                openPopup("File Loaded Successfully");
            }
            else{
                //open popup notifying the user about an issues
                openPopup("Could not read the data file");
            }
        }
        else{
            //open popup notifying the user about an issues
            openPopup("No viable file specified");
        }
    }
    
    //FXML Function saveFileAction()
    //Allows to choose a file location and save currently
    //active data as a PNG image or text file
    //Argument: FXML event
    //Return: None
    @FXML
    void saveFileAction(ActionEvent event) {
        
        //use File Chooser to open a file explorer
        //User chooses a fie
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG Files", "*.png"), new ExtensionFilter("TXT Files", "*.txt"));
        File file = fileChooser.showSaveDialog(saveFileButton.getScene().getWindow());
        
        //confirm that file is viable
        if (file != null) {
            //set the ending of file name to '.png' if 
            //the user did not specify it yet
            if(file.toString().endsWith(".png")){

                //retrieve the snapshot of the stack pane with charts
                WritableImage chartImage = chartStack.snapshot(new SnapshotParameters(), null);

                //try to use image writer
                try {
                    //save the snapshot in the file location chosen by the user
                    ImageIO.write(SwingFXUtils.fromFXImage(chartImage, null), "png", file);
                    
                    //notify user that saving was completed
                    openPopup("PNG image Saved Successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                    //open popup notifying the user about an issues
                    openPopup("Could not save file");
                }
            }
            else if(file.toString().endsWith(".txt")){
               //confirm if file was saved successfully
               if(modelInstance.writeData(file)){
                   
                  //notify user that saving was completed
                  openPopup("Text file Saved Succefully");
               }
               else{
                   //open popup notifying the user about an issues
                  openPopup("Could not save file"); 
               }
                
            }
        }
        else{
            //open popup notifying the user about an issues
            openPopup("No viable file specified");
        }
            
    }
    
    //FXML Function squareRootChosen()
    //Change the bin calculation rule to square root
    //Argument: FXML event
    //Return: None
    @FXML
    void squareRootChosen(ActionEvent event) {
        
        //set the new rule
        modelInstance.setBinRule(BinRule.SQUARE_ROOT);
        
        //check if data is available already
        if(modelInstance.dataIsAvailable()){
            
            //handle the latest data
            handleDataProcess();
        }
        
    }
    
    //FXML Function sturgesFormulaChosen()
    //Change the bin calculation rule to Sturges Formula
    //Argument: FXML event
    //Return: None
    @FXML
    void sturgesFormulaChosen(ActionEvent event) {
        
        //set the new rule
        modelInstance.setBinRule(BinRule.STURGES_FORMULA);
        
        //check if data is available already
        if(modelInstance.dataIsAvailable()){
            
            //handle the latest data
            handleDataProcess();
        }
        
        
    }
    
    //FXML Function riceRuleChosen()
    //Change the bin calculation rule to Rice Rule
    //Argument: FXML event
    //Return: None
    @FXML
    void riceRuleChosen(ActionEvent event) {
        
        //set the new rule
        modelInstance.setBinRule(BinRule.RICE_RULE);
        
        //check if data is available already
        if(modelInstance.dataIsAvailable()){
            
            //handle the latest data
            handleDataProcess();
        }
    }
    
    //FXML Function showBarChartAction()
    //Make bar chart visible
    //Argument: FXML event
    //Return: None
    @FXML
    void showBarChartAction(ActionEvent event) {
        
        //make bar chart visible
        setBarChartVisibility(barChartCheck.isSelected());
        
        //bar chart iis the base chart in this situation, thus
        //line chart visibility is affected as well and needs to be updated
        setLineChartVisibility(lineChartCheck.isSelected(), barChartCheck.isSelected());
        
    }
    
    //FXML Function showLinerChartAction()
    //Make line chart visible and operating
    //Argument: FXML event
    //Return: None
    @FXML
    void showLineChartAction(ActionEvent event) {
        
        //make line chart visible
        setLineChartVisibility(lineChartCheck.isSelected(), barChartCheck.isSelected());
        
    }
    
    //FXML Function quitAction()
    //Quit application and close the window
    //Argument: FXML event
    //Return: None
    @FXML
    void quitAction(ActionEvent event) {
        
        //exit the application
        System.exit(0);
        
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert barChartCategoryChartAxis != null : "fx:id=\"barChartCategoryChartAxis\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert barChartCheck != null : "fx:id=\"barChartCheck\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert barChartNumberAxis != null : "fx:id=\"barChartNumberAxis\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert binFormula != null : "fx:id=\"binFormula\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert dataBasicInfo != null : "fx:id=\"dataBasicInfo\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert dataBarFitCheck != null : "fx:id=\"dataBarFitCheck\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert dataLineFitCheck != null : "fx:id=\"dataBarFitCheck\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert inputDataDetails != null : "fx:id=\"inputDataDetails\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert lineChartCheck != null : "fx:id=\"lineChartCheck\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert normalizationCheck != null : "fx:id=\"normalizationCheck\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert openFIleMenuButton != null : "fx:id=\"openFIleMenuButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert openFileButton != null : "fx:id=\"openFileButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert riceRuleOption != null : "fx:id=\"riceRuleOption\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert saveFileButton != null : "fx:id=\"saveFileButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert saveFileMenuButton != null : "fx:id=\"saveFileMenuButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert squareRootOption != null : "fx:id=\"squareRootOption\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert sturgesFormulaOption != null : "fx:id=\"sturgesFormulaOption\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert fitParametersInfo != null : "fx:id=\"fitParametersInfo\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert clearMenuButton != null : "fx:id=\"clearMenuButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        assert quitMenuButton != null : "fx:id=\"quitMenuButton\" was not injected: check your FXML file 'theGUI.fxml'.";
        
        //initialize model class
        modelInstance = new HistogramApplicationGUIModel();
        
        //perform all graph setup required
        setupGraphs();
        
        //notification Alert setup
        makePopup();
        
        //create initial test data for chart
        List<Double> exampleData = new ArrayList<Double>();
        for(int i = 0;i<100;i++){
            
            exampleData.add((double)i);
            
        }
        
        //set test data
        modelInstance.setLatestData(exampleData);
        
        //handle test data
        handleDataProcess();
        
    }
    
    //Function setupGraphs()
    //Sets up all graphs initial parameters
    //and initializes data components
    //Argument: None
    //Return: None
    void setupGraphs(){
        
        //set default title text for diagram
        defaultTitle = "Deviation Histogram";
                
        //initialize data series for graphs
        theBarDataSeries = new XYChart.Series<String, Number>();
        theLineDataSeries = new XYChart.Series<String, Number>();      
        
        xBarAxis = new CategoryAxis();
        yBarAxis = new NumberAxis();
        
        xLineAxis = new CategoryAxis();
        yLineAxis = new NumberAxis();
        
        mainPlotBarArea = new BarChart<String, Number>(xBarAxis, yBarAxis);

        mainPlotLineArea = new LineChart<String, Number>(xLineAxis, yLineAxis);
        
        //The bar chart is the base chart in this situation.
        //If severtal charts need to be stacked and presented,]
        //the background of the bar chart will be used and all
        //other charts wil be transparent.
        
        //turn off animtation as it have proven to cause bugs
        //in data presentation
        mainPlotBarArea.setAnimated(false);
        //turn off legend as it is unecessery in this situation
        //and can provide confusion
        mainPlotBarArea.setLegendVisible(false);
        
        // set bar gap and category gap to zero to create a histogram look
        mainPlotBarArea.setBarGap(0);
        mainPlotBarArea.setCategoryGap(0);
        
        //set default background color of bar chart
        mainPlotBarArea.lookup(".chart-plot-background").setStyle("-fx-background-color: LIGHTGRAY;");
        
        mainPlotBarArea.getStylesheets().addAll(getClass().getResource("HistogramApplication_style.css").toExternalForm());
        mainPlotLineArea.getStylesheets().addAll(getClass().getResource("HistogramApplication_style.css").toExternalForm());
        
        //turn off animtation as it have proven to cause bugs
        mainPlotLineArea.setAnimated(false);
        //turn off legend as it is unecessery in this situation
        mainPlotLineArea.setLegendVisible(false);
        
        //make line chart invisible initially
        setLineChartVisibility(false, true);
        
        //set initial chart titles
        mainPlotBarArea.setTitle(defaultTitle+". "+"Test Data");
        mainPlotLineArea.setTitle(defaultTitle+". "+"Test Data");
        
        //set axis labels
        mainPlotBarArea.getXAxis().setLabel("Measurement Data*1.0");
        mainPlotBarArea.getYAxis().setLabel("Frequency");
        mainPlotLineArea.getXAxis().setLabel("Measurement Data*1.0");
        mainPlotLineArea.getYAxis().setLabel("Frequency");
        
        chartStack.getChildren().addAll( mainPlotBarArea, mainPlotLineArea);
        
    }
    
    //Function makePopup()
    //Set up notification popup for future use
    //Argument: None
    //Return: None
    void makePopup(){
        
        //notificationAlert setup
        notificationAlert = new Alert(Alert.AlertType.INFORMATION);
        
        //set initial information
        notificationAlert.setTitle("An issue occured");
        notificationAlert.setContentText("Information");
        
    }
    
    //Function openPopup()
    //Open a popup with a specified message
    //Argument: message - the message to display on the notification
    //Return: None
    void openPopup(String message){
        
        //set text to the notification
        notificationAlert.setContentText(message);
        
        //show the notifiation to user
        notificationAlert.show();
        
    }
    
    //Function handleDataProcess()
    //Handles the whole data process,
    //including all set parameters
    //Argument: None
    //Return: None
    void handleDataProcess(){
        
        //handle latest data at modle level
        modelInstance.handleLatestData();
        
        //apply active modifications
        modificationUpdate();
        
        //update all text areas
        updateBasicDataInfo();
        updateFitParametersInfo();
        
        //setup initial chart data, mostly X data adn labels
        setupCharts();
        
        //update charts with Y data
        handleCharts(); 
        
    }
    
    //Function modificationUpdate()
    //Handles modifications applied to the presented data
    //Argument: None
    //Return: None
    void modificationUpdate(){
        
        //handle data normalization if applicable
        handleDataNormalization();
        
        //handle data fitting if applicable
        handleDataFitting();
        
    }  
    
    //Function handleDataNormalization()
    //Handles the normalization process of latest data
    //Argument: None
    //Return: None
    void handleDataNormalization()
    {
        
        modelInstance.chooseNormalisation(normalizationCheck.isSelected());
        modelInstance.refreshDataFittingStage();
        
    }
    
    //Function handleDataFitting()
    //Handles the fitting process of latest data
    //Argument: None
    //Return: None
    void handleDataFitting(){
        
        //check if data fitting should be done for any chart
        if(dataBarFitCheck.isSelected()||dataLineFitCheck.isSelected()){
            
            //set up fitting data
            modelInstance.handleDataFitting();

        }
        else{
            //clears fitted data
            modelInstance.refreshDataFittingStage();
            
        }
        
        //update text area
        updateFitParametersInfo();
        
    }
    
    //Function clearData()
    //Clears all relevant data and nodes
    //Argument: None
    //Return: None
    void clearData(){
        
         //clear stored data
        modelInstance.clearLatestData();
        
        //clear charts
        mainPlotBarArea.getData().clear();
        mainPlotLineArea.getData().clear();
        
        //set default chart titles
        mainPlotBarArea.setTitle("No Data");
        mainPlotLineArea.setTitle("No Data");
        
        //clear all text areas
        inputDataDetails.clear();
        dataBasicInfo.clear();
        fitParametersInfo.clear();
        
    }
    
    //Function updateBasicDataInfo()
    //Updates the text area showing input data details and basic statistics
    //Argument: None
    //Return: None
    void updateBasicDataInfo(){
        
        //clear previuos text
        inputDataDetails.clear();
        
        //add all parameter values to the main text string
        String addedInputText = "Data Amount:\n" + String.valueOf(modelInstance.getLatestAmountOfData())+"\n";
        addedInputText += "Data scale up:\n" + String.valueOf(modelInstance.getScaleValue())+"\n";
        
        //set text to the text area
        inputDataDetails.setText(addedInputText);  
        
        //clear previuos text
        dataBasicInfo.clear();
        
        //get the value scale for values
        double scale = modelInstance.getScaleValue();
        
        //add all statistic values to the main text string
        String scaleString = String.valueOf(scale);
        String addedText = "Max (m*"+scaleString+"):\n" + String.format("%f", modelInstance.getMax()*scale)+"\n";
        addedText += "Min (m*"+scaleString+"):\n" + String.format("%f", modelInstance.getMin()*scale)+"\n";
        addedText += "Mean (m*"+scaleString+"):\n" + String.format("%f", modelInstance.getDataMean()*scale)+"\n";
        addedText += "Median (m*"+scaleString+"):\n" + String.format("%f", modelInstance.getDataMedian()*scale)+"\n";
        addedText += "Variance (m*("+scaleString+"^2)):\n" + String.format("%f", modelInstance.getDataVariance()*scale*scale)+"\n";
        addedText += "Standart Deviation (m*"+scaleString+"):\n" + String.format("%f", modelInstance.getDataStandartDeviation()*scale)+"\n";
        addedText += "Number Of Bins:\n" + String.valueOf(modelInstance.getNumberOfBins())+"\n";
        
        //set text to the text area
        dataBasicInfo.setText(addedText);                   
        
    }
    
    //Function updateFitParametersInfo()
    //Updates the text area showing fitting parameters of the data
    //Argument: None
    //Return: None
    void updateFitParametersInfo(){
        
        //clear previuos text
        fitParametersInfo.clear();
        
        //check if data fitting is selected
        if(dataBarFitCheck.isSelected()||dataLineFitCheck.isSelected()){
            //get the value scale for values
            double scale = modelInstance.getScaleValue();

            //add all fitting parameter values to the main text string
            String scaleString = String.valueOf(scale);
            String addedText = "Normalization Factor:\n " + String.format("%f", modelInstance.getNormalisationCoefficient())+"\n";
            addedText += "PDF Mean (m*"+scaleString+"):\n " + String.format("%f", modelInstance.getFittedPDFCentre()*scale)+"\n";
            addedText += "PDF Width (m*"+scaleString+"):\n " + String.format("%f", modelInstance.getFittedPDFWidth()*scale)+"\n";

            //set text to the text area
            fitParametersInfo.setText(addedText);                   
        }

    }

    //Function setBarChartVisibility()
    //Handles visibility of the bar chart in case it should not be shown.
    //The bar chart is considered to be main chart and only its background is
    //shown in case of several charts being enabled
    //Argument:
    //      enabled - specification if visibility should be enabled
    //Return: None
    void setBarChartVisibility(boolean enabled){
        
        //set the main visiblity iof the bar chart
        mainPlotBarArea.setVisible(enabled);
        
    }
    
    //Function setLineChartVisibility()
    //Handles visibility of the line chart in case it should not be shown
    //Argument:
    //      enabled - specification if visibility should be enabled
    //      baseChartEnabled - specification if the base chart is enabled,
    //                         line chart is set on top of base chart
    //Return: None
    void setLineChartVisibility(boolean enabled, boolean baseChartEnabled){
        
        //handle background visibility, which should not be shown if the base
        //chart is enabled
        mainPlotLineArea.setAlternativeRowFillVisible(enabled&&(!baseChartEnabled));
        mainPlotLineArea.setAlternativeColumnFillVisible(enabled&&(!baseChartEnabled));
        mainPlotLineArea.setHorizontalGridLinesVisible(enabled&&(!baseChartEnabled));
        mainPlotLineArea.setVerticalGridLinesVisible(enabled&&(!baseChartEnabled));
        
        //set axis label opacity to zero if base chart is active
        mainPlotLineArea.getXAxis().setOpacity((enabled&&(!baseChartEnabled))?1.0:0.0);
        mainPlotLineArea.getYAxis().setOpacity((enabled&&(!baseChartEnabled))?1.0:0.0);
        
        mainPlotLineArea.getYAxis().setAutoRanging(enabled&&(!baseChartEnabled));
        //set visibility using style attribute
        if(enabled&&(!baseChartEnabled)){
            //set default style
            mainPlotLineArea.lookup(".chart-plot-background").setStyle("-fx-background-color: LIGHTGRAY;");
        }
        else{
            //set background style to make background transparent
            mainPlotLineArea.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        }
        
        //set the main visiblity iof the line chart
        mainPlotLineArea.setVisible(enabled);
        
        if(baseChartEnabled){
            
            yLineAxis.setUpperBound(yBarAxis.getUpperBound());
            
        }
    }
    
    //Function handleCharts()
    //Handles data updates of the charts
    //Argument: None
    //Return: None
    void handleCharts(){
        
        //get Y data for bar chart
        getYDataForBarChart();
        
        //get Y data for bar chart
        getYDataForLineChart();
 
    }
    
    //Function setupCharts()
    //Handles inititial setup of new data for charts
    //Argument: None
    //Return: None
    void setupCharts(){
        
        //clear previous bar chart data
        mainPlotBarArea.getData().clear();
        theBarDataSeries.getData().clear();
        
        //clear previous line chart data
        mainPlotLineArea.getData().clear();
        theLineDataSeries.getData().clear();
        
        //retrieve X data used by charts
        List<Double> usedXData = modelInstance.getBinEndings();
        
        //retrieve scal that X values should increased by
        double scale = modelInstance.getScaleValue();
        
        //add initila data to the series objects
        for(int i = 0;i<usedXData.size();i++){
            theBarDataSeries.getData().add(new XYChart.Data(String.format("%.3f", usedXData.get(i)*scale), 0.0));
            theLineDataSeries.getData().add(new XYChart.Data(String.format("%.3f", usedXData.get(i)*scale), 0.0));
        }
        
        //add new data series to data charts
        mainPlotBarArea.getData().add(theBarDataSeries);
        mainPlotLineArea.getData().add(theLineDataSeries);
        
        //update chart parameters with information about latest new data
        updateChartParameters(); 
    }
    
    //Function updateChartParameters()
    //Handles any chart parameter changes
    //Argument: None
    //Return: None
    void updateChartParameters(){
        
        //set titles for charts
        mainPlotBarArea.setTitle(defaultTitle+". "+"Range("+modelInstance.getMin()+" - "+modelInstance.getMax()+")");
        mainPlotLineArea.setTitle(defaultTitle+". "+"Range("+modelInstance.getMin()+" - "+modelInstance.getMax()+")");
        
        //retrieve scale value that is used to simplifiy data
        String scaleString = String.valueOf(modelInstance.getScaleValue());
        mainPlotBarArea.getXAxis().setLabel("Measurement Data(σ*"+scaleString+")");
        mainPlotLineArea.getXAxis().setLabel("Measurement Data(σ*"+scaleString+")");
        
        if(barChartCheck.isSelected()){
            
            yLineAxis.setUpperBound(yBarAxis.getUpperBound());
            
        }
    }
    
    //Function getYDataForBarChart()
    //Retrieves new Y data for bar chart and updates the chart
    //Argument: None
    //Return: None
    void getYDataForBarChart(){
        
        //define Y data list for chart
        List<Double> usedYData;
        
        if(dataBarFitCheck.isSelected()){
            
            //use fitted data for line chart
            usedYData = modelInstance.getLatestFittedData();
            
        }
        else if(normalizationCheck.isSelected()){
            
            //use normalised data for line chart
            usedYData = modelInstance.getLatestNormalisedBinData();
            
        }
        else{
            
            //use normal bin data for line chart
            usedYData = modelInstance.getLatestBinFrequencies();
            
        }
        
        //update Y values of the Bar chart
        int i = 0;
        for (XYChart.Data<String, Number> data : mainPlotBarArea.getData().get(0).getData()) {
            data.setYValue(usedYData.get(i));
            i++;
        }
        
    }
    
    //Function getYDataForBarChart()
    //Retrieves new Y data for bar chart and updates the chart
    //Argument: None
    //Return: None
    void getYDataForLineChart(){
        
        //define Y data list for chart
        List<Double> usedYData;
        
        //set Y data list based on chosen data for line chart
        if(dataLineFitCheck.isSelected()){
            
            //use fitted data for line chart
            usedYData = modelInstance.getLatestFittedData();
            
        }
        else if(normalizationCheck.isSelected()){
            
            //use normalised data for line chart
            usedYData = modelInstance.getLatestNormalisedBinData();
            
        }
        else{
            //use normal bin data for line chart
            usedYData = modelInstance.getLatestBinFrequencies();
            
        }
        
        //update Y values of the Line chart
        int i = 0;
        for (XYChart.Data<String, Number> data : mainPlotLineArea.getData().get(0).getData()) {
            data.setYValue(usedYData.get(i));
            i++;
        }

    }
    
}
