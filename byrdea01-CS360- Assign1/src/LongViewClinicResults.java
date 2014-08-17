import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Elizabeth Mort
 * CS 360 
 * Assignment 1
 * Due January 28, 2014
 */

/*
 * This class uses data from the Patient class to analyze the results
 * of a lipid profile study done by the LongView clinic. This class reads data 
 * from a file, calculates ldl and total cholesterol/hdl ratio and determines 
 * the patient's risk calculation based on those and other values. 
 * This class also looks at the difference in average cholesterol values
 * for patients in a control group versus patients in a treatment group. 
 */
public class LongViewClinicResults {
	
	static ArrayList<Patient> patientList = new ArrayList<Patient>();
	static DecimalFormat df = new DecimalFormat("##.0");
	
	static int avgTotCholControl = 0;
	static int avgLdlControl = 0;
	static int avgHdlControl = 0;
	static int avgTrigControl = 0;
	static double avgTotCholRatioControl = 0.0;
	
	static int avgTotCholTreatment = 0;
	static int avgLdlTreatment = 0;
	static int avgHdlTreatment = 0;
	static int avgTrigTreatment = 0;
	static double avgTotCholRatioTreatment = 0.0;
	
	static int totCholDifference;
	static int hdlDifference;
	static int ldlDifference;
	static int trigDifference;
	static double totCholRatioDifference;
	
	public static void main(String[] args) throws IOException {
		
		String fileName = null;
		Scanner consoleScanner = new Scanner(System.in);
		
		
		System.out.println("Welcome to the Longview Lipid Profile Study");
		System.out.print("Please enter filename containing study data: ");
		
		fileName = consoleScanner.nextLine();
		Scanner fileScanner = new Scanner(new FileReader(fileName));
		
		consoleScanner.close();
		

		
		readFile(fileScanner);
		computeLDL();
		computeRatio();
		computeAverages();
		patientAssessment();
	
	}
		
	//Method to open and read the file
	public static void readFile(Scanner fileScanner) throws IOException{
		
		while(fileScanner.hasNext()){
			String columns[] = fileScanner.nextLine().split(" ");
			Patient patient = new Patient();
			patient.setPatientID(columns[0]);
			patient.setSex(columns[1]);
			patient.setTotalCholesterol(Integer.parseInt(columns[2]));
			patient.setHdl(Integer.parseInt(columns[3]));
			patient.setTriglycerides(Integer.parseInt(columns[4]));
			patientList.add(patient);
			}
		}

	//compute ldl: ldl = total cholesterol - hdl - trig/5
	public static void computeLDL(){
		for(int i = 0; i < patientList.size(); i++){
			patientList.get(i).setLdl(patientList.get(i).getTotalCholesterol() - patientList.get(i).getHdl() - (patientList.get(i).getTriglycerides()/5));
		}
		
	}
	
	//computer ratio: 	ratio = total cholesterol/hdl
	public static void computeRatio(){
		for(int i = 0; i < patientList.size(); i++){
			patientList.get(i).setTotCholRatio((double)patientList.get(i).getTotalCholesterol() / (double) patientList.get(i).getHdl());
		}
	}
	
	//compute average values for patient in the control group and 
	//patients in the treatment group.
	public static void computeAverages(){
		int sumTotCholControl = 0;
		int sumLdlControl = 0;
		int sumHdlControl = 0;
		int sumTrigControl = 0;
		double sumTotCholRatioControl = 0.0;
		int counterControl = 0;
		
		int sumTotCholTreatment = 0;
		int sumLdlTreatment = 0;
		int sumHdlTreatment = 0;
		int sumTrigTreatment = 0;
		double sumTotCholRatioTreatment = 0.0;
		int counterTreatment = 0;
		
	//average values for each patient group for each value
	//control group ends in 'A', treatment in 'B'
	for(int i = 0; i<patientList.size(); i++){
		
		if(patientList.get(i).getPatientID().charAt(4) == 'A'){
			sumTotCholControl = sumTotCholControl + patientList.get(i).getTotalCholesterol();
			sumLdlControl = sumLdlControl + patientList.get(i).getLdl();
			sumHdlControl = sumHdlControl + patientList.get(i).getHdl();
			sumTrigControl = sumTrigControl + patientList.get(i).getTriglycerides();
			sumTotCholRatioControl = sumTotCholRatioControl + patientList.get(i).getTotCholRatio();
			counterControl++;
		}
		if(patientList.get(i).getPatientID().charAt(4) == 'B'){
			sumTotCholTreatment = sumTotCholTreatment + patientList.get(i).getTotalCholesterol();
			sumLdlTreatment = sumLdlTreatment + patientList.get(i).getLdl();
			sumHdlTreatment = sumHdlTreatment + patientList.get(i).getHdl();
			sumTrigTreatment = sumTrigTreatment + patientList.get(i).getTriglycerides();
			sumTotCholRatioTreatment = sumTotCholRatioTreatment + patientList.get(i).getTotCholRatio();
			counterTreatment++;
		}
	}
		//averages for total cholesterol, hdl, ldl, triglycerides, and total cholesterol ratio
		avgTotCholControl = sumTotCholControl / counterControl;
		avgHdlControl = sumHdlControl / counterControl;
		avgLdlControl = sumLdlControl / counterControl;
		avgTrigControl = sumTrigControl / counterControl;
		avgTotCholRatioControl = sumTotCholRatioControl / counterControl;
		
		avgTotCholTreatment = sumTotCholTreatment / counterTreatment;
		avgHdlTreatment = sumHdlTreatment / counterTreatment;
		avgLdlTreatment = sumLdlTreatment / counterTreatment;
		avgTrigTreatment = sumTrigTreatment / counterTreatment;
		avgTotCholRatioTreatment = sumTotCholRatioTreatment / counterTreatment;
	
		computeDifferences();
	
	}
	
