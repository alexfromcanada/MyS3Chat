package com.mys3soft.mys3chat.Services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Tools {

    public static final String ENDPOINT = "https://mys3chat.firebaseio.com";

    public static String encodeString(String string) {
        return string.replace(".", ",");
    }

    public static String decodeString(String string) {
        return string.replace(",", ".");
    }

    public static IFireBaseAPI makeRetroFitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit.create(IFireBaseAPI.class);
    }

    public static String toProperName(String s) {
        if (s.length() <= 1)
            return s.toUpperCase();
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static int createUniqueIdPerUser(String userEmail) {
        String email = userEmail.split("@")[0].toLowerCase();
        final Map<Character, Integer> map;
        map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        map.put('i', 9);
        map.put('j', 10);
        map.put('k', 11);
        map.put('l', 12);
        map.put('m', 13);
        map.put('n', 14);
        map.put('o', 15);
        map.put('p', 16);
        map.put('q', 17);
        map.put('r', 18);
        map.put('s', 19);
        map.put('t', 20);
        map.put('u', 21);
        map.put('v', 22);
        map.put('w', 23);
        map.put('x', 24);
        map.put('y', 25);
        map.put('z', 26);
        String intEmail = "";

        for (char c : email.toCharArray()) {
            int val = map.get(c);
            intEmail += val;
        }

        if (intEmail.length() > 9) {
            intEmail = intEmail.substring(0, 9);
        }

        return Integer.parseInt(intEmail);

    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    public static String toCharacterMonth(int month) {
        if (month == 1) return "Jan";
        else if (month == 2) return "Feb";
        else if (month == 3) return "Mar";
        else if (month == 4) return "Apr";
        else if (month == 5) return "May";
        else if (month == 6) return "Jun";
        else if (month == 7) return "Jul";
        else if (month == 8) return "Aug";
        else if (month == 9) return "Sep";
        else if (month == 10) return "Oct";
        else if (month == 11) return "Nov";
        else return "Dec";
    }

    public static String lastSeenProper(String lastSeenDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yy hh:mm a");
        Date currentDate = new Date();
        String cuurentDateString = dateFormat.format(currentDate);

        Date nw = null;
        Date seen = null;
        try {

            nw = dateFormat.parse(cuurentDateString);
            seen = dateFormat.parse(lastSeenDate);
            long diff = nw.getTime() - seen.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffMinutes = diff / (60 * 1000) % 60;
            if (diffDays > 0)
                return "Last seen " + diffDays + " days ago";
            else if (diffHours > 0)
                return "Last seen " + diffHours + " hours ago";
            else if (diffMinutes > 0) {
                if (diffMinutes <= 1) {
                    return "Last seen 1 minute ago";
                } else {
                    return "Last seen " + diffMinutes + " minutes ago";
                }
            } else return "Last seen a moment ago";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }

}
