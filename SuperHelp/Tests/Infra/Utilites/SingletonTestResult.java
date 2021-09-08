package Infra.Utilites;

public class SingletonTestResult {
    private static SingletonTestResult _firstInstance = new SingletonTestResult();
    private static boolean testResult = false;

    private SingletonTestResult() { }

    public static SingletonTestResult getInstance() {  return _firstInstance; }

    public void setTestResult(boolean result) { testResult = result; }

    public boolean getTestResult() { return testResult; }
}
