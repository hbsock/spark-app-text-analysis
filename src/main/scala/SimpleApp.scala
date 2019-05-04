/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession

object SimpleApp {

  def main(args: Array[String]) {
    val input_text = "/home/hanbinsock/programman/haskell/web-scraper/output/a-will-eternal/chapter-0001.txt" // Should be some file on your system

    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local")
      .getOrCreate()

    val logData = spark.read.textFile(input_text).rdd.cache()

    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }

}
