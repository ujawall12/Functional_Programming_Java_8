package Java_8;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class Main {
    public static void main(String[] args) throws Exception{
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
                courses.stream().sorted(Comparator.comparing(Courses::getReviewScore)).filter(c->c.getReviewScore()>92)
                        .findFirst().get();

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

        courses.stream().collect(groupingBy(Courses::getCategory, counting()))
        );


        //To group the courses category wise & get the highest review course of that category.
        System.out.println("\nGroup courses category wise with highest reviewed course of that category is\n"+
                courses.stream().collect(groupingBy(Courses::getCategory,
                        Collectors.maxBy(Comparator.comparing(Courses::getReviewScore))))
                );

        // Create Stream using Stream.of()
        System.out.println("\nStream using stream.of()\n");
        Stream.of(1,2,3,4,5,6).forEach(System.out::println);

        //Create streams of all elements of array
        int num[]={1,2,3,4,5};
        System.out.println("\nStream of elements of int array\n");
        Arrays.stream(num).forEach(System.out::println);

        //Create a stream based on some condition
        System.out.println("\nStream based on some specific condition\n");
        int result7=IntStream.range(1,11).sum();
        System.out.println(result7);

        //Create a stream of numbers based on some specific condition and store it into a list
        System.out.println("\nStream of primitive stored in a list\n");
        List<Integer> result8=
                IntStream.iterate(1,e->e+2).limit(10).boxed()
                        .collect(Collectors.toList());

        System.out.println(result8);

        //The case if the result is going beyond Long.MAX_VALUE
        System.out.println("\nFor the calculations > Long.MAX_VALUE\n"+
        LongStream.range(1,50).mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE,BigInteger::multiply)
        ); //Factorial of 50

        //Print all the characters of courses
        System.out.println("\nPrinting all the characters of courses\n" +
        courses.stream().map(c->c.getName().split("")).
                flatMap(Arrays::stream).collect(Collectors.toList())
        );

        //Replacing the whole list with something
        System.out.println("\nReplacing the whole list with uppercase\n");
        List<String> modifyableCourse=new ArrayList<>(coursestemp);
        modifyableCourse.replaceAll(c->c.toUpperCase());
        System.out.println(modifyableCourse);

        //Remove the courses with length < 6
        System.out.println("\nRemove the courses whose length is < 6\n");
        modifyableCourse.removeIf(c->c.length()<6);
        System.out.println(modifyableCourse);


        //Manipulating files using Stream
//        System.out.println("\nFiles using streams\n");
//        Files.lines(Paths.get("file.txt")).map(str -> str.split(" ")).
//                flatMap(Arrays::stream).distinct().sorted().forEach(System.out::println);
//
//        Files.list(Paths.get("."))
//                .filter(Files::isDirectory)
//                .forEach(System.out::println);

        List<Integer> demo=List.of(1,2,5,4,23,24,52,78,18,9,11,121,212,1,2,5);

        //******************To print all numbers starting with 1*****************************************************
        System.out.println("\nAll numbers starting with 1 are\n");
        demo.stream().map(n->n+"").filter(s->s.startsWith("1")).forEach(System.out::println);

        //******************To print all the duplicate numbers*******************************************************
        System.out.println("\nDuplicate numbers are \n");
        Set<Integer> result9=
                demo.stream().filter(i-> Collections.frequency(demo,i)>1).collect(Collectors.toSet());
        System.out.println(result9);

        //                          OR
        System.out.println("\nDuplicate numbers are (Alterntive way)\n");
        Set<Integer> set=new HashSet<>();
        demo.stream().filter(i-> !set.add(i)).forEach(System.out::println);

        //*****************************To find the total number of elements in the list******************************
        System.out.println("\nFind total number of elements in list\n"+
                demo.stream().count());

        //*****************************To the maximum number in a list***********************************************
        System.out.println("\nMaximum element in the list is \n"+
                demo.stream().reduce(Integer.MIN_VALUE, Integer::max)
        );

        //*****************************To find the first non repeating character in a string******************************
        String string="Aaababcd";
//        char arr[]=string.toCharArray();
        Character result10= string.chars().mapToObj(i-> Character.toLowerCase(Character.valueOf((char)i)))
                .collect(groupingBy(Function.identity(),LinkedHashMap::new, counting()))
                .entrySet().stream().filter(e->e.getValue()==1).map(e->e.getKey())
                .findFirst().get();
        System.out.println("\nThe first non repating character in the string is \n"+result10);

        //*****************************To find first repeating character in a string**************************************
        Character result11=string.chars().mapToObj(s->Character.toLowerCase(Character.valueOf((char) s)))
                .collect(groupingBy(Function.identity(),LinkedHashMap::new, counting()))
                .entrySet().stream().filter(e->e.getValue()>1).map(e->e.getKey()).findFirst().get();
        System.out.println("\nThe first repating character in the string is \n"+result11);

        //*********************To find frequency of each element in a list************************************************
        System.out.println("\nFrequency of each element in a list is\n"+
                demo.stream()
                        .collect(groupingBy(Function.identity(), counting()))
        );

        //*********************To find frequency of each character in a string******************************************
        System.out.println("\nFind the frequency of each character in a string\n"+
                string.chars().mapToObj(i->Character.toLowerCase(Character.valueOf((char) i)))
                        .collect(groupingBy(Function.identity(), counting()))
        );

        //                                OR
        Map<String,Long> frequency=Arrays.stream(string.split(""))
                .collect(groupingBy(Function.identity(),counting()));
        System.out.println("Frequency of each character in a string\n"+frequency);

    }
}
