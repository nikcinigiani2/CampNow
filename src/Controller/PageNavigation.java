package Controller;

import View.*;

import javax.swing.*;
import java.awt.*;

public class PageNavigation {
    static PageNavigation istance;
    private JFrame currentFrame;
    private Dimension frameSize;
    private Point frameLocation;

    private PageNavigation(JFrame currentFrame) {
        this.currentFrame = currentFrame;
        this.frameSize = currentFrame.getSize(); // get the size of the current frame
        this.frameLocation = currentFrame.getLocation();
    }

    public static PageNavigation getInstance(JFrame currentFrame){
        if(istance==null){
            istance =new PageNavigation(currentFrame);
        }
        return istance;
    }

    public void navigateToLoginAs(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        LoginAs loginAs= new LoginAs();
        loginAs.setSize(frameSize);
        loginAs.setLocation(frameLocation);

        currentFrame = loginAs;
    }

    public void navigateToClubLogin(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        LoginClub lc = new LoginClub();
        lc.setSize(frameSize);
        lc.setLocation(frameLocation);

        currentFrame = lc;
    }

    public void navigateToClubRegister(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        RegisterClub rc = new RegisterClub();
        rc.setSize(frameSize);
        rc.setLocation(frameLocation);

        currentFrame = rc;
    }

    public void navigateToUserLogin(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        LoginUser lu = new LoginUser();
        lu.setSize(frameSize);
        lu.setLocation(frameLocation);

        currentFrame = lu;
    }

    public void navigateToUserRegister(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        RegisterUser ru = new RegisterUser();
        ru.setSize(frameSize);
        ru.setLocation(frameLocation);

        currentFrame = ru;
    }


    public void navigateToUserHome(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        HomeUser home = new HomeUser();
        home.setSize(frameSize);
        home.setLocation(frameLocation);
        currentFrame = home;
    }

    public void navigateToClubHome(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        HomeClub home = new HomeClub();
        home.setSize(frameSize);
        home.setLocation(frameLocation);
        currentFrame = home;
    }
}