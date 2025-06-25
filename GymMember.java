// creating a super class named 'GymMember' for the gym member management system
public abstract class GymMember{
    /* creating protected instance variables to not let them be 
    accessed by classes rather than the super class and sub-classes*/
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    // creating contructor of this class for initializing these variables 
    public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate){
        // assigning the value send to the parameter from 'RegularMember' class to the instance variable
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;

        /* initializing the values of following variable to zero, to make sure 
        the values are fresh from the start*/
        this.attendance = 0;        //default
        this.loyaltyPoints = 0.0;  //default
        this.activeStatus = false;  //default
    }

    // creating getter/setter methods for accessing the vlaues of the protected variables
    public int getId(){     //getter method for 'id'
        return this.id;
    }

    public String getName(){    //getter method for 'name'
        return this.name;
    }

    public String getLocation(){    //getter method for 'location'
        return this.location;
    }

    public String getPhone(){   //getter method for 'phone'
        return this.phone;
    }

    public String getEmail(){   //getter method for 'email'
        return this.email;
    }

    public String getGender(){    //getter method for 'gender'
        return this.gender;
    }

    public String getDOB(){     //getter method for 'DOB'
        return this.DOB;
    }

    public String getMembershipStartDate(){ //getter method for 'membershipStartDate'
        return this.membershipStartDate;
    }

    public int getAttendance(){             //getter method for 'attendance'
        return this.attendance;
    }

    public double getLoyaltyPoints(){      //getter method for 'loyaltyPoints'
        return this.loyaltyPoints;
    }

    public boolean getActiveStatus(){       //getter method for 'activeStatus'
        return this.activeStatus;
    }

    // creating abstract method 'markAttendance()' to track attendance of the gym member.
    public abstract void markAttendance();

    // creating method 'activateMembership()'to set 'activeStatus' of new member to true 
    public void activateMembership(){
        if(activeStatus ==true){
            System.out.println("The membership already activated. ");
        }
        else{
            activeStatus = true;
            System.out.println("The membership is activated.");
        }
    }

    // creating method 'deactivateMembership()'to set 'activeStatus' of new member to false
    public void deactivateMembership(){
        if(activeStatus == true){    //checking if the active status is 'true' or not
            activeStatus = false;    //if 'true' then it is set to 'false'
            System.out.println("The membership is deactivated");
        }
        else{    //if false then related message is displayed
            System.out.println("Please activate mebership beforehand to deactivate it later");
        }
    }

    // creating method 'resetMember()' which reset the values of 'activeStatus', 'attendance', 'loyaityPoints' to their respective default values
    public void resetMember(){
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0.0;
        System.out.println("The attendance and loyalty points of member is reset and member is no longer active");
    }

    // creating a method to display all the details of the member stored
    public void display(){
        System.out.println("The Details of the members are given below:");
        System.out.println("Id: "+getId());
        System.out.println("Name: "+getName());
        System.out.println("Loacation: "+getLocation());
        System.out.println("Phone Number: "+getPhone());
        System.out.println("Email Address: "+getEmail());
        System.out.println("Gender: "+getGender());
        System.out.println("Date Of Birth: "+getDOB());
        System.out.println("Membership start date: "+getMembershipStartDate());
        System.out.println("Attendence: "+getAttendance());
        System.out.println("Loyalty Points: "+getLoyaltyPoints());
        System.out.println("Active Status: "+getActiveStatus());
    }
}