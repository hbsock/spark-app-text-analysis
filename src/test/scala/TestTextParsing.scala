import org.apache.spark.sql.SparkSession
import org.scalatest._


class TestTextParsing extends FunSuite with BeforeAndAfterEach {

  var sparkSession : SparkSession = _
  override def beforeEach() {
    sparkSession = SparkSession
      .builder()
      .appName("Testing_text_parsing")
      .master("local[*]")
      .getOrCreate()
  }

  override def afterEach() {
    sparkSession.stop()
  }

  test("test initializing spark context") {
	val list = List(1, 2, 3, 4)
	val rdd = sparkSession.sparkContext.parallelize(list)

	assert(rdd.count === list.length)
  }

}
