package net.javaguides.springdemo.dto;

import lombok.Data;

@Data
public class Payment {
    private int id;
    private String firstName;
    private String lastName;
    private boolean read = false;
    public int getid()
    {
        return id;
    }
    public boolean isread()
    {
        return read;
    }
    public void setread()
    {
        this.read = true;
    }
}
