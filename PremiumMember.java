// creating a sub class of super class 'GymMember' as 'PremiumMember'
public class PremiumMember extends GymMember{
    
    // declaring variables with private access modifiers so that they cannot be accessed outside of this class
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    
    // creating contructor of this class for accessing and assigning values to variables of super class
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer){
       // invoking the parent class constructor with corresponding parameter values
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        
        // assigning default values to following variables 
        this.premiumCharge = 50000.0;       //fixed value
        this.paidAmount = 0.0;              //defualt value
        this.isFullPayment = false;         //defaut value
        this.discountAmount = 0.0;          //default value;
        this.personalTrainer = personalTrainer;
    }
   
    // Accessor/getter methods   for PremiumMember attributes
    public double getPremiumCharge() {           //getter method for 'premiumCharge'
       return this.premiumCharge;
    } 
    public String getPersonalTrainer() {         //getter method for 'personalTrainer'
        return this.personalTrainer;
    }
    public boolean getIsFullPayment() {             //getter method for 'fullPayement'
        return this.isFullPayment;
     }
    public double getPaidAmount() {              //getter method for 'paidAmount'
        return this.paidAmount;
    }
    public double getDiscountAmount() {          //getter method for 'discountAmount'
        return this.discountAmount;
    }
    
    /* overriding the abstract method 'markAttendance()' 
     * to increment the attendance value by 1 and loyalty points by 10 each time this method is invoked.*/
    @Override
    public void markAttendance(){
        this.attendance+=1;          //increment the attendance value by 1
        this.loyaltyPoints+=10.0;      //increment the loyalty points by 10
        System.out.println("Attendance is marked and loyalty points are increased by 10. " + "Total Attendance = " + this.attendance + " Total Loyalty Points = " + this.loyaltyPoints);
    }
    
    // creating method 'payDueAmount()' which takes 'paidAmount' as parameter and checks required conditions and returns a string
    public String payDueAmount(Double paidAmount){
        double remainingAmount = 0.0;
        if(this.isFullPayment == true){                     //checks if the member has already cleared their fees
            return "Full amount is already paid! no dues";  //return suitable message
        }
        
        if(paidAmount < 0){         //check if paidAmount is negetive
            return "Invalid amount! it cannot be negetive";     //return suitable message
        }
        
        if(paidAmount == 0){         //check if paidAmount is negetive
            return "Invalid amount! it cannot be Zero " + remainingAmount;     //return suitable message
        }
        
        if(this.paidAmount + paidAmount > premiumCharge){           //check if total price paid exceeds the fee
            return "Payment Exceeded than required! Over paid amount: "+((this.paidAmount+paidAmount) - premiumCharge)+". Payment is not charged!"; //return suitable message
        }
        //add parameter paid amount to the instance variable
        this.paidAmount += paidAmount;
        if(this.paidAmount == premiumCharge){           //check if full fees is cleared
            this.isFullPayment = true;
            remainingAmount = 0.0;
            return "Full payment is recieved! no dues";      //return suitable message
        }
        
        else{               //else for partial payment
            remainingAmount = premiumCharge - this.paidAmount;
            return "Partial payment is recieved! Due amount: "+remainingAmount;     //return suitable message
        }
    }
    
    //creating a method to calculate discount, if the member is eligible
    public void calculateDiscount(){
        //check if instance variable 'isFullPayment' is true
        if(this.isFullPayment == true){
            this.discountAmount = (premiumCharge/10);       //calculate discount
            System.out.println("The member can enjoy 10% discount on premium plan! discounted amount: "+this.discountAmount);       //print suitable message
        }
        else{
            System.out.println("The member is not eligible for Discount");   //print suitable message
        }
    }
    
    //creating method 'revertPremiumMember()' which reverts the certain details of member to default values
    public void revertPremiumMember(){
        
        resetMember();        //call super class method 'resetMember()' to reset the values of 'activeStatus', 'attendance', 'loyaityPoints' to their respective default values
        this.personalTrainer = "";  //reset to defualt value
        isFullPayment = false;      //reset to defualt value
        this.paidAmount = 0.0;      //reset to defualt value
        this.discountAmount = 0.0;  //reset to defualt value
    }
    
    //overriding method 'display()' which also calls parent method 'display()' and also
    @Override
    public void display(){
        super.display();    //call super class method 'display()'
        
        //display the Premiun Member details
        System.out.println("Personal Trainer: "+getPersonalTrainer());      //using getter method to return the variable values
        System.out.println("The price Paid "+getPaidAmount());
        System.out.println("Full payment Status: "+getIsFullPayment());
        System.out.println("Remaining Amount: "+(premiumCharge - this.paidAmount));
        if(this.isFullPayment == true){         //if the vlaue of 'this.isFullPayment' is true then do the if condition 
            calculateDiscount();                //run 'calculateDiscount()' so that 'discountAmount' gets updated and printed
        }
    }
}