	//the data obtained from the computerAverages method above is
	//used to compute the difference in values between the control
	//and treatment groups
	public static void computeDifferences(){
			
		totCholDifference = avgTotCholTreatment - avgTotCholControl;
		hdlDifference = avgHdlTreatment - avgHdlControl;
		ldlDifference = avgLdlTreatment - avgLdlControl;
		trigDifference = avgTrigTreatment - avgTrigControl;
		totCholRatioDifference = avgTotCholRatioTreatment - avgTotCholRatioControl;
		
		outputToConsole();
	}
	
	//patients are placed in a risk classification group based on
	//the values of various components of the lipid profile
	public static void patientAssessment(){
		for(int i = 0; i < patientList.size(); i++){
		//total cholesterol assessment
		if(patientList.get(i).getTotalCholesterol() < 200){
			patientList.get(i).setTotCholClass("DSRBL");
		}else if(patientList.get(i).getTotalCholesterol() >= 200 && patientList.get(i).getTotalCholesterol() < 240){
			patientList.get(i).setTotCholClass("BHIGH");
		}else{
			patientList.get(i).setTotCholClass("HIGH");
		}
		
		//hdl assessment
		if(patientList.get(i).getSex().equals("m")){
			if(patientList.get(i).getHdl() < 40){
				patientList.get(i).setHdlClass("LOW");
			}else if(patientList.get(i).getHdl() >= 40 && patientList.get(i).getHdl() < 60){
				patientList.get(i).setHdlClass("AVG");
			}else{
				patientList.get(i).setHdlClass("HIGH");
			}
		}
		if(patientList.get(i).getSex().equals("f")){
			if(patientList.get(i).getHdl() < 50){
				patientList.get(i).setHdlClass("LOW");
			}else if(patientList.get(i).getHdl() >= 50 && patientList.get(i).getHdl() < 60){
				patientList.get(i).setHdlClass("AVG");
			}else{
				patientList.get(i).setHdlClass("HIGH");
			}
			
		}
		
		
		//ldl assessment
		if(patientList.get(i).getLdl() < 100){
			patientList.get(i).setLdlClass("OPT");
		}else if(patientList.get(i).getLdl() >= 100 && patientList.get(i).getLdl() < 130){
			patientList.get(i).setLdlClass("NOPT");
		}else if(patientList.get(i).getLdl() >= 130 && patientList.get(i).getLdl() < 160){
			patientList.get(i).setLdlClass("BHIGH");
		}else{
			patientList.get(i).setLdlClass("HIGH");
		}
		
		//triglycerides assessment
		if(patientList.get(i).getTriglycerides() < 150){
			patientList.get(i).setTrigClass("DSRBL");
		}else if(patientList.get(i).getTriglycerides() >= 150 && patientList.get(i).getTriglycerides() < 200){
			patientList.get(i).setTrigClass("BHIGH");
		}else{
			patientList.get(i).setTrigClass("HIGH");
		}
		
		//ratio classification 
		if(patientList.get(i).getTotCholRatio() <= 3.3){
			patientList.get(i).setRatioClass("DEC");
		}else if(patientList.get(i).getTotCholRatio() > 3.3 && patientList.get(i).getTotCholRatio() < 5.0 ){
			patientList.get(i).setRatioClass("AVG");
		}else{
			patientList.get(i).setRatioClass("INC");
		}
		
		}
		try {
			outputToFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//output report to file (report.out)
	//results of each patient and assessment of risk
	public static void outputToFile() throws FileNotFoundException{
		
		File outFile = new File("report.out");
		PrintWriter pw = new PrintWriter(outFile);
		pw.println(String.format("%s %7s %4s %14s %12s %13s %14s", "Id", "Sex", "Tot", "HDL", "LDL", "TRI", "RAT"));
		pw.println("----------------------------------------------------------------------------------------");
		for(int i = 0; i < patientList.size(); i++){
			pw.println(String.format("%5s %3s %5d %s%5s%s %5d %s%5s%s %5d %s%5s%s %5d %s%5s%s %6s %s%5s%s", 
					patientList.get(i).getPatientID(), patientList.get(i).getSex(), patientList.get(i).getTotalCholesterol(), "(", 
					patientList.get(i).getTotCholClass(), ")", patientList.get(i).getHdl(), "(", patientList.get(i).getHdlClass(), ")", 
					patientList.get(i).getLdl(), "(", patientList.get(i).getLdlClass(), ")", patientList.get(i).getTriglycerides(),
					"(", patientList.get(i).getTrigClass(), ")", df.format(patientList.get(i).getTotCholRatio()), "(", 
					patientList.get(i).getRatioClass(), ")"));
		}
		pw.close();
	}
	
	
	//analysis report comparing aggregate lipid profiles of treatment and control groups
	public static void outputToConsole(){
		
		System.out.println("\n" + String.format("%15s %6s %6s %6s %6s %7s", " ", "TOT", "HDL", "LDL", "TRI", "RAT"));
		System.out.println(String.format("%15s %6d %6d %6d %6d %7s", "Control Group", 
				avgTotCholControl, avgHdlControl, avgLdlControl, avgTrigControl, df.format(avgTotCholRatioControl)));
		System.out.println(String.format("%15s %6d %6d %6d %6d %7s", "Treatment Group", 
				avgTotCholTreatment, avgHdlTreatment, avgLdlTreatment, avgTrigTreatment, df.format(avgTotCholRatioTreatment)));
		System.out.println(String.format("%15s %6d %6d %6d %6d %7s", "Difference", totCholDifference, hdlDifference,
				ldlDifference, trigDifference, df.format(totCholRatioDifference)));
		
	}
}
