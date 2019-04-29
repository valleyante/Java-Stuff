/*
Preston Taylor
Winter 2019
W0386573
TYPE 1 MULT
TYPE 2 TRU
TYPE 3 BLANK
haha I totally scrapped a GUI version of this because I have a fatally flawed understanding of Actionlisteners that resulted in me being unable to remove them once added.
 */

import javax.swing.*;//does windows things

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FinalProject {

    static TestBank Test = new TestBank();
    static Results Scores = new Results();
    static Scanner stdIn = new Scanner(System.in);
    static String name = "";
    static int MaxPoints = 0;
    static RightandWrong gordon = new RightandWrong();//gordon is my cats name and he is currently pestering for attention.
    static int points = 0;

    public static void main(String[] args) {


        int looping = 1;
        while (looping == 1) {
            displayMenuTestCreation();

            // KAR you can call methods directly since they are all static

            char choice = getMenuChoiceFromUser(); //preston.getMenuChoiceFromUser();
            switch (choice) {
                case '1'://add mult
                    addMult();
                    break;
                case '2':// truefalse
                    addTrueFalse();
                    break;
                case '3'://fillin
                    addFillin();
                    break;

                case '4'://Finish test
                    if (Test.getSize() >= 1) {
                        looping = 0;
                    } else {
                        System.out.println("\nYou need at least one question!");
                    }
                    break;

                case '5'://Quit, which apparently quit working HA HA HA Q.Q
                    Time2Go();
                    break;

                default:
                    System.out.println("Please enter a valid response");
                    break;
            }
        }
        Student student = displayStudentLogin();
        MaxPoints = Test.Size();
        points = Exam();
        float percent = points / MaxPoints;
        percent = percent * 100;
        Scores.addScore(student.Name, student.ID, percent);
        displayAfterActionReport();
        while (looping == 0) {
            char Choice = getMenuChoiceFromUser();
            switch (Choice) {
                case '1': {
                    Student preston = displayStudentLogin();
                    if (Scores.NoDoubleTest(preston.ID) == false) {
                        points = Exam();
                        float alsoPercent = points / MaxPoints;
                        alsoPercent = percent * 100;
                        Scores.addScore(preston.Name, preston.ID, alsoPercent);
                        displayAfterActionReport();
                    } else {
                        System.out.println("\nSorry, you can't take the test twice.");
                    }
                    break;


                }
                case '2': {
                    Scores.printResults();
                    break;

                }
                case '3': {
                    Time2Go();
                    break;
                }

            }

        }


    }

    private static void displayMenuTestCreation() {
        System.out.print("\n------------------------------------------");//40 '-'s
        System.out.print("\nQuestion Creation\n");
        System.out.print("------------------------------------------\n");//40 '-'s
        System.out.print("1 - Add Multiple Choice    2 - Add True or False \n");
        System.out.print("3 - Add Fill in the blank  4 - Finish Test\n");
        System.out.print("5 - Quit");
        System.out.print("\n------------------------------------------\n");
    }

    private static Student displayStudentLogin() {
        System.out.print("\n------------------------------------------");//40 '-'s
        System.out.print("\nStudent Menu\n");
        System.out.print("------------------------------------------\n");//40 '-'s
        System.out.print("1: Take the test          2: Quit");
        System.out.print("\n------------------------------------------\n");
        int seriouslyMoreLoops = 0;
        while (seriouslyMoreLoops == 0) {
            int charAznable = getMenuChoiceFromUserINT();
            switch (charAznable) {
                case '1':
                    System.out.println("\nPlease enter your Student ID\n");
                    String ID = stdIn.nextLine();
                    System.out.println("\nPlease enter your full name\n");
                    String name;
                    name = stdIn.nextLine();
                    Student bob = new Student(name, ID);
                    seriouslyMoreLoops = 1;
                    return bob;

                case '2':
                    Time2Go();
                    break;
                default:
                    System.out.println("Please enter a valid selection");
                    break;
            }
        }
        return null;
    }

    private static void displayAfterActionReport() {
        System.out.println(gordon.showResults());
        System.out.print("\n------------------------------------------");//40 '-'s
        System.out.print("\nResults menu\n");
        System.out.print("------------------------------------------\n");//40 '-'s
        System.out.print("1 - New Student   2 - Print Scores \n");
        System.out.print("3 - Quit  \n");
        System.out.print("\n------------------------------------------\n");

    }


    public static int Exam() {
        //System.out.print("it run");
        int i = 0;
        int points = 0;
        int loop = 0;
        //System.out.print(Test.getType(i)); This would probably be easier if I had sleep, but I am getting an A- in this class or die trying.
        while (Test.Size() > i) {
            int guy = Test.getType(i);
            switch (guy) {
                case 1:
                    System.out.print("\n------------------------------------------");//40 '-'s
                    System.out.printf("\nQuestion #%d\n", i + 1);
                    System.out.print("------------------------------------------\n");//40 '-'s
                    System.out.print(Test.getProblem(i));
                    System.out.print("\n------------------------------------------\n");
                    System.out.print("\nA: " + Test.getA(i));
                    System.out.print("\nB: " + Test.getB(i));
                    System.out.print("\nC: " + Test.getC(i));
                    System.out.print("\nD: " + Test.getD(i));
                    System.out.print("\n");
                    int loop2 = 0;
                    while (loop2 == 0) {
                        char choice = getMenuChoiceFromUser();
                        switch (choice) {
                            case 'A':
                                if (Test.getA(i).equals(Test.getCorrect(i))) {
                                    points++;
                                }
                                gordon.addAttempt(Test.getProblem(i),Test.getA(i), Test.getCorrect(i));
                                i++;
                                loop2 = 1;
                                break;
                            case 'B': {
                                if (Test.getB(i).equals(Test.getCorrect(i))) {
                                    points++;
                                }
                                gordon.addAttempt(Test.getProblem(i),Test.getB(i), Test.getCorrect(i));
                                i++;
                                loop2 = 1;
                                break;

                            }
                            case 'C': {
                                if (Test.getC(i).equals(Test.getCorrect(i))) {
                                    points++;
                                }
                                gordon.addAttempt(Test.getProblem(i),Test.getC(i), Test.getCorrect(i));
                                i++;
                                loop2 = 1;
                                break;
                            }
                            case 'D': {
                                if (Test.getD(i).equals(Test.getCorrect(i))) {
                                    points++;
                                }
                                gordon.addAttempt(Test.getProblem(i),Test.getD(i), Test.getCorrect(i));
                                i++;
                                loop2 = 1;
                                break;
                            }
                            default: {
                                System.out.println("\nPlease enter a valid answer");
                                break;
                            }

                        }

                    }
                    break;
                case 2: {
                    System.out.print("\n------------------------------------------");//40 '-'s
                    System.out.printf("\nQuestion #%d\n", i + 1);
                    System.out.print("------------------------------------------\n");//40 '-'s
                    System.out.print(Test.getProblem(i));
                    System.out.print("\n------------------------------------------\n");
                    System.out.print("\nA: " + Test.getA(i));
                    System.out.print("\nB: " + Test.getB(i));
                    int loop3 = 0;
                    while (loop3 == 0) {
                        char choice = getMenuChoiceFromUser();
                        switch (choice) {
                            case 'A':
                                if (Test.getA(i).equals(Test.getCorrect(i))) {
                                    points++;
                                }
                                gordon.addAttempt(Test.getProblem(i),Test.getA(i), Test.getCorrect(i));
                                i++;
                                loop3 = 1;
                                break;
                            case 'B': {
                                if (Test.getB(i).equals(Test.getCorrect(i))) {
                                    points++;
                                }
                                gordon.addAttempt(Test.getProblem(i),Test.getB(i), Test.getCorrect(i));
                                i++;
                                loop3 = 1;
                                break;
                            }
                            default: {
                                System.out.println("\nPlease enter a valid answer");
                                break;
                            }

                        }

                    }
                }
                break;
                case 3: {
                    System.out.print("\n------------------------------------------");//40 '-'s
                    System.out.printf("\nQuestion #%d\n", i + 1);
                    System.out.print("------------------------------------------\n");//40 '-'s
                    System.out.print(Test.getProblem(i));
                    System.out.print("\n------------------------------------------\n");
                    System.out.printf("Please fill in the blank\n");
                    String attempt = stdIn.nextLine();
                    if (attempt.equals(Test.getFB(i))) {
                        points++;
                        break;
                    }
                    gordon.addAttempt(Test.getProblem(i),attempt, Test.getCorrect(i));
                    i++;
                    break;
                }
                default: {
                    System.out.println("Wow, something went extremely wrong!");

                    break;
                }
            }

        }

        return points;
    }


    public static char getMenuChoiceFromUser() { //god does getting out of the Arraylist Mines feel good.
        System.out.println("\nEnter a choice >>");
        char respond = stdIn.nextLine().charAt(0);
        return respond;
    }

    public static int getMenuChoiceFromUserINT() { //god does getting out of the Arraylist Mines feel good.
        System.out.println("\nEnter a choice >>");
        int respond = stdIn.nextLine().charAt(0);
        return respond;
    }

    public static void Time2Go() {
        /*int response = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Do you want to quit?", "Quitters", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }*/
        System.out.println("\nAre you sure you want to leave?\n");
        System.out.println("Y, N?\n");
        char Choice = getMenuChoiceFromUser();
        if (Choice == 'Y' || Choice == 'y') {
            System.exit(0);
        }
    }

    public static void addMult() {
        String problem;
        String ResponseMA;
        String ResponseMB;
        String ResponseMC;
        String ResponseMD;
        String ResponseFB;
        String CorrectAns = "";
        Integer QuestionType = 1;


        System.out.println("\nPlease enter the question");
        problem = stdIn.nextLine();
        System.out.println("\nPlease enter option A\n");
        ResponseMA = stdIn.nextLine();
        System.out.println("\nPlease enter option B\n");
        ResponseMB = stdIn.nextLine();
        System.out.println("\nPlease enter option C\n");
        ResponseMC = stdIn.nextLine();
        System.out.println("\nPlease enter option D\n");
        ResponseMD = stdIn.nextLine();
        System.out.println("\nWhich one is correct? A, B, C, or D\n");
        char Choice = getMenuChoiceFromUser();
        int loop = 1;
        while (loop == 1) {
            switch (Choice) {
                case 'A':
                    CorrectAns = ResponseMA;
                    loop = 0;
                    break;
                case 'B':
                    CorrectAns = ResponseMB;
                    loop = 0;
                    break;
                case 'C':
                    CorrectAns = ResponseMC;
                    loop = 0;
                    break;
                case 'D':
                    CorrectAns = ResponseMD;
                    loop = 0;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        Test.addQuestion(1, problem, ResponseMA, ResponseMC, ResponseMD, ResponseMD, "", CorrectAns);
    }

    public static void addTrueFalse() {
        String problem;
        String ResponseMA;
        String ResponseMB;
        String ResponseMC = "";
        String ResponseMD = "";
        String ResponseFB = "";
        String CorrectAns = "";
        Integer QuestionType = 2;
        System.out.println("\nPlease enter the question");
        problem = stdIn.nextLine();
        System.out.println("\nPlease enter option A\n");
        ResponseMA = stdIn.nextLine();
        System.out.println("\nPlease enter option B\n");
        ResponseMB = stdIn.nextLine();
        System.out.println("Which one was correct?");
        int loop = 1;
        while (loop == 1) {

            char Choice = getMenuChoiceFromUser();
            switch (Choice) {
                case 'A':
                    CorrectAns = ResponseMA;
                    loop = 0;
                    break;
                case 'B':
                    CorrectAns = ResponseMB;
                    loop = 0;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        Test.addQuestion(2, problem, ResponseMA, ResponseMB, "", "", "", CorrectAns);
    }

    public static void addFillin() {
        String problem;
        String ResponseMA = "";
        String ResponseMB = "";
        String ResponseMC = "";
        String ResponseMD = "";
        String ResponseFB;
        String CorrectAns = "";
        Integer QuestionType = 3;
        System.out.println("\nPlease enter the question");
        problem = stdIn.nextLine();
        System.out.println("\nPlease the answer\n");
        ResponseFB = stdIn.nextLine();
        CorrectAns = ResponseFB;
        Test.addQuestion(3, problem, "", "", "", "", ResponseFB, CorrectAns);
    }
}

class TestBank {
    private static ArrayList<QuestionData> test = new ArrayList<>();

    public void addQuestion(int type, String problem, String A, String B, String C, String D, String FB, String Correct) {//this is almost definitely wrong
        //addBook(title:string,Author:string,isbn:string):void
        QuestionData Question = new QuestionData(type, problem, A, B, C, D, FB, Correct);
        test.add(Question);
    }

    public int getType(int i) {
        return test.get(i).QuestionType;

    }

    public int getSize() {
        return test.size();
    }

    public String getProblem(int i) {
        return test.get(i).problem;
    }

    public String getA(int i) {
        return test.get(i).ResponseMA;
    }

    public String getB(int i) {
        return test.get(i).ResponseMB;
    }

    public String getC(int i) {
        return test.get(i).ResponseMC;
    }

    public String getD(int i) {
        return test.get(i).ResponseMD;
    }

    public String getFB(int i) {
        return test.get(i).ResponseFB;
    }

    public String getCorrect(int i) {
        return test.get(i).CorrectAns;
    }

    public boolean NextQuestion(int i) {
        if (test.size() >= i) {
            return true;
        } else return false;
    }

    public int Size() {
        return test.size();
    }
}

class QuestionData {

    String ResponseMA;
    String ResponseMB;
    String ResponseMC;
    String ResponseMD;
    String ResponseFB;
    String CorrectAns;
    Integer QuestionType;
    String problem;


    QuestionData(Integer type, String prompt, String inA, String inB, String inC, String inD, String FB, String Correct) {
        ResponseMA = inA;
        ResponseMB = inB;
        ResponseMC = inC;
        ResponseMD = inD;
        CorrectAns = Correct;
        QuestionType = type;
        ResponseFB = FB;
        problem = prompt;

    }

    private String GetProblem() {
        return this.problem;
    }

    private void SetA(String InA) {
        this.ResponseMA = InA;
    }

    private void SetB(String InB) {
        this.ResponseMB = InB;

    }

    private void SetC(String InC) {
        this.ResponseMC = InC;

    }

    private void SetD(String InD) {
        this.ResponseMD = InD;

    }

    private void SetCorrect(String Correct) {
        this.CorrectAns = Correct;

    }

    private void SetQuestionType(Integer type) {
        this.QuestionType = type;
    }
}

class Results {
    private ArrayList<Student> Scores = new ArrayList<>();

    public void addScore(String Name, String ID, float points) {
        Student score = new Student(Name, ID);
        score.setScore(points);
        Scores.add(score);
    }

    public boolean NoDoubleTest(String ID) {
        if (Scores.contains(ID)) {
            return true;
        }
        return false;
    }

    public void popScores() {//Doesn't work!
        String results = "";

        for (Student b : Scores) {
            int josh = Scores.indexOf(b);

            results = Scores.get(josh).toString() + "\n";

        }
        JOptionPane.showMessageDialog(null, results);
    }

    public void printResults() {//printing to TXT for that sweet XTRA credit... theroretically?

        String results;
        /*for (Student b : Scores) {
            int josh = Scores.indexOf(b);
            results += Scores.get(josh).toString() + "\n";
        }*/
        try {
            FileWriter writer = new FileWriter("PrestonTXT.txt");
            for (Student b : Scores) {
                int josh = Scores.indexOf(b);
                results = Scores.get(josh).toString() + "\n";
                writer.write(results);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Student {
    String Name;
    double score;
    String ID;

    Student(String firstName, String IDnum) {
        Name = firstName;
        ID = IDnum;
    }

    public void setScore(float value) {
        score = value;
    }

    public String toString() {
        String results = "";
        results = ID + "  " + Name + "  " + score;
        return results;
    }
}

class RightandWrong {
    private static ArrayList<InandOut> burger = new ArrayList<>();

    public static void addAttempt(String Question,String Attempt, String Correct) {//I've actually become a huge fan of Freddie's
        InandOut triple = new InandOut(Question, Attempt, Correct);
        burger.add(triple);
    }

    public String showResults() {
        String results = "";
        for (InandOut b : burger) {
            int gordon = burger.indexOf(b);
            results = burger.get(gordon).toString();
        }
        return results;

    }
}

class InandOut {
    String Attempt;
    String Correct;
    String Question;

    InandOut(String question, String attempt, String correct) {
        Attempt = attempt;
        Correct = correct;
        Question = question;

    }

    public void addQuestion(String Questionthing){
        Question = Questionthing;

    }


    public String toString() {
        String results = "";
        results ="\nThe question was: "+ Question+"\n"+ "You Submitted: " + Attempt + "  Correct Answer: " + Correct + "\n";
        return results;
    }
}