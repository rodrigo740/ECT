package aula12.exercicio2.plugins;
import aula12.exercicio2.IPlugin;

public class Plugin2 implements IPlugin{

	@Override
	public void fazQualQuerCoisa() {
		System.out.println("Plugin 2 carregado!");
	}
}
