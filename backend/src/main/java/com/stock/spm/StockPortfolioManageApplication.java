package com.stock.spm;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockPortfolioManageApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StockPortfolioManageApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.setLogStartupInfo(true);

		app.run(args);
	}


}
