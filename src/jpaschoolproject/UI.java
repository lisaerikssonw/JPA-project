package jpaschoolproject;

import java.util.Scanner;
import utility.Gender;
import utility.Option;

public class UI {

    static Scanner sc = new Scanner(System.in);
    static Option option;

    public static int mainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("---------");
        System.out.println("1. Student Management");
        System.out.println("2. Teacher Management");
        System.out.println("3. Courses");
        System.out.println("4. Educations");
        System.out.println("0. Exit");

        return readInt("\nEnter choice: ");
    }

    public static void switchChoice(int choice) {
        switch (choice) {
            case 1:
                studentMenu();
                break;
            case 2:
                teacherMenu();
                break;
            case 3:
                courseMenu();
                break;
            case 4:
                educationMenu();
                break;
            case 0:
                System.out.println("\nGoodbye!");
                JpaSchoolProject.loop = false;
                break;
            default:
                System.out.println("\nTry again with number 0-4");
                break;
        }
    }

    public static void studentMenu() {
        boolean loop = true;

        while (loop) {
            System.out.println("\nSTUDENT MENU");
            System.out.println("------------");
            System.out.println("1. Add student");
            System.out.println("2. Update student");
            System.out.println("3. Assign student to education");
            System.out.println("4. Search student");
            System.out.println("5. Show all students");
            System.out.println("6. Remove student");
            System.out.println("7. Back to main menu");
            System.out.println("0. Exit");

            switch (readInt("\nEnter choice: ")) {
                case 1:
                    StudentMgmt.addStudent();
                    break;
                case 2:
                    updateStudentMenu(readLong("\nEnter social security number of student you want to update: "));
                    break;
                case 3:
                    StudentMgmt.assignToEducation(StudentMgmt.findBySsn(readLong("\nEnter social security number of the student you want to assign an education: ")));
                    break;
                case 4:
                    searchStudentMenu();
                    break;
                case 5:
                    StudentMgmt.printList(StudentMgmt.showAll());
                    break;
                case 6:
                    StudentMgmt.removeStudent(readLong("\nEnter social security number (YYMMDDXXXX): "));
                    break;
                case 7:
                    loop = false;
                    switchChoice(mainMenu());
                    break;
                case 0:
                    System.out.println("\nGoodbye!");
                    loop = false;
                    JpaSchoolProject.loop = false;
                    break;
                default:
                    System.out.println("\nTry again with number 0-7");
                    break;
            }
        }
    }

    public static void updateStudentMenu(Long ssn) {

        StudentMgmt.findBySsn(ssn);

        System.out.println("\nWhat du you want to update?");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Grade");

        switch (readInt("\nEnter choice: ")) {
            case 1:
                StudentMgmt.updateFirstName(ssn, readString("\nEnter new first name: "));
                break;
            case 2:
                StudentMgmt.updateLastName(ssn, readString("\nEnter new last name: "));
                break;
            case 3:
                StudentMgmt.updateGrade(ssn, readDouble("\nEnter new grade: "));
                break;
            default:
                System.out.println("\nWrong input!");
                break;
        }
    }

    public static void searchStudentMenu() {
        System.out.println("\nSEARCH STUDENT");
        System.out.println("--------------");
        System.out.println("1. Search by name");
        System.out.println("2. Search by social security number");

        switch (readInt("\nEnter choice: ")) {
            case 1:
                StudentMgmt.printList(StudentMgmt.findByName(readString("\nEnter last name or first name: ")));
                break;
            case 2:
                StudentMgmt.printStudent(StudentMgmt.findBySsn(readLong("\nEnter social security number (YYMMDDXXXX): ")));
                break;
            default:
                System.out.println("\nWrong input!");
                break;
        }

    }

    public static void teacherMenu() {
        boolean loop = true;

        while (loop) {
            System.out.println("\nTEACHER MENU");
            System.out.println("------------");
            System.out.println("1. Add teacher");
            System.out.println("2. Update teacher");
            System.out.println("3. Assign teacher to course");
            System.out.println("4. Search teacher");
            System.out.println("5. Show all teachers");
            System.out.println("6. Remove teacher");
            System.out.println("7. Back to main menu");
            System.out.println("0. Exit");

            switch (readInt("\nEnter choice: ")) {
                case 1:
                    TeacherMgmt.addTeacher();
                    break;
                case 2:
                    updateTeacherMenu(readLong("\nEnter social security number of the teacher you want to update: "));
                    break;
                case 3: //assignToCourse
                    break;
                case 4:
                    searchTeacherMenu();
                    break;
                case 5:
                    TeacherMgmt.printList(TeacherMgmt.showAll());
                    break;
                case 6:
                    TeacherMgmt.removeTeacher(readLong("\nEnter social security number of the teacher you want to remove: "));
                    break;
                case 7:
                    loop = false;
                    switchChoice(mainMenu());
                    break;
                case 0:
                    System.out.println("\nGoodbye!");
                    loop = false;
                    JpaSchoolProject.loop = false;
                    break;
                default:
                    System.out.println("\nTry again with number 0-7");
                    break;
            }
        }
    }

    public static void updateTeacherMenu(Long ssn) {
        TeacherMgmt.findBySsn(ssn);

        System.out.println("\nWhat du you want to update?");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Salary");

        switch (readInt("\nEnter choice: ")) {
            case 1:
                TeacherMgmt.updateFirstName(ssn, readString("\nEnter new first name: "));
                break;
            case 2:
                TeacherMgmt.updateLastName(ssn, readString("\nEnter new last name: "));
                break;
            case 3:
                TeacherMgmt.updateSalary(ssn, readInt("\nEnter new salary: "));
                break;
            default:
                System.out.println("\nWrong input!");
                break;
        }
    }

    public static void searchTeacherMenu() {
        System.out.println("\nSEARCH STUDENT");
        System.out.println("--------------");
        System.out.println("1. Search by name");
        System.out.println("2. Search by social security number");

        switch (readInt("\nEnter choice: ")) {
            case 1:
                TeacherMgmt.printList(TeacherMgmt.findByName(readString("\nEnter first or last name: ")));
                break;
            case 2:
                TeacherMgmt.printTeacher(TeacherMgmt.findBySsn(readLong("\nEnter social security number (YYMMDDXXXX): ")));
                break;
            default:
                System.out.println("\nWrong input!");
                break;
        }
    }

    public static void courseMenu() {
        boolean loop = true;

        while (loop) {
            System.out.println("\nCOURSE MENU");
            System.out.println("-----------");
            System.out.println("1. Add course");
            System.out.println("2. Update course");
            System.out.println("3. Search course");
            System.out.println("4. Show all courses");
            System.out.println("5. Remove course");
            System.out.println("6. Back to main menu");
            System.out.println("0. Exit");

            switch (readInt("\nEnter choice: ")) {
                case 1: //addCourse
                    break;
                case 2: //updateCourse
                    break;
                case 3: //searchCourse
                    break;
                case 4: //showAll
                    break;
                case 5: //removeCourse
                    break;
                case 6:
                    loop = false;
                    switchChoice(mainMenu());
                    break;
                case 0:
                    System.out.println("\nGoodbye!");
                    loop = false;
                    JpaSchoolProject.loop = false;
                    break;
                default:
                    System.out.println("\nTry again with number 0-6");
                    break;
            }
        }
    }

    public static void educationMenu() {
        boolean loop = true;

        while (loop) {
            System.out.println("\nEDUCATION MENU");
            System.out.println("-------------");
            System.out.println("1. Add education");
            System.out.println("2. Update education");
            System.out.println("3. Add courses to education");
            System.out.println("4. Search education");
            System.out.println("5. Show all educations");
            System.out.println("6. Remove education");
            System.out.println("7. Back to main menu");
            System.out.println("0. Exit");

            switch (readInt("\nEnter choice: ")) {
                case 1:
                    EducationMgmt.addEducation();
                    break;
                case 2: //updateEducation
                    break;
                case 3: //addCourses
                    break;
                case 4: //searchEducation
                    break;
                case 5:
                    EducationMgmt.printList(EducationMgmt.showAll());
                    break;
                case 6: //removeEducation
                    break;
                case 7:
                    loop = false;
                    switchChoice(mainMenu());
                    break;
                case 0:
                    System.out.println("\nGoodbye!");
                    loop = false;
                    JpaSchoolProject.loop = false;
                    break;
                default:
                    System.out.println("\nTry again with number 0-7");
                    break;
            }
        }
    }

    public static int readInt(String text) {
        boolean loop = true;
        int choice = 0;

        while (loop) {
            try {
                System.out.print(text);
                choice = sc.nextInt();
                sc.nextLine();
                loop = false;
            } catch (Exception e) {
                System.out.println("\nWrong input! Try again.");
                sc.next();
            }
        }
        return choice;
    }

    public static Long readLong(String text) {
        Long input = 0L;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(text);
                input = sc.nextLong();
                sc.nextLine();
                loop = false;
            } catch (Exception e) {
                System.out.println("\nWrong input! Try again.");
                sc.next();
            }
        }
        return input;
    }

    public static double readDouble(String text) {
        double input = 0;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(text);
                input = sc.nextDouble();
                sc.nextLine();
                loop = false;
            } catch (Exception e) {
                System.out.println("\nWrong input! Try again.");
                sc.next();
            }
        }
        return input;
    }

    public static String readString(String text) {
        String input = null;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(text);
                input = sc.nextLine();
                loop = false;
            } catch (Exception e) {
                System.out.println("\nWrong input! Try again.");
                sc.next();
            }
        }
        return input;
    }

    public static String fixStringLength(String text, int length) {
        if (text.length() >= length) {
            return text.substring(0, length);
        } else {
            while (text.length() < length) {
                text += " ";
            }
        }
        return text;
    }

    public static String fixStringLength(int i, int length) {
        String text = String.valueOf(i);

        if (text.length() >= length) {
            return text.substring(0, length);
        } else {
            while (text.length() < length) {
                text += " ";
            }
        }
        return text;
    }

    public static String fixStringLength(Long i, int length) {
        String text = String.valueOf(i);

        if (text.length() >= length) {
            return text.substring(0, length);
        } else {
            while (text.length() < length) {
                text += " ";
            }
        }
        return text;
    }

    public static String fixStringLength(double i, int length) {
        String text = String.valueOf(i);

        if (text.length() >= length) {
            return text.substring(0, length);
        } else {
            while (text.length() < length) {
                text += " ";
            }
        }
        return text;
    }

    public static String fixStringLength(Gender i, int length) {
        String text = String.valueOf(i);

        if (text.length() >= length) {
            return text.substring(0, length);
        } else {
            while (text.length() < length) {
                text += " ";
            }
        }
        return text;
    }

    public static boolean optionCheck(String text) {

        String input = null;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(text);
                input = sc.nextLine().toUpperCase();
                if(Option.valueOf(input) == Option.Y) {
                    loop = false;
                    return true;
                } else {
                    loop = false;
                    return false;
                }
            } catch(Exception e) {
                System.out.println("\nWrong input. Try again.");
            }
        }
        
        return false;
    }
    
    



}
