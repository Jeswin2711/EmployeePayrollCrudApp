package com.bridgelabz.assignment.utility;


/*
A utility class for return response
 */

/*
 A Utility Class which has a Static getResponse function to get the response by passing msg and data
 */
public class Utility {
    public static Response getResponse(String message,Object data){
        return new Response(message,data);
    }
}
