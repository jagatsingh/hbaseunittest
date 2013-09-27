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
		SampleUnit.class
})
public class BigSuiteTest
{
	private static final Logger log = Logger.getLogger(BigSuiteTest.class);
	private static HBaseTestingUtility hbaseTestingUtility;

	private static Configuration myConfiguration;

	@BeforeClass
	public static void setUpClass() throws Exception
	{
		log.info("Starting HBASE cluster");
		hbaseTestingUtility = new HBaseTestingUtility();
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		Logger.getLogger("hbaseunittest").setLevel(Level.ALL);
		hbaseTestingUtility.startMiniCluster();

		BigSuiteTest.myConfiguration = hbaseTestingUtility.getConfiguration();
	}

	@AfterClass
	public static void tearDownClass() throws Exception
	{
		log.info("Tearing down");
		hbaseTestingUtility.shutdownMiniCluster();

		log.info("Done");
	}

	public static Configuration getConfiguration()
	{
		return BigSuiteTest.myConfiguration;
	}
}