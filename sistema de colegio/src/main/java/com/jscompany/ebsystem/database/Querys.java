/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.database;

/**
 *
 * @author JoaoIsrael
 */
public class Querys {
    
    public static String getUsuariobyUserPass = "select e from Loguin e where e.username = :user and e.pass = :pass";
    
}
