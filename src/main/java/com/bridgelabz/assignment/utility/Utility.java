package com.bridgelabz.assignment.utility;


/*
A utility class for return response
 */
public class Utility {
    public static Response getResponse(String message,Object data){
        return new Response(message,data);
    }


}
