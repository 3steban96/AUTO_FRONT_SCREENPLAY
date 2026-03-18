package com.reservassofka.screenplay.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage {
    public static final Target AVAILABLE_WORKSPACE_BOOK_BUTTON = Target.the("Book Button of First Available Workspace")
            .located(By.xpath("(//div[contains(@class, 'item-card') and .//span[contains(@class, 'status-badge') and contains(text(), 'DISPONIBLE')]]//button[contains(@class, 'btn-book') and not(@disabled)])[1]"));
}
