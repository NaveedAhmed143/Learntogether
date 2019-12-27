package com.example.learntogether.Model;

public class Messagefromfirebase {
    String Message;
    String Tutor;

    public Messagefromfirebase(String message, String tutor) {
        Message = message;
        Tutor = tutor;
    }

    public Messagefromfirebase() {
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTutor() {
        return Tutor;
    }

    public void setTutor(String tutor) {
        Tutor = tutor;
    }
}
