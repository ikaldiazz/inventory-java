/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.misc;

/**
 *
 * @author ALPABETAPINTAR
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;

public class DayTime implements Runnable {

    JLabel jb;

//Constructor takes the clock JLabel
    public DayTime(JLabel jb) {
        this.jb = jb;
    }

    public DayTime(JLabel jb, int format) {
        this.jb = jb;
    }

    public void run() {
        while (true) {
            try {
                //Thread sleeps & updates ever 1 second, so the clock changes every 1 second.
                jb.setText(dayNow());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

//Gets the current time.
    public String dayNow() {
        Calendar now = Calendar.getInstance();

        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        String dayOfMonthStr = String.valueOf(dayOfMonth);

        int month = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);

        Date date = new Date();

        SimpleDateFormat dayname = new SimpleDateFormat("EEEE");
        
        SimpleDateFormat monthname = new SimpleDateFormat("MMMM");

//        simpleDateFormat = new SimpleDateFormat("MMMM");
//        System.out.println("MONTH " + simpleDateFormat.format(date).toUpperCase());
//
//        simpleDateFormat = new SimpleDateFormat("YYYY");
//        System.out.println("YEAR " + simpleDateFormat.format(date).toUpperCase());
        String time = toTitleCase(dayname.format(date)) + ", " + dayOfMonthStr + " " + toTitleCase(monthname.format(date)) + " " + year;
        //String time = zero(hrs) + ":" + zero(min) + ":" + zero(sec);
        return time;
    }

    //Sets the zeroes needed within our hh/mm/ss clock.
    public String zero(int num) {
        String number = (num < 10) ? ("0" + num) : ("" + num);
        return number;
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
