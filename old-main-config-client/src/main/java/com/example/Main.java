package com.example;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {


    public static void main(String... args) throws IOException {

        ResourceBundle rb = ResourceBundle.getBundle("main");

        Properties properties = ConfigReader.getConfiguration(rb, "production");
        System.out.println(" ************ Retrieve properties for profile = production ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        properties = ConfigReader.getConfiguration(rb, "pprd");
        System.out.println(" ************ Retrieve properties for profile = pprd ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        properties = ConfigReader.getConfiguration(rb, "ctrl");
        System.out.println(" ************ Retrieve properties for profile = ctrl ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        properties = ConfigReader.getConfiguration(rb, "dev");
        System.out.println(" ************ Retrieve properties for profile = dev ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        System.out.println(" \n\n************ Now let's fetch properties from v2 Git Branch ************\n ");

        properties = ConfigReader.getConfiguration(rb, "production", "v2");
        System.out.println(" ************ Retrieve properties for profile = production (v2) ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        properties = ConfigReader.getConfiguration(rb, "pprd", "v2");
        System.out.println(" ************ Retrieve properties for profile = pprd (v2) ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        properties = ConfigReader.getConfiguration(rb, "ctrl", "v2");
        System.out.println(" ************ Retrieve properties for profile = ctrl (v2) ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");

        properties = ConfigReader.getConfiguration(rb, "dev", "v2");
        System.out.println(" ************ Retrieve properties for profile = dev (v2) ************ ");
        System.out.println("\tlocal.message = " + properties.get("local.message"));
        System.out.println("\tglobal.message = " + properties.get("global.message"));
        System.out.println("\tmessage = " + properties.get("message"));
        System.out.println("\tserver.port = " + properties.get("server.port"));
        System.out.println(" ********************************************************************** \n");
    }

}
