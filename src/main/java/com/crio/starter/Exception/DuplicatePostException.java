package com.crio.starter.Exception;

public class DuplicatePostException extends RuntimeException{
   public DuplicatePostException(){
        super("error: duplicate post inserted");
   }
}
