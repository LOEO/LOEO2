import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-03-09 10:52:23
 * @version ：2018 Version：1.0

 */
public class TestScripting {
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager factory = new ScriptEngineManager();
		// create a JavaScript engine
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		// evaluate JavaScript code from String
		engine.eval("print('Hello, World')");
	}
}
