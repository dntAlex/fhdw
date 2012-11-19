package model;

import i_o.IO_Element;

import java.util.Iterator;
import java.util.Vector;

import buffer.Buffer;

public class ConfigurationManager {

	private static ConfigurationManager instance = null;
	private final Buffer output;
	private final Vector<Configuration> configurations;
	
	private ConfigurationManager() {
		this.output = new Buffer();
		this.configurations = new Vector<Configuration>();
	}
	
	public static ConfigurationManager getInstance() {
		if(instance == null) instance = new ConfigurationManager();
		return instance;
	}
	
	public void register(Configuration conf) {
		this.getConfigurations().add(conf);
	}
	
	public void deregister(Configuration conf) {
		this.getConfigurations().remove(conf);
	}

	/**
	 * @return the output
	 */
	public Buffer getOutput() {
		return this.output;
	}

	/**
	 * @return the activeAutomatons
	 */
	public Vector<Configuration> getConfigurations() {
		return this.configurations;
	}

	public void finished(IO_Element result) {
		Iterator<Configuration> i = this.getConfigurations().iterator();
		while (i.hasNext()) {
			Configuration current = i.next();
			current.setAlive(false);
		}
		System.out.println(result.resolve());
	}
	
}
