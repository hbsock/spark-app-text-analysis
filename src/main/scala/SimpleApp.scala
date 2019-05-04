/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD

object SimpleApp {


  def main(args: Array[String]) {
    val input_text_file = "/home/hanbinsock/programman/haskell/web-scraper/output/a-will-eternal/chapter-0001.txt" // Should be some file on your system

    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .getOrCreate()

    val input_text = spark.read.textFile(input_text_file).rdd

    val words = input_text
      .flatMap(_.split("\\s+"))
      .filter(_.matches("\\w+"))
      .cache()

    val word_counts = words
      .map(x => (x, 1L))
      .reduceByKey(_ + _)

    word_counts.collect().map(println)
    //words.take(100).map(println)
    

    /*
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"Lines with a: $numAs, Lines with b: $numBs")
    */

    //spark.stop()
  }

}
