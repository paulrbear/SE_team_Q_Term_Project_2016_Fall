package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import mdconverter.MDConverter;

public class MDConverterTest {

	@Test
	public void testMain() throws IOException {
		String[] args = {"test.md","-p","testoutput1"};
		MDConverter.main(args);
	}

}
