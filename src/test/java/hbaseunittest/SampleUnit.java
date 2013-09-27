package hbaseunittest;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleUnit
{
	private static final Logger log = Logger.getLogger(SampleUnit.class);

	@Before
	public void setUp() throws Exception
	{
		log.info("setting up...");
	}

	@After
	public void tearDown() throws Exception
	{
		log.info("tearing down");
	}

	@Test
	public void homePageTest() throws IOException
	{
		log.info("Performing the unit test");

		// Assert.fail("Couldn't load the homepage");

		// Let's create a table
		try (HBaseAdmin hbadmin = new HBaseAdmin(BigSuiteTest.getConfiguration()))
		{
			HTableDescriptor descriptor = new HTableDescriptor("newtable");
			descriptor.addFamily(new HColumnDescriptor("f1"));
			hbadmin.createTable(descriptor);
		}

		// insert a row
		try (HTable htable = new HTable(BigSuiteTest.getConfiguration(), "newtable"))
		{
			Put myPut = new Put(Bytes.toBytes("rowkey1"));
			myPut.add(Bytes.toBytes("f1"),
					Bytes.toBytes("q1"),
					Bytes.toBytes("myvalue"));

			htable.put(myPut);
		}

		// Read the row that we inserted
		try (HTable htable = new HTable(BigSuiteTest.getConfiguration(), "newtable"))
		{
			Result row = htable.get(new Get(Bytes.toBytes("rowkey1")));
			String value = Bytes.toString(row.getColumnLatest(Bytes.toBytes("f1"), Bytes.toBytes("q1")).getValue());

			if (value == null)
				Assert.fail("Failed due to null value");

			if (!value.equals("myvalue"))
				Assert.fail("Failed because value is not equal to 'myvalue'");
		}
	}
}