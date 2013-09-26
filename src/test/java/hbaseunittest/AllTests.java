package hbaseunittest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
{
		SmokeTests.class
})
public class AllTests
{
	private static final Logger log = Logger.getLogger(AllTests.class);
	private static HBaseTestingUtility hbaseTestingUtility;

	private static Configuration myConfiguration;

	@BeforeClass
	public static void setUpClass() throws Exception
	{
		hbaseTestingUtility = new HBaseTestingUtility();
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		Logger.getLogger("hbaseunittest").setLevel(Level.ALL);
		hbaseTestingUtility.startMiniCluster();

		AllTests.myConfiguration = hbaseTestingUtility.getConfiguration();
	}

	@AfterClass
	public static void tearDownClass() throws Exception
	{
		hbaseTestingUtility.shutdownMiniCluster();

		System.out.println("Done");
	}

	public static Configuration getConfiguration()
	{
		return AllTests.myConfiguration;
	}
}