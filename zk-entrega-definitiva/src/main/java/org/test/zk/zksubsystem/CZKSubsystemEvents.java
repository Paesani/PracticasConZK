package org.test.zk.zksubsystem;

import java.util.List;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.DesktopCleanup;
import org.zkoss.zk.ui.util.DesktopInit;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;
import org.zkoss.zk.ui.util.SessionCleanup;
import org.zkoss.zk.ui.util.SessionInit;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

public class CZKSubsystemEvents implements DesktopInit, DesktopCleanup, SessionInit, SessionCleanup, WebAppInit, WebAppCleanup, ExecutionInit, ExecutionCleanup {

    @Override
    public void cleanup(Execution exec, Execution parent, List<Throwable> errs) throws Exception {
        System.out.println("Executions Cleanup");
        
    }

    @Override
    public void init(Execution exec, Execution parent) throws Exception {
        System.out.println("Executions Init");
        
    }

    @Override
    public void cleanup(WebApp wapp) throws Exception {
        System.out.println("Web App Cleanup");
        
    }

    @Override
    public void init(WebApp wapp) throws Exception {
        System.out.println("Web App Init");
        
    }

    @Override
    public void cleanup(Session sess) throws Exception {
        System.out.println("Session Cleanup");
        
    }

    @Override
    public void init(Session sess, Object request) throws Exception {
        System.out.println("Session Init");
        
    }

    @Override
    public void cleanup(Desktop desktop) throws Exception {
        System.out.println("Desktop Cleanup");
        
    }

    @Override
    public void init(Desktop desktop, Object request) throws Exception {
        System.out.println("Desktop Init");
        
    }

}
