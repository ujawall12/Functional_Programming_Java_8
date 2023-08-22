package Java_8;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
//
public class Main {
    public static void main(String[] args) {
        List<Integer> list=List.of(1,33,22,52,56,21,90,3,4,4,5);
        // Sum of all elements in a list
        System.out.println("Sum of all elements in the list is/n");
        Integer result= list.stream().reduce(0,Integer::sum);
        System.out.println(result);

        // Maximum element in a list
        System.out.println("Maximum element in the list is");
        Integer result1=list.stream().reduce(Integer.MIN_VALUE, (a,b)-> a>b ? a:b);
        System.out.println(result1);

        //Squares of even numbers in a list
        System.out.println("Squares of all even numbers in a list are/n");
        list.stream().filter(integer -> integer%2==0).map(e-> e*e).forEach(System.out::println);


        //Creating a list of courses
        List<String> coursestemp=List.of("Kubernetes", "Docker", "Cloud", "Microservices");

        //Print number of characters in each course
        System.out.println("Number of characters in each course/n");
        coursestemp.stream().map(c->c+" "+c.length()).forEach(System.out::println);

        //Print list of numbers in descending order
        System.out.println("List of numbers in descending order/n");
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        //Print list of courses sorted in order of length of string
        System.out.println("List of courses in order of length of the strings/n\n");
        coursestemp.stream().sorted(Comparator.comparing(c->c.length())).forEach(System.out::println);



        //Adding courses inside the list
        List<Courses> courses = List.of(new Courses("Spring", "Framework", 98, 20000),
                new Courses("Spring Boot", "Framework", 95, 18000),
                new Courses("API", "Microservices", 97, 22000),
                new Courses("Microservices", "Microservices", 96, 25000),
                new Courses("FullStack", "FullStack", 91, 14000),
                new Courses("AWS", "Cloud", 92, 21000),
                new Courses("Azure", "Cloud", 99, 21000),
                new Courses("Docker", "Cloud", 92, 20000),
                new Courses("Kubernetes", "Cloud", 91, 20000));

        //To check if all courses have review score>95
        System.out.println("\nTo check if all the courses have review score >95\n"+
        courses.stream().allMatch(c->c.getReviewScore()>95)
        );

        //Sort the courses based on number of students
        System.out.println("\nCourses based on number of students \n");
        List<Courses>result2=
        courses.stream().sorted(Comparator.comparing(Courses::getNoOfStudents).reversed())
                .collect(Collectors.toList());
        System.out.println(result2);

        //Sort by no of students and review
        System.out.println("\nSort by no of students and review\n");
        List<Courses> result3=
        courses.stream().sorted(Comparator.comparing(Courses::getNoOfStudents)
                .thenComparing(Courses::getReviewScore).reversed()).collect(Collectors.toList());

        System.out.println(result3);

        //To get the course with second highest number of students
        System.out.println("\nCourse with second highest number of students is\n");
        List<Courses> result4=
        courses.stream().sorted(Comparator.comparing(Courses::getNoOfStudents).reversed())
                .limit(2).skip(1).collect(Collectors.toList());

        System.out.println(result4);

        //To find first element that meets a specific criteria
        System.out.println("\nFind first element with reviewScore>92\n");
        Courses courseResult=
                courses.stream().sorted(Comparator.comparing(Courses::getReviewScore)).filter(c->c.getReviewScore()>92).findFirst().get();

        System.out.println(courseResult);

        //To find total number of courses whose review score> 95
        System.out.println("\nTotal number of courses whose review score >95 \n");
        Long result5=
                courses.stream().filter(c->c.getReviewScore()>95).count();
        System.out.println(result5);

        //To find total number of students in courses whose review score>95
        System.out.println("\nTo find the number of students in courses whose review score >95\n");
        Integer result6=
                courses.stream().filter(c->c.getReviewScore()>95)
                        .map(Courses::getNoOfStudents).reduce(0,Integer::sum);

        System.out.println(result6);

        //To find total number of courses in each category
        System.out.println("\n To find total number of courses category wise\n"+

        courses.stream().collect(Collectors.groupingBy(Courses::getCategory,Collectors.counting()))
        );


        //To group the courses category wise & get the highest review course of that category.
        System.out.println("\nGroup courses category wise with highest reviewed course of that category is\n"+
                courses.stream().collect(Collectors.groupingBy(Courses::getCategory,
                        Collectors.maxBy(Comparator.comparing(Courses::getReviewScore))))
                );


    }
}
