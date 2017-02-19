package haven.automation;

import org.python.util.PythonInterpreter;

import haven.GameUI;
import haven.HavenPanel;

import org.python.core.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class JythonAutomation implements Runnable {
    private String botname = "";
    private Thread jt = null;

    public Thread getJt() {
        return jt;
    }

    public static class JythonAutomationHolder {
        public static final JythonAutomation HOLDER_INSTANCE = new JythonAutomation();
    }

    public static JythonAutomation getInstance() {
        return JythonAutomationHolder.HOLDER_INSTANCE;
    }

    public JythonAutomation() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        HavenPanel.lui.cons.out.println("--Running Jython Automation: " + this.botname + "--");
        PythonInterpreter interp = new PythonInterpreter();
        try {
            FileInputStream fis = new FileInputStream(new File("jbot/" + this.botname));
            interp.execfile(fis);
        } catch (Exception e) {
            HavenPanel.lui.cons.out.println("Something went wrong!");
            e.printStackTrace(HavenPanel.lui.cons.out);
        }
        HavenPanel.lui.cons.out.println("--/Running Jython Automation--");
    }

    public void start(String[] args) {
        this.botname = args[1];
        this.jt = new Thread(JythonAutomation.getInstance(), "JBot thread");
        this.jt.start();
    }

}
