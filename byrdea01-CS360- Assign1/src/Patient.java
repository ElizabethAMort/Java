/*
 * Elizabeth Mort
 * CS 360
 * Assignment 1
 * Due January 28, 2014
 * 
 */

/*
 * The patient class sets up the attributes and the getters
 * and setters for a patient object.
 * These objects are used by the LongViewClinicResults class.
 */
public class Patient {
	private String patientID;
	private String sex;
	private int totalCholesterol;
	private int hdl;
	private int ldl;
	private int triglycerides;
	private double totCholRatio;
	private String totCholClass;
	private String hdlClass;
	private String ldlClass;
	private String trigClass;
	private String ratioClass;
	
	
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getTotalCholesterol() {
		return totalCholesterol;
	}
	public void setTotalCholesterol(int totalCholesterol) {
		this.totalCholesterol = totalCholesterol;
	}
	public int getHdl() {
		return hdl;
	}
	public void setHdl(int hdl) {
		this.hdl = hdl;
	}
	public int getLdl() {
		return ldl;
	}
	public void setLdl(int ldl) {
		this.ldl = ldl;
	}
	public int getTriglycerides() {
		return triglycerides;
	}
	public void setTriglycerides(int triglycerides) {
		this.triglycerides = triglycerides;
	}
	public double getTotCholRatio() {
		return totCholRatio;
	}
	public void setTotCholRatio(double totCholRatio) {
		this.totCholRatio = totCholRatio;
	}
	public String getTotCholClass(){
		return totCholClass;
	}
	public void setTotCholClass(String totCholClass){
		this.totCholClass = totCholClass;
	}
	public String getHdlClass(){
		return hdlClass;
	}
	public void setHdlClass(String hdlClass){
		this.hdlClass = hdlClass;
	}
	public String getLdlClass(){
		return ldlClass;
	}
	public void setLdlClass(String ldlClass){
		this.ldlClass = ldlClass;
	}
	public String getTrigClass(){
		return trigClass;
	}
	public void setTrigClass(String trigClass){
		this.trigClass = trigClass;
    }
	public String getRatioClass(){
		return ratioClass;
	}
	public void setRatioClass(String ratioClass){
		this.ratioClass = ratioClass;
	}
}
