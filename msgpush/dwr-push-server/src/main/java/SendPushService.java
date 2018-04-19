import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

public class SendPushService {

    public void send(String msg) {

        System.out.println("调用了send方法...");

        ScriptBuffer scriptBuffer = new ScriptBuffer();

        WebContext context = WebContextFactory.get();

        ScriptSession scriptSession = context.getScriptSession();

        scriptBuffer.appendScript("dwrTest(");
        scriptBuffer.appendData(msg);
        scriptBuffer.appendScript(")");

        Util util = new Util(scriptSession);
        //向客户端发送消息
        util.addScript(scriptBuffer);

    }

}