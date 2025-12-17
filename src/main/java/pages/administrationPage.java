/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-16
 * Descripción: Page Object para la ventana Administración
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;
import utils.utilsScreen;

import java.time.Duration;

public class AdministrationPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private SafeClick safeClick;
	/*
	 * Por ahora solo tiene para ir al apartado de Usuarios
	 */

	@FindBy(xpath = "//ion-button[.//text()[normalize-space()='Usuarios']]")
	private WebElement botonUsuarios;

	public AdministrationPage(WebDriver driver) {
		this.driver = driver;

		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.safeClick = new SafeClick(driver, wait);
		PageFactory.initElements(driver, this);
	}

	public void clickUsuarios() {
		try {
			if (botonUsuarios != null) {
				safeClick.safeClick(botonUsuarios);
				return;
			}
		} catch (Exception ignored) {
		}

		try {
			WebElement irUsuario = driver.findElement(By.xpath("//*[normalize-space()='Usuarios']"));
			safeClick.safeClick(irUsuario);
		} catch (Exception error) {
			utilsScreen.takeScreenshot(driver, "administration_clickUsuarios_error");
			throw error;
		}
	}
}
