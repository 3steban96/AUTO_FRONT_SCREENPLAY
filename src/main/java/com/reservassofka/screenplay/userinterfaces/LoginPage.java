package com.reservassofka.screenplay.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target EMAIL_INPUT = Target.the("Email Input").located(By.id("email"));
    public static final Target PASSWORD_INPUT = Target.the("Password Input").located(By.id("password"));
    public static final Target LOGIN_BUTTON = Target.the("Login Button").located(By.cssSelector("button[type='submit']"));
}
