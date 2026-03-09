package kuby.web.utility;

import java.io.File;
import java.nio.file.FileSystems;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import kuby.web.testBase.TestBase;

public class ReportsUtils extends TestBase {

	public static ExtentReports getExtentReport() {
		String FS = FileSystems.getDefault().getSeparator();
		ExtentReports report = new ExtentReports();
		File extentFile = new File(
				System.getProperty("user.dir") + FS + "test-output" + FS + "htmlReport" + FS + "TestReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentFile);
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Kuby Test");
		//sparkReporter.config().setReportName("<div style='position: absolute; top: 10px; right: 250px; width: 100px; height: 65px;'><img src='data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyBpZD0iTGF5ZXJfMiIgZGF0YS1uYW1lPSJMYXllciAyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2aWV3Qm94PSIwIDAgMTE2NiAzNzMuMzYiPgogIDxkZWZzPgogICAgPHN0eWxlPgogICAgICAuY2xzLTEgewogICAgICAgIGZpbGw6IHVybCgjbGluZWFyLWdyYWRpZW50KTsKICAgICAgfQoKICAgICAgLmNscy0yIHsKICAgICAgICBmaWxsOiAjZmUwMTAwOwogICAgICB9CgogICAgICAuY2xzLTMgewogICAgICAgIGZpbGw6ICNmZmY7CiAgICAgIH0KCiAgICAgIC5jbHMtNCB7CiAgICAgICAgZmlsbDogIzM2OTZiMTsKICAgICAgfQoKICAgICAgLmNscy01IHsKICAgICAgICBmaWxsOiB1cmwoI2xpbmVhci1ncmFkaWVudC0yKTsKICAgICAgfQogICAgPC9zdHlsZT4KICAgIDxsaW5lYXJHcmFkaWVudCBpZD0ibGluZWFyLWdyYWRpZW50IiB4MT0iMCIgeTE9IjE2MS45MiIgeDI9IjI2MC4xOCIgeTI9IjE2MS45MiIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiPgogICAgICA8c3RvcCBvZmZzZXQ9IjAiIHN0b3AtY29sb3I9IiNmZmYiIHN0b3Atb3BhY2l0eT0iLjciLz4KICAgICAgPHN0b3Agb2Zmc2V0PSIuNyIgc3RvcC1jb2xvcj0iI2ZmZiIvPgogICAgPC9saW5lYXJHcmFkaWVudD4KICAgIDxsaW5lYXJHcmFkaWVudCBpZD0ibGluZWFyLWdyYWRpZW50LTIiIHgxPSIyNDcuMzIiIHkxPSIxNTguNTYiIHgyPSIyOS4xNSIgeTI9IjI1LjcxIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+CiAgICAgIDxzdG9wIG9mZnNldD0iMCIgc3RvcC1jb2xvcj0iI2ZmZiIgc3RvcC1vcGFjaXR5PSIuNyIvPgogICAgICA8c3RvcCBvZmZzZXQ9IjEiIHN0b3AtY29sb3I9IiNmZmYiLz4KICAgIDwvbGluZWFyR3JhZGllbnQ+CiAgPC9kZWZzPgogIDxnIGlkPSJMYXllcl8xLTIiIGRhdGEtbmFtZT0iTGF5ZXIgMSI+CiAgICA8Zz4KICAgICAgPGc+CiAgICAgICAgPGNpcmNsZSBjbGFzcz0iY2xzLTIiIGN4PSI0OS44MyIgY3k9IjMyMy41NyIgcj0iNDkuNzkiLz4KICAgICAgICA8cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Im01MS45NiwyMjkuNzRoMGMtMTYuOTYuNjMtMzEuMjEtMTIuNjEtMzEuODQtMjkuNTctLjYzLTE2Ljk2LDEyLjYxLTMxLjIxLDI5LjU3LTMxLjg0aDBjMTYuOTYtLjYzLDMxLjIxLDEyLjYxLDMxLjg0LDI5LjU3LjYzLDE2Ljk2LTEyLjYxLDMxLjIxLTI5LjU3LDMxLjg0Wk0yMDkuODcsOTIuNjRjMTYuOTYtLjYzLDMxLjIxLDEyLjYxLDMxLjg0LDI5LjU3LjYzLDE2Ljk2LTEyLjYxLDMxLjIxLTI5LjU3LDMxLjg0LTE2Ljk2LjYzLTMxLjIxLTEyLjYxLTMxLjg0LTI5LjU3LS42My0xNi45NiwxMi42MS0zMS4yMSwyOS41Ny0zMS44NFptNTAuMTcsMjguMTNjLS4wNy0uOTQtLjE5LTEuODgtLjMxLTIuODEtLjY3LTQuNzgtMi4wMy05LjU1LTQuMTctMTQuMTYtMTEuNjItMjUuMDgtNDEuMzUtMzUuOTgtNjYuNDMtMjQuMzYtMTIuODMsNS45NC0yMS45NiwxNi42NC0yNi4yMywyOS4wMy0uMTIuMjgtLjIxLjU4LS4zLjg3LS4yOSwxLjEzLS42MSwyLjI0LS45NSwzLjMzLTEuMyw0LjE1LTIuOTQsOC4xMi00Ljg3LDExLjktNi42MiwxMi45NS0xNi44NiwyMy40Ny0zMC4yNCwyOS42Ny0xNy4yMyw3Ljk4LTM2LjU0LDcuNC01NC4yNC0uMDMtMS4xOC0uNDctMi4zNC0xLTMuNDktMS41Ni0uMjMtLjEtLjQ3LS4yLS42OS0uMjgtMTIuMjItNC43NC0yNi4yNi00LjY4LTM5LjEsMS4yNkMzLjk1LDE2NS4yMy02Ljk3LDE5NC45Nyw0LjY1LDIyMC4wNWMxMS42MiwyNS4wOCw0MS4zNiwzNS45Nyw2Ni40MywyNC4zNiwxMi44My01Ljk0LDIxLjk1LTE2LjYyLDI2LjI0LTI5LjAxLjA3LjA1LjEyLjA5LjE4LjEyLDQuNzYtMjAuNzIsMTcuMjItMzguMDYsMzUuOTQtNDYuNzQsMTYuNjctNy43MiwzNS4zNC03LjIyLDUyLjU4LS4xMiwyLjA0Ljg1LDQuMDcsMS43OCw2LjA3LDIuODEsMTIuMjEsNC43NSwyNi4yNyw0LjcsMzkuMS0xLjI0LDE5LjI1LTguOTIsMzAuMTMtMjguNSwyOC45MS00OC40Ni0uMDItLjMzLS4wMy0uNjctLjA2LTFaIi8+CiAgICAgICAgPHBhdGggY2xhc3M9ImNscy01IiBkPSJtMjYwLjAzLDEyMC43N2MtLjA3LS45NC0uMTktMS44OC0uMzEtMi44MS0yLjEzLTE2LjQ2LTEyLjM1LTMxLjUtMjguNTEtMzkuMDEtMTIuODMtNS45NS0yNi44Ny02LjAyLTM5LjA5LTEuMjksMC0uMDgtNDAuMiwxMS4yMy01OC45MiwyLjU0LTE2LjY2LTcuNzMtMjguMzQtMjIuMzEtMzQuMDYtNDAuMDYtLjY3LTIuMS0xLjI3LTQuMjUtMS43OC02LjQ0LTQuMjYtMTIuMzktMTMuMzgtMjMuMDktMjYuMjEtMjkuMDVDNDYuMDctNi45NywxNi4zNCwzLjkxLDQuNywyOC45OGMtMTEuNjQsMjUuMDctLjc1LDU0LjgxLDI0LjMyLDY2LjQ0LDEyLjgzLDUuOTUsMjYuODksNi4wMSwzOS4xLDEuMjcuMjktLjA5LjU4LS4yMS44Ni0uMzMsMS4wNS0uNTEsMi4xLS45OCwzLjE2LTEuNDMsMTcuNzUtNy40OCwzNy4xMy04LjA3LDU0LjQxLS4wNSwxMy4zOCw2LjIxLDIzLjYxLDE2LjczLDMwLjIyLDI5LjY5LDEuOSwzLjcyLDMuNTEsNy42NCw0LjgsMTEuNzMuNCwxLjIxLjc1LDIuNDMsMS4wNiwzLjY4LjA3LjI0LjE1LjQ5LjIzLjcxLDQuMjgsMTIuMzksMTMuMzksMjMuMDcsMjYuMjIsMjkuMDMsMjUuMDUsMTEuNjMsNTQuODEuNzUsNjYuNDQtMjQuMzIsMy41NS03LjY2LDQuOTgtMTUuNzUsNC41Ny0yMy42My0uMDItLjMzLS4wMy0uNjctLjA2LTFaTTUxLjQ1LDgwLjg1aDBjLTE2Ljk2LjYzLTMxLjIxLTEyLjYxLTMxLjg0LTI5LjU3LS42My0xNi45NiwxMi42MS0zMS4yMSwyOS41Ny0zMS44NGgwYzE2Ljk2LS42MywzMS4yMSwxMi42MSwzMS44NCwyOS41Ny42MywxNi45Ni0xMi42MSwzMS4yMS0yOS41NywzMS44NFptMTYwLjY5LDczLjE5Yy0xNi45Ni42My0zMS4yMS0xMi42MS0zMS44NC0yOS41Ny0uNjMtMTYuOTYsMTIuNjEtMzEuMjEsMjkuNTctMzEuODQsMTYuOTYtLjYzLDMxLjIxLDEyLjYxLDMxLjg0LDI5LjU3LjYzLDE2Ljk2LTEyLjYxLDMxLjIxLTI5LjU3LDMxLjg0WiIvPgogICAgICA8L2c+CiAgICAgIDxnIGlkPSJVb3JrWXYiPgogICAgICAgIDxnPgogICAgICAgICAgPHBhdGggY2xhc3M9ImNscy0zIiBkPSJtMzQ4Ljk2LDIzMS41OGMwLDEzLjYyLjEsMzMuNDUsMCw0NS45Ni0uMDUsNy4wNS45MywxMS41NS0zLjk5LDExLjg2LTQuMDQuMjYtMTYuMjUuMS0xOS4wMi4xMi02LjcyLjA1LTUuOTctLjkyLTUuOTctMTEuNDIsMC01Ny4wOS4wOC0xMTQuMTgtLjE0LTE3MS4yNy0uMDItNS4zOSwxLjI5LTcuMSw2LjY5LTYuNjMsNS44Ni41LDExLjgxLjMyLDE3LjcxLjA0LDMuODYtLjE4LDUuMDQsMS4zMiw0Ljc2LDQuOTUtLjI1LDMuMi0uMDUsNi40NC0uMDUsMTAuODEsMzIuMDgtMjUuNjEsODAuMTMtMTkuMDUsMTAzLjAxLDE0LjY3LDQ0Ljk5LDYzLjkzLTI4LjY3LDE1Mi41My0xMDMuMDEsMTAwLjkxWm05Mi4zNC01Ny42OWMuMTMtMjUuOTYtMjEuMDItNDcuMjktNDcuMTEtNDcuNS02Mi44MiwxLjc2LTYzLjM5LDkxLjc3LTEuMDUsOTQuNDQsMjYuNjYuMTYsNDguMDMtMjAuNjYsNDguMTYtNDYuOTRaIi8+CiAgICAgICAgICA8cGF0aCBjbGFzcz0iY2xzLTMiIGQ9Im03NDMuNzQsMjM3LjJjLTguNTctNS4wMS0xNi4xNy0xMS45NC0yMi4xMi0yMC43Mi00Ni42NS02OS4yMywzNC4zOC0xNTEuODksMTA0LjQxLTk5LjY3LDAtNC43NS4xNy04LjU1LS4wNS0xMi4zMy0uMTgtMy4xMS43Ni00LjM4LDQuMDYtNC4yMyw1LjA4LjIzLDEwLjIxLjQyLDE1LjI2LS4wNCw1LjI4LS40OCw2Ljg0LDEuMDYsNi44LDYuNTktLjI2LDQyLjA0LS4xNCw4NC4wOC0uMTQsMTI2LjEyLDAsMi42NC0uMjQsNS4zMS4wNSw3LjkxLjQ0LDMuOTItMS4yOCw0Ljg3LTQuOSw0LjctNS4yOC0uMjYtMTAuNjEtLjM2LTE1Ljg3LjAyLTQuNDQuMzMtNS43NC0xLjMzLTUuMjctNS40OC4zMS0yLjc1LjA2LTUuNTYuMDYtOS4zNy0yNC4xLDE5LjQ4LTU3LjI5LDIxLjEzLTgyLjI5LDYuNW0zNS4wOS0xNi4zN2MyNi4xNS0uMDEsNDcuMzYtMjEuMTUsNDcuMzQtNDcuMTctLjAyLTI1Ljk5LTIxLjM0LTQ3LjI2LTQ3LjM5LTQ3LjI3LTI2LjExLS4wMi00Ny41MywyMS4xMi00Ny42Myw0Ny4wMS0uMTEsMjYuMzUsMjEuMTEsNDcuNDUsNDcuNjgsNDcuNDRaIi8+CiAgICAgICAgICA8cGF0aCBjbGFzcz0iY2xzLTMiIGQ9Im02MjguODcsMTczLjcyYzAsNDAuNDEtMzIuOTEsNzMuMzctNzMuMjcsNzMuNC05OC4wNi0zLjk4LTk4LjEzLTE0My4wMy4wOS0xNDYuODEsNDAuNDIuMDYsNzMuMTksMzIuOTMsNzMuMTgsNzMuNDFabS03My44Miw0Ny4xYzI2LjcuMDYsNDcuNzgtMjAuODcsNDcuNjctNDcuMzMtMi41MS02Mi41Ni05Mi4zNi02Mi40NS05NC43Mi4wNS0uMDUsMjYuMjUsMjAuODIsNDcuMjEsNDcuMDUsNDcuMjhaIi8+CiAgICAgICAgICA8cGF0aCBjbGFzcz0iY2xzLTMiIGQ9Im0xMDYzLjQzLDEyMi42M2M4LjU1LTguMjYsMTYuODEtMTUuNzUsMjguMDYtMTkuNTgsMzEuNTMtMTAuNzYsNzQuNywxMi43OCw3NC40Niw1MS43MS0uMTgsMjguNDQtLjEyLDU2Ljg5LjA2LDg1LjMzLjAzLDQuMTctLjk1LDUuNzYtNS4zNSw1LjQ3LTUuNjgtLjM4LTExLjQxLS4zNC0xNy4xLS4wMS00LjI3LjI1LTUuNTQtMS4xNS01LjUxLTUuNDUuMTktMjQuMzguMDktNDguNzYuMDktNzMuMTQsMS4xNy0yNi45Ny0xNi4zNC00Mi40Ni00Mi42NS0zNi43LTE1Ljk1LDIuNzktMzIuMjEsMTUuNjctMzIuMTEsMzMuMS4xMiwyNS4zOS0uMDksNTAuNzkuMTcsNzYuMTguMDUsNC45NS0xLjQ4LDYuMjktNi4xOCw2LjAxLTYuMDktLjM3LTEyLjIyLS4yMi0xOC4zMi0uMDQtMy4xLjA5LTQuMjUtLjgyLTQuMjQtNC4wOS4wOS00NS43MS4wOS05MS40MiwwLTEzNy4xMywwLTMuMzUsMS4zMS00LjEsNC4zMi00LjAzLDYuNzIuMTYsMTMuNDQuMTcsMjAuMTYsMCwzLjIxLS4wOCw0LjI5LDEuMDksNC4xOSw0LjI1LS4xOSw2LjAzLS4wNiwxMi4wOC0uMDYsMTguMTJaIi8+CiAgICAgICAgICA8cGF0aCBjbGFzcz0iY2xzLTMiIGQ9Im02ODUuNDIsMTQ0LjY3YzAsMzEuNjctLjA5LDYzLjM1LjEsOTUuMDIuMDMsNC40MS0uODcsNi4yNS01LjY1LDUuODgtNS40Ni0uNDItMTEuMDEtLjQ0LTE2LjQ3LDAtNS40NC40NC03LjE5LTEuMTItNy4xNi02LjkxLjI2LTUzLjYuMTQtMTA3LjIuMTQtMTYwLjgsMC04Ljc4LDAtOC45NC05LjAyLTguOS0zLjUyLjAxLTUuMjItLjY3LTQuOTktNC42NC4zMi01LjQ2LjIxLTEwLjk2LjAzLTE2LjQ0LS4xMS0zLjA4Ljc4LTQuMjgsNC4wOS00LjIzLDExLjYuMTgsMjMuMjEuMjUsMzQuOC0uMDIsNC41Ny0uMTEsNC4xNSwyLjQ5LDQuMTUsNS40My0uMDMsMzEuODgtLjAyLDYzLjc1LS4wMiw5NS42M1oiLz4KICAgICAgICAgIDxwYXRoIGNsYXNzPSJjbHMtMyIgZD0ibTkwOS4xOCwxMjIuMTRjMTIuMDEtMTMuNjksMjUuOTktMjAuMzEsNDMuMDEtMjEuOCwzLjc5LS40OSw1LjAxLDEuMDUsNC44Niw0Ljc3LS4yNSw2LjA4LS4xNywxMi4xOC0uMDIsMTguMjcuMDgsMy4wMi0uNTksNC4xNy00LjA2LDQuMDctMjQuMjgtLjcyLTQzLjc4LDE4LjU1LTQzLjc4LDQyLjk4LDAsMjIuOTQtLjE3LDQ1Ljg5LjEzLDY4LjgzLjA3LDUuMjEtMS41LDYuNjktNi41MSw2LjMtNS40Ni0uNDItMTAuOTktLjIyLTE2LjQ4LS4wNS0zLjAyLjA5LTQuMzEtLjY3LTQuMy00LjAyLjEtNDUuNjkuMDktOTEuMzcsMC0xMzcuMDYsMC0zLjE5Ljk5LTQuMjcsNC4xOC00LjE3LDYuMy4xOSwxMi42Mi4xOCwxOC45MywwLDMuMjctLjA5LDQuMjEsMS4yNSw0LjEsNC4zMy0uMTksNS4yNy0uMDUsMTAuNTQtLjA1LDE3LjU0WiIvPgogICAgICAgICAgPHBhdGggY2xhc3M9ImNscy0zIiBkPSJtMTEwMS4yMywzMTAuNjFjLTEuNzEtMi4zNi0zLjgzLTMuNDItNi44NC0zLjQyLTQuOTcsMC04LjcyLDMuNzUtOC43Miw5Ljg2djI1LjVoLTYuNTJ2LTQwLjc0aDYuNTJ2My45OWMxLjcxLTIuODUsNC40OC00LjY0LDkuNDUtNC42NCw1LjU0LDAsOS4wNCwxLjg3LDExLjY1LDUuNTRsLTUuNTQsMy45MVoiLz4KICAgICAgICA8cGF0aCBjbGFzcz0iY2xzLTMiIGQ9Im0xMTExLjI1LDMwMS44MWg2LjUydjMuOTljMS43OS0yLjc3LDQuODktNC42NCwxMC4xLTQuNjRzOS42MSwyLjIsMTEuOSw2LjQ0YzIuNzctNC4zMiw3LjI1LTYuNDQsMTIuMjItNi40NCw4LjcyLDAsMTQuMDEsNS4zOCwxNC4wMSwxNS45N3YyNS40MmgtNi41MnYtMjUuMzRjMC03LjQxLTMuNjctMTAuMDItOC40Ny0xMC4wMi01LjIxLDAtOS4xMiw0LjE2LTkuMTIsOS45NHYyNS40MmgtNi41MnYtMjUuMzRjMC03LjQxLTMuNjctMTAuMDItOC40Ny0xMC4wMi01LjMsMC05LjEyLDQuMzItOS4xMiw5Ljk0djI1LjQyaC02LjUydi00MC43NFoiLz4KICAgICAgPC9nPgogICAgPC9nPgogIDwvZz4KPC9zdmc+'/></div>");
		sparkReporter.config().thumbnailForBase64(true);
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Operating System", System.getProperty("os.name"));
		report.setSystemInfo("Environment", CommonUtilities.getEnvironment());
		report.setSystemInfo("Application URL", prop.getProperty("url"));
		return report;
	}

	public static String removeUnderScore(ITestResult result) {
		String underscore = result.getName().replaceAll("(\\d)_", "$1, ");
		String testName = underscore.replaceAll("_", " ");
		return testName;
	}
	/*********Hold htm code for email template Revamping*********/
	public static String generateHtmlContent(int total,int pass,int fail,int skip,String env,String date,String time,StringBuffer testcase) {
        // Generate HTML content with dynamic values
		String htmlContent = "<html>\r\n"
        		+ "<style> #table1{\r\n"
        		+ "            border-collapse: collapse;\r\n"
        		+ "            table-layout: fixed;\r\n"
        		+ "            width: 400px;}\r\n"
        		+ "       #table1 th, #table1 td {\r\n"
        		+ "            padding: 6px;\r\n"
        		+ "            text-align: center;\r\n"
        		+ "            border-bottom: 3px solid #ddd;\r\n"
        		+ "        }\r\n"
        		+ "       #table1 th {\r\n"
        		+ "            background-color: #def8fa;\r\n"
        		+ "        }\r\n"
        		+ "       #table1 tr:nth-child(even) {\r\n"
        		+ "            background-color: #f9f9f9;\r\n"
        		+ "        }\r\n"
        		+ "       #table1 tr:hover {\r\n"
        		+ "            background-color: #f5f5f5;\r\n"
        		+ "        }\r\n"
        		+ "       #table2{\r\n"
                + "       border-collapse: collapse;}\r\n"
        		+ "       #table2 th, #table2 td {\r\n"
        		+ "        border: 1px solid black;\r\n"
        		+ "        border-collapse: collapse;\r\n"
        		+ "        border-color: #00000057;\r\n"
        		+ "        padding: 3px 6px;\r\n"
        		+ "        font-size: 13px;\r\n"
        		+ "        } \r\n"
        		+ "</style>\r\n";
        htmlContent	+="<body>";
        htmlContent +="<p>Dear Team,</p>";
        htmlContent +="<p>Please find the Automation Testing Report for kuby executed in <b>" +env+ "</b> environment on Date <b>" +date+ "</b> Time <b>" +time+ "</b>. See the attached file. </p>";
        htmlContent += "<table id='table1' border='1'><tr><th> Total Tests </th><th> Passed </th><th> Failed </th><th> Skipped </th></tr>";
        htmlContent += "<tr><td>" + total + "</td><td>" + pass + "</td><td>" + fail + "</td><td>" + skip + "</td></tr></table>";
        htmlContent +="<p><b>Test Cases :</b></p>";
        htmlContent +="<table id='table2'>"+testcase+"</table>";


		htmlContent += "<p>Please check the  Detailed HTML Report & Logs attached.</p>";

		htmlContent += "<div class='footer'>"
				+ "<p>This is an automated email generated report by Selenium Automation Framework.</p>"
				+ "<p>For issues or questions, please contact the QA team or Sandeep Senior Test Engineer.</p>"
				+ "</div>";

		htmlContent +="<p> Thanks </p>";
		htmlContent +="<p> QA Team. </p>";

		htmlContent += "</body></html>";
        return htmlContent;
    }

}
