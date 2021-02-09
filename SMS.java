import java.util.*;
import java.io.*;
import java.time.*;

enum Section{ //enumeration
    K18ML(710,"Machine Learning"), K18DS(850,"Data Science\t"), K18CS(500,"Cyber Security"), K18MS(710,"Mean Stack\t"), K18IOT(150,"Internet of Things");
    
    int seats;
    String subject;
    
    Section(int seats, String subject){ //enum constructor
        this.seats = seats;
        this.subject = subject;
    }
}

class Student{
    static Scanner scanner = new Scanner(System.in); //scanner object
    static String name, sec;
    static int regno,registeration;
    static double cgpa;
    static String [] temp = new String[]{" "};
    public static Main mainMethod = new Main(); //Main class object
    
    Student(int regno){ //parameterized constructor
        this.regno = regno;
    }
    
    Student(String name, double cgpa){ //overloaded constructor
        this.name = name;
        this.cgpa = cgpa;
    }
    
    static void studentRegisteration(){
        //try block
        try{
            File file = new File("registeration.txt");//file opening
            Scanner scan = new Scanner(file);

            String regno = scan.next(); //reading content
            int registerno = Integer.parseInt(regno);
            registeration = registerno+1;

            System.out.println("\n\nRegistration Number allocated:\t"+registeration); //allocating registeration number

            FileWriter regfile = new FileWriter("registeration.txt"); //opening file
            String writes = String.valueOf(registeration);
            regfile.write(writes); //writing to file
            regfile.close(); //closing file
            
            System.out.println("\nRegisteration Successfull.");
            
            allocateSection();
            
        }catch(Exception e){ //catch block
            System.out.println("\nError. Try Again");
            studentRegisteration();
        }
            
    }
    
    static void allocateSection(){
        System.out.println("\n\nAvailable Sections: ");
        System.out.println("\n   Sections\t\t\tSeats\n");
        
        Section []s = Section.values();
        
        int i=1;
        
        for(Section ob : s){
            System.out.println(i+". "+ob.subject+"\t\t "+ob.seats); //displays lists of sections
            i++;
        }
        
        if(cgpa < 5.5){
            System.out.println("\nSorry your CGPA is less than required CGPA, you won't be given the option of choosing subject.\nYou will be currenlty provided with the default Section i.e "+s[4].toString()); //if cgpa is less than 5.5 than default section is allocated
            
            writeDetails(registeration,s[4].toString());
        }
        
        //student whose cgpa is higher than 5.5 is given chance to choose subject and section is allocated according to the choosen subject
        else{
            System.out.print("\nSelect Subject:\t");
            int choice = scanner.nextInt();

            if(choice == 1){
                sec = s[0].toString();
                System.out.println("\nSection allocated: "+sec);
            }

            else if(choice == 2){
                sec = s[1].toString();
                System.out.println("\nSection allocated: "+sec);
            }

            else if(choice == 3){
                sec = s[2].toString();
                System.out.println("\nSection allocated: "+sec);
            }

            else if(choice == 4){
                sec = s[3].toString();
                System.out.println("\nSection allocated: "+sec);
            }

            else if(choice == 5){
                sec = s[4].toString();
                System.out.println("\nSection allocated: "+sec);
            }

            else{
                System.out.println("\nWrong input. Please Try Again.\n\n");
                allocateSection();
            }
            
            writeDetails(registeration,sec);
        }
    }
    
    static void writeDetails(int registeration, String section){
        try{
            String reg = String.valueOf(registeration); //converting int to String
            String CGPA = String.valueOf(cgpa); //converting double to String
            
            FileWriter file = new FileWriter(registeration+".txt"); //open files
            
            //writing to file
            file.write("Name:\t\t\t"+name+"\n");
            file.write("CGPA:\t\t\t"+CGPA+"\n");
            file.write("Registeration Number:\t"+reg+"\n");
            file.write("Section:\t\t"+section+"\n");
            
            file.close(); //close file
            
            mainMethod.main(temp);
            
        }catch(Exception e){
            System.out.println("\nError. Try again");
            writeDetails(registeration,section);
        }
    }
    
    void checkDetails()throws Exception{
        File file = new File(regno+".txt"); //opens file
        Scanner scan = new Scanner(file);

        String name = scan.nextLine(); //reads data
        System.out.println("\n"+name); //displays data

        String cgpa = scan.nextLine();
        System.out.println(cgpa);

        String reg = scan.nextLine();
        System.out.println(reg);

        String section = scan.nextLine();
        System.out.println(section);
            
        mainMethod.main(temp);
       
    }
}

class Main{
    static Scanner scanner = new Scanner(System.in); //object of Scanner
    
    public static void main(String [] ak){
        System.out.println("\n\n\n\n\t\t\t\t\tWelcome to Student Managmenet System\n\n\n\n");
        
        System.out.println("1. Register a Student");
        System.out.println("2. Check Student details");
        System.out.println("3. Exit");
        
        System.out.print("\nEnter choice: ");
        int choice = scanner.nextInt();
        
        if(choice == 1){
            scanner.nextLine();
            System.out.print("\n\nEnter Name:\t");
            String name = scanner.nextLine();
            
            System.out.print("Enter CGPA:\t");
            double cgpa = scanner.nextDouble();
            
            Student st = new Student(name,cgpa); //constructor with two parameters called
            st.studentRegisteration();
        }
        
        else if(choice == 2){
            class Inner{ //inner class
                void register(){
                    try{
                        System.out.print("\n\nEnter Registration Number:\t");
                        int reg = scanner.nextInt();

                        Student st = new Student(reg);
                        st.checkDetails();
                        
                    }catch(Exception e){ //catches exception if wrong registeration number entered
                        System.out.println("\nWrong Registeration Number. Try Again");
                        register();
                    }
                }
            }
            
            new Inner().register();
        }
        
        else if(choice == 3){
            System.exit(0); //exits the program
        }
        
        else{
            System.out.println("\nWrong Input. Try Again");
            String temp[] = new String[]{" "};
            main(temp);
        }
    }
}