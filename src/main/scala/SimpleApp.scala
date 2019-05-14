/* SimpleApp.scala */
package textanalysis

import org.apache.log4j.{Level, LogManager, PropertyConfigurator}
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SaveMode

import textanalysis.Constants.getStopWords

object SimpleApp {


  def stripChars(s: String, ch: String) = s filterNot (ch contains _)

  def parseWords(text: RDD[String]): RDD[String] = {
    text
      .flatMap(
        stripChars(_, """“.,!?’':"""")
        .split("\\s+")
      )
      .filter(_.matches("""\w+"""))
      .map(_.toLowerCase)
      .filter(!getStopWords().contains(_))
  }


  def main(args: Array[String]) {

    val log = LogManager.getRootLogger
    args.map(log.info)

    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .master("local[*]")
      .getOrCreate()

    import spark.sqlContext.implicits._

    val input_path = args(0)
    val output_path = args(1)

    val input_text = spark.read.textFile(input_path).rdd

    val words = parseWords(input_text)

    val word_counts = words
      .map(x => (x, 1L))
      .reduceByKey(_ + _)

    val word_counts_df = word_counts.toDF()
    word_counts_df
      .write
      .mode(SaveMode.Overwrite)
      .format("csv")
      .save(output_path)

    spark.stop()
  }

}
