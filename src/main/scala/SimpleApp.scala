/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SaveMode

object SimpleApp {


  def main(args: Array[String]) {
    val input_path = "/home/hanbinsock/programman/haskell/web-scraper/output/a-will-eternal/chapter-0001.txt" 
    val output_path = "/home/hanbinsock/output/will-eternal-chapter-0001-word-count"

    val input_text_file = input_path // Should be some file on your system

    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .getOrCreate()

    val input_text = spark.read.textFile(input_text_file).rdd

    val words = input_text
      .flatMap(_.split("\\s+"))
      .filter(_.matches("\\w+"))
      .map(_.toLowerCase)
      .cache()

    val word_counts = words
      .map(x => (x, 1L))
      .reduceByKey(_ + _)
      .sortBy(_._2, false)
      .cache()

    val word_counts_df = word_counts.toDF
    word_counts_df
      .write
      .mode(SaveMode.Overwrite)
      .format("csv")
      .save(output_path)

    //  .map({ case (w, c) => w + "," + c }) // convert output to csv
    //  .saveAsTextFile(output_path)
    

    spark.stop()
  }

}
