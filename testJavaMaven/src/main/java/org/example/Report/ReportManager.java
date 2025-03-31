package org.example.Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Use relative paths
            String reportPath = "test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Test Report");
            sparkReporter.config().setReportName("Automation Test Report 123456");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            System.out.println("ExtentReports initialized with report path: " + reportPath);
        }
        return extent;
    }


    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println("ExtentReports flushed successfully");
        }
    }
}
