package com.cohort5projectrest.Validators;

import java.util.regex.*;

public class RegexMatches {
    public static void main(String[] args) {
        //Means it can start and end with anything
        Pattern pattern = Pattern.compile(".XX.");

        Matcher matcher = pattern.matcher("SXXB");
        Matcher matcher2 = pattern.matcher("SXXX");

        //This will return true
        System.out.println("String matches the given Regex - " + matcher.matches());
        System.out.println("String matches the given Regex - " + matcher2.matches());


        //Regex characters
        //includes a, b or c
        //The + sign means any character can occur at once
        Pattern pattern2 = Pattern.compile("[abc]+");

        //includes every other character apart from a, b or c
        //The * sign means the characters can occur at least zero times
        Pattern pattern3 = Pattern.compile("[^abc]*");

        //range of a to z for both cases
        //The length should be exactly of 5 characters
        Pattern pattern4 = Pattern.compile("[a-zA-Z]{5}");

        //Union a to d or m to s
        //The length should be of 5 characters or more but less than 10
        Pattern pattern5 = Pattern.compile("[a-d[m-s]]{5,10}");

        //Intersection
        //Means character occurs once or not at all, so length of string is 1 at most
        Pattern pattern6 = Pattern.compile("[a-z&&[def]]?");

        //Subtraction of b and c
        Pattern pattern7 = Pattern.compile("[a-z&&[^bc]]");

        //Subtraction of characters from m through to p
        Pattern pattern8 = Pattern.compile("[a-z&&[^m-p]]");

        Pattern pattern9 = Pattern.compile(".D*[a-z]*");


        Matcher matcher3 = pattern9.matcher("dddddeeeeeeee");

        System.out.println("String matches the given Regex - " + matcher3.matches());

    }
}
