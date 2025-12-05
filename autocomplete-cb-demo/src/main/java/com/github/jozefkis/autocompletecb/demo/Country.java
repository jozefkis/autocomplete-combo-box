/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jozefkis.autocompletecb.demo;

/**
 *
 * @author SOFIJA
 */
public class Country
{
    private  String name;
    private  String capital;
    private  String postcode;

    public Country(String name, String capital, String postcode)
    {
        this.name = name;
        this.capital = capital;
        this.postcode = postcode;
    }

    public String getName()
    {
        return name;
    }

    public String getCapital()
    {
        return capital;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCapital(String capital)
    {
        this.capital = capital;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }
    
    @Override
    public String toString()
    {
        return postcode + " - " + name + " (" + capital + ")";
    }
}
