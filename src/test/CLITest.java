package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.Permission;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import junit.framework.TestCase;
import mdconverter.CLI;

public class CLITest {
	CLI cli;
	@Rule  
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void constructor() throws IOException{
		String[] args = {"test.md","-f","output0"};
		cli= new CLI(args);
	}
	@Test
	public void noInput() throws IOException {
		String[] args = {};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void goodArgs() throws IOException{
		String[] args = {"test.md","-p","output0"};
		CLI.cliCheck(args);	
	}
	@Test
	public void help () throws Exception{
		String[] args = {"help"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void noFile() throws IOException{
		String[] args = {"nofile.md","-s","output.html"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void multipleOption() throws IOException{
		String[] args = {"nofile.md","-d","-f"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void multipleOutput() throws IOException{
		String[] args = {"nofile.md","-p","output4","output5"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void noStyle() throws IOException{
		String[] args = {"nofile.md","output6"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void wrongOrder() throws IOException{
		String[] args = {"output6","nofile.md","-p"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	@Test
	public void illegalFile() throws IOException{
		String[] args = {"asdf.md","-p","#$!<"};
		exit.expectSystemExitWithStatus(1);
		CLI.cliCheck(args);
	}
	
}
