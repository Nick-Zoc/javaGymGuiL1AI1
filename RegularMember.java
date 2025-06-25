// creating a sub class of super class 'GymMember' as 'RegularMember'
public class RegularMember extends GymMember{
    // declaring variables with private access modifiers so that they cannot be accessed outside of this class
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private Double price;
    
    // creating contructor of this class for accessing and assigning values to variables of super class
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource){
        // invoking the parent class constructor with corresponding parameter values
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        
        // assigning default values to following variables 
        this.isEligibleForUpgrade = false;    //default value
        this.attendanceLimit = 30;    //fixed value
        this.plan = "basic";    //default
        this.price = 6500.0;    //default
        this.removalReason = "";    //default
        
        // assigning parameter value to instance variable
        this.referralSource = referralSource;
    }
    /* overriding the abstract method 'markAttendance()' to increment the attendance value by 1 and 
    loyalty points by 5 each time this method is invoked.*/
    @Override
    public void markAttendance(){
        this.attendance+=1;          //increment the attendance value by 1
        this.loyaltyPoints+=5.0;      //increment the loyalty points by 5
        System.out.println("Attendance is marked and loyalty points are increased by 5. " + "Total Attendance = " + this.attendance + " Total Loyalty Points = " + this.loyaltyPoints);
    }
    // Accessor/getter methods   for RegularMember attributes
    public int getAttendanceLimit() {    //getter method for 'attendanceLimit'
        return this.attendanceLimit; 
    }
    public boolean getIsEligibleForUpgrade() {      //getter method for 'isEligibleForUpgrade' 
        return this.isEligibleForUpgrade; 
    }
    public String getRemovalReason() {      //getter method for 'removalReason'
        return this.removalReason; 
    }
    public String getReferralSource() {     //getter method for 'referralSource'
        return this.referralSource; 
    }
    public String getPlan() {       //getter method for 'plan'
        return this.plan; 
    }
    public double getPrice() {      //getter method for 'price'
        return this.price; 
    }
    
    
    
    /* creating a method 'getPlanPrice()' which takes String 'plan' 
     * as parameter and gives the desired price of plan using switch cases*/
    public double getPlanPrice(String plan){
        switch(plan.toLowerCase()){     //changes plan to lowercase
            case "basic":
                return 6500.0;          //return price of the plan
            case "standard":
                return 12500.0;         //return price of the plan
            case "deluxe":
                return 18500.0;         //return price of the plan
            default:
                return -1.0;            //returns default when no valid case
        }
    }
    
    // creating a method 'upgradePlan()' which upgrades the plan of the user as per desire
    public String upgradePlan(String plan){
        if(getAttendance() >= attendanceLimit){      //checking if 'attendance' is greater or equal to 'attendanceLimit'
            this.isEligibleForUpgrade = true;         //change 'isEligibleForUpgrade' to true
            double price = getPlanPrice(plan);        //store return value from 'getPlanPrice' to 'price'
            if (price == -1.0){
                return "Invalid plan selected! Please select a valid plan";    //returns suitable message
            }
            else if(this.plan.equals(plan.toLowerCase())){              //check if the chosen plan is already active
                return "Currently subscribed plan selected! Please select a new plan";      //returns suitable message
            }
            else{
                // update plan and price
                this.plan = plan;
                this.price = price;
                return "Plan succesfully changed to "+this.plan+"! new price "+this.price; //returns suitable message
            }
        }
        else if(this.attendance < attendanceLimit){
            return "Not Eligible for plan upgrade. Reach attendance to atleast 30 days"; //returns suitable message
        }
        return "unexpected error occured while upgrading the plan!.";    //returns error message when no conditions satisfy from above
    }
    
    // creating method 'revertRegularMember()' which reset member detail values to default
    public void revertRegularMember(String removalReason){
        // reverting member details back to default
        resetMember();    //calls parent clas method which reset the values of 'activeStatus', 'attendance', 'loyaityPoints' to their respective default values
        this.isEligibleForUpgrade = false;      //reset values to defaut
        this.plan = "basic";                    //reset values to defaut
        this.price =6500.0;                     //reset values to defaut
        this.removalReason = removalReason;     //reason to remove
        System.out.println("Removal Reson: "+this.removalReason);
    }
    
    // override the 'display()' method from parent class to call the original 'display()' method and add other print lines ot it
    @Override
    public void display(){
        super.display();        //calling parent 'display()' method
        System.out.println("The activated plan: "+getPlan());          //display plan activated by the member
        System.out.println("The price of the plan "+getPrice());     //display price of the plan activated
        if(this.removalReason.length() != 0){       //if removal reason is not empty then print the reason
            System.out.println("The reason of removal: "+getRemovalReason()); 
        }
        
    }
}