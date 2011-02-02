package learningci.chapter06

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.3 : after training 2 documents, 'quick' in 'good' " should "return 1.0" in {
    val classifier = new Classifier
    classifier.train(Document("the quick brown fox jumps over the lazy dog"), Tag("good"))
    classifier.train(Document("make quick money in the online casino"), Tag("bad"))
    val result = classifier.getWordCountPerTag(Word("quick"), Tag("good"))
    result should equal(1.0D)
  }

  "Chapter 6.3 : after training 2 documents, 'quick' in 'bad' " should "return 1.0" in {
    val classifier = new Classifier
    classifier.train(Document("the quick brown fox jumps over the lazy dog"), Tag("good"))
    classifier.train(Document("make quick money in the online casino"), Tag("bad"))
    val result = classifier.getWordCountPerTag(Word("quick"), Tag("bad"))
    result should equal(1.0D)
  }

  "Chapter 6.4 : get basic probability for 'quick' in 'good' " should "return 0.666..." in {
    val classifier = new Classifier
    Documents.getSampleTrainingData foreach {
      case (document, tag) => {
        classifier.train(document, tag)
      }
    }
    val result = classifier.getProbability(Word("quick"), Tag("good"))
    result should equal(0.6666666666666666D)
  }

  "Chapter 6.4.1 : get weighted probability for 'money' in 'good' " should "return 0.25, 0.166..." in {
    val classifier = new Classifier
    Documents.getSampleTrainingData foreach {
      case (document, tag) => {
        classifier.train(document, tag)
      }
    }
    val first = classifier.getWeightedProbability(Word("money"), Tag("good"))
    first should equal(0.25D)
    Documents.getSampleTrainingData foreach {
      case (document, tag) => {
        classifier.train(document, tag)
      }
    }
    val second = classifier.getWeightedProbability(Word("money"), Tag("good"))
    second should equal(0.16666666666666666D)
  }

}