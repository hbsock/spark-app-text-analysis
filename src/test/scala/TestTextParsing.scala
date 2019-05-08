import org.apache.spark.sql.SparkSession
import org.scalatest._
import textanalysis.SimpleApp.parseWords

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

  test("test basic word parsing no punctuation") {
    val text = Seq("cat dog animal")
    val rdd = sparkSession.sparkContext.parallelize(text)
    val parsed_rdd = parseWords(rdd)
    
    val parsed_text = parsed_rdd.collect()

    assert(parsed_text.length === 3)
    assert(parsed_text(0) === "cat")
    assert(parsed_text(1) === "dog")
    assert(parsed_text(2) === "animal")
  }

}
