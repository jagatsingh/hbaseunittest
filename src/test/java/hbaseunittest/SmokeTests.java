package hbaseunittest;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SmokeTests
{

	@Before
	public void setUp() throws Exception
	{
		System.out.println("setting up...");
	}

	@After
	public void tearDown() throws Exception
	{
		System.out.println("tearing down");
	}

	@Test
	public void homePageTest() throws IOException
	{
		System.out.println("Do stuff");

		// Assert.fail("Couldn't load the homepage");

		// Let's create a table
		HBaseAdmin hbadmin = new HBaseAdmin(AllTests.getConfiguration());

		HTableDescriptor descriptor = new HTableDescriptor("newtable");
		descriptor.addFamily(new HColumnDescriptor("f1"));
		hbadmin.createTable(descriptor);

		hbadmin.close();

		HTable htable = new HTable(AllTests.getConfiguration(), "newtable");

		Put myPut = new Put();
		myPut.add(Bytes.toBytes("f1"),
				Bytes.toBytes("q1"),
				Bytes.toBytes("myvalue"));

		htable.put(myPut);

		htable.close();
	}
}