package com.xsh.dwr;
import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

public class DwrPush {
	
	public void send(String msg) {

        System.out.println("调用了send方法...");
        WebContext context = WebContextFactory.get();
        
        Collection sessions = context.getAllScriptSessions();

        ScriptBuffer scriptBuffer = new ScriptBuffer();
        scriptBuffer.appendScript("callback(");
        scriptBuffer.appendData(msg);
        scriptBuffer.appendScript(")");

        Util util = new Util(sessions);
        //向客户端发送消息
        util.addScript(scriptBuffer);
    }
}
