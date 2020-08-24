package com.example.mysafedisclosure;

public class Intervention {

    private int id;
    private String category;
    private String message;
    private float risk;

    Intervention (int anId, String aCategory, String aMessage, float aRisk){
        id= anId;
        category =aCategory;
        message =aMessage;
        risk=aRisk;
    }

}
